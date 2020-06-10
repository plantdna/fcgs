package com.pids.core.initial;

import com.pids.core.exception.ICoreException;

/**
 * 用于实现初始化操作
 * @author jiangbin
 * @date 2012-5-10下午8:48:46
 */
public interface Initial {
	/**
	 * 无参的初始化方法
	 * @author Jiangbin
	 * @return
	 * @throws InitialException
	 * @date 2013-6-15上午1:58:52
	 */
	public boolean initial() throws ICoreException;
}
