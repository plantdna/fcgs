package com.pids.core.converter;

import com.pids.core.exception.ICoreException;

/**
 * 转换器接口
 * @author jiangbin
 * @date 2012-1-5上午12:15:04
 */
public interface Converter<S, T> {
	public T convert(S source) throws ICoreException;
}
