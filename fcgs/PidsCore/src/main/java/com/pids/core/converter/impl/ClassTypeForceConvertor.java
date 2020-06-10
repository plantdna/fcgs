package com.pids.core.converter.impl;

import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;

/**
 * 类实例类型强制转换器
 * @author wuht
 * @date 2016年2月24日下午5:46:52
 */
public class ClassTypeForceConvertor<S, T> extends ListConverterTemplate<S, T> {

	@SuppressWarnings("unchecked")
	@Override
	protected T convert(S source, boolean isInternal) throws ICoreException {
		return (T) source;
	}

}
