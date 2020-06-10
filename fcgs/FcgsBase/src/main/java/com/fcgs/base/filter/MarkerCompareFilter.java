package com.fcgs.base.filter;

import com.fcgs.base.datacopier.GeneCopier;
import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.domain.gene.SimpleGene;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

import java.util.List;

/**
 * 参与比对的指纹Marker列表滤器
 * @author jiangbin
 * @date 2012-5-4下午11:52:12
 */
public class MarkerCompareFilter extends ListFilterTemplate<Gene, Gene> {
	private List<String> compareMarkerNames;
	private GeneCopier geneCopier = new GeneCopier();

	@Override
	public List<Gene> filter(List<Gene> sources) throws ICoreException {
		if (!this.isNeedFilter()) {
			return sources;
		}
		return super.filter(sources);
	}

	@Override
	protected Gene filter(Gene source, boolean isInternal) throws ICoreException {
		Gene tmpGene = new SimpleGene();
		geneCopier.copy(source, tmpGene);
		List<Marker> markers = source.getMarkers();
		for (Marker marker : markers) {
			if (filterMakrer(marker)) {
				markers.add(marker);
			}
		}
		tmpGene.setMarkers(markers);
		return tmpGene;
	}

	protected boolean filterMakrer(Marker marker) {
		String markerName = marker.getPrimerName();
		if (markerName == null)
			return false;
		return compareMarkerNames.contains(markerName);
	}

	protected boolean isNeedFilter() {
		return (compareMarkerNames != null && !compareMarkerNames.isEmpty());
	}

	public final List<String> getCompareMarkerNames() {
		return compareMarkerNames;
	}

	public final void setCompareMarkerNames(List<String> compareMarkerNames) {
		this.compareMarkerNames = compareMarkerNames;
	}

	public final GeneCopier getGeneCopier() {
		return geneCopier;
	}

	public final void setGeneCopier(GeneCopier geneCopier) {
		this.geneCopier = geneCopier;
	}

}
