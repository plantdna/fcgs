package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.MarkerGroupMapper;
import com.fcgs.base.primer.PrimerInfo;
import com.fcgs.base.primer.PrimerInfoSorter;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;
import com.pids.core.utils.ListUtils;
import com.pids.core.utils.ObjectCopier;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 过滤出指纹交集引物信息
 * @author jiangbin
 * @date 2014年7月12日下午6:23:55
 */

public class GeneMixedPrimerFilter implements Filter<List<Gene>, List<PrimerInfo>> {
	private PrimerInfoSorter<PrimerInfo> primerInfoSorter=new PrimerInfoSorter<>();

	@Override
	public List<PrimerInfo> filter(List<Gene> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		MarkerGroupMapper mapper = new MarkerGroupMapper(sources);
		int geneCount = ListUtils.size(sources);
		List<PrimerInfo> targets = new ArrayList<PrimerInfo>();
		for (String primerName : mapper) {
			List<Marker> markerList = mapper.get(primerName);
			if(ListUtils.size(markerList) == geneCount) {
				PrimerInfo info = markerList.get(0);
				info = ObjectCopier.copy(info);
				targets.add(info);
			}
		}
		if (CollectionUtils.isEmpty(targets)) {
			return null;
		}
		Collections.sort(targets, primerInfoSorter);
		return targets;
	}

	public void setPrimerInfoSorter(PrimerInfoSorter<PrimerInfo> primerInfoSorter) {
		this.primerInfoSorter = primerInfoSorter;
	}

	public PrimerInfoSorter<PrimerInfo> getPrimerInfoSorter() {
		return primerInfoSorter;
	}
}
