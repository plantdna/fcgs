package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;

/**
 * 无样品编号的指纹过滤器
 * @author jiangbin
 * @date 2012-11-8下午3:25:59
 */
public class EmptySamNumGeneFilter extends AbstractEmptyPropGeneFilter {
	@Override
	protected Gene filter(Gene source, boolean isInternal) throws ICoreException {
		if (source == null) {
			return null;
		}
		if (StringUtils.isBlank(source.getSample().getSamBarcode())) {
			return source;
		}
		return null;
	}
}
