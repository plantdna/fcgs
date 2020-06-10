package com.pids.core.checker;

import com.pids.core.exception.ICoreException;

/**
 * 检验器接口
 * @Author Andory
 * @Date 2012-9-24上午11:18:49
 */
public interface Checker<S, T> {
	/**
	 * 对给定源数据对象进行某种规则的检测，通常本接口的返回值是个布尔值对象
	 * @Author Andory
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @Date 2012-9-24上午11:18:47
	 */
	public T check(S source) throws ICoreException;

}
