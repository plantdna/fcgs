package com.pids.core.builder;

import com.pids.core.exception.ICoreException;

/**
 * 构建器接口，主要应用于构建模式情景
 * @author jiangbin
 * @date 2012-5-1下午6:42:41
 */
public interface Builder<S, T> {
	public T build(S source) throws ICoreException;
}
