package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 指纹图片过滤器
 * @author Andory
 * @date 2013-7-9上午7:58:16
 */
public class MarkerPicturesFilter extends ListFilterTemplate<Marker, String> {
	@Override
	protected String filter(Marker source, boolean isInternal) throws ICoreException {
		return source.getPicture();
	}
}
