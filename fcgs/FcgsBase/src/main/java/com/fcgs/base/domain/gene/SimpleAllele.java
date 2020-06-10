package com.fcgs.base.domain.gene;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Alelle信息接口的简单实现类
 * @author jiangbin
 * @date 2014年3月21日上午11:09:04
 */
public class SimpleAllele implements Allele {
	private static final long serialVersionUID = 14223132367757706L;
	protected float allele;
	protected float size;
	protected float height;
	protected float area;
	protected float score;
	protected String comments;
	protected String quality;

	@Override
	public float getAllele() {
		return allele;
	}

	@Override
	public void setAllele(float allele) {
		this.allele = allele;
	}

	@Override
	public float getSize() {
		return size;
	}

	@Override
	public void setSize(float size) {
		this.size = size;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public float getArea() {
		return area;
	}

	@Override
	public void setArea(float area) {
		this.area = area;
	}

	@Override
	public float getScore() {
		return score;
	}

	@Override
	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public String getComments() {
		return comments;
	}

	@Override
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String getQuality() {
		return quality;
	}

	@Override
	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
