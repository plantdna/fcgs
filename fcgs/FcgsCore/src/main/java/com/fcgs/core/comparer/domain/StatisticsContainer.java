package com.fcgs.core.comparer.domain;

import com.pids.core.checker.number.NumberChecker;

import java.io.Serializable;
import java.util.List;

/**
 * 指纹统计信息容器接口
 *
 * @author jiangbin
 * @date 2019/11/7 3:49 下午
 **/
public interface StatisticsContainer extends Serializable {
    /**
     * 获取指纹容器信息对象
     *
     * @return com.viathink.ssr.core4.statistics.comparer.domain.GeneContainer
     * @author jiangbin
     * @date 2019/11/7 3:49 下午
     **/
    StatisticsGeneContainer getContainer();

    /**
     * 设置指纹容器信息对象
     *
     * @param container
     * @return void
     * @author jiangbin
     * @date 2019/11/7 3:49 下午
     **/
    void setContainer(StatisticsGeneContainer container);

    /**
     * 获取Ssr指纹比对参数
     *
     * @return
     * @author jiang
     * @date 2018年8月26日下午12:58:13
     */
    SsrComparerParams getParams();

    /**
     * 设置Ssr指纹比对参数
     *
     * @param params
     * @author jiang
     * @date 2018年8月26日下午12:58:16
     */
    void setParams(SsrComparerParams params);

    /**
     * 分割比对列表成多个列表，用于执行多线程比对任务
     *
     * @param step 分割步进值
     * @return
     * @author jiang
     * @date 2018年8月26日下午12:59:28
     */
    List<StatisticsContainer> split(int step);

    /**
     * 设置结果文件存储目录
     *
     * @param resultFolder
     * @return void
     * @author jiangbin
     * @date 2019/11/7 4:46 下午
     **/
    void setResultFolder(String resultFolder);

    /**
     * 获取结果文件存储目录
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2019/11/7 4:46 下午
     **/
    String getResultFolder();

    /**
     * 获取待比指纹数
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/7 5:03 下午
     **/
    int getSourceGeneCount();

    /**
     * 获取对比指纹数
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/7 5:03 下午
     **/
    int getTargetGeneCount();

    /**
     * 检测参数有效性
     *
     * @return boolean
     * @author jiangbin
     * @date 2019/11/7 5:06 下午
     **/
    boolean valid();

    /**
     * 获取差异位点数检查器，若未设置最大差异位点和最小差异位点则返回null
     *
     * @return com.viathink.core.checker.number.NumberChecker
     * @author jiangbin
     * @date 2019/11/7 5:50 下午
     **/
    NumberChecker getDiffChecker();

}