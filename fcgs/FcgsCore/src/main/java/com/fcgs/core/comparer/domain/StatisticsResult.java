package com.fcgs.core.comparer.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 统计结果信息对象
 *
 * @author jiangbin
 * @date 2019/11/7 3:51 下午
 **/
public interface StatisticsResult extends Serializable {

    /**
     * 设置比对结果文件路径列表
     *
     * @param resultFiles
     * @return void
     * @author jiangbin
     * @date 2019/11/9 3:02 下午
     **/
    void setResultFiles(List<String> resultFiles);

    /**
     * 获取比对结果文件路径列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/9 3:02 下午
     **/
    List<String> getResultFiles();

    /**
     * 添加比对结果文件路径
     *
     * @param resultFile
     * @return void
     * @author jiangbin
     * @date 2019/11/9 3:02 下午
     **/
    void addResultFile(String resultFile);

    /**
     * 设置差异位点数
     *
     * @param diffCount
     * @return void
     * @author jiangbin
     * @date 2019/11/9 4:53 下午
     **/
    void setDiffCount(int diffCount);

    /**
     * 获取差异位点数
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/9 4:53 下午
     **/
    int getDiffCount();

    /**
     * 添加差异位点数
     *
     * @param count
     * @return void
     * @author jiangbin
     * @date 2019/11/9 4:55 下午
     **/
    void addDiff(int count);
}
