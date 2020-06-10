package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 指纹状态接口
 * @author jiangbin
 * @date 2013-1-18上午9:55:30
 */
public interface GeneStatus extends Serializable {
	/**
	 * 获取指纹状态
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午10:59:09
	 */
	public Integer getGeneStatus();

	/**
	 * 设置指纹状态
	 * @author jiangbin
	 * @param status
	 * @date 2014年3月21日上午10:59:09
	 */
	public void setGeneStatus(Integer geneStatus);
}
