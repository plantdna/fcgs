package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 指纹状态接口
 * @author jiangbin
 * @date 2013-1-18上午9:55:30
 */
public class SimpleGeneStatus implements GeneStatus {
	private static final long serialVersionUID = -2016204056896228215L;
	private Integer geneStatus;

	@Override
	public void setGeneStatus(Integer geneStatus) {
		this.geneStatus = geneStatus;
	}

	@Override
	public Integer getGeneStatus() {
		return geneStatus;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
