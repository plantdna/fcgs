package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.formatter.MarkerFormatter;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;
import com.pids.core.utils.ListUtils;

import java.util.List;

/**
 * <pre>从位点对象中过滤出Allele的值并以字符串列表方式返回，
 * 对位无Allele的位点将返回空字符串列表
 * 注意：本功能的默认值只支持二倍体的构建</pre>
 * @author jiangbin
 * @date 2013-11-11上午12:36:47
 */
public class MarkerAlleleStrFilter implements Filter<Marker, List<String>> {
	private MarkerFormatter markerFormatter=new MarkerFormatter();

	@Override
	public List<String> filter(Marker source) throws ICoreException {
		markerFormatter.setEmptyMarker("/");
		return ListUtils.createList(markerFormatter.format(source));
	}

}
