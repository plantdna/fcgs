package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.gene.Gene;

import java.io.Serializable;

public class SimpleGeneHandle implements GeneHandle, Serializable {
	private static final long serialVersionUID = 6417773427940944153L;
	private Gene gene;

	@Override
	public void setGene(Gene gene) {
		this.gene = gene;
	}

	@Override
	public Gene getGene() {
		return gene;
	}

	/**
	 * 当前对象是否存在指纹数据对象且位点数不为0(not null && isEmpty()!=true)
	 * @author Andory
	 * @return
	 * @date 2013年9月11日下午1:41:56
	 */
	public boolean isEmpty() {
		return this.gene == null || this.gene.isEmpty();
	}
}
