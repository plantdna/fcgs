package com.pids.core.pools;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ID与索引间映射关系缓存池，本功能不支持多线程模式的同时读写，因为未加锁
 *
 * @author jiang
 * @date 2019-11-02 15:23
 */
public class SimpleIdPools implements IdPools {
    private final Map<String, Integer> idIndexMapper;
    private final Map<Integer, String> indexIdMapper;

    /**
     * 构造函数
     *
     * @param idList ID列表
     * @return
     * @author jiangbin
     * @date 2019/11/7 2:45 下午
     **/
    public SimpleIdPools(List<String> idList) {
        this.idIndexMapper = new ConcurrentHashMap<>();
        this.indexIdMapper = new ConcurrentHashMap<>();
        this.add(idList);
    }

    /**
     * 构造函数
     *
     * @param idArray
     * @return
     * @author jiangbin
     * @date 2019/12/31 2:16 下午
     **/
    public SimpleIdPools(String[] idArray) {
        this.idIndexMapper = new ConcurrentHashMap<>();
        this.indexIdMapper = new ConcurrentHashMap<>();
        this.add(idArray);
    }

    @Override
    public String getId(int index) {
        return this.indexIdMapper.get(index);
    }

    @Override
    public int getIndex(String id) {
        Integer index = this.idIndexMapper.get(id);
        return index == null ? INVALID : index;
    }

    @Override
    public void add(List<String> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        for (int index = 0; index < idList.size(); index++) {
            this.idIndexMapper.put(idList.get(index), index);
            this.indexIdMapper.put(index, idList.get(index));
        }
    }

    public void add(String[] idArray) {
        if (ArrayUtils.isEmpty(idArray)) {
            return;
        }
        for (int index = 0; index < idArray.length; index++) {
            this.idIndexMapper.put(idArray[index], index);
            this.indexIdMapper.put(index, idArray[index]);
        }
    }

    @Override
    public int size() {
        return this.idIndexMapper.size();
    }

    @Override
    public List<String> getIdList() {
        return new ArrayList<>(this.idIndexMapper.keySet());
    }
}
