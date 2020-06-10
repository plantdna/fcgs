package com.fcgs.core.comparer.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计结果信息对象
 *
 * @author jiangbin
 * @date 2019/11/7 3:51 下午
 **/
public class SimpleStatisticsResult implements StatisticsResult {
    private List<String> resultFiles;
    private int diffCount;

    @Override
    public void setResultFiles(List<String> resultFiles) {
        this.resultFiles = resultFiles;
    }

    @Override
    public List<String> getResultFiles() {
        return resultFiles;
    }

    @Override
    public void addResultFile(String resultFile) {
        if (StringUtils.isBlank(resultFile)) {
            return;
        }
        if (this.resultFiles == null) {
            this.resultFiles = new ArrayList<>();
        }
        this.resultFiles.add(resultFile);
    }

    @Override
    public void setDiffCount(int diffCount) {
        this.diffCount = diffCount;
    }

    @Override
    public int getDiffCount() {
        return diffCount;
    }

    @Override
    public void addDiff(int count) {
        this.diffCount += count;
    }
}
