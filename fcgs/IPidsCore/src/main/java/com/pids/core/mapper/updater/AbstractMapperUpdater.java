package com.pids.core.mapper.updater;

import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>根据给定映射表更新目标数据的字段信息
 * 其泛型的含义：
 * S-->原数据对象类型
 * V-->原数据对象添加到Mapper后的值对象类型
 * T-->目标数据对象类型</pre>
 * @author jiangbin
 * @date 2018年5月30日下午6:00:06
 */
public abstract class AbstractMapperUpdater<S, T, V> extends MapperTemplate<V, S> {

	private static final long serialVersionUID = 130354526053939701L;

	/**
	 * 依据源数据对象列表信息的更新给定的目标数据列表
	 * @author jiang
	 * @param sources 源数据对象列表，会先清空当前Mapper再添加后执行更新目标数据操作
	 * @param targets 目标数据列表
	 * @return 返回被更新过的目标对象列表
	 * @date 2018年5月30日下午6:10:03
	 */
	public List<T> update(List<S> sources, List<T> targets) {
		if (CollectionUtils.isEmpty(sources) || CollectionUtils.isEmpty(targets)) {
			return null;
		}

		//添加源数据列表
		this.clear();
		this.addAll(sources);

		//更新数据对象
		return this.update(targets);
	}

	/**
	 * 依据当前Mapper中的数据更新给定的目标数据列表
	 * @author jiang
	 * @param targets
	 * @return 返回被更新过的目标对象列表
	 * @date 2018年5月30日下午6:03:06
	 */
	public List<T> update(List<T> targets) {
		if (CollectionUtils.isEmpty(targets)) {
			return null;
		}
		List<T> updateds = new ArrayList<>();
		for (T target : targets) {
			if (target == null) {
				continue;
			}
			String key = getTargetKey(target);
			if (StringUtils.isBlank(key)) {
				continue;
			}
			V source = this.get(key);
			if (source == null) {
				continue;
			}
			target = update(source, target);
			if (target != null) {
				updateds.add(target);
			}
		}
		return updateds;
	}

	/**
	 * 获取目标对象中包含的Key
	 * @author jiang
	 * @param target
	 * @return
	 * @date 2018年5月30日下午6:29:46
	 */
	protected abstract String getTargetKey(T target);

	/**
	 * 依据源数据对象更新目标数据对象相关字段信息
	 * @author jiang
	 * @param source 源数据对象
	 * @param target 目标数据对象
	 * @return 返回被更新的目标对象
	 * @date 2018年5月30日下午6:03:03
	 */
	public abstract T update(V source, T target);
}
