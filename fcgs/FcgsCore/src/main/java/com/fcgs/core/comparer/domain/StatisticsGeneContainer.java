package com.fcgs.core.comparer.domain;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.GeneInfoMapper;
import com.fcgs.core.comparer.utils.SmartComparerResultGroupMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 指纹容器对象
 *
 * @author jiangbin
 * @date 2019/11/4 4:13 下午
 **/
public interface StatisticsGeneContainer extends Serializable {
    /**
     * 无效数据标志
     *
     * @author jiangbin
     * @date 2019/11/12 1:52 下午
     **/
    int INVALID = -1;

    /**
     * 指纹数据是否可用，即待比和对比指纹均不为空列表
     *
     * @return boolean true/false--可用/不可用
     * @author jiangbin
     * @date 2019/11/4 4:22 下午
     **/
    boolean valid();

    /**
     * 获取待比指纹ID列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/8 3:54 下午
     **/
    List<String> getSourceIdList();

    /**
     * 获取对比指纹ID列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/8 3:55 下午
     **/
    List<String> getTargetIdList();

    /**
     * 创建快速指纹比对结果对象
     *
     * @return com.viathink.ssr.core4.statistics.comparer.domain.FastGeneResultSet
     * @author jiangbin
     * @date 2019/11/19 7:29 下午
     **/
    FastGeneResultSet createFastResult();

    /**
     * 创建详细指纹比对结果对象
     *
     * @param primerNames 引物名称列表
     * @return com.viathink.ssr.core4.statistics.comparer.domain.DetailGeneResultSet
     * @author jiangbin
     * @date 2019/11/22 3:33 下午
     **/
    DetailGeneResultSet createDetailResult(List<String> primerNames);

    /**
     * 创建详细指纹比对结果对象
     *
     * @param primerNames 引物名称列表
     * @param mapper      快速比对结果的指纹ID对分组映射表，用于提高详细指纹比对时内存使用效率和统计速度
     * @return com.viathink.ssr.core4.statistics.comparer.domain.DetailGeneResultSet
     * @author jiangbin
     * @date 2019/11/27 5:21 下午
     **/
    DetailGeneResultSet createDetailResult(List<String> primerNames, SmartComparerResultGroupMapper mapper);

    /**
     * 构建位点分型信息映射表容器对象
     *
     * @return com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemContainer
     * @author jiangbin
     * @date 2019/11/20 1:53 下午
     **/
    MarkerItemContainer createMarkerItemContainer();

    /**
     * 获取待比指纹列表
     *
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
     * @author jiangbin
     * @date 2019/11/9 2:26 下午
     **/
    List<Gene> getSources();

    /**
     * 获取对比指纹列表
     *
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
     * @author jiangbin
     * @date 2019/11/9 2:26 下午
     **/
    List<Gene> getTargets();

    /**
     * 获取最大碱基偏移量
     *
     * @return int 将以小于该值为标准进行计算，故在SNP数据计算时要设置成1，表示完全一致时才算无差异
     * @author jiangbin
     * @date 2019/11/9 2:27 下午
     **/
    int getMaxSizeOffset();

    /**
     * 设置最大碱基偏移量
     *
     * @param maxSizeOffset 将以小于该值为标准进行计算，故在SNP数据计算时要设置成1，表示完全一致时才算无差异
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:27 下午
     **/
    void setMaxSizeOffset(int maxSizeOffset);

    /**
     * 获取比对结果对应的指纹列表
     *
     * @param results
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
     * @author jiangbin
     * @date 2019/11/12 5:24 下午
     **/
    List<Gene> getGenes(List<ComparerResult> results);

    /**
     * 获取指纹ID与指纹映射表
     *
     * @return com.viathink.ssr.core.mapper.GeneInfoMapper
     * @author jiangbin
     * @date 2019/11/12 5:29 下午
     **/
    GeneInfoMapper getGeneMapper();

    /**
     * 获取引物名称列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/25 3:49 下午
     **/
    List<String> getPrimerNames();

    /**
     * 设置引物名称列表
     *
     * @param primerNames
     * @return void
     * @author jiangbin
     * @date 2019/11/25 3:49 下午
     **/
    void setPrimerNames(List<String> primerNames);

}
