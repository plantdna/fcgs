package com.pids.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Bean对象初始化代码生成器
 * @author jiangbin
 * @date 2017年11月21日下午5:49:27
 */
public class BeanCoder {
	/**
	 * 创建setter代码
	 * @author jiangbin
	 * @param obj
	 * @param targetName
	 * @return
	 * @date 2017年11月21日下午5:50:55
	 */
	public static String createSetter(Class<?> clazz, String targetName) {
		if (clazz == null) {
			return "";
		}
		if (StringUtils.isBlank(targetName)) {
			targetName = "target";
		}
		StringBuilder sb = new StringBuilder();
		Method[] methods = clazz.getMethods();
		sb.append(clazz.getSimpleName()).append(" ").append(targetName).append(" = ").append("new ")
				.append(clazz.getSimpleName()).append("();\r\n");
		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				Class<?>[] types = method.getParameterTypes();
				if (types == null || types.length != 1) {
					continue;
				}
				Class<?> type = types[0];
				sb.append(targetName).append(".").append(method.getName()).append("(");
				if (type.isAssignableFrom(String.class)) {
					sb.append("\"").append(method.getName()).append("\"");
				} else if (type.isAssignableFrom(Integer.class)) {
					sb.append(100);
				} else if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(Float.class)) {
					sb.append(100.0);
				} else if (type.isAssignableFrom(Long.class)) {
					sb.append(100L);
				} else if (type.isAssignableFrom(Boolean.class)) {
					sb.append(true);
				} else if (type.isAssignableFrom(List.class)) {
					sb.append("new ArrayList<String>()");
				} else if (type.isAssignableFrom(Map.class)) {
					sb.append("new HashMap<String,String>()");
				} else if (type.isAssignableFrom(Set.class)) {
					sb.append("new HashSet<String,String>()");
				} else {
					sb.append("null");
				}
				sb.append(");\r\n");
			}
		}

		return sb.toString();
	}

	/**
	 * 创建setter代码，默认变量名为"target"
	 * @author jiangbin
	 * @param clazz
	 * @return
	 * @date 2017年11月21日下午5:55:02
	 */
	public static String createSetter(Class<?> clazz) {
		return createSetter(clazz, null);
	}

	/**
	 * 创建getter代码
	 * @author jiangbin
	 * @param obj
	 * @param targetName
	 * @return
	 * @date 2017年11月21日下午5:50:55
	 */
	public static String createGetter(Class<?> clazz, String targetName) {
		if (clazz == null) {
			return "";
		}
		if (StringUtils.isBlank(targetName)) {
			targetName = "source";
		}
		StringBuilder sb = new StringBuilder();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				Class<?> type = method.getReturnType();
				if (type == null) {
					continue;
				}
				if (method.getName().equalsIgnoreCase("getClass")) {
					continue;
				}
				if (type.isAssignableFrom(String.class)) {
					sb.append("String ");
				} else if (type.isAssignableFrom(Integer.class)) {
					sb.append("Integer ");
				} else if (type.isAssignableFrom(Double.class)) {
					sb.append("Double ");
				} else if (type.isAssignableFrom(Float.class)) {
					sb.append("Float ");
				} else if (type.isAssignableFrom(Short.class)) {
					sb.append("Short ");
				} else if (type.isAssignableFrom(Long.class)) {
					sb.append("Long ");
				} else if (type.isAssignableFrom(Boolean.class)) {
					sb.append("Boolean ");
				} else if (type.isAssignableFrom(List.class)) {
					sb.append("List<String> ");
				} else if (type.isAssignableFrom(Map.class)) {
					sb.append("Map<String,String> ");
				} else if (type.isAssignableFrom(Set.class)) {
					sb.append("Set<String,String> ");
				} else {
					sb.append("Object ");
				}

				String name = method.getName().replaceFirst("get", "");
				sb.append(ICStringUtils.firstToLower(name));
				sb.append(" = ");
				sb.append(targetName).append(".").append(method.getName()).append("();\r\n");
			}
		}

		return sb.toString();
	}

	/**
	 * 创建Getter代码，默认变量名为"target"
	 * @author jiangbin
	 * @param clazz
	 * @return
	 * @date 2017年11月21日下午5:55:02
	 */
	public static String createGetter(Class<?> clazz) {
		return createGetter(clazz, null);
	}

	/**
	 * 创建put方法，用于生成对象属性的别名表
	 * @author jiangbin
	 * @param clazz
	 * @param targetName
	 * @return
	 * @date 2019年2月28日上午2:04:49
	 */
	public static String createPutter(Class<?> clazz, String targetName) {
		if (clazz == null) {
			return "";
		}
		if (StringUtils.isBlank(targetName)) {
			targetName = "target";
		}
		StringBuilder sb = new StringBuilder();
		Field[] fields = clazz.getDeclaredFields();
		sb.append("Map<String, String> alias = new HashMap<>();\r\n");
		for (Field field : fields) {
			String name = field.getName();
			sb.append("alias.put(\"").append(targetName).append(".").append(name).append("\",\"").append(targetName)
					.append(".").append(name).append("\");\r\n");
		}

		return sb.toString();
	}
}
