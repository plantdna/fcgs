package com.pids.core.converter.impl;

import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;

/**
 * 将类转换成其父类类型的转换器
 * @author jiangbin
 * @date 2017年6月27日下午1:07:33
 */
public class ClassTypeConvertor<S extends T, T> extends ListConverterTemplate<S, T> {

	@Override
	protected T convert(S source, boolean isInternal) throws ICoreException {
		return source;
	}

}
