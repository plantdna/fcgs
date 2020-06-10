package com.fcgs.core.comparer.domain;

import java.io.Serializable;

/**
 * 位点分型对信息对象
 *
 * @author jiangbin
 * @date 2019/10/31 9:09 上午
 **/
public interface MarkerItemPair extends Serializable {

    /**
     * 获取待比位点分型信息
     *
     * @return com.viathink.ssr.core4.statistics.comparer.domain.MarkerItem
     * @author jiangbin
     * @date 2019/11/9 2:57 下午
     **/
    MarkerItem getSource();

    /**
     * 设置待比位点分型信息
     *
     * @param source
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:58 下午
     **/
    void setSource(MarkerItem source);

    /**
     * 获取对比位点分型信息
     *
     * @return com.viathink.ssr.core4.statistics.comparer.domain.MarkerItem
     * @author jiangbin
     * @date 2019/11/9 2:58 下午
     **/
    MarkerItem getTarget();

    /**
     * 设置对比位点分型信息
     *
     * @param target
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:58 下午
     **/
    void setTarget(MarkerItem target);

    /**
     * 获取引物名
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2019/11/9 2:58 下午
     **/
    String getPrimerName();

    /**
     * 设置引物名
     *
     * @param primerName
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:58 下午
     **/
    void setPrimerName(String primerName);

    /**
     * 是否为差异位点
     *
     * @return boolean
     * @author jiangbin
     * @date 2019/11/9 2:58 下午
     **/
    boolean isDiff();

    /**
     * 设置是否为差异位点
     *
     * @param diff true/false--差异/无差异
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:58 下午
     **/
    void setDiff(boolean diff);
}
