package com.pids.core.pair.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import com.pids.core.pair.Pair;

/**
 * 过滤出源数据列表
 * @author jiangbin
 * @date 2018年7月16日上午9:08:39
 */
public class PairTargetListFilter<S, T> extends ListFilterTemplate<Pair<S, T>, T> {

	@Override
	protected T filter(Pair<S, T> source, boolean isInternal) throws ICoreException {
		return source.getTarget();
	}

}
