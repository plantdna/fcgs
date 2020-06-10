package com.fcgs.base.domain.gene;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class SimpleMMarker extends SimpleSMarker implements MMarker {
	private static final long serialVersionUID = -2082459312428550723L;
	private List<SMarker> markers;

	@Override
	public void setMarkers(List<SMarker> markers) {
		this.markers = markers;
	}

	@Override
	public List<SMarker> getMarkers() {
		return markers;
	}

	@Override
	public void addMarker(SMarker marker) {
		if (marker == null) {
			return;
		}
		if (this.markers == null) {
			this.markers = new ArrayList<>();
		}
		this.markers.add(marker);

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
