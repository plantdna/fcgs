package com.pids.core.register;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 注册给定对象列表
 * @Author andory
 * @Date 2012-8-15下午2:14:00
 */
public interface ListRegister<S> extends Register<S> {
	/**
	 * 注册对象列表
	 * @Author andory
	 * @param sources 源数据列表
	 * @return true/false--注册成功/失败
	 * @throws ICoreException
	 * @Date 2012-8-15下午2:14:13
	 */
	public boolean register(List<S> sources) throws ICoreException;

	/**
	 * 注册对象列表
	 * @Author andory
	 * @param sources 源数据列表
	 * @param fullMode true/false 是/否使用全模式
	 * @return true/false--注册成功/失败
	 * @throws ICoreException
	 * @Date 2012-8-15下午2:14:13
	 */
	public boolean register(List<S> sources, boolean fullMode) throws ICoreException;
}
