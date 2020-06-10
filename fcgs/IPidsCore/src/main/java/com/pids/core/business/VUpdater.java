package com.pids.core.business;

import com.pids.core.exception.ICoreException;

/**
 * 复杂数据更新操作业务接口
 * @author jiangbin
 * @date 2013-1-18下午4:03:05
 */
public interface VUpdater<S,T> {
	public T update(S sources)throws ICoreException;
}
