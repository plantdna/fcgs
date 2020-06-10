package com.pids.core.mapper.utils;

import com.pids.core.mapper.MapperTemplate;

/**
 * 字符串映射关系表,key/value--String/String
 *
 * @author jiangbin
 * @date 2017年11月27日下午7:25:52
 */
public class KeyMapper extends MapperTemplate<String, String> {

    private static final long serialVersionUID = -1300266583181767326L;

    @Override
    protected String getMapperKey(String object) {
        return object;
    }

    @Override
    protected String getMapperValue(String object) {
        return object;
    }

    /**
     * 指定的Key值是否存在
     *
     * @param key
     * @return boolean
     * @author jiangbin
     * @date 2019/11/27 5:12 下午
     **/
    public boolean has(String key) {
        return this.get(key) != null;
    }

}
