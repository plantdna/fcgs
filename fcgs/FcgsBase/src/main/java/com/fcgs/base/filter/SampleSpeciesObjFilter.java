package com.fcgs.base.filter;

import com.fcgs.base.domain.SampleSpecies;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListObjFilterTemplate;

/**
 * 包含指定种属字段的对象过滤器，所有扩展了{@link SampleSpecies}接口的类均可以使用本功能
 * @author ANDORY
 * @date 2016年5月16日下午8:49:45
 */

public class SampleSpeciesObjFilter<S extends SampleSpecies> extends ListObjFilterTemplate<S, String> {

	@Override
	public S filter(S source, String samSpecies) throws ICoreException {
		if (source == null) {
			return null;
		}
		String tSamSpecies = source.getSamSpecies();
		if (samSpecies == null && tSamSpecies == null) {
			return source;
		}
		if (samSpecies != null && samSpecies.equals(tSamSpecies)) {
			return source;
		}
		return null;
	}

}
