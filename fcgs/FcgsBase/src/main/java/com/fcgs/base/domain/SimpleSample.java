package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 样品信息的简单实现类
 * @author Jiangbin
 * @date 2014-3-4上午12:20:28
 */
public class SimpleSample implements Sample {
	private static final long serialVersionUID = 5546067515389733346L;
	protected String samBarcode; // 样品条码号
	protected String samName; // 样品名称
	protected String samRealName; // 样品真实名称
	protected String samOrigin; // 样品来源
	protected String samSpecies; // 样品种属，如玉米、水稻等
	protected String samManager;// 样品负责人
	protected String samKind;// 样品类型,如自交系、杂交种等
	protected String samNumber;// 样品编号
	protected String samContrast;//是否可对照

	@Override
	public String getSamNumber() {
		return samNumber;
	}

	@Override
	public void setSamNumber(String samNumber) {
		this.samNumber = samNumber;
	}

	@Override
	public String getSamBarcode() {
		return samBarcode;
	}

	@Override
	public void setSamBarcode(String samBarcode) {
		this.samBarcode = samBarcode;
	}

	@Override
	public String getSamName() {
		return samName;
	}

	@Override
	public void setSamName(String samName) {
		this.samName = samName;
	}

	@Override
	public String getSamRealName() {
		return samRealName;
	}

	@Override
	public void setSamRealName(String samRealName) {
		this.samRealName = samRealName;
	}

	@Override
	public String getSamOrigin() {
		return samOrigin;
	}

	@Override
	public void setSamOrigin(String samOrigin) {
		this.samOrigin = samOrigin;
	}

	@Override
	public String getSamSpecies() {
		return samSpecies;
	}

	@Override
	public void setSamSpecies(String samSpecies) {
		this.samSpecies = samSpecies;
	}

	@Override
	public void setSamManager(String samManager) {
		this.samManager = samManager;
	}

	@Override
	public String getSamManager() {
		return samManager;
	}

	@Override
	public void setSamKind(String samKind) {
		this.samKind = samKind;
	}

	@Override
	public String getSamKind() {
		return samKind;
	}

	@Override
	public String getSamContrast() {
		return samContrast;
	}

	@Override
	public void setSamContrast(String samContrast) {
		this.samContrast = samContrast;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
