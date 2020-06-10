package com.fcgs.core.comparer.utils;

import com.fcgs.core.comparer.domain.DetailComparerResult;
import com.fcgs.core.comparer.domain.DetailGeneResultSet;
import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.fcgs.core.comparer.domain.StatisticsContext;
import com.fcgs.core.comparer.thread.DetailComparerResultFilterThread;
import com.pids.core.exception.ExceptionUtils;
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
 * 详细指纹比对结果过滤器，支持多线程提高性能
 *
 * @author jiangbin
 * @date 2019/11/26 5:34 下午
 **/

public class DetailComparerResultFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 过滤快速比对结果列表
	 *
	 * @param result   详细比对结果集
	 * @param sources  快速比对结果列表
	 * @param poolSize 缓存池数
	 * @return java.util.List<com.viathink.ssr.core.comparer.domain.SmartComparerResult>
	 * @author jiangbin
	 * @date 2019/11/26 4:52 下午
	 **/
	public List<DetailComparerResult> filter(DetailGeneResultSet result, List<SmartComparerResult> sources,
			int poolSize) throws ICoreException {
		StopWatch watch = new StopWatch();
		watch.start();

		List<List<SmartComparerResult>> groups = SpliterUtils.split(sources, StatisticsContext.getGroupSize());
		CountDownLatch latch = new CountDownLatch(groups.size());
		ExecutorService service = ThreadPoolContainer.create(poolSize);

		try {
			// 创建线程列表
			List<DetailComparerResultFilterThread> threads = new ArrayList<>();
			for (List<SmartComparerResult> group : groups) {
				DetailComparerResultFilterThread thread = new DetailComparerResultFilterThread();
				thread.setLatch(latch);
				thread.setResult(result);
				thread.setSources(group);
				threads.add(thread);
			}

			// 调用线程
			service.invokeAll(threads);

			// 等待线程执行完毕
			latch.await();

			return filterResults(threads);
		} catch (Exception e) {
			log.error("过滤详细比对结果列表失败==>" + ExceptionUtils.getStackTrace(e));
		} finally {
			service.shutdown();
			watch.stop();
			log.info("获取详细指纹比对数据耗时(ms)==>" + watch.getTime());
		}

		return null;
	}

	/**
	 * 过滤出线程过滤出的结果列表
	 *
	 * @param threads
	 * @return java.util.List<com.viathink.ssr.core.comparer.domain.SmartComparerResult>
	 * @author jiangbin
	 * @date 2019/11/26 4:51 下午
	 **/
	private List<DetailComparerResult> filterResults(List<DetailComparerResultFilterThread> threads) {
		List<DetailComparerResult> results = new ArrayList<>();
		for (DetailComparerResultFilterThread thread : threads) {
			if (CollectionUtils.isNotEmpty(thread.getTargets())) {
				results.addAll(thread.getTargets());
			}
		}
		return results;
	}
}
