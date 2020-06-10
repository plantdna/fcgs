package com.pids.core.loader;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 支持列表数据加载
 * @Author andory
 * @Date 2012-8-9下午9:39:45
 */
public interface ListLoader<S, T> extends Loader<S, T> {
	/**
	 * 列表数据加载
	 * @Author andory
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-9下午9:40:02
	 */
	public List<T> load(List<S> sources) throws ICoreException;

	/**
	 * 列表数据加载
	 * @Author andory
	 * @param sources 源数据列表
	 * @param fullMode 是/否使用全模式
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-9下午9:40:02
	 */
	public List<T> load(List<S> sources, boolean fullMode) throws ICoreException;
}
