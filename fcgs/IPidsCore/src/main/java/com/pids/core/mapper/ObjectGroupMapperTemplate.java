package com.pids.core.mapper;

import com.pids.core.converter.Converter;
import com.pids.core.exception.ICoreException;
import com.pids.core.grouper.Grouper;
import com.pids.core.spliter.impl.SpliterUtils;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持分组功能的Mapper模板类
 *
 * @author jiangbin
 * @date 2013-3-31上午11:19:33
 */
public abstract class ObjectGroupMapperTemplate<K, V, S> extends ObjectMapperTemplate<K, List<V>, S>
        implements Converter<S, V>, Grouper<List<S>, List<List<V>>> {

    private static final long serialVersionUID = -8636199403784157116L;
    private static Logger log = LoggerFactory.getLogger(ObjectGroupMapperTemplate.class);

    @Override
    protected List<V> getMapperValue(S object) {
        try {
            V target = convert(object);
            if (target == null) {
                return null;
            }
            K key = getMapperKey(object);
            if (!isAcceptKey(key)) {
                return null;
            }
            List<V> targets = this.get(key);
            if (targets == null) {
                targets = new ArrayList<V>();
            }
            return addGroup(targets, target);
        } catch (Exception e) {
            log.error("给定源数据对象({})分组转换成到值列表失败", ToStringBuilder.reflectionToString(this));
            return null;
        }
    }

    /**
     * 添加数据项到目标分组列表中
     *
     * @param targets 目标分组列表
     * @param target  数据项
     * @author jiang
     * @date 2018年6月26日下午6:38:11
     */
    protected List<V> addGroup(List<V> targets, V target) {
        targets.add(target);
        return targets;
    }

    /**
     * 分组给定源类型S列表数据，本方法只是对removeRepeat()方法的一个包装，
     * 相关业务逻辑请参看removeRepeat()方法
     */
    @Override
    public List<List<V>> group(List<S> sources) throws ICoreException {
        return this.removeRepeat(sources);
    }

    /**
     * 获取所有值的集合
     *
     * @return
     * @author Jiangbin
     * @date 2013-6-12下午6:37:37
     */
    public List<V> getValuesAsList() {
        return this.getValuesAsList(getKeys());
    }

    /**
     * 获取指定键名对应的值的集合
     *
     * @param keys
     * @return
     * @author Jiangbin
     * @date 2013-6-12下午6:37:36
     */
    public List<V> getValuesAsList(List<K> keys) {
        List<V> targets = new ArrayList<V>();
        if (CollectionUtils.isEmpty(keys)) {
            return targets;
        }
        for (K key : keys) {
            List<V> tmps = this.get(key);
            if (CollectionUtils.isEmpty(tmps)) {
                continue;
            }
            targets.addAll(tmps);
        }
        return targets;
    }

    /**
     * 本方法在此处用于将给定源对象转换成目标对象，
     * 如果源类型S与值类型V相同则直接返回给定的源对象即可
     */
    @Override
    public abstract V convert(S source) throws ICoreException;

    /**
     * 获取给定Key分组包含元素个数
     *
     * @param key
     * @return
     * @author jiangbin
     * @date 2014年10月15日上午9:42:04
     */
    public int getValueCount(K key) {
        return ListUtils.size(get(key));
    }

    /**
     * 获取指定长度的分组列表
     *
     * @param counts 分组长度列表
     * @return
     * @author jiang
     * @date 2018年6月1日上午3:33:11
     */
    public List<List<V>> getValueBySize(int... counts) {
        if (ArrayUtils.isEmpty(counts)) {
            return null;
        }
        List<List<V>> values = new ArrayList<>();
        for (K key : keySet()) {
            List<V> list = get(key);
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            int count = ListUtils.size(list);
            if (ArrayUtils.contains(counts, count)) {
                values.add(list);
            }
        }
        return values;
    }

    /**
     * 获取指定长度的分组列表
     *
     * @param counts 分组长度列表
     * @return
     * @author jiang
     * @date 2018年6月1日上午3:34:38
     */
    public List<V> getValueBySizeAsList(int... counts) {
        if (ArrayUtils.isEmpty(counts)) {
            return null;
        }
        List<List<V>> values = this.getValueBySize(counts);
        return ListUtils.mergerList(values);
    }

    /**
     * 获取指定长度分组的Key列表
     *
     * @param counts 分组长度列表
     * @return
     * @author jiang
     * @date 2018年6月1日上午3:33:11
     */
    public List<K> getKeyBySize(int... counts) {
        if (ArrayUtils.isEmpty(counts)) {
            return null;
        }
        List<K> keys = new ArrayList<>();
        for (K key : keySet()) {
            List<V> list = get(key);
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            int count = ListUtils.size(list);
            if (ArrayUtils.contains(counts, count)) {
                keys.add(key);
            }
        }
        return keys;
    }

    /**
     * 删除指定长度分组并返回这些分组的元素集合
     *
     * @param counts 分组长度列表
     * @return 被删除分组中元素集合列表
     * @author jiangbin
     * @date 2019-08-10 14:21
     **/
    public List<V> deleteBySize(int... counts) {
        if (ArrayUtils.isEmpty(counts)) {
            return null;
        }
        List<K> keys = this.getKeyBySize(counts);
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        List<V> values = this.getValuesAsList(keys);
        this.delete(keys);
        return values;
    }

    /**
     * 按照给定步进值分片所有Key后，再将这些分片的Key对应值合并成一个分片列表返回
     *
     * @param step 步进值
     * @return java.util.List<java.util.List < V>>
     * @author jiangbin
     * @date 2019-08-18 15:16
     **/
    public List<List<V>> split(int step) {
        //获取Key列表
        List<K> keys = this.getKeys();
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }

        //分片Key列表
        List<List<K>> groups = SpliterUtils.split(keys, step);

        //生成分片的数据列表
        List<List<V>> targets = new ArrayList<>();
        for (List<K> group : groups) {
            List<V> values = this.getValuesAsList(group);
            if (CollectionUtils.isNotEmpty(values)) {
                targets.add(values);
            }
        }
        return targets;
    }
}
