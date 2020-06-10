package com.fcgs.core.comparer;

import com.fcgs.base.container.GeneContainer;
import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.GeneInfoMapper;
import com.fcgs.core.comparer.domain.*;
import com.fcgs.core.comparer.statistics.DetailGeneStatistics;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 指纹详细比对算法
 *
 * @author jiangbin
 * @date 2019/11/22 1:30 下午
 **/

public class SimpleDetailGeneComparer implements DetailGeneComparer {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    protected MarkerItemComparer markerItemComparer = new SimpleMarkerItemComparer();
    protected DetailGeneStatistics geneStatistics = new DetailGeneStatistics();

    @Override
    public List<DetailComparerResult> compare(StatisticsGeneContainer sgcontainer, List<SmartComparerResult> results, GeneContainer container) {
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }

        //过滤待比和对比指纹ID列表
        List<String> sGeneIdList = filterSourceGeneIds(results);
        List<String> tGeneIdList = filterTargetGeneIds(results);
        if (CollectionUtils.isEmpty(sGeneIdList) || CollectionUtils.isEmpty(tGeneIdList)) {
            return null;
        }

        //获取待比和对比指纹列表
        List<Gene> sGenes = getSourceGenes(container, sGeneIdList);
        List<Gene> tGenes = getTargetGenes(container, tGeneIdList);
        if (CollectionUtils.isEmpty(sGenes) || CollectionUtils.isEmpty(tGenes)) {
            return null;
        }

        //构建指纹比对参数
        StatisticsGeneContainer tmpContainer = new SimpleStatisticsGeneContainer(sGenes, tGenes);
        tmpContainer.setMaxSizeOffset(sgcontainer.getMaxSizeOffset());
        tmpContainer.setPrimerNames(sgcontainer.getPrimerNames());

        //详细指纹比对
        DetailGeneResultSet detail = this.compare(tmpContainer, results);
        if (detail == null) {
            return null;
        }

        //获取详细指纹比对结果
        return detail.getDetailComparerResults(results,  StatisticsContext.getPoolSize());
    }

    @Override
    public DetailGeneResultSet compare(StatisticsGeneContainer container, List<SmartComparerResult> sResults) {
        if (container != null && !container.valid()) {
            return null;
        }

        StopWatch watch = new StopWatch();
        watch.start();
        try {
            //对各指纹数据分型进行分组
            MarkerItemContainer miContainer = container.createMarkerItemContainer();

            //比对各分型间差异
            List<MarkerItemPair> results = compareMarkerItems(miContainer, container.getMaxSizeOffset());

            //统计差异和无差异位点信息
            return statistics(container, miContainer, results, sResults);
        } finally {
            watch.stop();
            log.info("比对[ " + container.getSources().size() + "-" + container.getTargets().size() + " ]个指纹共耗时(ms)==>" + watch.getTime());
        }
    }

    @Override
    public DetailGeneResultSet compare(StatisticsGeneContainer container) {
        if (container != null && !container.valid()) {
            return null;
        }

        StopWatch watch = new StopWatch();
        watch.start();
        try {
            //对各指纹数据分型进行分组
            MarkerItemContainer miContainer = container.createMarkerItemContainer();

            //比对各分型间差异
            List<MarkerItemPair> results = compareMarkerItems(miContainer, container.getMaxSizeOffset());

            //统计差异和无差异位点信息
            return statistics(container, miContainer, results);
        } finally {
            watch.stop();
            log.info("比对[ " + container.getSources().size() + "-" + container.getTargets().size() + " ]个指纹共耗时(ms)==>" + watch.getTime());
        }
    }

    /**
     * 统计指纹差异和无差异结果信息
     *
     * @param container   指纹容器对象
     * @param miContainer 指纹分型分组映射表
     * @param results     指纹分型比对结果列表
     * @return com.viathink.ssr.core4.statistics.comparer.domain.GeneResultDetails
     * @throws
     * @author jiang
     * @date 2019/11/9 23:39
     **/
    protected DetailGeneResultSet statistics(StatisticsGeneContainer container, MarkerItemContainer miContainer, List<MarkerItemPair> results) {
        //统计比对结果
        return this.geneStatistics.statistics(container, miContainer, results);
    }

    /**
     * 统计指纹差异和无差异结果信息
     *
     * @param container   指纹容器对象
     * @param miContainer 指纹分型分组映射表
     * @param results     指纹分型比对结果列表
     * @param sResults    快速比对结果列表
     * @return com.viathink.ssr.core4.statistics.comparer.domain.GeneResultDetails
     * @throws
     * @author jiang
     * @date 2019/11/9 23:39
     **/
    protected DetailGeneResultSet statistics(StatisticsGeneContainer container, MarkerItemContainer miContainer, List<MarkerItemPair> results, List<SmartComparerResult> sResults) {
        //统计比对结果
        return this.geneStatistics.statistics(container, miContainer, results, sResults);
    }

    /**
     * 获取对比指纹列表
     *
     * @param container
     * @param tGeneIdList
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
     * @author jiangbin
     * @date 2019/11/22 4:11 下午
     **/
    private List<Gene> getTargetGenes(GeneContainer container, List<String> tGeneIdList) {
        GeneInfoMapper mapper = container.getTargetGeneInfoMapper();
        return mapper.getValues(tGeneIdList);
    }

    /**
     * 获取待比指纹列表
     *
     * @param container
     * @param sGeneIdList
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
     * @author jiangbin
     * @date 2019/11/22 4:11 下午
     **/
    private List<Gene> getSourceGenes(GeneContainer container, List<String> sGeneIdList) {
        GeneInfoMapper mapper = container.getSourceGeneInfoMapper();
        return mapper.getValues(sGeneIdList);
    }

    /**
     * 过滤对比指纹ID列表
     *
     * @param results
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/22 4:11 下午
     **/
    private List<String> filterTargetGeneIds(List<SmartComparerResult> results) {
        List<String> geneIdList = new ArrayList<>();
        for (SmartComparerResult result : results) {
            geneIdList.add(result.getTargetGeneId());
        }
        return ListUtils.unique(geneIdList);
    }

    /**
     * 过滤待比指纹ID列表
     *
     * @param results
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/22 4:12 下午
     **/
    private List<String> filterSourceGeneIds(List<SmartComparerResult> results) {
        List<String> geneIdList = new ArrayList<>();
        for (SmartComparerResult result : results) {
            geneIdList.add(result.getSourceGeneId());
        }
        return ListUtils.unique(geneIdList);
    }

    /**
     * 比对各引物的位点分型间差异信息
     *
     * @param container
     * @param maxSizeOffset
     * @return java.util.List<com.viathink.gene.comparer.domain.MarkerItemPair>
     * @throws
     * @author jiang
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
