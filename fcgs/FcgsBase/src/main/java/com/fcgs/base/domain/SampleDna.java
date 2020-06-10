package com.fcgs.base.domain;

/**
 * 样品DNA信息接口
 * @author jiangbin
 * @date 2014年3月21日下午12:30:08
 */
public interface SampleDna extends Sample, DnaBarcode {

	/**
	 * 设置DNA提取方式，参见:混株/单株
	 * @author jiangbin
	 * @param extractWay
	 * @date 2014年3月21日下午12:35:37
	 */
	public void setExtractWay(Integer extractWay);

	/**
	 * 获取DNA提取方式，参见:混株/单株
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日下午12:35:54
	 */
	public Integer getExtractWay();

	/**
	 * 获取DNA类型:SSR/SNP
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日下午12:36:37
	 */
	public Integer getDnaType();

	/**
	 * 设置DNA类型:SSR/SNP
	 * @author jiangbin
	 * @param dnaType
	 * @date 2014年3月21日下午12:36:35
	 */
	public void setDnaType(Integer dnaType);

	/**
	 * 设置负责人帐号
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日下午12:44:22
	 */
	public String getDnaManager();

	/**
	 * 获取DNA负责人帐号
	 * @author jiangbin
	 * @param dnaManager
	 * @date 2014年3月21日下午12:44:23
	 */
	public void setDnaManager(String dnaManager);

}
