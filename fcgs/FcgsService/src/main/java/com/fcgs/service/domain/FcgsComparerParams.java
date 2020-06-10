package com.fcgs.service.domain;

import com.pids.core.checker.number.NumberChecker;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;

/**
 * FCGS比对算法参数对象
 *
 * @author jiangbin
 */
public class FcgsComparerParams implements IFcgsComparerParams {
    private static final long serialVersionUID = 8762651049196420939L;

    private String algorithm; //指纹比对算法类型
    private String maxDiffLoci; //最大差异位点数
    private String minDiffLoci; //最小差异位点数
    private TagTypeEnum tagType; //标记类型
    private File testCompareGeneFile; //待比指纹数据文件
    private File refCompareGeneFile; //对比指纹数据文件
    private String geneInitNum; //起始指纹数
    private String stepValue; //步进值

    @Override
    public String getGeneInitNum() {
        return geneInitNum;
    }

    @Override
    public Integer getGeneInitNumInt() {
        if (StringUtils.isBlank(geneInitNum)) {
            return null;
        }
        return NumberUtils.toInt(geneInitNum);
    }

    @Override
    public void setGeneInitNum(String geneInitNum) {
        this.geneInitNum = geneInitNum;
    }

    @Override
    public String getStepValue() {
        return stepValue;
    }

    @Override
    public Integer getStepValueInt() {
        if (StringUtils.isBlank(stepValue)) {
            return null;
        }
        return NumberUtils.toInt(stepValue);
    }

    @Override
    public void setStepValue(String stepValue) {
        this.stepValue = stepValue;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getMaxDiffLoci() {
        return maxDiffLoci;
    }

    @Override
    public Integer getMaxDiffLociInt() {
        if (StringUtils.isBlank(maxDiffLoci)) {
            return null;
        }
        return NumberUtils.toInt(maxDiffLoci);
    }

    @Override
    public void setMaxDiffLoci(String maxDiffLoci) {
        this.maxDiffLoci = maxDiffLoci;
    }

    @Override
    public String getMinDiffLoci() {
        return minDiffLoci;
    }

    @Override
    public void setMinDiffLoci(String minDiffLoci) {
        this.minDiffLoci = minDiffLoci;
    }

    @Override
    public Integer getMinDiffLociInt() {
        if (StringUtils.isBlank(minDiffLoci)) {
            return null;
        }
        return NumberUtils.toInt(minDiffLoci);
    }

    @Override
    public TagTypeEnum getTagType() {
        return tagType;
    }

    @Override
    public void setTagType(TagTypeEnum tagType) {
        this.tagType = tagType;
    }

    @Override
    public File getTestCompareGeneFile() {
        return testCompareGeneFile;
    }

    @Override
    public void setTestCompareGeneFile(File testCompareGeneFile) {
        this.testCompareGeneFile = testCompareGeneFile;
    }

    @Override
    public File getRefCompareGeneFile() {
        return refCompareGeneFile;
    }

    @Override
    public void setRefCompareGeneFile(File refCompareGeneFile) {
        this.refCompareGeneFile = refCompareGeneFile;
    }

    @Override
    public NumberChecker getDiffChecker() {
        Integer minDiffLoci = this.getMinDiffLociInt();
        Integer maxDiffLoci = this.getMaxDiffLociInt();
        if (minDiffLoci == null && maxDiffLoci == null) {
            return null;
        }
        NumberChecker checker = new NumberChecker();
        checker.setMinValue(minDiffLoci);
        checker.setMaxValue(maxDiffLoci);
        return checker;
    }

    @Override
    public boolean hasDiffChecker() {
        Integer minDiffLoci = this.getMinDiffLociInt();
        Integer maxDiffLoci = this.getMaxDiffLociInt();
        return minDiffLoci != null || maxDiffLoci != null;
    }

    public boolean isMicrosatellite() {
        return tagType == TagTypeEnum.MICROSATELLITE;
    }

    /**
     * 测试参数验证是否有效
     *
     * @return boolean
     * @author: WUHAOTIAN
     * @date: 2020/3/18 下午6:11
     **/
    public boolean testParamsValid() {
        if (StringUtils.isEmpty(stepValue)) {
            return false;
        }
        return true;
    }
}
