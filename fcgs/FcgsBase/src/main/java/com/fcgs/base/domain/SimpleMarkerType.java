package com.fcgs.base.domain;

/**
 * 标记类型数据接口实现类
 * @author jiangbin
 * @date 2017年8月31日下午2:42:32
 */
public class SimpleMarkerType implements MarkerType {

	private static final long serialVersionUID = 3294973978835892107L;
	private Integer markerType;

	@Override
	public Integer getMarkerType() {
		return markerType;
	}

	@Override
	public void setMarkerType(Integer markerType) {
		this.markerType = markerType;
	}

}
