package com.pids.core.utils;

import com.pids.core.converter.impl.ClassTypeConvertor;
import com.pids.core.converter.impl.ClassTypeForceConvertor;
import com.pids.core.converter.impl.SubClassTypeConvertor;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 对Bean进行操作的相关工具方法
 * @author jiangbin
 *
 */
public class BeanUtils {
	/**
	 * 将Bean对象转换成Map对象，将忽略掉值为null或size=0的属性
	 * @param obj 对象
	 * @return 若给定对象为null则返回size=0的map对象
	 */
	public static Map<String, Object> toMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		BeanMap beanMap = new BeanMap(obj);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Object value = beanMap.get(name);
			// 转换时会将类名也转换成属性，此处去掉
			if (value != null && !name.equals("class")) {
				map.put(name, value);
			}
		}
		return map;
	}

	/**
	 * 该方法将给定的所有对象参数列表转换合并生成一个Map，对于同名属性，依次由后面替换前面的对象属性
	 * @param objs 对象列表
	 * @return 对于值为null的对象将忽略掉
	 */
	public static Map<String, Object> toMap(Object... objs) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object object : objs) {
			if (object != null) {
				map.putAll(toMap(object));
			}
		}
		return map;
	}

	/**
	 * <pre>将Bean对象转换成Map对象，将忽略掉值为null或size=0的属性
	 * 注：在数据类型为java.util.List时会以字符串方式进行表示，即以逗号分隔来构建列表的字符串表示，
	 * 若这种转换尝试失败了则忽略该列表属性值</pre>
	 * @param obj 对象
	 * @return 若给定对象为null则返回size=0的map对象
	 */
	@SuppressWarnings({ "unchecked" })
	public static Map<String, String> toStrMap(Object obj) {
		Map<String, String> map = new HashMap<String, String>();
		if (obj == null) {
			return map;
		}
		BeanMap beanMap = new BeanMap(obj);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Object value = beanMap.get(name);
			// 转换时会将类名也转换成属性，此处去掉
			if (value == null || name.equals("class")) {
				continue;
			}
			try {// 尝试以字符串方式转换成字符串
				if (value.getClass().isAssignableFrom(List.class)) {
					value = ListUtils.list2Str((List<String>) value);
				}
			} catch (Exception e) {
			}
			map.put(name, StringEx.sNull(value));
		}
		return map;
	}

	/**
	 * <pre>该方法将给定的所有对象参数列表转换合并生成一个Map，对于同名属性，依次由后面替换前面的对象属性
	 * 注：在数据类型为java.util.List时会以字符串方式进行表示，即以逗号分隔来构建列表的字符串表示，
	 * 若这种转换尝试失败了则忽略该列表属性值</pre>
	 * @param objs 对象列表
	 * @return 对于值为null的对象将忽略掉
	 */
	public static Map<String, String> toStrMap(Object... objs) {
		Map<String, String> map = new HashMap<String, String>();
		for (Object object : objs) {
			if (object != null) {
				map.putAll(toStrMap(object));
			}
		}
		return map;
	}

	/**
	 * 过滤列表的属性，如果列表为空或null则返回null
	 * @author LiuJunGuang
	 * @param list
	 * @param propertyName 属性名称
	 * @return
	 * @date 2013年8月16日下午6:35:43
	 */
	public static <T> List<String> filterProperty(List<T> list, String propertyName) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<String> propertyList = new ArrayList<String>();
		try {
			for (T obj : list) {
				if (obj == null) {
					continue;
				}
				String val = org.apache.commons.beanutils.BeanUtils.getProperty(obj, propertyName);
				if (StringUtils.isNotEmpty(val)) {
					propertyList.add(val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyList;
	}

	/**
	 * 过滤列表不重复的属性值，如果列表为空或null则返回null
	 * @author LiuJunGuang
	 * @param list
	 * @param propertyName 属性名称
	 * @return
	 * @date 2013年8月16日下午6:36:35
	 */
	public static <T> List<String> filterNoRepeatProperty(List<T> list, String propertyName) {
		List<String> propertyList = BeanUtils.filterProperty(list, propertyName);
		if (CollectionUtils.isEmpty(propertyList)) {
			return null;
		}
		return new ArrayList<String>(new LinkedHashSet<String>(propertyList));
	}

	/**
	 * <pre>获取Bean对象字段名的JSON格式字符串表示
	 * 本功能用于前台编辑JSON脚本时使用
	 * 比如给定Person对象含有地址、姓名、编号属性，
	 * 其生成的属性字段JSON字符串为:
	 * 	'address','name','number'
	 * 在脚本中可能会用到，比如Extjs的表格脚本编写
	 * </pre>
	 * @author jiangbin
	 * @return
	 * @date 2013-11-22下午7:42:19
	 */
	public static String getBeanFieldNameJsonStr(Object bean) {
		List<String> keys = getBeanFields(bean);
		String target = ListUtils.list2Str(keys);
		if (StringEx.isNull(target)) {
			return "";
		}
		target = target.replaceAll(",", "','");
		target = String.format("'%s'", target);
		return target;
	}

	/**
	 * 获取给定Bean对象的属性名列表
	 * @author jiangbin
	 * @param bean
	 * @return 若不存在属性名列表或给定Bean为null则返回空字符串列表List对象
	 * @date 2013-11-22下午9:24:36
	 */
	public static List<String> getBeanFields(Object bean) {
		List<String> targets = new ArrayList<String>();
		if (bean == null) {
			return targets;
		}
		BeanMap beanMap = new BeanMap(bean);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			targets.add(it.next());
		}
		targets.remove("class");// 去掉类名字段
		return targets;
	}

	/**
	 * 获取Bean对象各属性类型映射关系，key/value--属性名/属性类型
	 * @author jiangbin
	 * @param bean bean对象
	 * @return
	 * @date 2013年12月25日下午4:21:55
	 */
	public static Map<String, Class<?>> getBeanPropertyTypes(Object bean) {
		Map<String, Class<?>> types = new HashMap<String, Class<?>>();
		if (bean == null) {
			return types;
		}
		BeanMap beanMap = new BeanMap(bean);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Class<?> class1 = beanMap.getType(name);
			types.put(name, class1);
		}
		return types;
	}

	/**
	 * 实例对象列表向父类型列表转换
	 * @author ANDORY
	 * @param sources
	 * @return
	 * @date 2016年2月6日下午9:49:19
	 */
	public static <S extends T, T> List<T> convert2ParentType(List<S> sources) {
		return new ClassTypeConvertor<S, T>().convert(sources);
	}

	/**
	 * 列表数据强制类型转换
	 * @author wuht
	 * @param sources
	 * @return
	 * @date 2016年2月24日下午5:50:16
	 */
	public static <S, T> List<T> forceConvertType(List<S> sources) {
		return new ClassTypeForceConvertor<S, T>().convert(sources);
	}

	/**
	 * <pre>列表数据类型转换，通过
	 * {@link org.apache.commons.beanutils.BeanUtils#copyProperties(Object, Object)}
	 * 完成对象属性拷贝</pre>
	 * @author jiangbin
	 * @param sources
	 * @param clazz
	 * @return
	 * @date 2017年6月27日下午5:59:53
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <S, T> List<T> convertType(List<S> sources, Class<T> clazz) {
		return new SubClassTypeConvertor(clazz).convert(sources);
	}

}