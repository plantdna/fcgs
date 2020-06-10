package com.pids.core.caculator;

import com.pids.core.exception.ICoreException;

/**
 * 用于计算方面的操作接口
 * @author Andory
 * @date 2012-8-4下午1:20:01
 */
public interface Caculator<S, T> {
	/**
	 * 对源数据进行一些计算操作并生成目标数据返回
	 * @author Andory
	 * @param source 源数据
	 * @return 目标数据
	 * @throws ICoreException
	 * @date 2012-8-4下午1:20:23
	 */
	public T caculate(S source) throws ICoreException;
}
