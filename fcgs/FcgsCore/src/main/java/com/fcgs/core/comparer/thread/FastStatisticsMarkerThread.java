package com.fcgs.core.comparer.thread;

import com.fcgs.core.comparer.domain.FastGeneResultSet;
import com.fcgs.core.comparer.domain.MarkerItemPair;
import com.fcgs.core.comparer.domain.StatisticsGeneContainer;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 快速模式下只统计差异位点线程对象，不带位点名称的统计
 *
 * @author jiangbin
 * @date 2019/11/1 12:49 下午
 **/

public class FastStatisticsMarkerThread implements Runnable {
    private List<MarkerItemPair> results;
    private FastGeneResultSet details;
    private CountDownLatch latch;
    private StatisticsGeneContainer container;

    @Override
    public void run() {
        try {
            if (CollectionUtils.isEmpty(results) || details == null) {
                return;
            }

            //差异位点信息统计
            for (MarkerItemPair pair : results) {
                statistics(pair);
            }
        } finally {
            latch.countDown();
        }
    }

    /**
     * 统计差异位点数据信息
     *
     * @param pair
     * @return void
     * @author jiangbin
     * @date 2019/11/4 5:00 下午
     **/
    protected void statistics(MarkerItemPair pair) {
        //统计差异位点
        this.statistics(pair.getSource().getGeneIdList(), pair.getTarget().getGeneIdList());
    }

    /**
     * 统计差异位点数据
     *
     * @param sGeneIdList 待比指纹ID列表
     * @param tGeneIdList 对比指纹ID列表
     * @return void
     * @author jiangbin
     * @date 2019/11/19 4:44 下午
     **/
    protected void statistics(List<String> sGeneIdList, List<String> tGeneIdList) {
        if (CollectionUtils.isEmpty(sGeneIdList) || CollectionUtils.isEmpty(tGeneIdList)) {
            return;
        }
        for (String sGeneId : sGeneIdList) {
            for (String tGeneId : tGeneIdList) {
                int row = details.getsPools().getIndex(sGeneId);
                int col = details.gettPools().getIndex(tGeneId);

                //差异位点计数
                details.addDiff(row, col, 1);
            }
        }
    }

    /**
     * 获取位点分型结果列表
     *
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemPair>
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public List<MarkerItemPair> getResults() {
        return results;
    }

    /**
     * 设置位点分型结果列表
     *
     * @param results
     * @return void
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public void setResults(List<MarkerItemPair> results) {
        this.results = results;
    }

    /**
     * 获取指纹比对结果集
     *
     * @return com.viathink.ssr.core4.statistics.comparer.domain.FastGeneResultSet
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public FastGeneResultSet getDetails() {
        return details;
    }

    /**
     * 设置指纹比对结果集
     *
     * @param details
     * @return void
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public void setDetails(FastGeneResultSet details) {
        this.details = details;
    }

    /**
     * 获取线程计数器
     *
     * @return java.util.concurrent.CountDownLatch
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public CountDownLatch getLatch() {
        return latch;
    }

    /**
     * 设置线程计数器
     *
     * @param latch
     * @return void
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 获取指纹容器对象
     *
     * @return com.viathink.ssr.core4.statistics.comparer.domain.GeneContainer
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public StatisticsGeneContainer getContainer() {
        return container;
    }

    /**
     * 设置指纹容器对象
     *
     * @param container
     * @return void
     * @author jiangbin
     * @date 2019/11/21 9:02 上午
     **/
    public void setContainer(StatisticsGeneContainer container) {
        this.container = container;
    }

}
