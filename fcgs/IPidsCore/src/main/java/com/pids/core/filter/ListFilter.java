package com.pids.core.filter;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 集合类型数据过滤器顶级接口
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public interface ListFilter<S, T> extends Filter<S, T> {
	/**
	 * 过滤器顶级接口,该接口使用非全模式进行处理
	 * @author jiangbin
	 * @param sources
	 * @return
	 * @date 2012-1-4上午2:45:48
	 */
	public List<T> filter(List<S> sources) throws ICoreException;

	/**
	 * 全模式过滤器顶级接口
	 * @author Jiangbin
	 * @param sources
	 * @param fullMode true/false--是/否使用全模式
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-16上午11:16:30
	 */
	public List<T> filter(List<S> sources, boolean fullMode) throws ICoreException;
}
