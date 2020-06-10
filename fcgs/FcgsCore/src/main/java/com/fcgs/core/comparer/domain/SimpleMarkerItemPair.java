package com.fcgs.core.comparer.domain;

/**
 * 位点分型对信息对象
 *
 * @author jiangbin
 * @date 2019/10/31 9:09 上午
 **/
public class SimpleMarkerItemPair implements MarkerItemPair {
	private static final long serialVersionUID = -5042773003000740174L;

	/**
     * 待比位点分型
     *
     * @author jiangbin
     * @date 2019/11/5 9:10 上午
     **/
    private MarkerItem source;

    /**
     * 对比位点分型
     *
     * @author jiangbin
     * @date 2019/11/5 9:10 上午
     **/
    private MarkerItem target;

    /**
     * 引物名
     *
     * @author jiangbin
     * @date 2019/11/5 9:10 上午
     **/
    private String primerName;

    /**
     * 是否为差异位点
     *
     * @author jiangbin
     * @date 2019/11/5 9:11 上午
     **/
    private boolean diff;

    @Override
    public MarkerItem getSource() {
        return source;
    }

    @Override
    public void setSource(MarkerItem source) {
        this.source = source;
    }

    @Override
    public MarkerItem getTarget() {
        return target;
    }

    @Override
    public void setTarget(MarkerItem target) {
        this.target = target;
    }

    @Override
    public String getPrimerName() {
        return primerName;
    }

    @Override
    public void setPrimerName(String primerName) {
        this.primerName = primerName;
    }

    @Override
    public boolean isDiff() {
        return diff;
    }

    @Override
    public void setDiff(boolean diff) {
        this.diff = diff;
    }
}
