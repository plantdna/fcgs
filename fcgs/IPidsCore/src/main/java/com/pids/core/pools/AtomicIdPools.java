package com.pids.core.pools;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ID与索引间映射关系缓存池，本功能支持多线程模式的同时读写，因为具备自动加锁功能，
 * 但性能没有{@link SimpleIdPools}好
 *
 * @author jiang
 * @date 2019-11-02 15:23
 */
public class AtomicIdPools implements IdPools {
    private final Map<String, Integer> idIndexMapper;
    private final Map<Integer, String> indexIdMapper;
    private Lock lock = new ReentrantLock(true);

    /**
     * 构造函数
     *
     * @param idList ID列表
     * @return
     * @author jiangbin
     * @date 2019/11/7 2:45 下午
     **/
    public AtomicIdPools(List<String> idList) {
        this.idIndexMapper = new ConcurrentHashMap<>();
        this.indexIdMapper = new ConcurrentHashMap<>();
        this.add(idList);
    }

    public AtomicIdPools(String[] idList) {
        this.idIndexMapper = new ConcurrentHashMap<>();
        this.indexIdMapper = new ConcurrentHashMap<>();
        this.add(idList);
    }

    @Override
    public String getId(int index) {
        lock.lock();
        try {
            return this.indexIdMapper.get(index);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getIndex(String id) {
        lock.lock();
        try {
            Integer index = this.idIndexMapper.get(id);
            return index == null ? INVALID : index;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void add(List<String> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        lock.lock();
        try {
            for (int index = 0; index < idList.size(); index++) {
                this.idIndexMapper.put(idList.get(index), index);
                this.indexIdMapper.put(index, idList.get(index));
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void add(String[] idArray) {
        if (ArrayUtils.isEmpty(idArray)) {
            return;
        }
        lock.lock();
        try {
            for (int index = 0; index < idArray.length; index++) {
                this.idIndexMapper.put(idArray[index], index);
                this.indexIdMapper.put(index, idArray[index]);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return this.idIndexMapper.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<String> getIdList() {
        lock.lock();
        try {
            return new ArrayList<>(this.idIndexMapper.keySet());
        } finally {
            lock.unlock();
        }
    }
}
