package com.fcgs.core.comparer;

import com.fcgs.core.comparer.domain.*;
import com.pids.core.checker.number.NumberChecker;

import java.util.List;

/**
 * 基于位点分型统计方式的指纹比对业务接口定义
 *
 * @author jiangbin
 * @date 2019/11/4 2:57 下午
 **/
public interface FastGeneComparer {
    /**
     * 指纹比对功能接口
     *
     * @param container 指纹数据容器对象
     * @return com.viathink.gene.comparer.domain.GeneResultDetails
     * @author jiangbin
     * @date 2019/11/4 4:17 下午
     **/
    GeneResultSet compare(StatisticsGeneContainer container);

    /**
     * 指纹比对功能接口
     *
     * @param containers 指纹数据容器对象列表
     * @return com.viathink.gene.comparer.domain.GeneResultDetails
     * @author jiangbin
     * @date 2019/11/4 4:17 下午
     **/
    List<GeneResultSet> compare(List<StatisticsGeneContainer> containers);

    /**
     * 快速批量筛查功能，本功能支持大批量指纹分组分段比对后汇总比对结果，可以降低内存消耗的情况下，
     * 可能性能会有所下降
     *
     * @param params      比对参数
     * @param diffChecker 差异位点检查器
     * @return java.util.List<com.viathink.ssr.core.comparer.domain.SmartComparerResult>
     * @author jiangbin
     * @date 2019/11/22 9:03 上午
     **/
    List<SmartComparerResult> batch(StatisticsGeneContainer params, NumberChecker diffChecker);

    /**
     * 支持批量指纹比对，通过分组数据进行比对，本功能的比对结果将分多个CSV文件进行存储，
     * 需要在参数中指定存储目录路径，本功能实际上是支持任意长度队列的比对的，
     * 因为其分组比对并即时写入了磁盘文件中存储，故本功能可用于耗时长且结果数据较多的业务场景下
     *
     * @param params 比对参数
     * @return com.viathink.ssr.core4.statistics.comparer.domain.SimpleStatisticsResult
     * @author jiangbin
     * @date 2019/11/7 4:59 下午
     **/
    StatisticsResult batch(StatisticsContainer params);

}
