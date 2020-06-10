package com.pids.core.comparer;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表比较器接口
 * @author jiangbin
 * @date 2012-10-16上午9:06:26
 */
public interface ListComparer<S, T, R> extends Comparer<S, T, R> {
	/**
	 * 比较源列表和目标列表
	 * @author jiangbin
	 * @param sources 源列表数据
	 * @param targets 目标列表数据
	 * @return 比较结果列表
	 * @throws ComparerException
	 * @date 2012-10-16上午9:06:28
	 */
	public List<R> compare(List<S> sources, List<T> targets) throws ICoreException;
}
