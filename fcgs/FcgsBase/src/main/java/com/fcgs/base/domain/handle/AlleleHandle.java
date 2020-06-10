package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.gene.Allele;

import java.io.Serializable;
import java.util.List;

/**
 * Allele句柄信息接口
 * @author jiangbin
 * @date 2014年3月21日上午11:05:40
 */
public interface AlleleHandle extends Serializable {
	/**
	 * 获取Alelle信息列表
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:05:40
	 */
	public List<Allele> getAlleles();

	/**
	 * 设置Alelle信息列表
	 * @author jiangbin
	 * @param alleles
	 * @date 2014年3月21日上午11:05:37
	 */
	public void setAlleles(List<Allele> alleles);
}
