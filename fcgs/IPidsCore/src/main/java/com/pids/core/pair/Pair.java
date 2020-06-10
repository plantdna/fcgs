package com.pids.core.pair;

import java.io.Serializable;

/**
 * 数据对信息对象
 * @author jiangbin
 * @date 2012-11-25下午10:09:57
 */
public interface Pair<S, T> extends Serializable {

	/**
	 * 获取源数据
	 * @author jiangbin
	 * @return
	 * @date 2012-11-25下午10:31:09
	 */
	public S getSource();

	/**
	 * 设置源数据
	 * @author jiangbin
	 * @param source
	 * @date 2012-11-25下午10:31:10
	 */
	public void setSource(S source);

	/**
	 * 获取目标数据
	 * @author jiangbin
	 * @return
	 * @date 2012-11-25下午10:31:12
	 */
	public T getTarget();

	/**
	 * 设置目标数据
	 * @author jiangbin
	 * @param target
	 * @date 2012-11-25下午10:31:13
	 */
	public void setTarget(T target);

	/**
	 * 交换源数据和目标数据
	 * 
	 * @author Ritchieyan
	 * @date 2014年8月6日下午4:14:48
	 */
	public void wrap();

}
