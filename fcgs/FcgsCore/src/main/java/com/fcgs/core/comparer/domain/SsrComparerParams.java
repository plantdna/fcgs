package com.fcgs.core.comparer.domain;

import com.fcgs.base.checker.DiffMarkerCountChecker;
import com.pids.core.checker.number.IntRangeChecker;
import com.pids.core.checker.number.NumberChecker;

/**
 * Ssr指纹比对参数
 *
 * @author jiangbin
 * @date 2018年8月25日下午2:15:58
 */
public interface SsrComparerParams extends SsrGeneParams {
    /**
     * 设置最小无差异位点数
     *
     * @param minSameMarkerCount
     * @author Andory
     * @date 2013年9月13日上午12:53:52
     */
    public void setMinSameMarkerCount(Integer minSameMarkerCount);

    /**
     * 获取最小无差异位点数
     *
     * @return
     * @author Andory
     * @date 2013年9月13日上午12:53:54
     */
    public Integer getMinSameMarkerCount();

    /**
     * 设置最大无差异位点数
     *
     * @param maxSameMarkerCount
     * @author Andory
     * @date 2013年9月13日上午12:53:55
     */
    public void setMaxSameMarkerCount(Integer maxSameMarkerCount);

    /**
     * 获取最大无差异位点数
     *
     * @return
     * @author Andory
     * @date 2013年9月13日上午12:53:56
     */
    public Integer getMaxSameMarkerCount();

    /**
     * 设置最小差异位点数
     *
     * @param minDiffMarkerCount
     * @author jiangbin
     * @date 2018年8月25日下午2:16:08
     */
    public void setMinDiffMarkerCount(Integer minDiffMarkerCount);

    /**
     * 获取最小差异位点数
     *
     * @return
     * @author jiangbin
     * @date 2018年8月25日下午2:16:11
     */
    Integer getMinDiffMarkerCount();

    /**
     * 设置最大差异位点数
     *
     * @param maxDiffMarkerCount
     * @author jiangbin
     * @date 2018年8月25日下午2:16:13
     */
    public void setMaxDiffMarkerCount(Integer maxDiffMarkerCount);

    /**
     * 获取最大差异位点数
     *
     * @return
     * @author jiangbin
     * @date 2018年8月25日下午2:16:15
     */
    public Integer getMaxDiffMarkerCount();

    /**
     * 设置最大位点总数限定值
     *
     * @param maxMarkerCount
     * @author jiangbin
     * @date 2012-11-9下午12:24:46
     */
    public void setMaxMarkerCount(Integer maxMarkerCount);

    /**
     * 获取最小位点总数限定值
     *
     * @return
     * @author jiangbin
     * @date 2012-11-9下午12:24:50
     */
    public Integer getMinMarkerCount();

    /**
     * 设置最小位点总数限定值
     *
     * @param minMarkerCount
     * @author jiangbin
     * @date 2012-11-9下午12:24:51
     */
    public void setMinMarkerCount(Integer minMarkerCount);

    /**
     * 获取最大结果数限制值
     *
     * @return
     * @author jiangbin
     * @date 2018年9月17日下午1:26:28
     */
    public Integer getMaxResultsSize();

    /**
     * 设置最大结果数限制值
     *
     * @param maxResultsSize
     * @author jiangbin
     * @date 2018年9月17日下午1:26:43
     */
    public void setMaxResultsSize(Integer maxResultsSize);

    /**
     * 获取比对总位点数检测器
     *
     * @return
     * @author jiang
     * @date 2018年9月21日下午4:54:04
     */
    IntRangeChecker getMarkerCountChecker();

    /**
     * 获取无差异位点数检测器
     *
     * @return
     * @author jiang
     * @date 2018年9月21日下午4:54:06
     */
    IntRangeChecker getSameMarkerCountChecker();

    /**
     * 获取差异位点数检测器
     *
     * @return
     * @author jiang
     * @date 2018年9月21日下午4:54:08
     */
    DiffMarkerCountChecker getDiffMarkerCountChecker();

    /**
     * 获取碱基偏移量检测器
     *
     * @return
     * @author jiang
     * @date 2018年9月21日下午4:54:10
     */
    IntRangeChecker getSizeOffsetChecker();

    /**
     * 获取比对结果数检测器
     *
     * @return
     * @author jiang
     * @date 2018年9月21日下午4:54:12
     */
    IntRangeChecker getResultsSizeChecker();

    /**
     * 设置差异位点数检查器
     *
     * @param diffMarkerCountChecker
     * @return void
     * @author jiangbin
     * @date 2019-09-09 18:38
     **/
    void setDiffMarkerCountChecker(DiffMarkerCountChecker diffMarkerCountChecker);

    /**
     * 设置位点数检查器
     *
     * @param markerCountChecker
     * @return void
     * @author jiangbin
     * @date 2019-09-09 18:38
     **/
    void setMarkerCountChecker(NumberChecker markerCountChecker);

    /**
     * 设置无差异位点数检查器
     *
     * @param sameMarkerCountChecker
     * @return void
     * @author jiangbin
     * @date 2019-09-09 18:38
     **/
    void setSameMarkerCountChecker(NumberChecker sameMarkerCountChecker);

    /**
     * 设置碱基偏移量检查器
     *
     * @param sizeOffsetChecker
     * @return void
     * @author jiangbin
     * @date 2019-09-09 18:39
     **/
    void setSizeOffsetChecker(NumberChecker sizeOffsetChecker);

    /**
     * 设置比对结果数检查器
     *
     * @param resultsSizeChecker
     * @return void
     * @author jiangbin
     * @date 2019-09-09 18:39
     **/
    void setResultsSizeChecker(NumberChecker resultsSizeChecker);

    /**
     * 设置是否过滤重复结果
     *
     * @param filterRepeatResults 0/1--不过滤/过滤
     * @return void
     * @author jiangbin
     * @date 2019/10/10 11:13 上午
     **/
    void setFilterRepeatResults(boolean filterRepeatResults);

    /**
     * 是否过滤重复结果
     *
     * @return boolean true/false--过滤/不过滤重复结果
     * @author jiangbin
     * @date 2019/10/10 11:13 上午
     **/
    boolean isFilterRepeatResults();
}
