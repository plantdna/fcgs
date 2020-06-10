package com.fcgs.base.genefile;

import com.fcgs.base.domain.gene.Gene;

/**
 * 指纹数据文件详细信息简单实现
 * @author Andory
 * @date 2013年9月10日下午8:11:46
 */
public class SimpleGeneFileDetail implements GeneFileDetail {
	private static final long serialVersionUID = -7580029819229700554L;
	private Gene gene;
	private String geneFilePath;

	@Override
	public void setGene(Gene gene) {
		this.gene = gene;
	}

	@Override
	public Gene getGene() {
		return gene;
	}

	@Override
	public void setGeneFilePath(String geneFilePath) {
		this.geneFilePath = geneFilePath;
	}

	@Override
	public String getGeneFilePath() {
		return geneFilePath;
	}
}
