package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.MarkerMapper;
import com.pids.core.exception.ICoreException;
import com.pids.core.utils.ObjectCopier;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标指纹位点过滤器
 * @author jiangbin
 * @date 2015年5月22日上午11:37:31
 */

public class TargetMarkersFilter {
	/**
	 * 过滤出指定位点名的位点列表
	 * @author jiangbin
	 * @param gene 指纹数据对象
	 * @param markerNames 需要过滤出来的位点名列表(同引物名列表)
	 * @return
	 * @throws ICoreException
	 * @date 2015年5月22日上午11:38:17
	 */
	public Gene filter(final Gene gene, List<String> markerNames) throws ICoreException {
		MarkerMapper mapper = new MarkerMapper(gene.getMarkers());
		List<Marker> markers = mapper.getValues(markerNames);
		if (CollectionUtils.isEmpty(markers)) {
			return null;
		}
		Gene target = ObjectCopier.copy(gene);
		target.setMarkers(markers);
		return target;
	}

	/**
	 * 过滤出指定位点名的位点列表
	 * @author jiangbin
	 * @param genes 指纹列表
	 * @param markerNames 需要过滤出来的位点名列表(同引物名列表)
	 * @return
	 * @throws ICoreException
	 * @date 2015年5月22日上午11:38:33
	 */
	public List<Gene> filter(final List<Gene> genes, List<String> markerNames) throws ICoreException {
		if (CollectionUtils.isEmpty(genes)) {
			return null;
		}
		List<Gene> targets = new ArrayList<>();
		for (Gene gene : genes) {
			Gene target = filter(gene, markerNames);
			if (target != null && !target.isEmpty()) {
				targets.add(target);
			}
		}
		return targets;
	}
}
