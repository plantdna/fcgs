package com.fcgs.core.comparer.utils;

import com.fcgs.core.comparer.domain.MarkerItemPair;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 过滤出存在差异的位点分型结果
 *
 * @author jiangbin
 * @date 2019/11/19 7:04 下午
 **/
public class DiffMarkerItemPairFilter extends ListFilterTemplate<MarkerItemPair, MarkerItemPair> {

    @Override
    protected MarkerItemPair filter(MarkerItemPair source, boolean isInternal) throws ICoreException {
        return source.isDiff() ? source : null;
    }
}
