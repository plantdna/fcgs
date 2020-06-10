package com.fcgs.core.comparer.utils;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.filter.GeneIdFilter;
import com.pids.core.utils.ListUtils;

import java.util.List;

/**
 * 指纹工具类
 *
 * @author jiangbin
 * @date 2019/11/21 5:27 下午
 **/
public class GeneUtils {
    private static GeneIdFilter<Gene> geneIdFilter = new GeneIdFilter<>();

    /**
     * 两个指纹列表是否完全相同
     *
     * @param sources 待比指纹列表
     * @param targets 对比指纹列表
     * @return boolean
     * @author jiangbin
     * @date 2019/11/21 5:32 下午
     **/
    public static boolean isSame(List<Gene> sources, List<Gene> targets) {
        if (sources == targets) {
            return true;
        }

        List<String> sIdList = geneIdFilter.filter(sources);
        sIdList = ListUtils.unique(sIdList);

        List<String> tIdList = geneIdFilter.filter(targets);
        tIdList = ListUtils.unique(tIdList);

        if (!ListUtils.isEqualSize(sIdList, tIdList)) {
            return false;
        }
        
        sIdList.removeAll(tIdList);
        return ListUtils.isEmpty(sIdList);
    }
}
