package com.fcgs.core.comparer;

import com.fcgs.base.container.GeneContainer;
import com.fcgs.core.comparer.domain.DetailComparerResult;
import com.fcgs.core.comparer.domain.DetailGeneResultSet;
import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.fcgs.core.comparer.domain.StatisticsGeneContainer;

import java.util.List;

/**
 * 指纹详细比对算法
 *
 * @author jiangbin
 * @date 2019/11/22 1:30 下午
 **/
public interface DetailGeneComparer {
    /**
     * 指纹详细比对
     *
     * @param sgcontainer 统计模式指纹容器对象
     * @param results     快速比对结果列表
     * @param container   指纹数据容器对象
     * @return java.util.List<com.viathink.ssr.core.comparer.domain.DetailComparerResult>
     * @author jiangbin
     * @date 2019/11/22 1:33 下午
     **/
    List<DetailComparerResult> compare(StatisticsGeneContainer sgcontainer, List<SmartComparerResult> results, GeneContainer container);

    /**
     * 指纹比对，限定了结果范围
     *
     * @param container 指纹和参数信息
     * @param sResults  快速筛查结果列表，用于限制结果范围，避免无效结果统计，提高程序性能
     * @return com.viathink.ssr.core4.statistics.comparer.domain.DetailGeneResultSet
     * @author jiangbin
     * @date 2019/11/22 6:47 下午
     **/
    DetailGeneResultSet compare(StatisticsGeneContainer container, List<SmartComparerResult> sResults);

    /**
     * 指纹详细比对
     *
     * @param sgcontainer 统计模式指纹容器对象
     * @return com.viathink.ssr.core4.statistics.comparer.domain.DetailGeneResultSet
     * @author jiangbin
     * @date 2019/11/22 5:03 下午
     **/
    DetailGeneResultSet compare(StatisticsGeneContainer sgcontainer);
}
