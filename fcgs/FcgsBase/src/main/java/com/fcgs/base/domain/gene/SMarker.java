package com.fcgs.base.domain.gene;

/**
 * 单株位点信息接口，用于单株指纹数据合并功能
 * @author jiangbin
 * @date 2014年11月19日下午9:31:41
 */
public interface SMarker extends Marker {
	/**
	 * 获取单株位点的统计占比
	 * @author jiangbin
	 * @return
	 * @date 2014年11月19日下午9:23:49
	 */
	public double getPersent();
	
	/**
	 * 设置单株位点的统计占比
	 * @author jiangbin
	 * @param persent
	 * @date 2014年11月19日下午9:23:51
	 */
	public void setPersent(double persent);
}
