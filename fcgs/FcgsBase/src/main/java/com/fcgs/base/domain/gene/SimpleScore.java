package com.fcgs.base.domain.gene;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimpleScore implements Score {
	private static final long serialVersionUID = -5438112949320928143L;
	private float score;

	@Override
	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public float getScore() {
		return score;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
