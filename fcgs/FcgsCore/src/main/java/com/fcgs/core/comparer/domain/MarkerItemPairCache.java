package com.fcgs.core.comparer.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 位点分型数据对缓存，用于去除重复分析结果对
 *
 * @author jiangbin
 * @date 2019/11/21 9:26 上午
 **/
public class MarkerItemPairCache {
    private Map<String, String> cache = new HashMap<>();

    /**
     * 缓存分型数据对
     *
     * @param source
     * @param target
     * @return void
     * @author jiangbin
     * @date 2019/11/21 9:30 上午
     **/
    public void cache(MarkerItem source, MarkerItem target) {
        String sMarkerStr = source.getMarkerStr();
        String tMarkerStr = target.getMarkerStr();
        String key = sMarkerStr + "-" + tMarkerStr;
        cache.put(key, key);
        key = tMarkerStr + "-" + sMarkerStr;
        cache.put(key, key);
    }

    /**
     * 分型数据对是否已经被缓存
     *
     * @param source
     * @param target
     * @return boolean
     * @author jiangbin
     * @date 2019/11/21 9:30 上午
     **/
    public boolean has(MarkerItem source, MarkerItem target) {
        String sMarkerStr = source.getMarkerStr();
        String tMarkerStr = target.getMarkerStr();
        String key = sMarkerStr + "-" + tMarkerStr;
        if (StringUtils.isNotBlank(cache.get(key))) {
            return true;
        }
        key = tMarkerStr + "-" + sMarkerStr;
        return StringUtils.isNotBlank(cache.get(key));
    }
}
