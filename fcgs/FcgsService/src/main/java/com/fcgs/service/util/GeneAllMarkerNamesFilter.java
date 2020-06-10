package com.fcgs.service.util;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 过滤出全部的指纹位点名称列表
 *
 * @author jiangbin
 * @date 2020/1/5 2:37 下午
 **/

public class GeneAllMarkerNamesFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 过滤出全部位点名称列表
	 *
	 * @param genes
	 * @return java.util.List<java.lang.String>
	 * @author jiangbin
	 * @date 2020/1/5 6:06 下午
	 **/
	public List<String> filter(List<Gene> genes) {
		if (CollectionUtils.isEmpty(genes)) {
			return null;
		}
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			Set<String> markerNames = new HashSet<>();
			for (Gene gene : genes) {
				if (gene == null || gene.isEmpty()) {
					continue;
				}
				List<Marker> markers = gene.getMarkers();
				for (Marker marker : markers) {
					if (StringUtils.isNotBlank(marker.getPrimerName())) {
						markerNames.add(marker.getPrimerName());
					}
				}
			}
			return new ArrayList<>(markerNames);
		} finally {
			watch.stop();
			log.info("过滤全部指纹位点列表耗时(ms)==>" + watch.getTime());
		}
	}

}
