package com.fcgs.base.domain.gene.utils;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;

/**
 * 属性填充接口
 *
 * @author jiangbin
 * @date 2019-09-01 23:49
 **/
public abstract class GenePropFiller {
    //属性映射关系表

    /**
     * 填充指纹信息
     *
     * @param gene
     * @param value
     * @return void
     * @author jiangbin
     * @date 2019-09-01 22:52
     **/
    public void set(Gene gene, String value) {
    }

    /**
     * 填充指纹信息
     *
     * @param marker
     * @param value
     * @return void
     * @author jiangbin
     * @date 2019-09-01 22:52
     **/
    public void set(Marker marker, String value) {
    }

    /**
     * 填充Allele的属性值
     *
     * @param allele
     * @param value
     * @return void
     * @author jiangbin
     * @date 2019-09-25 16:33
     **/
    public void set(Allele allele, String value) {
    }

    /**
     * 是否为位点的基本信息字段，用于判定是否重新创建一个新位点
     *
     * @return boolean
     * @author jiangbin
     * @date 2019-09-25 16:53
     **/
    public boolean isMarker() {
        return false;
    }

    /**
     * 是否为Allele的基本信息字段，用于判定是否重新创建一个新Allele
     *
     * @return boolean
     * @author jiangbin
     * @date 2019-09-25 16:53
     **/
    public boolean isAllele() {
        return false;
    }

    /**
     * 是否为指纹的基本信息字段
     *
     * @return boolean
     * @author jiangbin
     * @date 2019-09-25 16:53
     **/
    public boolean isGene() {
        return false;
    }
}
