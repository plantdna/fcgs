package com.pids.core.parser;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 支持列表数据的解析
 * @Author andory
 * @Date 2012-8-9下午1:38:17
 */
public interface ListParser<S, T> extends Parser<S, T> {
	/**
	 * 列表数据解析,注意：解析后的列表与给定的源数据列表可能会长度不一致，
	 * 因为解析单个数据时可能会解析失败
	 * @Author andory
	 * @param sources 源数据
	 * @return 解析后的数据列表
	 * @throws ICoreException
	 * @Date 2012-8-9下午1:38:28
	 */
	public List<T> parser(List<S> sources) throws ICoreException;

	/**
	 * 解析数据列表
	 * @author Jiangbin
	 * @param sources 需要被解析的数据列表
	 * @param fullMode 全模式
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-16上午11:05:39
	 */
	public List<T> parser(List<S> sources, boolean fullMode) throws ICoreException;
}
