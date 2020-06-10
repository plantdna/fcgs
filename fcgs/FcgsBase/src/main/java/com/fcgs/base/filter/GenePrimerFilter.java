package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.MarkerMapper;
import com.fcgs.base.primer.PrimerInfo;
import com.fcgs.base.primer.PrimerInfoSorter;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;
import com.pids.core.utils.ObjectCopier;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 指纹引物信息过滤器，过滤得到的引物信息是指纹中所有引物信息的并集，获取到的引物信息将按照引物编号排序
 * @author jiangbin
 * @date 2014年7月12日下午6:23:55
 */
public class GenePrimerFilter implements Filter<List<Gene>, List<PrimerInfo>> {
	private PrimerInfoSorter<PrimerInfo> primerInfoSorter=new PrimerInfoSorter<>();

	@Override
	public List<PrimerInfo> filter(List<Gene> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		MarkerMapper mapper = new MarkerMapper();
		for (Gene source : sources) {
			mapper.addAll(source.getMarkers());
		}
		List<PrimerInfo> primers = ObjectCopier.copy(mapper.getValues());
		if (CollectionUtils.isEmpty(primers)) {
			return null;
		}
		Collections.sort(primers, primerInfoSorter);
		return primers;
	}

	public void setPrimerInfoSorter(PrimerInfoSorter<PrimerInfo> primerInfoSorter) {
		this.primerInfoSorter = primerInfoSorter;
	}

	public PrimerInfoSorter<PrimerInfo> getPrimerInfoSorter() {
		return primerInfoSorter;
	}

}
