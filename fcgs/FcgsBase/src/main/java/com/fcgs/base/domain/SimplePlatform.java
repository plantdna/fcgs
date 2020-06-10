package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimplePlatform implements Platform {

	private static final long serialVersionUID = -167244992413198608L;
	private String platformCode;
	private String platformName;

	@Override
	public String getPlatformCode() {
		return platformCode;
	}

	@Override
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	@Override
	public String getPlatformName() {
		return platformName;
	}

	@Override
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
