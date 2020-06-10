package com.fcgs.core.comparer.mapper;

import com.fcgs.core.comparer.domain.MarkerItemPair;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;

/**
 * 位点分型数据对按引物名称分组，key/value--引物名称/位点分型列表
 *
 * @author jiangbin
 * @date 2019/11/21 10:04 上午
 **/
public class MarkerItemPairPrimerGroupMapper extends GroupMapperTemplate<MarkerItemPair, MarkerItemPair> {
    @Override
    public MarkerItemPair convert(MarkerItemPair source) throws ICoreException {
        return source;
    }

    @Override
    protected String getMapperKey(MarkerItemPair object) {
        return object.getPrimerName();
    }
}
