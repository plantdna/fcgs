package com.fcgs.base.domain;

/**
 * 样品种属信息
 * @author Jiangbin
 * @date 2014-4-25下午10:31:19
 */
public class SimpleSampleSpecies implements SampleSpecies {
	private static final long serialVersionUID = 5583933612939716663L;
	private String samSpecies;

	@Override
	public void setSamSpecies(String samSpecies) {
		this.samSpecies = samSpecies;
	}

	@Override
	public String getSamSpecies() {
		return samSpecies;
	}
}
