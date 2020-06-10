package com.pids.core.formatter;

import com.pids.core.exception.ICoreException;

/**
 * 格式化目标对象功能接口
 * @author Andory
 * @date 2012-5-26上午07:41:56
 */
public interface Formatter<S, T> {
	/**
	 * 将指定源数据格式化成目标格式数据
	 * @Author Andory
	 * @param source 源数据
	 * @return  目标数据
	 * @throws ICoreException
	 * @Date 2012-8-16上午12:24:05
	 */
	public T format(S source) throws ICoreException;
}
