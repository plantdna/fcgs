package com.pids.core.spliter;

import com.pids.core.exception.ICoreException;

/**
 * 将给定源对象按某种规则进行分割
 * @author jiangbin
 * @date 2012-11-13下午9:34:52
 */
public interface Spliter<S, T, STEP> {
	/**
	 * 按指定步进值分割
	 * @author jiangbin
	 * @param sources
	 * @param step
	 * @return
	 * @throws ICoreException
	 * @date 2014年7月12日下午7:31:56
	 */
	public T split(S sources, STEP step) throws ICoreException;
}
