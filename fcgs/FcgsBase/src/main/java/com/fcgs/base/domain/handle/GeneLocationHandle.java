package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.GeneLocation;

import java.io.Serializable;

/**
 * 指纹位置句柄信息
 * @author jiangbin
 * @date 2014年3月21日下午2:06:47
 */
public interface GeneLocationHandle extends Serializable {
	/**
	 * 获取指纹位置信息
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日下午2:06:47
	 */
	public GeneLocation getLocation();

	/**
	 * 设置指纹位置信息
	 * @author jiangbin
	 * @param location
	 * @date 2014年3月21日下午2:06:45
	 */
	public void setLocation(GeneLocation location);
}
