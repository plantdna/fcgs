package com.pids.core.business;

import com.pids.core.exception.ICoreException;

/**
 * 复杂数据删除操作业务接口
 * @author jiangbin
 * @date 2013-1-18下午4:02:28
 */
public interface VDeleter<S, T> {
	public T delete(S sources) throws ICoreException;
}
