package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;

/**
 * 样品条码号与指纹间映射表,key/value--样品条码号/指纹对象
 *
 * @author jiangbin
 * @date 2019-08-18 15:01
 **/
public class GeneSamBarcodeMapper extends MapperTemplate<Gene, Gene> {
    @Override
    protected String getMapperKey(Gene object) {
        return object.getSamBarcode();
    }

    @Override
    protected Gene getMapperValue(Gene object) {
        return object;
    }
}
