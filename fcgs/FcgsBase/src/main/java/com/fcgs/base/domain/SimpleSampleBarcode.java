package com.fcgs.base.domain;

/**
 * 样品条码号特征信息接口简单实现类
 * @author Jiangbin
 * @date 2014年9月20日上午3:31:03
 */
public class SimpleSampleBarcode implements SampleBarcode {
	private static final long serialVersionUID = 4599604465919558639L;
	private String samBarcode;

	@Override
	public void setSamBarcode(String samBarcode) {
		this.samBarcode = samBarcode;
	}

	@Override
	public String getSamBarcode() {
		return samBarcode;
	}
}
