package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.Sample;

/**
 * 样品信息句柄对象
 * @author jiangbin
 * @date 2014年3月21日上午11:58:33
 */
public class SimpleSampleHandle<S extends Sample> implements SampleHandle<S> {
	private static final long serialVersionUID = 7758678926852618206L;
	private S sample;

	@Override
	public void setSample(S sample) {
		this.sample = sample;
	}

	@Override
	public S getSample() {
		return sample;
	}

	@Override
	public void setSamBarcode(String samBarcode) {
		this.sample.setSamBarcode(samBarcode);
	}

	@Override
	public String getSamBarcode() {
		if (this.sample != null) {
			return this.sample.getSamBarcode();
		}
		return null;
	}
}
