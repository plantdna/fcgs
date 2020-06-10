package com.fcgs.base.domain;

import java.util.HashMap;

/**
 * 精简指纹信息对象
 * @author jiangbin
 * @date 2017年5月30日下午9:27:39
 */
public class SimpleSmartGene extends HashMap<String, int[]> implements SmartGene {

	private static final long serialVersionUID = 6058422005139693565L;
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
