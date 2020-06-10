package com.fcgs.service.util;

/**
 * 比对结果信息对象
 *
 * @author WUHAOTIAN
 * @date 2020-3-3 10:34
 **/
public class GCResults {
    private int sGeneCount;
    private int tGeneCount;
    private long times;
    private long resultCount;
    private long allResultCount;//总记录数
    private long allDiffCount;//总差异位点数
    private long avgDiffCount;//平均差异位点数
    private Integer maxDiff;
    private Integer minDiff;
    private String tagType;
    private String csvPath;
    private String matrixCsvPath;

    public int getsGeneCount() {
        return sGeneCount;
    }

    public void setsGeneCount(int sGeneCount) {
        this.sGeneCount = sGeneCount;
    }

    public int gettGeneCount() {
        return tGeneCount;
    }

    public void settGeneCount(int tGeneCount) {
        this.tGeneCount = tGeneCount;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public long getResultCount() {
        return resultCount;
    }

    public void setResultCount(long resultCount) {
        this.resultCount = resultCount;
    }

    public Integer getMaxDiff() {
        return maxDiff;
    }

    public void setMaxDiff(Integer maxDiff) {
        this.maxDiff = maxDiff;
    }

    public Integer getMinDiff() {
        return minDiff;
    }

    public void setMinDiff(Integer minDiff) {
        this.minDiff = minDiff;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public void setMatrixCsvPath(String matrixCsvPath) {
        this.matrixCsvPath = matrixCsvPath;
    }

    public String getMatrixCsvPath() {
        return matrixCsvPath;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTagType() {
        return tagType;
    }

    /**
     * 单个记录耗时=总耗时/总记录数
     *
     * @return long
     * @author jiangbin
     * @date 2020/5/30 15:26
     **/
    public double getAvgTimes() {
        return allResultCount == 0 ? 0 : (times * 1.0 / allResultCount);
    }

    /**
     * 获取差异位点的平均比对耗时
     *
     * @return double
     * @author jiangbin
     * @date 2020/6/4 19:11
     **/
    public double getAvgDiffTimes() {
        return avgDiffCount == 0 ? 0 : (getAvgTimes() * 1.0 / avgDiffCount);
    }

    public void setAllResultCount(long allResultCount) {
        this.allResultCount = allResultCount;
    }

    public long getAllResultCount() {
        return allResultCount;
    }

    public void setAllDiffCount(long allDiffCount) {
        this.allDiffCount = allDiffCount;
    }

    public long getAllDiffCount() {
        return allDiffCount;
    }

    public long getAvgDiffCount() {
        return avgDiffCount;
    }

    public void setAvgDiffCount(long avgDiffCount) {
        this.avgDiffCount = avgDiffCount;
    }

    public void setAvgDiffCount(long allResultCount, long allDiffCount) {
        this.allResultCount = allResultCount;
        this.allDiffCount = allDiffCount;
        if (this.allResultCount == 0) {
            this.avgDiffCount = 0;
        } else {
            this.avgDiffCount = this.allDiffCount / this.allResultCount;
        }
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", maxDiff == null ? "Max" : maxDiff, sGeneCount, tGeneCount, resultCount, times, (times * 1.0 / resultCount));
    }
}
