package com.fcgs.core.comparer.mapper;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.mapper.MapperTemplate;

/**
 * Key/Value--引物名-位点Allele字符串/位点分型信息分组列表
 *
 * @author jiangbin
 * @date 2019/10/28 3:46 下午
 **/
public class MarkerItemPrimerMapper extends MapperTemplate<MarkerItemMapper, Marker> {

    @Override
    protected MarkerItemMapper getMapperValue(Marker object) {
        MarkerItemMapper mapper = this.get(object.getPrimerName());
        if (mapper == null) {
            mapper = new MarkerItemMapper();
        }
        mapper.add(object);
        return mapper;
    }

    @Override
    protected String getMapperKey(Marker object) {
        return object.getPrimerName();
    }
}
