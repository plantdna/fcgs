package com.fcgs.core.comparer.mapper;

import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.pids.core.mapper.MapperTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 待比和对比指纹ID对映射表,key/value--待比ID/对比ID
 *
 * @author jiangbin
 * @date 2019/11/22 5:38 下午
 **/
public class GeneIdPairMapper extends MapperTemplate<Map<String, String>, SmartComparerResult> {
    @Override
    protected String getMapperKey(SmartComparerResult object) {
        return object.getSourceGeneId();
    }

    @Override
    protected Map<String, String> getMapperValue(SmartComparerResult object) {
        Map<String, String> map = this.get(object.getSourceGeneId());
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(object.getTargetGeneId(), object.getTargetGeneId());
        return map;
    }

    /**
     * 给定待比和对比指纹ID数据对是否存在
     *
     * @param sGeneId 待比指纹ID
     * @param tGeneId 对比指纹ID
     * @return boolean
     * @author jiangbin
     * @date 2019/11/22 9:07 下午
     **/
    public boolean has(String sGeneId, String tGeneId) {
        Map<String, String> map = this.get(sGeneId);
        if (map == null) {
            return false;
        }
        return tGeneId.equals(map.get(tGeneId));
    }

}
