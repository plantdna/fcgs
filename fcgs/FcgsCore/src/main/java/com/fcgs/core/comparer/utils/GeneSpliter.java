package com.fcgs.core.comparer.utils;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.core.comparer.domain.SimpleStatisticsGeneContainer;
import com.fcgs.core.comparer.domain.StatisticsContext;
import com.fcgs.core.comparer.domain.StatisticsGeneContainer;
import com.pids.core.spliter.impl.SpliterUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 指纹容器分组工具类
 *
 * @author jiangbin
 * @date 2019/11/4 4:09 下午
 **/
public class GeneSpliter {

    /**
     * 对待比和对比指纹进行分组，以便将大规模比对数据拆分成多个线程来执行
     *
     * @param source 源数据对象
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.StatisticsGeneContainer>
     * @author jiangbin
     * @date 2019/11/26 5:57 下午
     **/
    public static List<StatisticsGeneContainer> group(StatisticsGeneContainer source) {
        List<List<Gene>> sGroups = SpliterUtils.split(source.getSources(), StatisticsContext.getBatchGroupSize());
        if (source.getSources() == source.getTargets()) {
            return createContainers(source, sGroups);
        } else {
            List<List<Gene>> tGroups = SpliterUtils.split(source.getTargets(), StatisticsContext.getBatchGroupSize());
            return createContainers(source, sGroups, tGroups);
        }
    }

    /**
     * 使用待比和对比分组列表创建指纹容器对象列表
     *
     * @param source
     * @param sGroups
     * @param tGroups
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.GeneContainer>
     * @author jiangbin
     * @date 2019/11/8 9:29 上午
     **/
    private static List<StatisticsGeneContainer> createContainers(StatisticsGeneContainer source, List<List<Gene>> sGroups, List<List<Gene>> tGroups) {
        List<StatisticsGeneContainer> containers = new ArrayList<>();
        for (List<Gene> sGroup : sGroups) {
            for (List<Gene> tGroup : tGroups) {
                StatisticsGeneContainer container = new SimpleStatisticsGeneContainer(sGroup, tGroup);
                container.setMaxSizeOffset(source.getMaxSizeOffset());
                containers.add(container);
            }
        }
        return containers;
    }

    /**
     * 使用单个分组作为待比对对比指纹分组列表创建指纹容器对象列表，本方法避免队列内互比时出现重复比对的情况
     *
     * @param source
     * @param groups
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.GeneContainer>
     * @author jiangbin
     * @date 2019/11/8 9:29 上午
     **/
    private static List<StatisticsGeneContainer> createContainers(StatisticsGeneContainer source, List<List<Gene>> groups) {
        List<StatisticsGeneContainer> containers = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            for (int j = i; j < groups.size(); j++) {
                StatisticsGeneContainer container = new SimpleStatisticsGeneContainer(groups.get(i), groups.get(j));
                container.setMaxSizeOffset(source.getMaxSizeOffset());
                containers.add(container);
            }
        }
        return containers;
    }
}
