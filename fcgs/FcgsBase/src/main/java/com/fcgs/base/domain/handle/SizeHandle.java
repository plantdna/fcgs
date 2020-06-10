package com.fcgs.base.domain.handle;

/**
 * 列表大小信息句柄接口
 * @author jiangbin
 * @date 2014年3月21日上午11:42:22
 */
public interface SizeHandle {
	/**
	 * 是否为空列表
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:40:15
	 */
	public boolean isEmpty();

	/**
	 * 获取列表元素总数
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:40:16
	 */
	public int size();

}
