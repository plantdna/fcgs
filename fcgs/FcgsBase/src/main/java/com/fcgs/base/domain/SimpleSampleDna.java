package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 样品DNA信息接口
 * @author jiangbin
 * @date 2014年3月21日下午12:30:08
 */
public class SimpleSampleDna extends SimpleSample implements SampleDna {
	private static final long serialVersionUID = -7527467939069824566L;
	protected Integer extractWay;
	protected String dnaManager;
	protected Integer dnaType;
	protected String dnaBarcode;

	@Override
	public Integer getExtractWay() {
		return extractWay;
	}

	@Override
	public void setExtractWay(Integer extractWay) {
		this.extractWay = extractWay;
	}

	@Override
	public String getDnaManager() {
		return dnaManager;
	}

	@Override
	public void setDnaManager(String dnaManager) {
		this.dnaManager = dnaManager;
	}

	@Override
	public Integer getDnaType() {
		return dnaType;
	}

	@Override
	public void setDnaType(Integer dnaType) {
		this.dnaType = dnaType;
	}

	@Override
	public String getDnaBarcode() {
		return dnaBarcode;
	}

	@Override
	public void setDnaBarcode(String dnaBarcode) {
		this.dnaBarcode = dnaBarcode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
