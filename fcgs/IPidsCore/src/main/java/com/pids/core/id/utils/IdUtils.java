package com.pids.core.id.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.id.ID;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * ID接口想着操作工具方法
 * @author ANDORY
 * @date 2016年2月22日下午12:41:32
 */
public class IdUtils {
	/**
	 * 删除指定ID号列表对应的数据对象
	 * @author ANDORY
	 * @param sources 源数据列表
	 * @param idList ID号列表
	 * @return 删除后的数据列表
	 * @throws ICoreException
	 * @date 2016年2月22日下午12:41:29
	 */
	public static <S extends ID> List<S> remove(List<S> sources, List<String> idList) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return sources;
		}
		if (CollectionUtils.isEmpty(idList)) {
			return sources;
		}
		IdMapper<S> mapper = new IdMapper<>();
		mapper.addAll(sources);
		List<S> remove = mapper.getValues(idList);
		if (CollectionUtils.isEmpty(remove)) {
			return sources;
		}
		sources.removeAll(remove);
		return sources;
	}

	/**
	 * 删除指定ID号对应的数据对象
	 * @author ANDORY
	 * @param sources 源数据列表
	 * @param id ID号
	 * @return 删除后的数据列表
	 * @throws ICoreException
	 * @date 2016年2月22日下午12:41:29
	 */
	public static <S extends ID> List<S> remove(List<S> sources, String id) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return sources;
		}
		if (StringUtils.isBlank(id)) {
			return sources;
		}
		IdMapper<S> mapper = new IdMapper<>();
		mapper.addAll(sources);
		S remove = mapper.get(id);
		if (remove == null) {
			return sources;
		}
		sources.remove(remove);
		return sources;
	}

	/**
	 * 添加给定源数据列表到目标数据列表中，会先移除目标数据列表中与源数据列表中ID号重复记录再添加
	 * @author ANDORY
	 * @param sources 源数据列表
	 * @param targets 目标数据列表
	 * @return 添加后的数据列表
	 * @throws ICoreException
	 * @date 2016年2月22日下午12:41:29
	 */
	public static <S extends ID> List<S> add(List<S> sources, List<S> targets) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return targets;
		}
		if (CollectionUtils.isEmpty(targets)) {
			return sources;
		}
		List<String> idList = new IdFilter<S>().filter(sources);
		remove(targets, idList);
		targets.addAll(sources);
		return targets;
	}
}
