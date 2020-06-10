package com.pids.core.mapper.utils;

import com.pids.core.mapper.MapperTemplate;
import com.pids.core.pair.Pair;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Pair<String, String>字符串映射关系表,key/value--String/String
 *
 * @author jiangbin
 * @date 2017年11月27日下午7:25:52
 */
public class StringMapper extends MapperTemplate<String, Pair<String, String>> {

    private static final long serialVersionUID = -1300266583181767326L;

    @Override
    protected String getMapperKey(Pair<String, String> object) {
        return object.getSource();
    }

    @Override
    protected String getMapperValue(Pair<String, String> object) {
        return object.getTarget();
    }

    /**
     * 添加字符串列表，设置成:Key=Value
     *
     * @param strList
     * @return void
     * @author jiangbin
     * @date 2019/11/26 3:11 下午
     **/
    public void add(List<String> strList) {
        if (CollectionUtils.isEmpty(strList)) {
            return;
        }
        for (String str : strList) {
            this.add(str, str);
        }
    }
}
