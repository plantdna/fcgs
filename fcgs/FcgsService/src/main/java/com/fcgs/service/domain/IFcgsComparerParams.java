package com.fcgs.service.domain;

import com.pids.core.checker.number.NumberChecker;

import java.io.File;
import java.io.Serializable;

/**
 * FCGS比对参数
 *
 * @author WUHAOTIAN
 * @date 2020-3-3上午8:02:43
 */
public interface IFcgsComparerParams extends Serializable {

    String getGeneInitNum();

    Integer getGeneInitNumInt();

    void setGeneInitNum(String geneInitNum);

    String getStepValue();

    Integer getStepValueInt();

    void setStepValue(String stepValue);

    String getAlgorithm();

    void setAlgorithm(String algorithm);

    String getMaxDiffLoci();

    Integer getMaxDiffLociInt();

    void setMaxDiffLoci(String maxDiffLoci);

    String getMinDiffLoci();

    void setMinDiffLoci(String minDiffLoci);

    Integer getMinDiffLociInt();

    TagTypeEnum getTagType();

    void setTagType(TagTypeEnum tagType);

    File getTestCompareGeneFile();

    void setTestCompareGeneFile(File testCompareGeneFile);

    File getRefCompareGeneFile();

    void setRefCompareGeneFile(File refCompareGeneFile);

    NumberChecker getDiffChecker();

    boolean hasDiffChecker();
}