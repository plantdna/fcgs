package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 手动审核位点过滤器
 * @author jiangbin
 * @date 2016年7月25日下午4:33:39
 */

public class ManualMarkerFilter<S extends Marker> extends ListFilterTemplate<S, S> {

	@Override
	protected S filter(S source, boolean isInternal) throws ICoreException {
		return source.isManual() ? source : null;
	}

}
