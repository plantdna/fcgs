package com.fcgs.core.comparer.thread;

import com.fcgs.core.comparer.domain.DetailGeneResultSet;
import com.fcgs.core.comparer.domain.MarkerItemPair;
import com.fcgs.core.comparer.domain.StatisticsGeneContainer;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 详细模式下只统计差异位点线程对象，不带位点名称的统计
 *
 * @author jiangbin
 * @date 2019/11/1 12:49 下午
 **/

public class DetailStatisticsMarkerThread implements Runnable {
    private List<MarkerItemPair> results;
    private DetailGeneResultSet details;
    private CountDownLatch latch;
    private StatisticsGeneContainer container;

    @Override
    public void run() {
        try {
            if (CollectionUtils.isEmpty(results) || details == null) {
                return;
            }

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
        int pIndex = details.getpPools().getIndex(pair.getPrimerName());
        if (pair.isDiff()) {
            this.statisticsDiff(pIndex, pair.getSource().getGeneIdList(), pair.getTarget().getGeneIdList());
        } else {
            this.statisticsSame(pIndex, pair.getSource().getGeneIdList(), pair.getTarget().getGeneIdList());
        }
    }

    /**
     * 统计差异位点数据
     *
     * @param pIndex      引物索引号
     * @param sGeneIdList 待比指纹ID列表
     * @param tGeneIdList 对比指纹ID列表
     * @return void
     * @author jiangbin
     * @date 2019/11/19 4:44 下午
     **/
    protected void statisticsDiff(int pIndex, List<String> sGeneIdList, List<String> tGeneIdList) {
        if (CollectionUtils.isEmpty(sGeneIdList) || CollectionUtils.isEmpty(tGeneIdList)) {
            return;
        }
        for (String sGeneId : sGeneIdList) {
            for (String tGeneId : tGeneIdList) {
                int row = details.getsPools().getIndex(sGeneId);
                int col = details.gettPools().getIndex(tGeneId);

                //差异位点计数
                if (details.get(row, col) != null) {
                    details.addDiff(row, col, pIndex);
                }
            }
        }
    }

    /**
     * 统计无差异位点数据
     *
     * @param pIndex      引物索引号
     * @param sGeneIdList 待比指纹ID列表
     * @param tGeneIdList 对比指纹ID列表
     * @return void
     * @author jiangbin
     * @date 2019/11/19 4:44 下午
     **/
    protected void statisticsSame(int pIndex, List<String> sGeneIdList, List<String> tGeneIdList) {
        if (CollectionUtils.isEmpty(sGeneIdList) || CollectionUtils.isEmpty(tGeneIdList)) {
            return;
        }
        for (String sGeneId : sGeneIdList) {
            for (String tGeneId : tGeneIdList) {
                int row = details.getsPools().getIndex(sGeneId);
                int col = details.gettPools().getIndex(tGeneId);

                //无差异位点计数
                if (details.get(row, col) != null) {
                    details.addSame(row, col, pIndex);
                }
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
     * @return com.viathink.ssr.core4.statistics.comparer.domain.DetailGeneResultSet
     * @author jiangbin
     * @date 2019/11/22 3:37 下午
     **/
    public DetailGeneResultSet getDetails() {
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
    public void setDetails(DetailGeneResultSet details) {
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
