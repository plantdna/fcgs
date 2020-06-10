package com.fcgs.base.datacopier;

import com.fcgs.base.domain.gene.Allele;
import com.pids.core.datacopier.utils.SimpleDataCopier;
import com.pids.core.exception.ICoreException;

/**
 * Allele对象数据深度拷贝器
 * @author jiangbin
 * @date 2012-10-15下午9:41:57
 */
public class AlleleCopier extends SimpleDataCopier<Allele> {

	@Override
	public Allele copy(Allele source) throws ICoreException {
		return super.copy(source);
	}

	@Override
	public boolean copy(Allele source, Allele target) throws ICoreException {
		return super.copy(source, target);
	}
}
