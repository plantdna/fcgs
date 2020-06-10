package com.pids.core.counter.impl;

import com.pids.core.counter.Counter;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.utils.StringItemCountMapper;
import com.pids.core.pair.Pair;
import com.pids.core.pair.SimplePair;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 最佳字符串统计器，返回的Pair对象中:source=最佳统计字符串,target=最佳字符串重复数，
 * 该功能实际是用于统计出在给定列表中重复次数最多的字符串及其重复数
 * @author YuanChunYan
 * @date 2012-12-28上午9:58:00
 */
public class BestStringItemCounter implements Counter<List<String>, Pair<String, Integer>> {

	@Override
	public Pair<String, Integer> count(List<String> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		return countMaxItem(sources);
	}

	/**
	 * 根据带型类型计算带型个数
	 * @author YuanChunYan
	 * @param sources
	 * @param forms
	 * @return
	 * @throws ICoreException
	 * @date 2013-1-4上午10:35:29
	 */
	public Pair<String, Integer> count(List<String> sources, String forms) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		return countMaxItem(sources, forms);
	}

	/**
	 * 计算最大值
	 * @author YuanChunYan
	 * @param sources
	 * @return
	 * @date 2012-12-27下午4:46:28
	 */
	protected Pair<String, Integer> countMaxItem(List<String> sources) {
		StringItemCountMapper mapper = new StringItemCountMapper();
		mapper.addAll(sources);
		String bestItem = mapper.getMaxCountKey();
		return new SimplePair<String, Integer>(bestItem, mapper.get(bestItem));
	}

	/**
	 * 根据带型类型计算带型个数
	 * @author YuanChunYan
	 * @param sources
	 * @param forms
	 * @return
	 * @date 2013-1-4上午10:57:03
	 */
	protected Pair<String, Integer> countMaxItem(List<String> sources, String forms) {
		StringItemCountMapper mapper = new StringItemCountMapper();
		mapper.addAll(sources);
		return new SimplePair<String, Integer>(forms, mapper.get(forms));
	}

}
