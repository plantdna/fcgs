package com.pids.core.spliter;

import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 以指定的步进值将给定列表分割
 * @author jiangbin
 * @date 2012-11-13下午10:40:18
 */
public class SimpleListSpliter<S> implements ListSpliter<S, Integer> {

	@Override
	public List<List<S>> split(List<S> sources, Integer step) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		List<List<S>> targets = new ArrayList<>();
		if (step == null || step >= sources.size() || step < 1) {
			targets.add(sources);
			return targets;
		}
		int fromIndex = 0;
		while (true) {
			int endIndex = fromIndex + step;
			if (endIndex >= sources.size()) {
				endIndex = sources.size();
			}
			List<S> tmps = sources.subList(fromIndex, endIndex);
			targets.add(tmps);
			fromIndex = endIndex;
			if (fromIndex >= sources.size()) {
				break;
			}
		}
		return targets;
	}
}
