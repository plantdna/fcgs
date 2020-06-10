package com.fcgs.base.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 指纹审核方式接口
 * @author jiangbin
 * @date 2013-1-18上午9:55:30
 */
public interface GeneAuditWay extends Serializable {
	/**
	 * 获取指纹审核方式
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午10:59:09
	 */
	public Integer getGeneAuditWay();

	/**
	 * 设置指纹审核方式
	 * @author jiangbin
	 * @param status
	 * @date 2014年3月21日上午10:59:09
	 */
	public void setGeneAuditWay(Integer geneAuditWay);

	/**
	 * 获取手动审核位点列表
	 * @author jiangbin
	 * @return
	 * @date 2015年2月13日下午11:43:15
	 */
	public List<String> getManualMarkerNames();

	/**
	 * 设置手动审核位点列表
	 * @author jiangbin
	 * @param manualMarkerNames 手动审核位点列表
	 * @date 2016年7月25日下午4:54:28
	 */
	public void setManualMarkerNames(List<String> manualMarkerNames);
}
