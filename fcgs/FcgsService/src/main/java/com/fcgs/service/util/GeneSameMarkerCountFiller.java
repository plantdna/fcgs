package com.fcgs.service.util;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.GeneInfoMapper;
import com.fcgs.core.comparer.domain.SmartComparerResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 填充指纹数据无差异位点数,本功能要求必需填充总位点数、差异位点数和缺失位点列表，
 * 以便于通过公式进行计算:
 *  无差异位点数=总位点数-差异位点数-缺失位点数
 * </pre>
 *
 * @author jiangbin
 * @date 2020/1/5 2:37 下午
 **/

public class GeneSameMarkerCountFiller {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 填充无差异位点数
	 *
	 * @param results 快速比对结果列表
	 * @param sgenes  源指纹列表
	 * @param tgenes  对比指纹列表
	 * @return void
	 * @author jiangbin
	 * @date 2020/1/5 2:43 下午
	 **/
	public void fill(List<SmartComparerResult> results, List<Gene> sgenes, List<Gene> tgenes) {
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			GeneInfoMapper smapper = new GeneInfoMapper();
			smapper.addAll(sgenes);
			GeneInfoMapper tmapper = new GeneInfoMapper();
			if (sgenes != tgenes) {
				tmapper.addAll(tgenes);
			} else {
				tmapper = smapper;
			}

			this.fill(results, smapper, tmapper);
		} finally {
			watch.stop();
			log.info("填充无差异位点数信息耗时(ms)==>" + watch.getTime());
		}
	}

	/**
	 * 填充无差异位点数
	 *
	 * @param results
	 * @param smapper
	 * @param tmapper
	 * @return void
	 * @author jiangbin
	 * @date 2020/1/7 2:07 下午
	 **/
	public void fill(List<SmartComparerResult> results, GeneInfoMapper smapper, GeneInfoMapper tmapper) {
		StopWatch watch = new StopWatch();
		watch.start();

		for (SmartComparerResult result : results) {
			Gene sgene = smapper.get(result.getSourceGeneId());
			Gene tgene = tmapper.get(result.getTargetGeneId());

			// 差异位点数
			int diffCount = result.getDiffCount();

			// 总位点数
			int allCount = sgene.getMarkerCount();

			// 缺失位点数
			Set<String> missMarkers = new HashSet<>();
			if (CollectionUtils.isNotEmpty(sgene.getMissMarkers())) {
				missMarkers.addAll(sgene.getMissMarkers());
			}
			if (CollectionUtils.isNotEmpty(tgene.getMissMarkers())) {
				missMarkers.addAll(tgene.getMissMarkers());
			}

			// 无差异位点数=总位点数-缺失位点数-差异位点数
			result.setSameCount(allCount - missMarkers.size() - diffCount);
		}

		watch.stop();
	}

}
