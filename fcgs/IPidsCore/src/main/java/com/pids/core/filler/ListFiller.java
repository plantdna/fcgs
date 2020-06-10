package com.pids.core.filler;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表填充器
 * @author Andory
 * @date 2012-8-7下午8:22:36
 */
public interface ListFiller<S, T> extends Filler<S, T> {
	/**
	 * 支持列表数据填充操作
	 * @author Andory
	 * @param sources 数据列表
	 * @return 填充完毕的数据列表
	 * @throws ICoreException
	 * @date 2012-8-7下午8:22:45
	 */
	public List<T> fill(List<S> sources) throws ICoreException;

	/**
	 * 全模式的数据填充器
	 * @author Jiangbin
	 * @param sources 数据列表，若为null或空则直接返回null
	 * @param fullMode true/false--全模式/通用模式
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-16上午11:11:59
	 */
	public List<T> fill(List<S> sources, boolean fullMode) throws ICoreException;
}
