package com.pids.core.locker;

import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据锁实现类
 * @author jiang
 * @date 2018年11月14日下午7:31:08
 */
public class SimpleDataLocker implements DataLocker {
	private Map<String, Lock> locker = new ConcurrentHashMap<>();

	@Override
	public void lock(String owner) throws ICoreException {
		if (StringUtils.isBlank(owner)) {
			return;
		}
		Lock lock = locker.get(owner);
		if (lock == null) {
			lock = new ReentrantLock(true);
			locker.put(owner, lock);
		}
		lock.lock();
	}

	@Override
	public void unlock(String owner) throws ICoreException {
		if (StringUtils.isBlank(owner)) {
			return;
		}
		Lock lock = locker.get(owner);
		if (lock != null) {
			lock.unlock();
			this.locker.remove(owner);
		}
	}

}
