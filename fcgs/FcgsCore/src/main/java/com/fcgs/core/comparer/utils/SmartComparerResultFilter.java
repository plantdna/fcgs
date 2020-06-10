package com.fcgs.core.comparer.utils;

import com.fcgs.core.comparer.domain.FastGeneResultSet;
import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.fcgs.core.comparer.thread.SmartComparerResultFilterThread;
import com.pids.core.checker.number.NumberChecker;
import com.pids.core.exception.ExceptionUtils;
import com.pids.core.exception.ICoreException;
import com.pids.core.pools.ThreadPoolContainer;
import com.pids.core.spliter.impl.IntNumberSpliter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 快速比对结果过滤器
 *
 * @author jiangbin
 * @date 2019/11/26 4:34 下午
 **/

public class SmartComparerResultFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private IntNumberSpliter spliter = new IntNumberSpliter();

	/**
	 * 过滤快速比对结果列表
	 *
	 * @param result      快速比对结果集
	 * @param diffChecker 差异位点检查器
	 * @param poolSize    缓存池数
	 * @return java.util.List<com.viathink.ssr.core.comparer.domain.SmartComparerResult>
	 * @author jiangbin
	 * @date 2019/11/26 4:52 下午
	 **/
	public List<SmartComparerResult> filter(FastGeneResultSet result, NumberChecker diffChecker, int poolSize)
			throws ICoreException {
		StopWatch watch = new StopWatch();
		watch.start();

		List<Integer> groups = spliter.split(result.getCol(), 500);
		int end = groups.size() - 1;
		CountDownLatch latch = new CountDownLatch(end);
		ExecutorService service = ThreadPoolContainer.create(poolSize);

		try {
			// 创建线程列表
			List<SmartComparerResultFilterThread> threads = new ArrayList<>();
			for (int i = 0; i < end; i++) {
				SmartComparerResultFilterThread thread = new SmartComparerResultFilterThread();
				thread.setsCol(groups.get(i));
				thread.settCol(groups.get(i + 1));
				thread.setLatch(latch);
				thread.setResult(result);
				thread.setChecker(diffChecker);
				threads.add(thread);
			}

			// 调用线程
			service.invokeAll(threads);

			// 等待线程执行完毕
			latch.await();

			return filterResults(threads);
		} catch (Exception e) {
			log.error("过滤快速比对结果列表失败==>" + ExceptionUtils.getStackTrace(e));
		} finally {
			service.shutdown();
			watch.stop();
			log.info("获取快速指纹比对数据耗时(ms)==>" + watch.getTime());
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
	private List<SmartComparerResult> filterResults(List<SmartComparerResultFilterThread> threads) {
		List<SmartComparerResult> results = new ArrayList<>();
		for (SmartComparerResultFilterThread thread : threads) {
			if (CollectionUtils.isNotEmpty(thread.getTargets())) {
				results.addAll(thread.getTargets());
			}
		}
		return results;
	}
}
