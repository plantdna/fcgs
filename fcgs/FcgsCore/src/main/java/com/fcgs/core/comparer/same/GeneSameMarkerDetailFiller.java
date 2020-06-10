package com.fcgs.core.comparer.same;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.GeneInfoMapper;
import com.fcgs.core.comparer.domain.DetailComparerResult;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 填充指纹数据无差异位点数和无差异位点列表,本功能要求必需填充总位点数、差异位点数和缺失位点列表，
 * 以便于通过公式进行计算:
 *  无差异位点数=总位点数-差异位点数-缺失位点数
 * </pre>
 *
 * @author jiangbin
 * @date 2020/1/5 2:37 下午
 **/

public class GeneSameMarkerDetailFiller {
	private static Logger log = LoggerFactory.getLogger(GeneSameMarkerDetailFiller.class);

	/**
	 * 填充无差异位点数
	 *
	 * @param results    比对结果列表
	 * @param allMarkers 作为基准的引物组位点列表
	 * @param sgenes     待比指纹列表
	 * @param tgenes     对比指纹列表
	 * @return void
	 * @author jiangbin
	 * @date 2020/1/5 2:43 下午
	 **/
	public void fill(List<DetailComparerResult> results, List<String> allMarkers, List<Gene> sgenes,
			List<Gene> tgenes) {
		if (CollectionUtils.isEmpty(results) || CollectionUtils.isEmpty(sgenes) || CollectionUtils.isEmpty(tgenes)
				|| CollectionUtils.isEmpty(allMarkers)) {
			return;
		}

		StopWatch watch = new StopWatch();
		watch.start();

		GeneInfoMapper smapper = new GeneInfoMapper();
		smapper.addAll(sgenes);
		GeneInfoMapper tmapper = new GeneInfoMapper();
		tmapper.addAll(tgenes);

		this.fill(results, allMarkers, smapper, tmapper);

		watch.stop();
		log.info("填充无差异位点数耗时(ms)==>" + watch.getTime());
	}

	/**
	 * 填充无差异位点数
	 *
	 * @param results    比对结果列表
	 * @param allMarkers 作为基准的引物组位点列表
	 * @param smapper    待比指纹映射表
	 * @param tmapper    对比指纹映射表
	 * @return void
	 * @author jiangbin
	 * @date 2020/1/7 2:07 下午
	 **/
	public void fill(List<DetailComparerResult> results, List<String> allMarkers, GeneInfoMapper smapper,
			GeneInfoMapper tmapper) {
		if (CollectionUtils.isEmpty(results) || smapper == null || smapper.isEmpty() || tmapper == null
				|| tmapper.isEmpty() || CollectionUtils.isEmpty(allMarkers)) {
			return;
		}

		StopWatch watch = new StopWatch();
		watch.start();

		// 基准位总点数
		int markerCount = ListUtils.size(allMarkers);

		for (DetailComparerResult result : results) {
			Gene sgene = smapper.get(result.getSourceGeneId());
			Gene tgene = tmapper.get(result.getTargetGeneId());
			if (sgene == null || sgene.isEmpty() || tgene == null || tgene.isEmpty()) {
				continue;
			}

			// 两个指纹的缺失位点并集
			Set<String> missMarkers = new HashSet<>();
			if (CollectionUtils.isNotEmpty(sgene.getMissMarkers())) {
				missMarkers.addAll(sgene.getMissMarkers());
			}
			if (CollectionUtils.isNotEmpty(tgene.getMissMarkers())) {
				missMarkers.addAll(tgene.getMissMarkers());
			}

			// 设置缺失位点列表
			result.setMissMarkerNames(new ArrayList<>(missMarkers));
			result.setMissMarkerCount(ListUtils.size(missMarkers));

			// 无差异位点数=基准总位点数-缺失位点数-差异位点数
			Set<String> noDiffs = new HashSet<>();
			noDiffs.addAll(allMarkers);
			noDiffs.removeAll(missMarkers);
			noDiffs.removeAll(result.getDiffMarkerNames());

			result.setNoDiffMarkerNames(new ArrayList<>(noDiffs));
			result.setNoDiffMarkerCount(ListUtils.size(noDiffs));

			// 参与比对的有效数据位点数:基准总位点数-缺失位点数
			result.setMarkerCount(markerCount - ListUtils.size(missMarkers));
		}

		watch.stop();
		log.info("填充无差异位点数耗时(ms)==>" + watch.getTime());
	}

}
