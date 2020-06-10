package com.fcgs.core.comparer;

import com.fcgs.core.comparer.csv.GeneResultSetCsvCreator;
import com.fcgs.core.comparer.domain.*;
import com.fcgs.core.comparer.statistics.GeneStatistics;
import com.fcgs.core.comparer.statistics.SmartGeneStatistics;
import com.fcgs.core.comparer.utils.GeneResultSetDiffMerger;
import com.fcgs.core.comparer.utils.GeneSpliter;
import com.fcgs.core.comparer.utils.RepeatComparerResultFilter;
import com.pids.core.checker.number.NumberChecker;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于统计方式的精简指纹比对算法:多线程处理两个指纹列表间两两比对， 结果集采用数组对象进行构建来规避大规模数据结果集造成内存溢出的问题，
 * 本功能允许给定任意的待比和对比对指纹队列， 本功能不会统计差异和无差异位点名称信息
 *
 * @author jiangbin
 * @date 2019/10/31 9:06 上午
 **/

public class SimpleFastGeneComparer implements FastGeneComparer {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	protected MarkerItemComparer markerItemComparer = new SimpleMarkerItemComparer();
	protected GeneResultSetCsvCreator resultCsvCreator = new GeneResultSetCsvCreator();
	protected GeneStatistics geneStatistics = new SmartGeneStatistics();
	protected GeneResultSetDiffMerger geneResultSetDiffMerger = new GeneResultSetDiffMerger();
	protected RepeatComparerResultFilter<SmartComparerResult> repeatComparerResultFilter = new RepeatComparerResultFilter<>();

	@Override
	public List<GeneResultSet> compare(List<StatisticsGeneContainer> containers) {
		if (CollectionUtils.isEmpty(containers)) {
			return null;
		}
		List<GeneResultSet> results = new ArrayList<>();
		for (StatisticsGeneContainer container : containers) {
			GeneResultSet result = this.compare(container);
			results.add(result);
		}
		return results;
	}

	@Override
	public GeneResultSet compare(StatisticsGeneContainer container) {
		if (container != null && !container.valid()) {
			return null;
		}

		StopWatch watch = new StopWatch();
		watch.start();
		try {
			// 对各指纹数据分型进行分组
			MarkerItemContainer miContainer = container.createMarkerItemContainer();

			// 比对各分型间差异
			List<MarkerItemPair> results = compareMarkerItems(miContainer, container.getMaxSizeOffset());

			// 统计差异和无差异位点信息
			return statistics(container, miContainer, results);
		} finally {
			watch.stop();
			log.info("比对[ " + container.getSources().size() + "-" + container.getTargets().size() + " ]个指纹共耗时(ms)==>"
					+ watch.getTime());
		}
	}

	/**
	 * 统计指纹差异和无差异结果信息
	 *
	 * @param container   指纹容器对象
	 * @param miContainer 指纹分型分组映射表
	 * @param results     指纹分型比对结果列表
	 * @return com.viathink.ssr.core4.statistics.comparer.domain.GeneResultDetails
	 * @throws @author jiang
	 * @date 2019/11/9 23:39
	 **/
	protected GeneResultSet statistics(StatisticsGeneContainer container, MarkerItemContainer miContainer,
			List<MarkerItemPair> results) {
		// 统计比对结果
		GeneResultSet result = this.geneStatistics.statistics(container, miContainer, results);

		// 合并差异位点和标记无效数据
		if (miContainer.isSame()) {
			geneResultSetDiffMerger.merge(result, StatisticsContext.getPoolSize());
		}

		return result;
	}

	@Override
	public List<SmartComparerResult> batch(StatisticsGeneContainer params, NumberChecker diffChecker) {
		if (params == null || !params.valid() || diffChecker == null) {
			return null;
		}

		StopWatch counter = new StopWatch();
		counter.start();

		// 对指纹数据分组，以便实现大规模数据比对
		List<StatisticsGeneContainer> containers = GeneSpliter.group(params);
		if (CollectionUtils.isEmpty(containers)) {
			return null;
		}

		log.info("共分成[ " + containers.size() + " ]个指纹数据组进行批量比对!");

		StopWatch watch;
		List<SmartComparerResult> targets = new ArrayList<>();

		for (int index = 0; index < containers.size(); index++) {
			StatisticsGeneContainer container = containers.get(index);

			watch = new StopWatch();
			watch.start();

			// 指纹比对
			GeneResultSet details = this.compare(container);
			if (details == null) {
				continue;
			}

			// 标记无效数据
			details.invalid();

			// 保存分组的比对结果到csv文件中
			List<SmartComparerResult> results = details.getSmartComparerResult(diffChecker,
					StatisticsContext.getPoolSize());

			watch.stop();
			log.info("第" + (index + 1) + "个分组指纹产生" + ListUtils.size(results) + "个比对结果耗时(ms)===>" + watch.getTime());

			if (CollectionUtils.isNotEmpty(results)) {
				targets.addAll(results);
			}
		}

		// 过滤重复结果
		targets = this.repeatComparerResultFilter.filter(targets);

		counter.stop();
		log.info("全部线程的两两互比" + ListUtils.size(params.getSources()) + "-" + ListUtils.size(params.getTargets())
				+ "个指纹产生[ " + ListUtils.size(targets) + " ]个比对结果,共耗时(ms)===>" + counter.getTime());
		return targets;
	}

	@Override
	public StatisticsResult batch(StatisticsContainer params) {
		if (params == null || !params.valid() || StringUtils.isBlank(params.getResultFolder())) {
			return null;
		}

		StopWatch counter = new StopWatch();
		counter.start();

		// 对指纹数据分组，以便实现大规模数据比对
		List<StatisticsContainer> containers = params.split(StatisticsContext.getBatchGroupSize());
		if (CollectionUtils.isEmpty(containers)) {
			return null;
		}

		log.info("共分成[ " + containers.size() + " ]个指纹数据组进行批量比对!");

		NumberChecker diffChecker = params.getDiffChecker();

		StopWatch watch;
		StatisticsResult result = new SimpleStatisticsResult();

		for (int index = 0; index < containers.size(); index++) {
			StatisticsGeneContainer container = containers.get(index).getContainer();

			// 比对结果文件存储路径
			String csvPath = getResultFilePath(params, index);
			result.addResultFile(csvPath);

			watch = new StopWatch();
			watch.start();

			// 指纹比对
			GeneResultSet details = this.compare(container);

			// 标记无效数据
			details.invalid();

			// 标记出符合要求的指纹数据
			if (diffChecker != null) {
				int count = details.markDiff(diffChecker);
				result.addDiff(count);
				log.info("第" + (index + 1) + "个分组指纹比对共有[ " + count + " ]个符合要求的结果!");
			}

			// 保存分组的比对结果到csv文件中
			this.resultCsvCreator.create(details, csvPath);

			watch.stop();
			log.info("第" + (index + 1) + "个分组指纹比对耗时(ms)===>" + watch.getTime());
		}

		counter.stop();
		if (diffChecker != null) {
			log.info("全部线程的两两互比" + params.getSourceGeneCount() + "-" + params.getTargetGeneCount() + "个指纹产生[ "
					+ result.getDiffCount() + " ]个比对结果,共耗时(ms)===>" + counter.getTime());
		} else {
			log.info("全部线程的两两互比" + params.getSourceGeneCount() + "-" + params.getTargetGeneCount() + "个指纹共耗时(ms)===>"
					+ counter.getTime());
		}
		return result;
	}

	/**
	 * 获取比对结果文件存储路径
	 *
	 * @param params
	 * @param index
	 * @return java.lang.String
	 * @author jiangbin
	 * @date 2019/11/7 5:14 下午
	 **/
	private String getResultFilePath(StatisticsContainer params, int index) {
		return String.format("%s%s.csv", params.getResultFolder(), index + 1);
	}

	/**
	 * 比对各引物的位点分型间差异信息
	 *
	 * @param container
	 * @param maxSizeOffset
	 * @return java.util.List<com.viathink.gene.comparer.domain.MarkerItemPair>
	 * @throws @author jiang
	 * @date 2019/11/3 20:16
	 **/
	protected List<MarkerItemPair> compareMarkerItems(MarkerItemContainer container, int maxSizeOffset) {
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			return this.markerItemComparer.compare(container, maxSizeOffset);
		} finally {
			watch.stop();
			log.info("各位点分型间比对耗时(ms)==>" + watch.getTime());
		}
	}

}
