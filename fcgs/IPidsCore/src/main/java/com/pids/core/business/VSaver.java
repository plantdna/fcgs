package com.pids.core.business;

import com.pids.core.exception.ICoreException;

/**
 * 复杂数据保存操作业务接口
 * @author jiangbin
 * @date 2013-1-18下午4:03:05
 */
public interface VSaver<S,T> {
	public T save(S sources)throws ICoreException;
}
