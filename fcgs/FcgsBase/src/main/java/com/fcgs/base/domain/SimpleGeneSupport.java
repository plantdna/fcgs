package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 指纹信息简单支持类,本类会自动初始化相关属性值
 * @author jiangbin
 * @date 2014年3月21日下午2:46:35
 */
public class SimpleGeneSupport implements GeneSupport {
	private static final long serialVersionUID = 3314388309539437720L;
	protected GeneLocation location;
	protected SampleDna sample;
	protected Platform platform;
	
	public SimpleGeneSupport() {
		this.location = new SimpleGeneLocation();
		this.sample = new SimpleSampleDna();
		this.platform = new SimplePlatform();
	}
	
	@Override
	public GeneLocation getLocation() {
		return location;
	}
	
	@Override
	public void setLocation(GeneLocation location) {
		this.location = location;
	}
	
	@Override
	public void setSample(SampleDna sample) {
		this.sample = sample;
	}
	
	@Override
	public SampleDna getSample() {
		return sample;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@Override
	public void setSamBarcode(String samBarcode) {
		this.sample.setSamBarcode(samBarcode);
	}
	
	@Override
	public String getSamBarcode() {
		return this.sample.getSamBarcode();
	}
	
	@Override
	public void setDnaBarcode(String dnaBarcode) {
		this.sample.setDnaBarcode(dnaBarcode);
	}
	
	@Override
	public String getDnaBarcode() {
		return this.sample.getDnaBarcode();
	}
	
	@Override
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	@Override
	public Platform getPlatform() {
		return platform;
	}
	
	@Override
	public void setPlatformCode(String platformCode) {
		this.platform.setPlatformCode(platformCode);
	}
	
	@Override
	public String getPlatformCode() {
		return this.platform.getPlatformCode();
	}
	
}
