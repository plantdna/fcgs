package com.fcgs.core.comparer.utils;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.MarkerGroupMapper;
import com.fcgs.core.comparer.domain.StatisticsContext;
import com.fcgs.core.comparer.mapper.MarkerItemMapper;
import com.fcgs.core.comparer.mapper.MarkerItemPrimerMapper;
import com.fcgs.core.comparer.thread.FastMarkerItemMapperThread;
import com.fcgs.i18n.FcgsCoreI18N;
import com.pids.core.exception.ICoreException;
import com.pids.core.pools.ThreadPoolContainer;
import com.pids.core.spliter.impl.SpliterUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * SNP基于多线程方式的位点分型映射器创建器，针对SNP的位点数较多的情况进行了优化
 * 
 * @author jiangbin
 * @date 2019/11/20 5:33 下午
 **/

public class SnpMarkerItemPrimerMapperCreator {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 位点分型映射表创建器
	 * 
	 * @param markers 位点列表
	 * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemPrimerMapper
	 * @author jiangbin
	 * @date 2019/11/20 5:44 下午
	 **/
	public MarkerItemPrimerMapper create(List<Marker> markers) throws ICoreException {
		if (CollectionUtils.isEmpty(markers)) {
			throw new ICoreException(FcgsCoreI18N.SSR_SNPMARKERITEMPRIMERMAPPERCREATOR_ERROR01.get());
		}

		StopWatch watch = new StopWatch();
		watch.start();

		StopWatch watch1 = new StopWatch();
		watch1.start();

		// 按引物名称分组
		MarkerGroupMapper groups = new MarkerGroupMapper();
		groups.addAll(markers);

		watch1.stop();
		log.info(markers.size() + "个位点按名称分组耗时(ms)==>" + watch1.getTime());

		// 全部引物名列表
		List<String> primerNames = groups.getKeys();
		if (CollectionUtils.isEmpty(primerNames)) {
			throw new ICoreException(FcgsCoreI18N.SSR_SNPMARKERITEMPRIMERMAPPERCREATOR_ERROR02.get());
		}

		watch1 = new StopWatch();
		watch1.start();

		List<List<String>> prGroups = SpliterUtils.split(primerNames, StatisticsContext.getGroupSize());

		log.info("将创建" + prGroups.size() + "个线程执行按分型进行位点分组!");

		// 线程计数器
		CountDownLatch latch = new CountDownLatch(prGroups.size());

		// 创建线程池
		ExecutorService service = ThreadPoolContainer.create(StatisticsContext.getPoolSize());

		// 创建并执行线程列表
		List<FastMarkerItemMapperThread> threads = new ArrayList<>();
		for (List<String> prGroup : prGroups) {
			FastMarkerItemMapperThread thread = new FastMarkerItemMapperThread();
			thread.setMarkers(groups.getValues(prGroup));
			thread.setLatch(latch);
			threads.add(thread);
			service.submit(thread);
		}

		// 等待各线程全部执行完成
		try {
			latch.await();
		} catch (InterruptedException e) {
		} finally {
			service.shutdown();
		}

		watch1.stop();
		log.info("多线程构建各位点的分型映射表耗时(ms)==>" + watch1.getTime());

		watch1 = new StopWatch();
		watch1.start();

		// 汇总各位点的分型数据映射表
		MarkerItemPrimerMapper mapper = createMarkerItemPrimerMapper(threads);

		watch1.stop();
		log.info("合并各位点的分型映射表耗时(ms)==>" + watch1.getTime());

		watch.stop();
		log.info("对" + markers.size() + "个位点进行位点分型耗时(ms)==>" + watch.getTime());
		return mapper;
	}

	/**
	 * 创建引物与位点分型映射表
	 * 
	 * @param threads
	 * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemPrimerMapper
	 * @author jiangbin
	 * @date 2019/11/29 1:41 下午
	 **/
	private MarkerItemPrimerMapper createMarkerItemPrimerMapper(List<FastMarkerItemMapperThread> threads) {
		MarkerItemPrimerMapper mapper = new MarkerItemPrimerMapper();
		for (FastMarkerItemMapperThread thread : threads) {
			List<MarkerItemMapper> mappers = thread.getMappers();
			for (MarkerItemMapper tmpMapper : mappers) {
				mapper.add(tmpMapper.getPrimerName(), tmpMapper);
			}
		}
		return mapper;
	}

}
