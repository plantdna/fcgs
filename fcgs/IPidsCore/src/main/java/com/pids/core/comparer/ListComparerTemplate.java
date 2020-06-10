package com.pids.core.comparer;

import com.pids.core.exception.ICoreException;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表比较器模板类实现，本模板类主要是实现对给定两个列表进行两两比对的业务控制
 * @author jiangbin
 * @date 2012-10-16上午9:09:23
 */
public abstract class ListComparerTemplate<S, T, R> implements ListComparer<S, T, R> {
	@Override
	public List<R> compare(List<S> sources, List<T> targets) throws ICoreException {
		if (sources == null || sources.isEmpty() || targets == null || targets.isEmpty()) {
			return null;
		}
		List<R> results = new ArrayList<R>();
		for (S source : sources) {
			for (T target : targets) {
				R result = this.compare(source, target);
				if (result != null) {
					results.add(result);
				}
			}
		}
		return results;
	}

}
