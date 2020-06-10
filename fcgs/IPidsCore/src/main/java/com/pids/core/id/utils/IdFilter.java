package com.pids.core.id.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import com.pids.core.id.ID;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 过滤出ID号列表
 * @author jiangbin
 * @date 2015年1月7日上午11:27:05
 */
public class IdFilter<S extends ID> extends ListFilterTemplate<S, String> {
	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getId();
	}

	/**
	 * 过滤指定ID号列表对象的数据对象列表
	 * @author jiangbin
	 * @param sources 若无数据对象或为null则返回null
	 * @param idList 若未指定ID列表或为null则返回null
	 * @return
	 * @throws ICoreException
	 * @date 2016年9月19日上午11:16:49
	 */
	public List<S> filter(List<S> sources, List<String> idList) throws ICoreException {
		if (CollectionUtils.isEmpty(sources) || CollectionUtils.isEmpty(idList)) {
			return null;
		}
		IdGroupMapper<S> grouper = new IdGroupMapper<>();
		grouper.addAll(sources);
		return grouper.getValuesAsList(idList);
	}
}
