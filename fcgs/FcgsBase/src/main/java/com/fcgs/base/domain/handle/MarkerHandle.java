package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.gene.Marker;

import java.io.Serializable;
import java.util.List;

/**
 * 位点信息句柄
 * @author jiangbin
 * @date 2014年3月21日上午11:02:15
 */
public interface MarkerHandle extends Serializable {
	/**
	 * 获取位点信息列表
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:02:17
	 */
	public List<Marker> getMarkers();

	/**
	 * 设置位点信息列表
	 * @author jiangbin
	 * @param markers
	 * @date 2014年3月21日上午11:02:18
	 */
	public void setMarkers(List<Marker> markers);
}
