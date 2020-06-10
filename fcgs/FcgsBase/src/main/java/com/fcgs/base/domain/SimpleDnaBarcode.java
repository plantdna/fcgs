package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DNA条码号字段接口
 * @author jiangbin
 * @date 2015年6月11日下午4:33:46
 */
public class SimpleDnaBarcode implements DnaBarcode {
	private static final long serialVersionUID = 8326118127317929442L;
	private String dnaBarcode;

	public void setDnaBarcode(String dnaBarcode) {
		this.dnaBarcode = dnaBarcode;
	}

	public String getDnaBarcode() {
		return dnaBarcode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
