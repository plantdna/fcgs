package com.fcgs.base.domain.gene;

import com.fcgs.base.domain.DnaBarcode;
import com.fcgs.base.domain.GeneAuditWay;
import com.fcgs.base.domain.GeneStatus;
import com.fcgs.base.domain.GeneSupport;
import com.fcgs.base.domain.handle.GeneHandle;
import com.fcgs.base.domain.handle.MarkerHandle;
import com.fcgs.base.domain.handle.SizeHandle;
import com.pids.core.id.ID;

import java.io.Serializable;
import java.util.List;

/**
 * 指纹信息对象接口
 *
 * @author jiangbin
 * @date 2012-4-11下午4:43:55
 */
public interface Gene extends Serializable, GeneHandle, GeneStatus, SizeHandle, MarkerHandle, GeneSupport, GeneAuditWay,
        DnaBarcode, GeneId, ID {

    /**
     * 设置指纹库类型
     *
     * @param geneLib
     * @author jiangbin
     * @date 2014年3月21日下午1:01:44
     */
    void setGeneLib(Integer geneLib);

    /**
     * 获取指纹库类型
     *
     * @return
     * @author jiangbin
     * @date 2014年3月21日下午1:01:43
     */
    Integer getGeneLib();

    /**
     * 添加位点
     *
     * @param marker
     * @author jiangbin
     * @date 2014年3月21日下午2:55:19
     */
    void addMarker(Marker marker);

    /**
     * 获取指纹内位点名列表
     *
     * @return
     * @author jiangbin
     * @date 2014年3月21日下午5:51:19
     */
    List<String> getMarkerNames();

    /**
     * 查找指定引物合成编号的位点
     *
     * @param dye
     * @return
     * @author Jiangbin
     * @date 2014-4-17上午1:39:33
     */
    Marker getMarkerByDye(String dye);

    /**
     * 查找指定引物名的位点
     *
     * @param primerName
     * @return
     * @author Jiangbin
     * @date 2014-4-17上午1:39:34
     */
    Marker getMarkerByName(String primerName);

    /**
     * 获取给定位点名列表对应的位点列表
     *
     * @param primerNameList 引物名列表
     * @return 位点列表
     * @author jiangbin
     * @date 2016年7月25日下午4:19:23
     */
    List<Marker> getMarkerByNameList(List<String> primerNameList);

    /**
     * 设置指纹来源
     * 如：来源包括：excel、 zip、fsa
     *
     * @param geneOrigin
     * @author Ritchieyan
     * @date 2014年8月30日下午6:36:46
     */
    void setGeneOrigin(Integer geneOrigin);

    /**
     * 获取指纹来源
     *
     * @return
     * @author Ritchieyan
     * @date 2014年8月30日下午6:39:34
     */
    Integer getGeneOrigin();

    /**
     * 移除位点列表
     *
     * @param markerNames
     * @author jiangbin
     * @date 2015年2月14日上午12:18:38
     */
    void removeMarkers(List<String> markerNames);

    /**
     * 添加位点列表
     *
     * @param markers
     * @author jiangbin
     * @date 2015年2月14日上午12:18:40
     */
    void addMarkers(List<Marker> markers);

    /**
     * 清空DNA条码号
     *
     * @author jiangbin
     * @date 2015年5月14日下午12:04:53
     */
    void clearDnaBarcode();

    /**
     * 是否为单株指纹数据
     *
     * @return
     * @author Jiangbin
     * @date 2015年5月16日下午11:21:19
     */
    boolean isSingleGene();

    /**
     * 设置是否为单株指纹数据
     *
     * @param isSingleGene
     * @author Jiangbin
     * @date 2015年5月16日下午11:21:20
     */
    void setSingleGene(boolean isSingleGene);

    /**
     * 设置杂合率
     *
     * @param hybridPercent
     * @author ANDORY
     * @date 2016年1月4日下午4:53:23
     */
    void setHybridPercent(Double hybridPercent);

    /**
     * 获取杂合率
     *
     * @return
     * @author ANDORY
     * @date 2016年1月4日下午4:53:22
     */
    Double getHybridPercent();

    /**
     * 获取手动审核位点列表
     *
     * @return
     * @author jiangbin
     * @date 2015年2月13日下午11:49:43
     */
    List<Marker> getManualMarkers();

    /**
     * 设置给定位点列表为手动审核位点
     *
     * @param markerNames
     * @author jiangbin
     * @date 2015年2月13日下午11:43:18
     */
    @Override
    void setManualMarkerNames(List<String> markerNames);

    /**
     * 设置位点是否为手动审核位点
     *
     * @param markerName 引物名
     * @param isManual   是否为手动审核位点,true/false--是/否
     * @author jiangbin
     * @date 2016年7月25日下午4:12:19
     */
    void setManualMarker(String markerName, boolean isManual);

    /**
     * 设置位点列表是否为手动审核位点
     *
     * @param markerNames 引物名列表
     * @param isManual    是否为手动审核位点,true/false--是/否
     * @author jiangbin
     * @date 2016年7月25日下午4:12:21
     */
    void setManaulMarkers(List<String> markerNames, boolean isManual);

    /**
     * 清除所有位点的手动审核标志
     *
     * @author jiangbin
     * @date 2016年7月25日下午11:33:28
     */
    void clearAllManualTag();

    /**
     * 是否为手动审核指纹数据
     *
     * @return
     * @author jiangbin
     * @date 2016年7月26日上午11:11:00
     */
    boolean isManualGene();

    /**
     * 是否为手动审核位点
     *
     * @param markerName 位点名
     * @return
     * @author jiangbin
     * @date 2016年7月26日下午12:49:16
     */
    boolean isManualMarker(String markerName);

    /**
     * 获取种属
     *
     * @return
     * @author jiangbin
     * @date 2018年8月27日下午3:22:55
     */
    String getSamSpecies();

    /**
     * 设置用户
     *
     * @param user
     * @author WUHAOTIAN
     * @date 2019年4月25日下午2:18:54
     */
    void setUser(String user);

    /**
     * 获取用户
     *
     * @return
     * @author WUHAOTIAN
     * @date 2019年4月25日下午2:19:01
     */
    String getUser();

    /**
     * 移除指定引物名称位点
     *
     * @param primerName
     * @return void
     * @author jiangbin
     * @date 2019/11/13 4:14 下午
     **/
    void removeMarker(String primerName);

    /**
     * 获取缺失位点列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2020/1/5 4:44 下午
     **/
    List<String> getMissMarkers();

    /**
     * 设置缺失位点列表
     *
     * @param missMarkers
     * @return void
     * @author jiangbin
     * @date 2020/1/5 4:44 下午
     **/
    void setMissMarkers(List<String> missMarkers);

    /**
     * 添加缺失位点
     *
     * @param missMarker
     * @return void
     * @author jiangbin
     * @date 2020/1/5 4:44 下午
     **/
    void addMissMarker(String missMarker);

    /**
     * 获取位点总数
     *
     * @return int
     * @author jiangbin
     * @date 2020/1/5 6:23 下午
     **/
    int getMarkerCount();

    /**
     * 设置位点总数
     *
     * @param markerCount
     * @return void
     * @author jiangbin
     * @date 2020/1/5 6:23 下午
     **/
    void setMarkerCount(int markerCount);
}
