package com.pids.core.comparer;

import com.pids.core.exception.ICoreException;

/**
 * 比较器接口
 * @author jiangbin
 * @date 2012-10-16上午9:00:49
 */
public interface Comparer<S, T, R> {
	/**
	 * 比较给定两个对象
	 * @author jiangbin
	 * @param source 源数据
	 * @param target 目标数据
	 * @return 返回比较结果
	 * @throws ComparerException
	 * @date 2012-10-16上午9:00:59
	 */
	public R compare(S source, T target) throws ICoreException;
}
