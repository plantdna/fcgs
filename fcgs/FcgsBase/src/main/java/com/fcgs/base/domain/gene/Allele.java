package com.fcgs.base.domain.gene;

import java.io.Serializable;

/**
 * 等位基因信息
 * @author jiangbin
 * @date 2012-4-11下午4:48:23
 */
public interface Allele extends Serializable, Score {

	/**
	 * 设置等位基因的质量描述，请参见GeneMarker相关质量值描述
	 * @author jiangbin
	 * @param quality
	 * @date 2014年3月21日上午11:09:37
	 */
	public void setQuality(String quality);

	/**
	 * 获取等位基因的质量描述，请参见GeneMarker相关质量值描述
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:09:40
	 */
	public String getQuality();

	/**
	 * 设置等基因的备注信息
	 * @author jiangbin
	 * @param comments
	 * @date 2014年3月21日上午11:09:41
	 */
	public void setComments(String comments);

	/**
	 * 获取等基因的备注信息
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:09:42
	 */
	public String getComments();

	/**
	 * 设置峰区域面积
	 * @author jiangbin
	 * @param area
	 * @date 2014年3月21日上午11:09:47
	 */
	public void setArea(float area);

	/**
	 * 获取峰区域面积
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:09:46
	 */
	public float getArea();

	/**
	 * 设置峰高
	 * @author jiangbin
	 * @param height
	 * @date 2014年3月21日上午11:09:49
	 */
	public void setHeight(float height);

	/**
	 * 获取峰高
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:09:51
	 */
	public float getHeight();

	/**
	 * 设置峰顶点Size值
	 * @author jiangbin
	 * @param size
	 * @date 2014年3月21日上午11:09:50
	 */
	public void setSize(float size);

	/**
	 * 获取峰顶点Size值
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:09:53
	 */
	public float getSize();

	/**
	 * 设置等位基因区间别名
	 * @author jiangbin
	 * @param allele
	 * @date 2014年3月21日上午11:09:54
	 */
	public void setAllele(float allele);

	/**
	 * 获取等位基因区间别名
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:09:55
	 */
	public float getAllele();

}
