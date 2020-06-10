package com.fcgs.base.marker.comparer.impl;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.marker.comparer.MarkerComparer;
import com.fcgs.base.marker.comparer.MarkersComparer;
import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 二倍体Marker列表比较器,该比较器会计算了无差异Marker的权重合，故该算法通常用于择优
 * @author Andory
 * @date 2012-8-4下午2:22:37
 */
public class MarkersComparerImpl implements MarkersComparer {
	private MarkerComparer markerComparer = new MarkerComparerImpl();
	private int noDiffMarkerCount;
	private double noDiffMarkerWeightCount;

	@Override
	public int comparer(Marker sourceMarker, List<Marker> targetMarkers) throws ICoreException {
		this.noDiffMarkerCount = 0;
		this.noDiffMarkerWeightCount = 0;
		for (Marker targetMarker : targetMarkers) {
			try {
				if (getMarkerComparer().comparer(sourceMarker, targetMarker)) {
					noDiffMarkerCount++;
					noDiffMarkerWeightCount += targetMarker.getWeight();
				}
			} catch (Exception e) {
				String msg = String.format("位点%s与%s比对出错!", sourceMarker, targetMarker);
				throw new ICoreException(msg, e);
			}
		}
		return noDiffMarkerCount;
	}

	@Override
	public int getNoDiffMarkerCount() {
		return noDiffMarkerCount;
	}

	@Override
	public void setNoDiffMarkerCount(int noDiffMarkerCount) {
		this.noDiffMarkerCount = noDiffMarkerCount;
	}

	@Override
	public double getNoDiffMarkerWeightCount() {
		return noDiffMarkerWeightCount;
	}

	@Override
	public void setNoDiffMarkerWeightCount(double noDiffMarkerWeightCount) {
		this.noDiffMarkerWeightCount = noDiffMarkerWeightCount;
	}

	@Override
	public void setMarkerComparer(MarkerComparer markerComparer) {
		this.markerComparer = markerComparer;
	}

	@Override
	public MarkerComparer getMarkerComparer() {
		return markerComparer;
	}
}
