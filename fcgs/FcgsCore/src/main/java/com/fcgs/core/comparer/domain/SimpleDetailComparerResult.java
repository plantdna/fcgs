package com.fcgs.core.comparer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 详细比对结果信息对象实现类
 *
 * @author Jiangbin
 * @date 2014-3-4上午12:29:15
 */
public class SimpleDetailComparerResult extends SimpleComparerResult implements DetailComparerResult {
    private static final long serialVersionUID = -2964321692572260731L;
    private Integer markerCount;
    private Integer diffMarkerCount;
    private Integer noDiffMarkerCount;
    private Integer missMarkerCount;
    private Integer diffAlleleCount;
    private Double similarity;
    private List<String> noDiffMarkerNames;
    private List<String> diffMarkerNames;
    private List<String> missMarkerNames;

    public SimpleDetailComparerResult() {
        this.markerCount = 0;
        this.diffMarkerCount = 0;
        this.noDiffMarkerCount = 0;
        this.diffAlleleCount = 0;
        this.missMarkerCount = 0;
        this.noDiffMarkerNames = new ArrayList<>();
        this.diffMarkerNames = new ArrayList<>();
        this.missMarkerNames = new ArrayList<>();
    }

    @Override
    public Double getSimilarity() {
        return similarity;
    }

    @Override
    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    @Override
    public Integer getMarkerCount() {
        return markerCount;
    }

    @Override
    public void setMarkerCount(Integer markerCount) {
        this.markerCount = markerCount;
    }

    @Override
    public Integer getDiffMarkerCount() {
        return diffMarkerCount;
    }

    @Override
    public void setDiffMarkerCount(Integer diffMarkerCount) {
        this.diffMarkerCount = diffMarkerCount;
    }

    @Override
    public Integer getNoDiffMarkerCount() {
        return noDiffMarkerCount;
    }

    @Override
    public void setNoDiffMarkerCount(Integer noDiffMarkerCount) {
        this.noDiffMarkerCount = noDiffMarkerCount;
    }

    @Override
    public List<String> getNoDiffMarkerNames() {
        return noDiffMarkerNames;
    }

    @Override
    public void setNoDiffMarkerNames(List<String> noDiffMarkerNames) {
        this.noDiffMarkerNames = noDiffMarkerNames;
    }

    @Override
    public List<String> getDiffMarkerNames() {
        return diffMarkerNames;
    }

    @Override
    public void setDiffMarkerNames(List<String> diffMarkerNames) {
        this.diffMarkerNames = diffMarkerNames;
    }

    @Override
    public void setMissMarkerNames(List<String> missMarkerNames) {
        this.missMarkerNames = missMarkerNames;
    }

    @Override
    public List<String> getMissMarkerNames() {
        return missMarkerNames;
    }

    @Override
    public void addMissMarkerNames(List<String> misss) {
        this.missMarkerNames.addAll(misss);
    }

    @Override
    public void addMissMarkerNames(String miss) {
        this.missMarkerNames.add(miss);
    }

    @Override
    public Integer getMissMarkerCount() {
        return missMarkerCount;
    }

    @Override
    public void setMissMarkerCount(Integer missMarkerCount) {
        this.missMarkerCount = missMarkerCount;
    }

    @Override
    public void addMarkerCount(Integer markerCount) {
        this.markerCount += markerCount;
    }

    @Override
    public void addDiffMarkerCount(Integer diffMarkerCount) {
        this.diffMarkerCount += diffMarkerCount;
    }

    @Override
    public void addNoDiffMarkerCount(Integer noDiffMarkerCount) {
        this.noDiffMarkerCount += noDiffMarkerCount;
    }

    @Override
    public void addNoDiffMarkerName(String noDiffMarkerName) {
        this.noDiffMarkerNames.add(noDiffMarkerName);
    }

    @Override
    public void addDiffMarkerName(String diffMarkerName) {
        this.diffMarkerNames.add(diffMarkerName);
    }

    @Override
    public void setDiffAlleleCount(Integer diffAlleleCount) {
        this.diffAlleleCount = diffAlleleCount;
    }

    @Override
    public Integer getDiffAlleleCount() {
        return diffAlleleCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
