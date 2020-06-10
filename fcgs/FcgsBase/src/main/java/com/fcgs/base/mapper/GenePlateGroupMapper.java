package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹电泳板信息分组Mapper,key/value--GelRecordId/指纹分组列表
 *
 * @author jiangbin
 * @date 2019-08-14 14:56
 **/
public class GenePlateGroupMapper extends GroupMapperTemplate<Gene, Gene> {

    private static final long serialVersionUID = 6207780839224830634L;

    @Override
    public Gene convert(Gene source) throws ICoreException {
        return source;
    }

    @Override
    protected String getMapperKey(Gene object) {
        if (object != null && object.getLocation() != null && StringUtils.isNotBlank(object.getLocation().getPlate())) {
            return object.getLocation().getPlate().toUpperCase();
        }
        return null;
    }
}
