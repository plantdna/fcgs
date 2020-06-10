package com.fcgs.base.genefile.utils;

import com.fcgs.base.genefile.GeneFilePath;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 指纹文件路径过滤器
 * @author wuhaotian
 * @email nghsky@foxmail.com
 * @date 2018年5月28日下午3:31:40
 * @param <S>
 */
public class GeneFilePathFilter<S extends GeneFilePath> extends ListFilterTemplate<S, String> {

	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getGeneFilePath();
	}
}
