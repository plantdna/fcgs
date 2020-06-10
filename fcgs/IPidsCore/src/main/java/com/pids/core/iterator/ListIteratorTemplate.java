package com.pids.core.iterator;

import com.pids.core.callback.Callback;
import com.pids.core.exception.ICoreException;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据列表迭代处理模板实现类
 * 
 * @Author andory
 * @Date 2012-8-9下午1:48:30
 */
public abstract class ListIteratorTemplate<S, T> implements ListIterator<S, T>, Callback<S, T> {

	@Override
	public List<T> iterator(List<S> sources) throws ICoreException {
		return this.iterator(sources, false);
	}

	@Override
	public List<T> iterator(List<S> sources, boolean fullMode) throws ICoreException {
		if (sources == null || sources.isEmpty()) {
			return null;
		}
		List<T> targets = new ArrayList<T>();
		for (S source : sources) {
			if (source == null) {
				if (fullMode) {
					targets.add(null);
				}
				continue;
			}
			T target = this.call(source);
			if (target == null && fullMode) {
				targets.add(target);
				continue;
			}
			if (target != null) {
				targets.add(target);
			}
		}
		return targets;
	}

}
