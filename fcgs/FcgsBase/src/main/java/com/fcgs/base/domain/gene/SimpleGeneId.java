package com.fcgs.base.domain.gene;

/**
 * 指纹记录ID信息接口类
 * @author jiangbin
 * @date 2015年7月5日下午2:27:24
 */
public class SimpleGeneId implements GeneId {
	private static final long serialVersionUID = 156551711877062519L;
	private String geneId;

	@Override
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	@Override
	public String getGeneId() {
		return geneId;
	}

}
