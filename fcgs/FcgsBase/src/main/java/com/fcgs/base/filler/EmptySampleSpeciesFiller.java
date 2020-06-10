package com.fcgs.base.filler;

import com.fcgs.base.domain.SampleSpecies;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;

/**
 * 清空样品种属信息
 * @author Jiangbin
 * @date 2014-4-25下午11:19:25
 */

public class EmptySampleSpeciesFiller<S extends SampleSpecies> extends ListFillerTemplate<S, S> {

	@Override
	protected S fill(S source, boolean isInternal) throws ICoreException {
		source.setSamSpecies(null);
		return source;
	}

}
