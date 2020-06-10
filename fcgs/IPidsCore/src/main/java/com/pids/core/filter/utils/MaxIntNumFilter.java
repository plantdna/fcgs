package com.pids.core.filter.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 最大int数字的过滤器，从一个列表中选择出最大数字，若未选择到则返回null
 * @author ANDORY
 * @date 2016年3月10日上午10:10:22
 */
public class MaxIntNumFilter implements Filter<List<Integer>, Integer> {
	
	@Override
	public Integer filter(List<Integer> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		Integer max = null;
		for (Integer source : sources) {
			if (source == null) {
				continue;
			}
			if (max == null || max.intValue() < source.intValue()) {
				max = source;
			}
		}
		return max;
	}
}
