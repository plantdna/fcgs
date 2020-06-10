package com.pids.core.finder;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;

import java.util.List;

/**
 * 查找功能的模板实现类
 * @author Andory
 * @date 2012-8-7下午8:53:07
 */
public abstract class ListFinderTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListFinder<S, T> {
	@Override
	public T find(List<S> sources, int index) throws ICoreException {
		List<T> targets = this.findAll(sources);
		if (targets == null || targets.isEmpty()) {
			return null;
		}
		if (index < 0 || index >= targets.size()) {
			return null;
		}
		return targets.get(index);
	}

	@Override
	public List<T> findAll(List<S> sources) throws ICoreException {
		return iterator(sources);
	}

	@Override
	public T findFirst(List<S> sources) throws ICoreException {
		return this.find(sources, 0);
	}

	@Override
	public T findLast(List<S> sources) throws ICoreException {
		List<T> targets = this.findAll(sources);
		if (targets == null || targets.isEmpty()) {
			return null;
		}
		return targets.get(targets.size() - 1);
	}

	@Override
	public T call(S source) throws ICoreException {
		return this.find(source, true);
	}

	@Override
	public T find(S source) throws ICoreException {
		return this.find(source, false);
	}

	protected abstract T find(S source, boolean isInternal) throws ICoreException;
}
