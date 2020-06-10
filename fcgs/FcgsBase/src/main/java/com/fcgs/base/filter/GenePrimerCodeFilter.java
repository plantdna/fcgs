package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.MarkerPrimerCodeMapper;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;

import java.util.List;

/**
 * 过滤出给定指纹列表的所有位点引物编号的并集
 * @author jiangbin
 * @date 2014年7月11日下午6:15:53
 */

public class GenePrimerCodeFilter implements Filter<List<Gene>, List<String>> {
	private GeneMarkerListFilter geneMarkerListFilter=new GeneMarkerListFilter();

	@Override
	public List<String> filter(List<Gene> sources) throws ICoreException {
		List<Marker> markers = this.geneMarkerListFilter.filter(sources);
		MarkerPrimerCodeMapper mapper = new MarkerPrimerCodeMapper();
		mapper.addAll(markers);
		return mapper.getKeys();
	}

}
