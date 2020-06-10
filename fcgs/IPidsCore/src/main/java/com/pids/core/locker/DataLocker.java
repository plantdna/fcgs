package com.pids.core.locker;

import com.pids.core.exception.ICoreException;

/**
 * 数据锁接口，该接口允许用户对某个数据进行加锁和解锁，而不是整个线程，
 * 加锁解锁方法中的锁拥有者指的是加锁或解锁的目标，这个锁拥有者可以是用户帐号、数字等任何数据
 * @author jiang
 * @date 2018年11月14日下午7:25:48
 */
public interface DataLocker {
	/**
	 * 数据加锁
	 * @author jiang
	 * @param owner 锁拥有者,若未给定则不加锁
	 * @throws ICoreException
	 * @date 2018年11月14日下午7:29:54
	 */
	public void lock(String owner) throws ICoreException;

	/**
	 * 数据解锁
	 * @author jiang
	 * @param owner 锁拥有者,若未给定则不解锁
	 * @throws ICoreException
	 * @date 2018年11月14日下午7:29:56
	 */
	public void unlock(String owner) throws ICoreException;
}
