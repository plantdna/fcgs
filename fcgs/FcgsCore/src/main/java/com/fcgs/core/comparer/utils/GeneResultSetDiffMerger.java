package com.fcgs.core.comparer.utils;

import com.fcgs.core.comparer.domain.GeneResultSet;
import com.fcgs.core.comparer.thread.ResultSetDiffMergeThread;
import com.pids.core.pools.ThreadPoolContainer;
import com.pids.core.spliter.impl.IntNumberSpliter;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 多线程方式的差异位点数据合并
 *
 * @author jiangbin
 * @date 2019/11/21 12:10 下午
 **/

public class GeneResultSetDiffMerger {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private IntNumberSpliter spliter = new IntNumberSpliter();

	/**
	 * 合并差异位点数据
	 *
	 * @param result
	 * @param poolSize
	 * @return void
	 * @author jiangbin
	 * @date 2019/11/21 12:18 下午
	 **/
	public void merge(GeneResultSet result, int poolSize) {
		StopWatch watch = new StopWatch();
		watch.start();

		List<Integer> groups = spliter.split(result.getCol(), 500);
		int end = groups.size() - 1;
		CountDownLatch latch = new CountDownLatch(end);
		ExecutorService service = ThreadPoolContainer.create(poolSize);
		try {

			for (int i = 0; i < end; i++) {
				ResultSetDiffMergeThread thread = new ResultSetDiffMergeThread();
				thread.setsCol(groups.get(i));
				thread.settCol(groups.get(i + 1));
				thread.setLatch(latch);
				thread.setResult(result);
				service.submit(thread);
			}

			latch.await();
		} catch (Exception e) {
		} finally {
			service.shutdown();
		}

		watch.stop();
		log.info("合并差异位点数据耗时(ms)==>" + watch.getTime());
	}

}
