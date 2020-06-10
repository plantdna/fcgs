package com.pids.core.converter.impl;

import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 将类转换成其子类类型的转换器
 * @author jiangbin
 * @date 2017年6月27日下午1:07:33
 */
public class SubClassTypeConvertor<S, T> extends ListConverterTemplate<S, T> {
	private final Class<T> clazz;

	public SubClassTypeConvertor(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	protected T convert(S source, boolean isInternal) throws ICoreException {
		try {
			T target = createTarget();
			BeanUtils.copyProperties(target, source);
			return target;
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (InstantiationException e) {
		}
		return null;
	}

	/**
	 * 创建子类对象实例
	 * @author jiangbin
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @date 2017年6月27日下午1:48:00
	 */
	private T createTarget() throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}

}
