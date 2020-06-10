package com.fcgs.base.datacopier;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.i18n.FcgsBase3I18N;
import com.pids.core.datacopier.utils.SimpleDataCopier;

/**
 * 深度拷贝Gene对象
 * @author jiangbin
 * @date 2012-4-16上午12:42:15
 */
public class GeneCopier extends SimpleDataCopier<Gene> {
    @Override
    public Gene copy(Gene source) {
        try {
            return super.copy(source);
        } catch (Exception e) {
            throw new RuntimeException(FcgsBase3I18N.SSR_GENECOPIER_ERROR01.get());
        }
    }

    @Override
    public boolean copy(Gene source, Gene target) {
        try {
            return super.copy(source, target);
        } catch (Exception e) {
            throw new RuntimeException(FcgsBase3I18N.SSR_GENECOPIER_ERROR01.get());
        }
    }
}
