package com.fcgs.base.marker.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 位点信息视图对象,包含三方面信息：缺失位点、无缺失位点、差异位点，
 * 主要是用来标准化比对、合并后的结果信息，以及标准化缺失位点的计算及引物名转换等操作
 * @author jiangbin
 * @date 2012-11-24上午12:56:08
 */
public interface MarkerNamesViwer extends Serializable {
	
	/**
	 * 获取无差异位点名列表
	 * @author jiangbin
	 * @return
	 * @date 2012-11-24上午1:09:54
	 */
	public List<String> getNoDiffMarkerNames();
	
	/**
	 * 设置无差异位点名列表
	 * @author jiangbin
	 * @param noDiffMarkerNames
	 * @date 2012-11-24上午1:09:55
	 */
	public void setNoDiffMarkerNames(List<String> noDiffMarkerNames);
	
	/**
	 * 获取差异位点名列表
	 * @author jiangbin
	 * @return
	 * @date 2012-11-24上午1:09:57
	 */
	public List<String> getDiffMarkerNames();
	
	/**
	 * 设置差异位点名列表
	 * @author jiangbin
	 * @param diffMarkerNames
	 * @date 2012-11-24上午1:09:58
	 */
	public void setDiffMarkerNames(List<String> diffMarkerNames);
	
	/**
	 * 获取缺失位点名列表
	 * @author jiangbin
	 * @return
	 * @date 2012-11-24上午1:10:00
	 */
	public List<String> getMissMarkerNames();
	
	/**
	 * 设置缺失位点名列表
	 * @author jiangbin
	 * @param missMarkerNames
	 * @date 2012-11-24上午1:10:14
	 */
	public void setMissMarkerNames(List<String> missMarkerNames);
	
	/**
	 * 获取无差异位点名数
	 * @author jiangbin
	 * @return
	 * @date 2012-11-24上午1:10:13
	 */
	public Integer getNoDiffMarkerCount();
	
	/**
	 * 设置无差异位点名数
	 * @author jiangbin
	 * @param noDiffMarkerCount
	 * @date 2012-11-24上午1:10:13
	 */
	public void setNoDiffMarkerCount(Integer noDiffMarkerCount);
	
	/**
	 * 获取差异位点名数
	 * @author jiangbin
	 * @return
	 * @date 2012-11-24上午1:10:12
	 */
	public Integer getDiffMarkerCount();
	
	/**
	 * 设置差异位点名数
	 * @author jiangbin
	 * @param diffMarkerCount
	 * @date 2012-11-24上午1:10:11
	 */
	public void setDiffMarkerCount(Integer diffMarkerCount);
	
	/**
	 * 获取缺失位点名数
	 * @author jiangbin
	 * @return
	 * @date 2012-11-24上午1:10:11
	 */
	public Integer getMissMarkerCount();
	
	/**
	 * 设置缺失位点名数
	 * @author jiangbin
	 * @param missMarkerCount
	 * @date 2012-11-24上午1:10:10
	 */
	public void setMissMarkerCount(Integer missMarkerCount);
	
	/**
	 * 设置位点视图信息
	 * @author Jiangbin
	 * @param viwer
	 * @date 2013-6-21上午12:37:44
	 */
	public void setMarkerNamesViwer(MarkerNamesViwer viwer);
	
}