package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 指纹样品条码号过滤器
 *
 * @author Jiangbin
 * @date 2013-6-15上午1:51:37
 */
public class GeneSamNumFilter extends ListFilterTemplate<Gene, String> {
    @Override
    protected String filter(Gene source, boolean isInternal) throws ICoreException {
        if (source.getSample() == null) {
            return null;
        }
        return source.getSample().getSamBarcode();
    }
}
