package com.fcgs.core.comparer.domain;

import java.util.List;

/**
 * 详细比对结果信息对象
 *
 * @author jiangbin
 * @date 2012-10-29上午10:51:33
 */
public interface DetailComparerResult extends ComparerResult {
    /**
     * 获取差异位点名列表
     *
     * @return
     * @author jiangbin
     * @date 2013-4-14下午5:28:33
     */
    List<String> getDiffMarkerNames();

    /**
     * 设置差异位点名列表
     *
     * @param diffMarkerNames
     * @author jiangbin
     * @date 2013-4-14下午5:28:34
     */
    void setDiffMarkerNames(List<String> diffMarkerNames);

    /**
     * 获取无差异位点名称列表
     *
     * @return
     * @author jiangbin
     * @date 2013-4-14下午5:28:36
     */
    List<String> getNoDiffMarkerNames();

    /**
     * 设置无差异位点名称列表
     *
     * @param noDiffMarkerNames
     * @author jiangbin
     * @date 2013-4-14下午5:28:38
     */
    void setNoDiffMarkerNames(List<String> noDiffMarkerNames);

    /**
     * 获取比较位点总数
     *
     * @return
     * @author jiangbin
     * @date 2013-4-14下午5:28:38
     */
    Integer getMarkerCount();

    /**
     * 设置比较位点总数
     *
     * @param markerCount
     * @author jiangbin
     * @date 2013-4-14下午5:28:37
     */
    void setMarkerCount(Integer markerCount);

    /**
     * 获取差异位点数
     *
     * @return
     * @author jiangbin
     * @date 2013-4-14下午5:28:41
     */
    Integer getDiffMarkerCount();

    /**
     * 设置无差异位点数
     *
     * @param diffMarkerCount
     * @author jiangbin
     * @date 2013-4-14下午5:28:45
     */
    void setDiffMarkerCount(Integer diffMarkerCount);

    /**
     * 获取无差异位点数
     *
     * @return
     * @author jiangbin
     * @date 2013-4-14下午5:28:46
     */
    Integer getNoDiffMarkerCount();

    /**
     * 添加无差异位点数
     *
     * @param noDiffMarkerCount
     * @author jiangbin
     * @date 2013-4-14下午5:28:47
     */
    void setNoDiffMarkerCount(Integer noDiffMarkerCount);

    /**
     * 添加比较位点总数
     *
     * @param markerCount
     * @author jiangbin
     * @date 2013-4-14下午5:28:49
     */
    void addMarkerCount(Integer markerCount);

    /**
     * 添加差异位点数
     *
     * @param diffMarkerCount
     * @author jiangbin
     * @date 2013-4-14下午5:28:50
     */
    void addDiffMarkerCount(Integer diffMarkerCount);

    /**
     * 添加无差异位点数
     *
     * @param noDiffMarkerCount
     * @author jiangbin
     * @date 2013-4-14下午5:28:51
     */
    void addNoDiffMarkerCount(Integer noDiffMarkerCount);

    /**
     * 添加无差异位点名
     *
     * @param noDiffMarkerName
     * @author jiangbin
     * @date 2013-4-14下午5:28:53
     */
    void addNoDiffMarkerName(String noDiffMarkerName);

    /**
     * 添加差异位点名
     *
     * @param diffMarkerName
     * @author jiangbin
     * @date 2013-4-14下午5:28:54
     */
    void addDiffMarkerName(String diffMarkerName);

    /**
     * 获取遗传相似度
     *
     * @return
     * @author jiangbin
     * @date 2013-4-14下午5:28:55
     */
    Double getSimilarity();

    /**
     * 设置遗传相似度
     *
     * @param similarity
     * @author jiangbin
     * @date 2013-4-14下午5:28:57
     */
    void setSimilarity(Double similarity);

    /**
     * 获取差异Allele数
     *
     * @return
     * @author jiangbin
     * @date 2013-4-14下午5:28:58
     */
    Integer getDiffAlleleCount();

    /**
     * 设置差异Allele数
     *
     * @param diffAlleleCount
     * @author jiangbin
     * @date 2013-4-14下午5:29:00
     */
    void setDiffAlleleCount(Integer diffAlleleCount);

    /**
     * 获取缺失位点数据
     *
     * @param misss
     * @return void
     * @author jiangbin
     * @date 2019/11/25 3:30 下午
     **/
    void setMissMarkerNames(List<String> misss);

    /**
     * 添加缺失位点
     *
     * @param miss
     * @return void
     * @author jiangbin
     * @date 2020/1/30 9:22 PM
     **/
    void addMissMarkerNames(String miss);

    /**
     * 添加缺失位点列表
     *
     * @param misss
     * @return void
     * @author jiangbin
     * @date 2020/1/30 9:22 PM
     **/
    void addMissMarkerNames(List<String> misss);

    /**
     * 设置缺失位点数据
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/25 3:46 下午
     **/
    List<String> getMissMarkerNames();

    /**
     * 设置缺失位点数据
     *
     * @param missMarkerCount
     * @return void
     * @author jiangbin
     * @date 2019/11/25 3:30 下午
     **/
    void setMissMarkerCount(Integer missMarkerCount);

    /**
     * 获取缺失位点数据
     *
     * @return java.lang.Integer
     * @author jiangbin
     * @date 2019/11/25 3:45 下午
     **/
    Integer getMissMarkerCount();
}
