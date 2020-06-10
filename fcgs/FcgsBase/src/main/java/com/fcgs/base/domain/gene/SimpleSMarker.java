package com.fcgs.base.domain.gene;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimpleSMarker extends SimpleMarker implements SMarker {
	private static final long serialVersionUID = -3781327505753614475L;
	private double persent;

	@Override
	public void setPersent(double persent) {
		this.persent = persent;
	}

	@Override
	public double getPersent() {
		return persent;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
