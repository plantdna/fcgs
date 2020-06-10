package com.pids.core.allocator;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表数据分配器
 * @author jiangbin
 * @date 2012-11-9下午9:52:52
 */
public interface ListAllocator<S, T> extends Allocator<S, T> {
	/**
	 * 列表数据分配功能
	 * @author jiangbin
	 * @param sources
	 * @return
	 * @throws ICoreException
	 * @date 2012-11-9下午9:52:51
	 */
	public List<T> allocate(List<S> sources) throws ICoreException;

	/**
	 * 列表数据分配功能
	 * @author jiangbin
	 * @param sources
	 * @param fullMode 是/否使用全模式
	 * @return
	 * @throws ICoreException
	 * @date 2012-11-9下午9:52:51
	 */
	public List<T> allocate(List<S> sources, boolean fullMode) throws ICoreException;
}
