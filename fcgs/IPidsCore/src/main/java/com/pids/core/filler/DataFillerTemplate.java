package com.pids.core.filler;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 对象字段值填充器模板实现类，本模板实现类只适合单个对象循环填充的模式
 * @author jiangbin
 * @date Jun 28, 201910:36:11 AM
 */
public abstract class DataFillerTemplate<S, V> implements DataFiller<S, V> {

	@Override
	public List<S> fill(List<S> sources, V value) throws ICoreException {
		return new _Filler(value).fill(sources);
	}

	@Override
	public S fill(S source, V value) throws ICoreException {
		return new _Filler(value).fill(source);
	}

	/**
	 * 内部类填充器
	 * @author jiangbin
	 * @date Jun 28, 201910:43:01 AM
	 */
	protected class _Filler extends ListFillerTemplate<S, S> {
		private final V value;

		public _Filler(V value) {
			this.value = value;
		}

		@Override
		protected S fill(S source, boolean isInternal) throws ICoreException {
			setValue(source, this.value);
			return source;
		}
	}

	//////////////////////////////////////////////////////////////////////

	/**
	 * 设置给字段定值到源对象中
	 * @author jiangbin
	 * @param source 源对象
	 * @param value 字段值
	 * @date Jun 28, 201910:43:47 AM
	 */
	protected abstract void setValue(S source, V value);

}
