package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.SampleDna;

public class SimpleSampleDnaHandle<S extends SampleDna> implements SampleDnaHandle<S> {

	private static final long serialVersionUID = -8778915782352512537L;
	private S sample;

	@Override
	public void setDnaBarcode(String dnaBarcode) {
		this.sample.setDnaBarcode(dnaBarcode);
	}

	@Override
	public String getDnaBarcode() {
		if (this.sample != null) {
			return this.sample.getDnaBarcode();
		}
		return null;
	}

	@Override
	public String getSamBarcode() {
		if (this.sample != null) {
			return this.sample.getSamBarcode();
		}
		return null;
	}

	@Override
	public void setSamBarcode(String samBarcode) {
		this.sample.setSamBarcode(samBarcode);
	}

	@Override
	public S getSample() {
		return this.sample;
	}

	@Override
	public void setSample(S sampleDna) {
		this.sample = sampleDna;
	}

}
