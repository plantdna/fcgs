package com.fcgs.core.comparer.utils;

import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.pids.core.mapper.MapperTemplate;
import com.pids.core.mapper.utils.KeyMapper;

/**
 * 快速比对结果对ID分组功能,key/value--待比指纹ID/对比指纹ID的Mapper
 *
 * @author jiangbin
 * @date 2019/11/27 5:01 下午
 **/
public class SmartComparerResultGroupMapper extends MapperTemplate<KeyMapper, SmartComparerResult> {

	private static final long serialVersionUID = -605125697514554308L;

	@Override
    protected String getMapperKey(SmartComparerResult object) {
        return object.getSourceGeneId();
    }

    @Override
    protected KeyMapper getMapperValue(SmartComparerResult object) {
        KeyMapper mapper = get(object.getSourceGeneId());
        if (mapper == null) {
            mapper = new KeyMapper();
        }
        mapper.add(object.getTargetGeneId());
        return mapper;
    }

    /**
     * 给定的待比和对比指纹ID是否存在
     *
     * @param sId 待比指纹ID
     * @param tId 对比指纹ID
     * @return boolean
     * @author jiangbin
     * @date 2019/11/27 5:12 下午
     **/
    public boolean has(String sId, String tId) {
        KeyMapper mapper = this.get(sId);
        if (mapper == null) {
            return false;
        }
        return mapper.has(tId);
    }
}
