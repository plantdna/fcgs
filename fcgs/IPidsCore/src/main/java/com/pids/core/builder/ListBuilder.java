package com.pids.core.builder;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表数据构建器
 * @Author andory
 * @Date 2012-8-9下午2:27:42
 */
public interface ListBuilder<S, T> extends Builder<S, T> {
	/**
	 * 列表数据构建
	 * @Author andory
	 * @param sources
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-9下午2:27:43
	 */
	public List<T> build(List<S> sources) throws ICoreException;

	/**
	 * 列表数据构建
	 * @Author andory
	 * @param sources
	 * @param fullMode 是/否使用全模式
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-9下午2:27:43
	 */
	public List<T> build(List<S> sources, boolean fullMode) throws ICoreException;
}
