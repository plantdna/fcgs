package com.fcgs.base.filter;

import com.fcgs.base.constants.ExtractWay;
import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 混株指纹数据过滤器
 * @author Jiangbin
 * @date 2015年5月7日上午10:05:13
 */

public class MixGeneFilter extends ListFilterTemplate<Gene, Gene> {

	@Override
	protected Gene filter(Gene source, boolean isInternal) throws ICoreException {
		if (source == null || source.getSample() == null) {
			return null;
		}
		Integer extractWay = source.getSample().getExtractWay();
		return extractWay != null && extractWay.intValue() == ExtractWay.MIX ? source : null;
	}
}
