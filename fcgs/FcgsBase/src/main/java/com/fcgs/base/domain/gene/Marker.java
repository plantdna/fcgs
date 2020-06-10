package com.fcgs.base.domain.gene;

import com.fcgs.base.domain.handle.AlleleHandle;
import com.fcgs.base.domain.handle.SizeHandle;
import com.fcgs.base.domain.handle.WeightHandle;
import com.fcgs.base.primer.PrimerInfo;

import java.io.Serializable;

/**
 * 位点信息接口
 * @author jiangbin
 * @date 2012-4-11下午4:44:46
 */
public interface Marker extends Serializable, AlleleHandle, PrimerInfo, SizeHandle, WeightHandle, GeneId {

	/**
	 * 添加一个等位基因
	 * @author jiangbin
	 * @param allele
	 * @date 2014年3月21日上午11:31:11
	 */
	public void addAllele(Allele allele);

	/**
	 * 获取位点备注信息
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:30:58
	 */
	public String getComment();

	/**
	 * 设置位点备注信息
	 * @author jiangbin
	 * @param comment
	 * @date 2014年3月21日上午11:30:59
	 */
	public void setComment(String comment);

	/**
	 * 获取位点图谱路径 
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:31:02
	 */
	public String getPicture();

	/**
	 * 设置位点图谱路径 
	 * @author jiangbin
	 * @param picture
	 * @date 2014年3月21日上午11:31:01
	 */
	public void setPicture(String picture);

	/**
	 * 设置位点数据到当前Marker
	 * @author jiangbin
	 * @param marker
	 * @date 2014年11月20日上午1:37:09
	 */
	public void setMarker(Marker marker);

	/**
	 * 获取位点字符串表示，示例如：204/265
	 * @author jiangbin
	 * @return
	 * @date 2015年6月15日下午2:57:56
	 */
	public String getMarkerStr();

	/**
	 * 清空Allele
	 * @author jiangbin
	 * @date 2015年1月7日下午7:54:23
	 */
	public void clear();

	/**
	 * 判定是否为纯合子位点(即A/A带型位点)，若不包含allele则视为杂合子
	 * @author jiangbin
	 * @return true/false--是/否
	 * @date 2016年7月13日下午1:56:55
	 */
	public boolean isHomozygote();

	/**
	 * 设置是否为手动审核位点
	 * @author jiangbin
	 * @param isManual true/false--是/否
	 * @date 2016年7月25日下午4:02:34
	 */
	public void setManual(boolean isManual);

	/**
	 * 是否为手动审核位点
	 * @author jiangbin
	 * @return true/false--是/否
	 * @date 2016年7月25日下午4:02:36
	 */
	public boolean isManual();
}
