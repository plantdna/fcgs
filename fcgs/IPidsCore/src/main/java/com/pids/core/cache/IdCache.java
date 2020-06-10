package com.pids.core.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存ID数据对，用于数据处理过程中的去重
 *
 * @author jiangbin
 * @date 2019/11/26 10:20 上午
 **/
public class IdCache {
    private Map<String, Map<String, String>> cache = new HashMap<>();

    /**
     * 缓存ID数据对
     *
     * @param sId 源ID
     * @param tId 目标ID
     * @return boolean true/false--存在缓存/不存在缓存
     * @author jiangbin
     * @date 2019/11/26 10:25 上午
     **/
    public boolean cache(String sId, String tId) {
        Map<String, String> map = cache.get(sId);
        if (map == null) {
            map = new HashMap<>();
            map.put(tId, tId);
            cache.put(sId, map);
            return false;
        } else {
            String value = map.get(tId);
            if (value == null) {
                map.put(tId, tId);
                return false;
            } else {
                return true;
            }
        }
    }
}
