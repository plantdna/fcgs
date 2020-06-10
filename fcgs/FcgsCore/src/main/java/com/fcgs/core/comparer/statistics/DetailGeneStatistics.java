package com.fcgs.core.comparer.statistics;

import com.fcgs.core.comparer.domain.*;
import com.fcgs.core.comparer.thread.DetailStatisticsMarkerThread;
import com.fcgs.core.comparer.utils.SmartComparerResultGroupMapper;
import com.pids.core.pools.ThreadPoolContainer;
import com.pids.core.spliter.impl.SpliterUtils;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 详细模式统计指纹比对结果
 *
 * @author jiang
 * @date 2019-11-09 23:01
 */

public class DetailGeneStatistics {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 统计差异和无差异位点数以及位点名列表
	 *
	 * @param container
	 * @param miContainer
	 * @param results
	 * @return com.viathink.ssr.core4.statistics.comparer.domain.DetailGeneResultSet
	 * @author jiangbin
	 * @date 2019/11/22 3:43 下午
	 **/
	public DetailGeneResultSet statistics(StatisticsGeneContainer container, MarkerItemContainer miContainer,
			List<MarkerItemPair> results) {
		StopWatch watch = new StopWatch();
		watch.start();

		// 创建比对结果信息对象
		DetailGeneResultSet details = container.createDetailResult(miContainer.getPrimerNames());

		// 统计差异位点信息
		List<List<MarkerItemPair>> groups = SpliterUtils.split(results, StatisticsContext.getGroupSize());

		log.info("分" + ListUtils.size(groups) + "个线程进行详细模式的指纹差异位点统计!");

		// 构建线程池和线程计数器
		ExecutorService service = ThreadPoolContainer.create(StatisticsContext.getPoolSize());
		CountDownLatch latch = new CountDownLatch(ListUtils.size(groups));

		try {
			// 统计差异位点信息
			for (List<MarkerItemPair> group : groups) {
				DetailStatisticsMarkerThread thread = new DetailStatisticsMarkerThread();
				thread.setDetails(details);
				thread.setResults(group);
				thread.setLatch(latch);
				thread.setContainer(container);
				service.submit(thread);
			}

			// 等待各线程执行完毕
			latch.await();
		} catch (Exception e) {
		} finally {
			service.shutdown();// 关闭线程池
		}

		watch.stop();
		log.info("详细模式-完成指纹数据差异和无差异位点信息统计耗时(ms)==>" + watch.getTime());

		return details;
	}

	/**
	 * 统计差异和无差异位点数以及位点名列表
	 *
	 * @param container
	 * @param miContainer
	 * @param results
	 * @param sResults
	 * @return com.viathink.ssr.core4.statistics.comparer.domain.DetailGeneResultSet
	 * @author jiangbin
	 * @date 2019/11/22 3:43 下午
	 **/
	public DetailGeneResultSet statistics(StatisticsGeneContainer container, MarkerItemContainer miContainer,
			List<MarkerItemPair> results, List<SmartComparerResult> sResults) {
		StopWatch watch = new StopWatch();
		watch.start();

		// 获取引物名称列表
		List<String> primerNames = container.getPrimerNames();
		if (CollectionUtils.isEmpty(primerNames)) {
			primerNames = miContainer.getPrimerNames();
		}

		// 获取快速比对结果指纹ID对分组映射表
		SmartComparerResultGroupMapper mapper = new SmartComparerResultGroupMapper();
		mapper.addAll(sResults);

		// 创建比对结果信息对象
		DetailGeneResultSet details = container.createDetailResult(primerNames, mapper);

		// 统计差异位点信息
		List<List<MarkerItemPair>> groups = SpliterUtils.split(results, StatisticsContext.getGroupSize());

		log.info("分" + ListUtils.size(groups) + "个线程进行详细模式的指纹差异位点统计!");

		// 构建线程池和线程计数器
		ExecutorService service = ThreadPoolContainer.create(StatisticsContext.getPoolSize());
		CountDownLatch latch = new CountDownLatch(ListUtils.size(groups));

		try {
			// 统计差异位点信息
			for (List<MarkerItemPair> group : groups) {
				DetailStatisticsMarkerThread thread = new DetailStatisticsMarkerThread();
				thread.setDetails(details);
				thread.setResults(group);
				thread.setLatch(latch);
				thread.setContainer(container);
				service.submit(thread);
			}

			// 等待各线程执行完毕
			latch.await();
		} catch (Exception e) {
		} finally {
			service.shutdown();// 关闭线程池
		}

		watch.stop();
		log.info("详细模式-完成指纹数据差异和无差异位点信息统计耗时(ms)==>" + watch.getTime());

		return details;
	}

}
