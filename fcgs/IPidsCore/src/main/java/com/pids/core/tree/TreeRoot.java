package com.pids.core.tree;

import java.io.Serializable;

/**
 * 树根结点，本接口用于构建树型级联结构
 * @author jiangbin
 * @date 2017年6月21日下午4:06:38
 */
public interface TreeRoot<T extends TreeRoot<T>> extends Serializable {
	/**
	 * 获取根结点名
	 * @author jiangbin
	 * @return
	 * @date 2014年7月18日上午12:23:37
	 */
	public String getName();

	/**
	* 获取父结点信息
	* @author jiangbin
	* @return
	* @date 2014年7月18日上午12:30:58
	*/
	public T getParent();
}
