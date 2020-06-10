package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.gene.Gene;

import java.io.Serializable;

/**
 * 指纹句柄接口，用于获取指纹对象信息
 * @author Andory
 * @date 2012-6-30上午11:37:58
 */
public interface GeneHandle extends Serializable {
	/**
	 * 获取指纹信息对象句柄
	 * @author Andory
	 * @return
	 * @date 2012-6-30上午11:39:00
	 */
	public Gene getGene();

	/**
	 * 设置指纹信息对象句柄
	 * @author Andory
	 * @param gene
	 * @date 2012-6-30上午11:39:46
	 */
	public void setGene(Gene gene);
}
