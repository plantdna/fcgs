package com.fcgs.core.comparer.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;

/**
 * 指纹ID与位点数的映射表,key/value--指纹ID/位点数
 *
 * @author jiangbin
 * @date 2019/11/12 2:36 下午
 **/
public class GeneIdMarkerCountMapper extends MapperTemplate<Integer, Gene> {
    @Override
    protected String getMapperKey(Gene object) {
        return object.getGeneId();
    }

    @Override
    protected Integer getMapperValue(Gene object) {
        return object.size();
    }
}
