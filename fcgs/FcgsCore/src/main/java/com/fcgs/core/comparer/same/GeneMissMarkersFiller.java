package com.fcgs.core.comparer.same;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.utils.StringMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 填充指纹数据缺失位点列表
 *
 * @author jiangbin
 * @date 2020/1/5 2:37 下午
 **/

public class GeneMissMarkersFiller {
	private static Logger log = LoggerFactory.getLogger(GeneMissMarkersFiller.class);

	/**
	 * 填充缺失位点名列表
	 *
	 * @param genes      指纹列表
	 * @param allMarkers 引物组中包含的引物列表
	 * @return void
	 * @author jiangbin
	 * @date 2020/1/5 8:30 下午
	 **/
	public void fill(List<Gene> genes, List<String> allMarkers) {
		if (CollectionUtils.isEmpty(genes) || CollectionUtils.isEmpty(allMarkers)) {
			return;
		}
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			int markerCount = allMarkers.size();
			for (Gene gene : genes) {
				gene.setMarkerCount(markerCount);
				if (gene == null || gene.isEmpty()) {
					continue;
				}
				StringMapper mapper = new StringMapper();
				mapper.add(allMarkers);
				mapper.delete(gene.getMarkerNames());
				gene.setMissMarkers(mapper.getKeys());
			}
		} finally {
			watch.stop();
			log.info("填充缺失位点列表耗时(ms)==>" + watch.getTime());
		}
	}
}
