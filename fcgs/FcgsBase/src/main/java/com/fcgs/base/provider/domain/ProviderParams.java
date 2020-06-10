package com.fcgs.base.provider.domain;

import java.io.Serializable;

/**
 * 合并和比对核心算法对象实例的提供方参数对象接口
 * @author Jiangbin
 * @date 2015年5月29日上午9:51:37
 */
public interface ProviderParams extends Serializable {

	/**
	 * 获取最小碱基偏移量
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:31
	 */
	public Integer getMinSizeOffset();

	/**
	 * 设置最小碱基偏移量
	 * @author jiangbin
	 * @param minSizeOffset
	 * @date 2012-11-9下午12:24:34
	 */
	public void setMinSizeOffset(Integer minSizeOffset);

	/**
	 * 获取最大碱基偏移量
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:35
	 */
	public Integer getMaxSizeOffset();

	/**
	 * 设置最大碱基偏移量
	 * @author jiangbin
	 * @param maxSizeOffset
	 * @date 2012-11-9下午12:24:37
	 */
	public void setMaxSizeOffset(Integer maxSizeOffset);

	/**
	 * 获取最大差异位点限定值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:39
	 */
	public Integer getMaxDiffMarkerCount();

	/**
	 * 设置最大差异位点限定值
	 * @author jiangbin
	 * @param maxDiffMarkerCount
	 * @date 2012-11-9下午12:24:40
	 */
	public void setMaxDiffMarkerCount(Integer maxDiffMarkerCount);

	/**
	 * 获取最小差异位点限定值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:41
	 */
	public Integer getMinDiffMarkerCount();

	/**
	 * 设置最小差异位点限定值
	 * @author jiangbin
	 * @param minDiffMarkerCount
	 * @date 2012-11-9下午12:24:43
	 */
	public void setMinDiffMarkerCount(Integer minDiffMarkerCount);

	/**
	 * 获取最大位点总数限定值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:44
	 */
	public Integer getMaxMarkerCount();

	/**
	 * 设置最大位点总数限定值
	 * @author jiangbin
	 * @param maxMarkerCount
	 * @date 2012-11-9下午12:24:46
	 */
	public void setMaxMarkerCount(Integer maxMarkerCount);

	/**
	 * 获取最小位点总数限定值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:50
	 */
	public Integer getMinMarkerCount();

	/**
	 * 设置最小位点总数限定值
	 * @author jiangbin
	 * @param minMarkerCount
	 * @date 2012-11-9下午12:24:51
	 */
	public void setMinMarkerCount(Integer minMarkerCount);

}