package com.fcgs.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 指纹板孔位置
 * @author jiangbin
 * @date 2014年3月21日下午2:04:57
 */
public class SimpleGeneLocation implements GeneLocation {
	private static final long serialVersionUID = -4279200594468869910L;
	protected String plate;
	protected String well;

	public SimpleGeneLocation() {
	}

	public SimpleGeneLocation(String plate, String well) {
		this.plate = plate;
		this.well = well;
	}

	public SimpleGeneLocation(GeneLocation location) {
		this.plate = location.getPlate();
		this.well = location.getWell();
	}

	@Override
	public String getPlate() {
		return plate;
	}

	@Override
	public void setPlate(String plate) {
		this.plate = plate;
	}

	@Override
	public String getWell() {
		return well;
	}

	@Override
	public void setWell(String well) {
		this.well = well;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
