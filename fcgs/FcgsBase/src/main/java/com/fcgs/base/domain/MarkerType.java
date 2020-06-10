package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 标记类型数据接口
 * @author jiangbin
 * @date 2017年8月31日下午2:42:07
 */
public interface MarkerType extends Serializable {

	/**
	 * 设置标记类型
	 * @author jiangbin
	 * @param markerType
	 * @date 2017年8月31日下午2:44:31
	 */
	public void setMarkerType(Integer markerType);

	/**
	 * 获取标记类型
	 * @author jiangbin
	 * @return
	 * @date 2017年8月31日下午2:44:33
	 */
	public Integer getMarkerType();

}
