package com.pids.core.mapper;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.utils.ObjectMapperFilter;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ObjectMapper抽象实现类
 *
 * @Author andory
 * @Date 2012-8-9下午1:35:15
 */
public abstract class AbstractObjectMapper<K, V, S> implements ObjectMapper<K, V, S> {
    private static final long serialVersionUID = -5590274105666407761L;
    protected Map<K, V> mapper;

    public AbstractObjectMapper() {
        this.mapper = new ConcurrentHashMap<K, V>();
    }

    @Override
    public boolean isEmpty() {
        return this.mapper.isEmpty();
    }

    @Override
    public void add(K key, V t) {
        if (key == null) {
            return;
        }
        this.mapper.put(key, t);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        return this.mapper.get(key);
    }

    @Override
    public V delete(K key) {
        if (key == null) {
            return null;
        }
        return this.mapper.remove(key);
    }

    @Override
    public List<V> delete(List<K> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        List<V> removed = new LinkedList<V>();
        for (K key : keys) {
            V target = this.delete(key);
            if (target != null) {
                removed.add(target);
            }
        }
        return removed;
    }

    @Override
    public void clear() {
        this.mapper.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.mapper.keySet();
    }

    @Override
    public int size() {
        return this.mapper.size();
    }

    @Override
    public Map<K, V> getMapper() {
        return this.mapper;
    }

    @Override
    public void setMapper(Map<K, V> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<K> getKeys() {
        List<K> keys = new ArrayList<K>();
        if (this.keySet() != null) {
            keys.addAll(this.keySet());
        }
        return keys;
    }

    @Override
    public List<V> getValues() {
        List<V> values = new ArrayList<V>();
        if (!this.isEmpty()) {
            values.addAll(this.mapper.values());
        }
        return values;
    }

    @Override
    public List<V> getValues(List<K> keys) {
        try {
            return this.getValues(keys, false);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<V> getFullValues(List<K> keys) {
        try {
            return this.getValues(keys, true);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取指定key列表对应的值列表
     *
     * @param keys
     * @param fullMode
     * @return
     * @author Andory
     * @date 2013-7-24上午10:55:52
     */
    protected List<V> getValues(List<K> keys, boolean fullMode) {
        try {
            if (CollectionUtils.isEmpty(keys)) {
                return new ArrayList<V>();
            }
            _MapperFilter filter = new _MapperFilter();
            filter.setMapper(this.mapper);
            List<V> list = filter.filter(keys);
            if (CollectionUtils.isEmpty(list)) {
                return new ArrayList<V>();
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 设置成全迭代模式
     *
     * @author jiangbin
     * @date 2014年3月13日下午7:33:40
     */
    private class _MapperFilter extends ObjectMapperFilter<K, V> {
        @Override
        public List<V> filter(List<K> keys, Map<K, V> rules) throws ICoreException {
            this.setMapper(rules);
            return iterator(keys, true);
        }
    }

    @Override
    public boolean exist(K key) {
        return this.get(key) != null;
    }

    @Override
    public List<V> removeRepeat(List<S> sources) {
        this.clear();
        this.addAll(sources);
        return this.getValues();
    }

    @Override
    public boolean hasSameKey(List<S> sources) {
        this.clear();
        this.addAll(sources);
        return this.getKeys() != null || this.getKeys().size() == 1;
    }

}
