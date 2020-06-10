package com.fcgs.base.domain.gene;

import java.util.List;

/**
 * 主带位点信息接口，用于单株指纹数据合并功能
 * @author jiangbin
 * @date 2014年11月19日下午9:28:51
 */
public interface MMarker extends Marker, SMarker {
	/**
	 * 设置非主带位点列表
	 * @author jiangbin
	 * @return
	 * @date 2014年11月19日下午9:27:45
	 */
	public List<SMarker> getMarkers();
	
	/**
	 * 获取非主带位点列表
	 * @author jiangbin
	 * @param markers
	 * @date 2014年11月19日下午9:27:47
	 */
	public void setMarkers(List<SMarker> markers);
	
	/**
	 * 添加单株位点
	 * @author jiangbin
	 * @param marker
	 * @date 2014年11月20日上午1:39:42
	 */
	public void addMarker(SMarker marker);
	
}
