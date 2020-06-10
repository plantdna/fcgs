package com.pids.core.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>对象拷贝工具,本功能通过序列化方式来深度拷贝一个对象，故要求目标对象扩展序列化接口:
 * {@link java.io.Serializable}
 * </pre>
 * @author jiangbin
 * @date 2014年2月13日下午4:03:43
 */
public class ObjectCopier {
	/**
	 * 通过序列化方式生成对象的拷贝,若源数据对象为null则直接返回null
	 * @author jiangbin
	 * @param source
	 * @return 
	 * @date 2014年2月13日下午4:08:48
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copy(Serializable source) {
		if (source == null) {
			return null;
		}
		byte[] bytes = SerializeUtil.serialize(source);
		return (T) SerializeUtil.deserialize(bytes);
	}

	/**
	 * 拷贝对象列表,若源数据对象为null则直接返回null
	 * @author jiangbin
	 * @param sources 源数据对象列表，列表中不支持序列化的对象将被忽略之
	 * @return
	 * @date 2014年2月13日下午4:48:46
	 */
	public static <S extends Serializable, T> List<T> copy(List<S> sources) {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		List<T> targets = new ArrayList<T>();
		for (Serializable source : sources) {
			T target = copy(source);
			if (target != null) {
				targets.add(target);
			}
		}
		return targets;
	}

	/**
	 * 通过序列化方式深度拷贝源对象数据到目标对象，本方法要求必需指定源和目标数据对象
	 * @author jiangbin
	 * @param source 源数据对象，必需是可序列化的对象
	 * @param target 目标对象，不要求可序列化
	 * @return true/false--拷贝 成功/失败
	 * @date 2014年2月13日下午4:36:30
	 */
	public static boolean copy(Serializable source, Object target) {
		if (source == null || target == null) {
			return false;
		}
		Object copier = copy(source);
		if (copier == null) {
			return false;
		}
		try {
			BeanUtils.copyProperties(target, copier);
			return true;
		} catch (Exception e) {
			Logger.getLogger(ObjectCopier.class).warn(e);
			return false;
		}
	}

}
