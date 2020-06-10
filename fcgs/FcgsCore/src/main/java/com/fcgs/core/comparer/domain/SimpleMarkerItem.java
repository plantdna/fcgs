package com.fcgs.core.comparer.domain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 位点分型信息对象
 *
 * @author jiangbin
 * @date 2019/10/31 9:10 上午
 **/
public class SimpleMarkerItem implements MarkerItem {
    /**
     * 引物名
     *
     * @author jiangbin
     * @date 2019/11/5 9:11 上午
     **/
    private String primerName;

    private int allele1;
    private int allele2;

    /**
     * 获取位点分型字符串
     *
     * @author jiangbin
     * @date 2019/11/20 2:30 下午
     **/
    private String markerStr;

    /**
     * 是否为纯合子
     *
     * @author jiangbin
     * @date 2019/11/5 9:12 上午
     **/
    private boolean isozygote;//是否为纯合子

    /**
     * 待比和对比分型关联的指纹ID列表
     *
     * @author jiangbin
     * @date 2019/11/5 9:12 上午
     **/
    private List<String> geneIdList = new ArrayList<>();

    /**
     * 待比和对比分型关联的指纹ID映射表,key/value--指纹ID/指纹ID
     *
     * @author jiangbin
     * @date 2019/11/8 6:21 下午
     **/
    private final Map<String, String> cache = new HashMap<>();

    @Override
    public String getPrimerName() {
        return primerName;
    }

    @Override
    public void setPrimerName(String primerName) {
        this.primerName = primerName;
    }

    @Override
    public int getAllele1() {
        return allele1;
    }

    @Override
    public void setAllele1(int allele1) {
        this.allele1 = allele1;
    }

    @Override
    public String getMarkerStr() {
        return markerStr;
    }

    @Override
    public void setMarkerStr(String markerStr) {
        this.markerStr = markerStr;
    }

    @Override
    public int getAllele2() {
        return allele2;
    }

    @Override
    public void setAllele2(int allele2) {
        this.allele2 = allele2;
    }

    @Override
    public boolean isIsozygote() {
        return isozygote;
    }

    @Override
    public void setIsozygote(boolean isozygote) {
        this.isozygote = isozygote;
    }

    @Override
    public List<String> getGeneIdList() {
        return geneIdList;
    }

    @Override
    public void setGeneIdList(List<String> geneIdList) {
        this.geneIdList = geneIdList;
    }

    @Override
    public boolean has(String geneId) {
        return StringUtils.isNotBlank(cache.get(geneId));
    }

    @Override
    public boolean add(String geneId) {
        if (has(geneId)) {
            return false;
        }
        cache.put(geneId, geneId);
        geneIdList.add(geneId);
        return true;
    }

    @Override
    public void add(List<String> geneIds) {
        if (CollectionUtils.isEmpty(geneIds)) {
            return;
        }
        for (String geneId : geneIds) {
            this.add(geneId);
        }
    }
}
