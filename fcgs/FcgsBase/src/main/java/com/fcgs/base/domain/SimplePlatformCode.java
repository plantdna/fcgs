package com.fcgs.base.domain;

public class SimplePlatformCode implements PlatformCode {
	private static final long serialVersionUID = 6651073945083705119L;
	private String platformCode;

	@Override
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	@Override
	public String getPlatformCode() {
		return platformCode;
	}
}
