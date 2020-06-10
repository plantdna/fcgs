package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.gene.Marker;

import java.util.List;

/**
 * 位点信息句柄简单实现类
 * @author jiangbin
 * @date 2014年3月21日上午11:02:15
 */
public class SimpleMarkerHandle implements MarkerHandle {
	private static final long serialVersionUID = 4036685967681391866L;
	private List<Marker> markers;

	@Override
	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}

	@Override
	public List<Marker> getMarkers() {
		return markers;
	}
}
