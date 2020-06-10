package com.pids.core.allocator;

import com.pids.core.exception.ICoreException;

/**
 * 分配器接口
 * @author jiangbin
 * @date 2012-11-9下午9:48:38
 */
public interface Allocator<S, T> {
	/**
	 * 分配功能接口
	 * @author jiangbin
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @date 2012-11-9下午9:50:05
	 */
	public T allocate(S source) throws ICoreException;
}
