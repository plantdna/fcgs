package com.pids.core.pair.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import com.pids.core.pair.Pair;

/**
 * 过滤出源数据列表
 * @author jiangbin
 * @date 2018年7月16日上午9:08:39
 */
public class PairSourceListFilter<S, T> extends ListFilterTemplate<Pair<S, T>, S> {

	@Override
	protected S filter(Pair<S, T> source, boolean isInternal) throws ICoreException {
		return source.getSource();
	}

}
