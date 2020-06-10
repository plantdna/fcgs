package com.fcgs.core.comparer.statistics;

import com.fcgs.core.comparer.domain.*;
import com.fcgs.core.comparer.thread.FastStatisticsMarkerThread;
import com.fcgs.core.comparer.utils.DiffMarkerItemPairFilter;
import com.pids.core.pools.ThreadPoolContainer;
import com.pids.core.spliter.impl.SpliterUtils;
import com.pids.core.utils.ListUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 统计指纹比对结果
 *
 * @author jiang
 * @date 2019-11-09 23:01
 */

public class SmartGeneStatistics implements GeneStatistics {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    private DiffMarkerItemPairFilter diffMarkerItemPairFilter = new DiffMarkerItemPairFilter();

    @Override
    public GeneResultSet statistics(StatisticsGeneContainer container, MarkerItemContainer miContainer, List<MarkerItemPair> results) {
        StopWatch watch = new StopWatch();
        watch.start();

        //创建比对结果信息对象
        FastGeneResultSet details = container.createFastResult();

        //过滤出全部差异的位点分型比对结果
        results = this.diffMarkerItemPairFilter.filter(results);

        //统计差异位点信息
        List<List<MarkerItemPair>> groups = SpliterUtils.split(results, StatisticsContext.getGroupSize());

        log.info("分" + ListUtils.size(groups) + "个线程进行快速模式的指纹差异位点统计!");

        //构建线程池和线程计数器
        ExecutorService service = ThreadPoolContainer.create( StatisticsContext.getPoolSize());
        CountDownLatch latch = new CountDownLatch(ListUtils.size(groups));

        try {
            //统计差异位点信息
            for (List<MarkerItemPair> group : groups) {
                FastStatisticsMarkerThread thread = new FastStatisticsMarkerThread();
                thread.setDetails(details);
                thread.setResults(group);
                thread.setLatch(latch);
                thread.setContainer(container);
                service.submit(thread);
            }

            //等待各线程执行完毕
            latch.await();
        } catch (Exception e) {
        } finally {
            service.shutdown();//关闭线程池
        }

        watch.stop();
        log.info("快速模式-完成指纹数据差异位点信息统计耗时(ms)==>" + watch.getTime());

        return details;
    }

}
