package com.pids.core.id;

import java.io.Serializable;

/**
 * ID号接口，通常用于做为数据唯一性标识，如表格主键等的标识接口
 * @author jiangbin
 * @date 2017年3月20日下午10:54:06
 */
public interface ID extends Serializable {
	/**
	 * 获取主键ID
	 * @author jiangbin
	 * @return
	 * @date 2017年3月20日下午10:54:35
	 */
	public String getId();

	/**
	 * 设置主键ID
	 * @author jiangbin
	 * @param id
	 * @date 2017年3月20日下午10:54:37
	 */
	public void setId(String id);
}
