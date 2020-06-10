package com.fcgs.core.comparer;

import com.fcgs.core.comparer.domain.MarkerItem;
import com.fcgs.core.comparer.domain.MarkerItemContainer;
import com.fcgs.core.comparer.domain.MarkerItemPair;
import com.fcgs.core.comparer.mapper.MarkerItemMapper;
import com.fcgs.core.comparer.mapper.MarkerItemPrimerMapper;

import java.util.List;

/**
 * 位点比对算法（LCA算法）的接口定义
 *
 * @author jiangbin
 * @date 2019/11/1 5:39 下午
 **/
public interface MarkerItemComparer {

    /**
     * 比对两个位点分型数据是否为差异位点
     *
     * @param source        待比位点分型信息
     * @param target        对比位点分型信息
     * @param maxSizeOffset 最大碱基偏移量,注意：碱基差异必需小于该参数才判定为无差异
     * @return
     */
    boolean isDiff(MarkerItem source, MarkerItem target, int maxSizeOffset);

    /**
     * 比对位点分型间的差异性
     *
     * @param markerItems   位点分型信息
     * @param maxSizeOffset 碱基偏移量
     * @return java.util.List<com.viathink.markers.counter.SsrSampleDiffCounter.MarkerItemPair>
     * @author jiangbin
     * @date 2019/10/29 12:42 下午
     **/
    List<MarkerItemPair> compare(List<MarkerItem> markerItems, int maxSizeOffset);

    /**
     * 比对给定位点分型信息
     *
     * @param source        待比分型对象
     * @param target        对比分型对象
     * @param maxSizeOffset 碱基偏移量
     * @return com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemPair
     * @author jiangbin
     * @date 2019/11/6 12:53 下午
     **/
    MarkerItemPair compare(MarkerItem source, MarkerItem target, int maxSizeOffset);

    /**
     * 位点分型间比对
     *
     * @param sMarkerItems  待比位点分型列表
     * @param tMarkerItems  对比位点分型列表
     * @param maxSizeOffset 碱基偏移量
     * @param isSame        待比和对比指纹是否相同，该参数可以优化单指纹队列内部互比时的比对效率
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemPair>
     * @author jiangbin
     * @date 2019/11/6 12:39 下午
     **/
    List<MarkerItemPair> compare(List<MarkerItem> sMarkerItems, List<MarkerItem> tMarkerItems, int maxSizeOffset, boolean isSame);

    /**
     * 位点分型间比对
     *
     * @param sMarkerItems  待比位点分型列表
     * @param tMarkerItems  对比位点分型列表
     * @param maxSizeOffset 碱基偏移量
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemPair>
     * @author jiangbin
     * @date 2019/11/6 12:39 下午
     **/
    List<MarkerItemPair> compare(List<MarkerItem> sMarkerItems, List<MarkerItem> tMarkerItems, int maxSizeOffset);

    /**
     * 比对各引物的位点分型间差异信息
     *
     * @param mapper
     * @param maxSizeOffset
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemPair>
     * @author jiangbin
     * @date 2019/11/6 12:01 下午
     **/
    List<MarkerItemPair> compare(MarkerItemPrimerMapper mapper, int maxSizeOffset);

    /**
     * 比对各引物的位点分型间差异信息
     *
     * @param container     位点分型数据映射表容器对象
     * @param maxSizeOffset 最大碱基偏移量
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemPair>
     * @author jiangbin
     * @date 2019/11/20 2:15 下午
     **/
    List<MarkerItemPair> compare(MarkerItemContainer container, int maxSizeOffset);

    /**
     * 比对给定引物的位点分型间差异信息
     *
     * @param mapper        引物的位点分型映射信息
     * @param maxSizeOffset 碱基偏移量
     * @return java.util.List<com.viathink.ssr.core4.statistics.comparer.domain.MarkerItemPair>
     * @author jiangbin
     * @date 2019/11/7 1:39 下午
     **/
    List<MarkerItemPair> compare(MarkerItemMapper mapper, int maxSizeOffset);
}
