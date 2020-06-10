package com.fcgs.base.filter;

import com.fcgs.base.domain.SampleBarcode;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 基于样品条码号接口的样品条码号过滤器
 * @author jiangbin
 * @date 2014年11月29日上午12:30:14
 */

public class SampleBarcodeFilter<S extends SampleBarcode> extends ListFilterTemplate<S, String> {
	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getSamBarcode();
	}

}
