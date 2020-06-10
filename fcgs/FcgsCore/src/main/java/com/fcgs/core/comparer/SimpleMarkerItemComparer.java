package com.fcgs.core.comparer;

import com.fcgs.core.comparer.domain.*;
import com.fcgs.core.comparer.mapper.MarkerItemMapper;
import com.fcgs.core.comparer.mapper.MarkerItemPrimerMapper;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 位点比对算法（LCA算法）实现
 *
 * @author jiangbin
 * @date 2019/11/1 5:39 下午
 **/
public class SimpleMarkerItemComparer implements MarkerItemComparer {

    @Override
    public boolean isDiff(MarkerItem source, MarkerItem target, int maxSizeOffset) {
        //判定分型类型是否相同：纯合子和杂合子
        if (source.isIsozygote() != target.isIsozygote()) {
            return true;
        }

        boolean isSame = Math.abs(source.getAllele1() - target.getAllele1()) < maxSizeOffset;
        boolean isSame2 = Math.abs(source.getAllele2() - target.getAllele2()) < maxSizeOffset;
        boolean isSame3 = Math.abs(source.getAllele1() - target.getAllele2()) < maxSizeOffset;
        boolean isSame4 = Math.abs(source.getAllele2() - target.getAllele1()) < maxSizeOffset;
        return !((isSame && isSame2) || (isSame3 && isSame4));
    }

    @Override
    public List<MarkerItemPair> compare(List<MarkerItem> markerItems, int maxSizeOffset) {
        if (CollectionUtils.isEmpty(markerItems)) {
            return null;
        }
        List<MarkerItemPair> pairs = new ArrayList<>();
        for (MarkerItem source : markerItems) {
            for (MarkerItem target : markerItems) {
                MarkerItemPair pair = new SimpleMarkerItemPair();
                pair.setDiff(this.isDiff(source, target, maxSizeOffset));
                pair.setSource(source);
                pair.setTarget(target);
                pair.setPrimerName(source.getPrimerName());
                pairs.add(pair);
            }
        }

        return pairs;
    }

    @Override
    public List<MarkerItemPair> compare(List<MarkerItem> sMarkerItems, List<MarkerItem> tMarkerItems, int maxSizeOffset, boolean same) {
        if (CollectionUtils.isEmpty(sMarkerItems) || CollectionUtils.isEmpty(tMarkerItems)) {
            return null;
        }

        MarkerItemPairCache cache = new MarkerItemPairCache();
        List<MarkerItemPair> pairs = new ArrayList<>();
        for (MarkerItem source : sMarkerItems) {
            for (MarkerItem target : tMarkerItems) {
                //待比和对比指纹相同时，通过本功能规避多余结果统计以提升性能
                if (same) {
                    if (cache.has(source, target)) {
                        continue;
                    }
                    cache.cache(source, target);
                }

                //比对位点分型
                pairs.add(compare(source, target, maxSizeOffset));
            }
        }

        return pairs;
    }

    @Override
    public List<MarkerItemPair> compare(List<MarkerItem> sMarkerItems, List<MarkerItem> tMarkerItems, int maxSizeOffset) {
        return this.compare(sMarkerItems, tMarkerItems, maxSizeOffset, false);
    }

    @Override
    public MarkerItemPair compare(MarkerItem source, MarkerItem target, int maxSizeOffset) {
        if (source == null || target == null) {
            return null;
        }

        MarkerItemPair pair = new SimpleMarkerItemPair();
        pair.setDiff(this.isDiff(source, target, maxSizeOffset));
        pair.setSource(source);
        pair.setTarget(target);
        pair.setPrimerName(source.getPrimerName());
        return pair;
    }

    @Override
    public List<MarkerItemPair> compare(MarkerItemPrimerMapper mapper, int maxSizeOffset) {
        if (mapper == null || mapper.isEmpty()) {
            return null;
        }
        List<MarkerItemPair> results = new ArrayList<>();//分型结果表
        Set<String> primerNames = mapper.keySet();
        for (String primerName : primerNames) {
            MarkerItemMapper pmapper = mapper.get(primerName);
            List<MarkerItemPair> pairs = compare(pmapper, maxSizeOffset);
            if (CollectionUtils.isNotEmpty(pairs)) {
                results.addAll(pairs);
            }
        }
        return results;
    }

    @Override
    public List<MarkerItemPair> compare(MarkerItemContainer container, int maxSizeOffset) {
        if (container == null || container.getSource().isEmpty() || container.getTarget().isEmpty()) {
            return null;
        }
        List<MarkerItemPair> results = new ArrayList<>();//分型结果表
        List<String> primerNames = container.getPrimerNames();
        for (String primerName : primerNames) {
            List<MarkerItem> smarkers = container.getSourceMarkerItems(primerName);
            List<MarkerItem> tmarkers = container.getTargetMarkerItems(primerName);
            if (CollectionUtils.isEmpty(smarkers) || CollectionUtils.isEmpty(tmarkers)) {
                continue;
            }
            List<MarkerItemPair> pairs = compare(smarkers, tmarkers, maxSizeOffset, container.isSame());
            if (CollectionUtils.isNotEmpty(pairs)) {
                results.addAll(pairs);
            }
        }
        return results;
    }

    @Override
    public List<MarkerItemPair> compare(MarkerItemMapper mapper, int maxSizeOffset) {
        if (mapper == null || mapper.isEmpty()) {
            return null;
        }
        List<MarkerItemPair> results = new ArrayList<>();//分型结果表
        List<String> pkeys = mapper.getKeys();//分型列表
        for (int i = 0; i < pkeys.size(); i++) {
            String sMarkerStr = pkeys.get(i);
            MarkerItem sMarkerItem = mapper.get(sMarkerStr);
            for (int j = i + 1; j < pkeys.size(); j++) {//避免重复比对
                String tMarkerStr = pkeys.get(j);
                MarkerItem tMarkerItem = mapper.get(tMarkerStr);
                MarkerItemPair pair = this.compare(sMarkerItem, tMarkerItem, maxSizeOffset);
                results.add(pair);
            }
        }
        return results;
    }

}
