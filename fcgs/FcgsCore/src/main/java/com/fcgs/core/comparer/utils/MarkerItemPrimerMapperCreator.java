package com.fcgs.core.comparer.utils;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.MarkerGroupMapper;
import com.fcgs.core.comparer.domain.StatisticsContext;
import com.fcgs.core.comparer.mapper.MarkerItemPrimerMapper;
import com.fcgs.core.comparer.thread.MarkerItemMapperThread;
import com.fcgs.i18n.FcgsCoreI18N;
import com.pids.core.exception.ICoreException;
import com.pids.core.pools.ThreadPoolContainer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 基于多线程方式的位点分型映射器创建器
 * 
 * @author jiangbin
 * @date 2019/11/20 5:33 下午
 **/

public class MarkerItemPrimerMapperCreator {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SnpMarkerItemPrimerMapperCreator snpMarkerItemPrimerMapperCreator = new SnpMarkerItemPrimerMapperCreator();

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
			throw new ICoreException(FcgsCoreI18N.SSR_MARKERITEMPRIMERMAPPERCREATOR_ERROR01.get());
		}

		if (StatisticsContext.isSsr()) {
			return ssr(markers);
		} else {
			return snp(markers);
		}
	}

	/**
	 * SNP模式下采用对应的分组对象，以优化性能
	 * 
	 * @param markers
	 * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemPrimerMapper
	 * @author jiangbin
	 * @date 2019/11/29 2:59 下午
	 **/
	private MarkerItemPrimerMapper snp(List<Marker> markers) {
		return snpMarkerItemPrimerMapperCreator.create(markers);
	}

	/**
	 * SSR模式下采用对应的分组对象，以优化性能
	 * 
	 * @param markers
	 * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemPrimerMapper
	 * @author jiangbin
	 * @date 2019/11/29 3:00 下午
	 **/
	private MarkerItemPrimerMapper ssr(List<Marker> markers) {
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
			throw new ICoreException(FcgsCoreI18N.SSR_MARKERITEMPRIMERMAPPERCREATOR_ERROR02.get());
		}

		log.info("将创建" + primerNames.size() + "个线程执行按分型进行位点分组!");

		watch1 = new StopWatch();
		watch1.start();

		// 线程计数器
		CountDownLatch latch = new CountDownLatch(primerNames.size());

		// 创建线程池
		ExecutorService service = ThreadPoolContainer.create(StatisticsContext.getPoolSize());

		// 创建并执行线程列表
		List<MarkerItemMapperThread> threads = new ArrayList<>();
		for (String primerName : primerNames) {
			MarkerItemMapperThread thread = new MarkerItemMapperThread();
			thread.setMarkers(groups.get(primerName));
			thread.setLatch(latch);
			thread.setPrimerName(primerName);
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
	 * 创建引物与位点分型分组映射表
	 * 
	 * @param threads
	 * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemPrimerMapper
	 * @author jiangbin
	 * @date 2019/11/29 2:11 下午
	 **/
	private MarkerItemPrimerMapper createMarkerItemPrimerMapper(List<MarkerItemMapperThread> threads) {
		MarkerItemPrimerMapper mapper = new MarkerItemPrimerMapper();
		for (MarkerItemMapperThread thread : threads) {
			mapper.add(thread.getPrimerName(), thread.getMapper());
		}
		return mapper;
	}

}
