package com.fcgs.core.comparer.statistics;

import com.fcgs.core.comparer.domain.GeneResultSet;
import com.fcgs.core.comparer.domain.MarkerItemContainer;
import com.fcgs.core.comparer.domain.MarkerItemPair;
import com.fcgs.core.comparer.domain.StatisticsGeneContainer;

import java.util.List;

/**
 * 指纹比对结果统计功能，支持快速统计和详细统计两种方式，
 * 前者用于快速过滤结果，后者用于获取详细的差异和无差异位点名列表
 *
 * @author jiang
 * @date 2019-11-09 23:31
 */
public interface GeneStatistics {
    /**
     * 快速模式统计差异位点信息，不带位点名称统计
     *
     * @param container   指纹数据容器对象
     * @param miContainer 位点分型分组映射表
     * @param results     位点分型比对结果列表
     * @return void
     * @author jiangbin
     * @date 2019/10/30 11:39 上午
     **/
    GeneResultSet statistics(StatisticsGeneContainer container, MarkerItemContainer miContainer, List<MarkerItemPair> results);

}
