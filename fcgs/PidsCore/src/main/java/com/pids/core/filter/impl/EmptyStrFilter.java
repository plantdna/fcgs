package com.pids.core.filter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 空字符串过滤器，给定字符串列表中所有值为null或空字符串的值将被过滤掉
 *
 * @author jiangbin
 * @date 2013-3-31下午2:26:51
 */
public class EmptyStrFilter extends ListFilterTemplate<String, String> {
    @Override
    protected String filter(String source, boolean isInternal) throws ICoreException {
        return StringUtils.isBlank(source) ? null : source;
    }

}
