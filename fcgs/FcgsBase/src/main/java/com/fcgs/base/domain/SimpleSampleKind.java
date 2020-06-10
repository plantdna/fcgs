package com.fcgs.base.domain;

/**
 * 样品种属信息
 * @author Jiangbin
 * @date 2014-4-25下午10:31:19
 */
public class SimpleSampleKind implements SampleKind {
	private static final long serialVersionUID = 5583933612939716663L;
	private String samKind;

	@Override
	public void setSamKind(String samKind) {
		this.samKind = samKind;
	}

	@Override
	public String getSamKind() {
		return samKind;
	}
}
