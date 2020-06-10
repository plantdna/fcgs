package com.pids.core.converter;

import com.pids.core.exception.ICoreException;

import java.util.Map;

/**
 * 将给定列表集合按指定映射关系转换成Map的值列表集合
 * @author jiangbin
 * @date 2012-1-4下午11:16:36
 */
public interface MapConverter extends ListConverter<String, String>, ReversibleConverter<String, String> {
	public void setConverter(Map<String, String> converter);

	public Map<String, String> getConverter();

	/**
	 * 作为key值转换成value值
	 * @author jiangbin
	 * @param key 键值
	 * @return
	 * @date 2012-4-14下午9:31:37
	 */
	@Override
	public String convert(String key) throws ICoreException;

	/**
	 * 作为value值转换成key值
	 * @author jiangbin
	 * @param value 键值
	 * @return
	 * @date 2012-4-14下午9:31:37
	 */
	@Override
	public String reconvert(String value) throws ICoreException;

	/**
	 * 转换方向：true:key==>value/false:value==>key
	 * @author jiangbin
	 * @return
	 * @date 2012-4-14下午9:39:49
	 */
	public boolean isForwardConverter();

	/**
	 * 设置转换方向：true:key==>value/false:value==>key
	 * @author jiangbin
	 * @param isForward 转换方向：true:key==>value/false:value==>key，默认是正向转换
	 * @date 2012-4-14下午9:40:28
	 */
	public void setForwardConverter(boolean isForward);

	/**
	 * 判定Map是否存在元素
	 * @author jiangbin
	 * @return
	 * @date 2013-12-18下午12:39:36
	 */
	public boolean isEmpty();
}
