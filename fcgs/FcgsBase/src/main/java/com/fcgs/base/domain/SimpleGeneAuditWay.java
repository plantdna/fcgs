package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 指纹审核方式接口
 * @author jiangbin
 * @date 2013-1-18上午9:55:30
 */
public class SimpleGeneAuditWay implements GeneAuditWay {
	private static final long serialVersionUID = 1360581138804446523L;
	private Integer geneAuditWay;
	private List<String> manualMarkerNames;

	@Override
	public Integer getGeneAuditWay() {
		return geneAuditWay;
	}

	@Override
	public void setGeneAuditWay(Integer geneAuditWay) {
		this.geneAuditWay = geneAuditWay;
	}

	@Override
	public List<String> getManualMarkerNames() {
		return manualMarkerNames;
	}

	@Override
	public void setManualMarkerNames(List<String> manualMarkerNames) {
		this.manualMarkerNames = manualMarkerNames;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
