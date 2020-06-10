package com.fcgs.base.filter;

import com.fcgs.base.domain.DnaBarcode;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 基于Dna条码号接口的Dna条码号过滤器
 * @author jiangbin
 * @date 2014年11月29日上午12:30:14
 */

public class DnaBarcodeFilter<S extends DnaBarcode> extends ListFilterTemplate<S, String> {
	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getDnaBarcode();
	}

}
