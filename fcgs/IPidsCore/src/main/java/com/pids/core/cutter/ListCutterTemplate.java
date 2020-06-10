package com.pids.core.cutter;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;

import java.util.List;

/**
 * 剪切操作模板实现类
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public abstract class ListCutterTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListCutter<S, T> {

	@Override
	public List<T> cut(List<S> sources) throws ICoreException {
		return this.iterator(sources);
	}

	@Override
	public List<T> cut(List<S> sources, boolean fullMode) throws ICoreException {
		return this.iterator(sources, fullMode);
	}

	@Override
	public T call(S source) throws ICoreException {
		return this.cut(source, true);
	}

	@Override
	public T cut(S source) throws ICoreException {
		return this.cut(source, false);
	}

	protected abstract T cut(S source, boolean isInternal) throws ICoreException;

}
