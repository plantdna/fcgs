package com.pids.core.provider;

import com.pids.core.exception.ICoreException;

/**
 * 带参的类对象提供者
 * @author jiangbin
 * @date 2012-4-24下午10:23:13
 */
public interface VProvider<S, T> {
	/**
	 * 本方法每次调用均会生成一个新的实例对象
	 * @author jiangbin
	 * @return
	 * @throws ICoreException
	 * @date 2012-4-25上午8:47:47
	 */
	public T provide(S source) throws ICoreException;

}