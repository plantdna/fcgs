package com.fcgs.core.comparer.utils;

import com.fcgs.core.comparer.domain.ComparerResult;
import com.fcgs.core.comparer.mapper.ComparerResultMapper;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;

import java.util.List;

/**
 * 重复比对结果过滤器
 * 
 * @author jiangbin
 * @date 2012-10-29上午11:23:24
 */
public class RepeatComparerResultFilter<T extends ComparerResult> implements Filter<List<T>, List<T>> {

	@Override
	public List<T> filter(List<T> sources) throws ICoreException {
		if (sources == null || sources.isEmpty()) {
			return null;
		}
		ComparerResultMapper<T> mapper = new ComparerResultMapper<T>();
		mapper.addAll(sources);
		return mapper.getValues();
	}
}
