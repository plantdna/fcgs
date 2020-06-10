package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.GeneMapper;
import com.fcgs.base.mapper.MarkerMapper;
import com.pids.core.exception.ICoreException;
import com.pids.core.pair.StringPair;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤出指定的Map<GeneId,MarkerName>的Marker列表，这个过滤器通常用来挑选指纹Marker，
 * 如前台页面中用户挑选了多组指纹中的某些Marker进行合并
 * @author jiangbin
 * @date 2012-11-25下午3:20:47
 */
public class IntegrateMarkersFilter {
	/**
	 * 过滤源指纹列表中指定的Marker列表
	 * @author jiangbin
	 * @param sources 源指纹
	 * @param filter　指纹ID与MarkerName的映射关系表:source/target--GeneId/MarkerName
	 * @return
	 * @throws ICoreException
	 * @date 2012-11-25下午3:24:14
	 */
	public List<Marker> filter(List<Gene> sources, List<StringPair> filter) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		if (filter == null || filter.isEmpty()) {
			return null;
		}
		GeneMapper geneMapper = new GeneMapper();
		geneMapper.addAll(sources);
		return filter(geneMapper, filter);
	}

	/**
	 * 从给定的指纹Mapper中过滤指定的GeneId-PrimerName对对应的Marker列表
	 * @author jiangbin
	 * @param geneMapper 指纹位点映射关系表
	 * @param filter source--GeneId/target--PrimerName
	 * @return 目标Marker列表
	 * @date 2013-1-28下午12:12:04
	 */
	protected List<Marker> filter(GeneMapper geneMapper, List<StringPair> filter) {
		List<Marker> markers = new ArrayList<Marker>();
		for (StringPair pair : filter) {
			Marker marker = filterMarker(geneMapper, pair);
			if (marker == null) {
				continue;
			}
			markers.add(marker);
		}
		return markers;
	}

	/**
	 * 过滤出指定指纹的指定Marker
	 * @author jingbin
	 * @param geneMapper
	 * @param pair
	 * @return
	 * @date 2013-4-26下午8:37:11
	 */
	protected Marker filterMarker(GeneMapper geneMapper, StringPair pair) {
		String geneId = pair.getSource();
		String markerName = pair.getTarget();
		MarkerMapper mapper = geneMapper.get(geneId);
		if (mapper == null || mapper.isEmpty()) {
			return null;
		}
		return mapper.get(markerName);
	}
}
