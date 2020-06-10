package com.pids.core.formatter;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 支持列表数据内容格式化功能
 * @Author andory
 * @Date 2012-8-9下午1:03:56
 */
public interface ListFormatter<S, T> extends Formatter<S, T> {
	/**
	 * 格式化列表
	 * @Author andory
	 * @param sources 数据列表，若给定列表为null或空则返回null值
	 * @return 格式化后的数据列表
	 * @throws ICoreException
	 * @Date 2012-8-9下午1:04:22
	 */
	public List<T> format(List<S> sources) throws ICoreException;

	/**
	 * 以全模式进行格式化
	 * @author Jiangbin
	 * @param sources 数据列表
	 * @param fullMode 是否为全模式
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-16上午11:07:30
	 */
	public List<T> format(List<S> sources, boolean fullMode) throws ICoreException;
}
