package com.fcgs.base.genefile;

/**
 * 指纹及存储路径信息简单实现类
 * @author Andory
 * @date 2013年9月10日下午6:43:11
 */
public class SimpleGeneFilePath implements GeneFilePath {
	private static final long serialVersionUID = -4804408987420341339L;
	private String geneFilePath;

	@Override
	public String getGeneFilePath() {
		return geneFilePath;
	}

	@Override
	public void setGeneFilePath(String geneFilePath) {
		this.geneFilePath = geneFilePath;
	}

}
