package com.pids.core.filter;

import com.pids.core.exception.ICoreException;

/**
 * 对象过滤器顶级接口
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public interface ObjFilter<S, P, T> {
	/**
	 * 过滤器顶级接口
	 * @author jiangbin
	 * @param source 被过滤对象
	 * @param params 过滤条件
	 * @return 给定对象整个被过滤掉时将返回null值
	 * @date 2012-1-4上午2:45:48
	 */
	public T filter(S source, P params) throws ICoreException;
}
