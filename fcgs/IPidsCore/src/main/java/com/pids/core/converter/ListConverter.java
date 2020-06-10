package com.pids.core.converter;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表转换器
 * @author andory
 * @date 2012-8-9上午4:59:04
 */
public interface ListConverter<S, T> extends Converter<S, T> {
	/**
	 * 转换集合列表包含的对象
	 * @author jiangbin
	 * @param sources 集合对象
	 * @return 转换失败将返回null
	 * @date 2012-4-11下午12:09:31
	 */
	public List<T> convert(List<S> sources) throws ICoreException;

	/**
	 * 转换集合列表包含的对象
	 * @author jiangbin
	 * @param sources 集合对象
	 * @param fullMode 是/否使用全模式
	 * @return 转换失败将返回null
	 * @date 2012-4-11下午12:09:31
	 */
	public List<T> convert(List<S> sources, boolean fullMode) throws ICoreException;
}
