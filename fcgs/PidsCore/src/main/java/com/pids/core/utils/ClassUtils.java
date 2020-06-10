package com.pids.core.utils;

import java.util.Date;
import java.util.List;

/**
 * 类类型判定工具类
 * @author jiangbin
 * @date 2013年12月25日下午3:39:45
 */
public class ClassUtils {
	/**
	 * @fields STRING_TYPE 字符串类型
	 */
	public static final int STRING_TYPE = 0;
	/**
	 * @fields LIST_TYPE 列表类型
	 */
	public static final int LIST_TYPE = 1;
	/**
	 * @fields ARRAY_TYPE 数组类型
	 */
	public static final int ARRAY_TYPE = 2;
	/**
	 * @fields DATE_TYPE 日期类型
	 */
	public static final int DATE_TYPE = 3;

	/**
	 * 获取类类型
	 * @author jiangbin
	 * @param obj
	 * @return
	 * @date 2013年12月25日下午3:40:02
	 */
	public static int getObjectType(Object obj) {
		if (obj == null) {
			return STRING_TYPE;
		}
		if (List.class.isAssignableFrom(obj.getClass())) {
			return LIST_TYPE;
		} else if (Object[].class.isAssignableFrom(obj.getClass())) {
			return ARRAY_TYPE;
		} else if (Date.class.isAssignableFrom(obj.getClass())) {
			return DATE_TYPE;
		}
		return STRING_TYPE;
	}

}
