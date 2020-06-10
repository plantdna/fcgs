package com.pids.core.register;

import com.pids.core.exception.ICoreException;

/**
 * 注册功能接口
 * @Author andory
 * @Date 2012-8-15下午2:08:25
 */
public interface Register<S> {
	/**
	 * 注册指定对象
	 * @Author andory
	 * @param source 源数据对象
	 * @return true/false--注册成功/失败
	 * @throws ICoreException
	 * @Date 2012-8-15下午2:12:03
	 */
	public boolean register(S source) throws ICoreException;

}
