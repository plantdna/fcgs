package com.pids.core.filter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>指定类类型对象过滤器:
 * 1、Map类型过滤出符合类型的Value列表
 * 2、List类型过滤出符合类类型的列表
 * 3、Object类型过滤出符合类类型的数据</pre>
 * @author jiangbin
 * @date 2014年4月30日上午10:20:54
 */

public class ClassFilter<T> {
	/**
	 * 过滤出给定参数列表中继承自指定类类型的参数列表
	 * @author Andory
	 * @param sources 源对象数组
	 * @param clazz 被过滤出接口或对象实例的类型
	 * @return
	 * @date 2013年9月11日下午4:39:14
	 */
	public List<T> filter(Object[] sources, Class<T> clazz) {
		if (sources == null || ArrayUtils.isEmpty(sources)) {
			return null;
		}
		List<T> details = new ArrayList<T>();
		for (Object source : sources) {
			if (source == null) {
				continue;
			}
			List<T> objects = filter(source, clazz);
			if (CollectionUtils.isNotEmpty(objects)) {
				details.addAll(objects);
			}
		}
		return details;
	}

	/**
	 * 过滤源对象中给定类型接口或类实例的列表
	 * @author jiangbin
	 * @param source 源对象
	 * @param clazz 被过滤出接口或对象实例的类型
	 * @return 接口或对象实例的列表
	 * @throws ICoreException
	 * @date 2014年4月30日上午10:23:40
	 */
	@SuppressWarnings("unchecked")
	public List<T> filter(Object source, Class<T> clazz) throws ICoreException {
		if (this.isMap(source, clazz)) {
			return this.filterMap(source);
		}
		if (this.isList(source, clazz)) {
			return (List<T>) source;
		}
		if (this.isObject(source, clazz)) {
			return ListUtils.createList((T) source);
		}
		return null;
	}

	/**
	 * 过滤出Map值列表
	 * @author jiangbin
	 * @param param
	 * @return
	 * @date 2014年3月14日下午5:29:17
	 */
	@SuppressWarnings("unchecked")
	protected List<T> filterMap(Object param) {
		return new ArrayList<T>(((Map<?, T>) param).values());
	}

	/**
	 * 该对象是否为给定类类型的一个扩展或子类型
	 * @author Andory
	 * @param param
	 * @param clazz
	 * @return
	 * @date 2013年9月11日下午4:29:31
	 */
	protected boolean isObject(Object param, Class<T> clazz) {
		return param != null && clazz.isAssignableFrom(param.getClass());
	}

	/**
	 * 是否为List类型对象
	 * @author jiangbin
	 * @param param
	 * @return
	 * @date 2014年3月14日下午5:22:56
	 */
	protected boolean isListObject(Object param) {
		return param != null && List.class.isAssignableFrom(param.getClass());
	}

	/**
	 * 是否为Map类型对象
	 * @author jiangbin
	 * @param param
	 * @return
	 * @date 2014年3月14日下午5:23:31
	 */
	protected boolean isMapObject(Object param) {
		return param != null && Map.class.isAssignableFrom(param.getClass());
	}

	/**
	 * 是否为列表
	 * @author Andory
	 * @param param
	 * @param clazz
	 * @return
	 * @date 2013年9月11日下午4:20:10
	 */
	@SuppressWarnings("unchecked")
	protected boolean isList(Object param, Class<T> clazz) {
		if (!isListObject(param)) {
			return false;
		}
		List<T> details = (List<T>) param;
		if (details.isEmpty()) {
			return false;
		}
		//判定列表元素
		return isObject(details.get(0), clazz);
	}

	/**
	 * 判定是否为Map值类型为给定类类型
	 * @author jiangbin
	 * @param param
	 * @param clazz
	 * @return
	 * @date 2014年3月14日下午5:27:22
	 */
	protected boolean isMap(Object param, Class<T> clazz) {
		if (!isMapObject(param)) {
			return false;
		}
		List<?> values = new ArrayList<>(((Map<?, ?>) param).values());
		if (CollectionUtils.isEmpty(values)) {
			return false;
		}
		return isList(values, clazz);
	}

}
