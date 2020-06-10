package com.pids.core.cutter;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 集合类型数据裁剪器顶级接口
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public interface ListCutter<S, T> extends Cutter<S, T> {
	/**
	 * 列表裁剪器顶级接口
	 * @author jiangbin
	 * @param sources
	 * @return
	 * @date 2012-1-4上午2:45:48
	 */
	public List<T> cut(List<S> sources) throws ICoreException;

	/**
	 * 全模式列表裁剪器顶级接口
	 * @author Jiangbin
	 * @param sources
	 * @param fullMode
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-16上午11:14:48
	 */
	public List<T> cut(List<S> sources, boolean fullMode) throws ICoreException;

}
