package com.fcgs.core.comparer.domain;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.core.comparer.mapper.MarkerItemMapper;
import com.fcgs.core.comparer.mapper.MarkerItemPrimerMapper;
import com.pids.core.pools.IdPools;
import com.pids.core.pools.SimpleIdPools;

import java.io.Serializable;
import java.util.List;

/**
 * 位点分型信息容器对象，包含待比和对比指纹位点分型映射表
 *
 * @author jiangbin
 * @date 2019/11/20 1:44 下午
 **/
public class MarkerItemContainer implements Serializable {
    private MarkerItemPrimerMapper source = new MarkerItemPrimerMapper();
    private MarkerItemPrimerMapper target = new MarkerItemPrimerMapper();
    private boolean same = false;

    /**
     * 获取待比分型映射表
     *
     * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemPrimerMapper
     * @author jiangbin
     * @date 2019/11/21 10:16 上午
     **/
    public MarkerItemPrimerMapper getSource() {
        return source;
    }

    /**
     * 设置待比分型映射表
     *
     * @param source
     * @return void
     * @author jiangbin
     * @date 2019/11/21 10:16 上午
     **/
    public void setSource(MarkerItemPrimerMapper source) {
        this.source = source;
    }

    /**
     * 获取对比分型映射表
     *
     * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemPrimerMapper
     * @author jiangbin
     * @date 2019/11/21 10:16 上午
     **/
    public MarkerItemPrimerMapper getTarget() {
        return target;
    }

    /**
     * 设置对比分型映射表
     *
     * @param target
     * @return void
     * @author jiangbin
     * @date 2019/11/21 10:16 上午
     **/
    public void setTarget(MarkerItemPrimerMapper target) {
        this.target = target;
    }

    /**
     * 添加待比指纹位点列表
     *
     * @param sources
     * @return void
     * @author jiangbin
     * @date 2019/11/21 10:15 上午
     **/
    public void addSources(List<Marker> sources) {
        this.source.addAll(sources);
    }

    /**
     * 获取对比指纹位点列表
     *
     * @param targets
     * @return void
     * @author jiangbin
     * @date 2019/11/21 10:15 上午
     **/
    public void addTargets(List<Marker> targets) {
        this.target.addAll(targets);
    }

    /**
     * 获取全部位点的引物名称集合
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/21 10:15 上午
     **/
    public List<String> getPrimerNames() {
        IdPools pools = new SimpleIdPools(this.source.getKeys());
        pools.add(this.target.getKeys());
        return pools.getIdList();
    }

    /**
     * 获取指定引物名称关联的待比位点分型映射表
     *
     * @param primerName
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItem>
     * @author jiangbin
     * @date 2019/11/21 10:15 上午
     **/
    public List<MarkerItem> getSourceMarkerItems(String primerName) {
        MarkerItemMapper mapper = this.source.get(primerName);
        return mapper == null ? null : mapper.getValues();
    }

    /**
     * 获取指定引物名称关联的对比位点分型映射表
     *
     * @param primerName
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItem>
     * @author jiangbin
     * @date 2019/11/21 10:16 上午
     **/
    public List<MarkerItem> getTargetMarkerItems(String primerName) {
        MarkerItemMapper mapper = this.target.get(primerName);
        return mapper == null ? null : mapper.getValues();
    }

    /**
     * 设置待比和对比指纹是否相同
     *
     * @param same
     * @return void
     * @author jiangbin
     * @date 2019/11/21 10:34 上午
     **/
    public void setSame(boolean same) {
        this.same = same;
    }

    /**
     * 待比和对比指纹是否相同
     *
     * @return boolean
     * @author jiangbin
     * @date 2019/11/21 10:34 上午
     **/
    public boolean isSame() {
        return same;
    }
}
