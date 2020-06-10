package com.pids.core.creator;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 支持列表数据的构建
 * @Author andory
 * @Date 2012-8-9下午1:26:19
 */
public interface ListCreator<S, T> extends Creator<S, T> {
	/**
	 * 列表数据构建器
	 * @Author andory
	 * @param sources
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-9下午1:26:18
	 */
	public List<T> create(List<S> sources) throws ICoreException;

	/**
	 * 列表数据构建器
	 * @Author andory
	 * @param sources
	 * @param fullMode 是/否使用全模式
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-9下午1:26:18
	 */
	public List<T> create(List<S> sources, boolean fullMode) throws ICoreException;
}
