package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 指纹的Marker过滤器，将给定指纹列表中的所有位点列表
 * @author jiangbin
 * @date 2013-4-16上午10:51:51
 */
public class GeneMarkerListFilter implements Filter<List<Gene>, List<Marker>> {

	@Override
	public List<Marker> filter(List<Gene> source) throws ICoreException {
		List<Marker> markers = new ArrayList<Marker>();
		for (Gene gene : source) {
			if (gene == null || gene.isEmpty()) {
				continue;
			}
			markers.addAll(gene.getMarkers());
		}
		return markers;
	}

}
