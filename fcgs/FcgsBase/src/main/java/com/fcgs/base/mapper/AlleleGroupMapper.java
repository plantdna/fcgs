package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Allele;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * Allele信息Mapper,key/value--allele值/Allele对象分组列表
 * @author Jiangbin
 * @date 2013-8-20下午11:36:48
 */
public class AlleleGroupMapper extends GroupMapperTemplate<Allele, Allele> {

	private static final long serialVersionUID = 6914903091673726582L;

	@Override
	protected String getMapperKey(Allele object) {
		return StringUtils.stripToEmpty((int) object.getAllele() + "");
	}

	@Override
	public Allele convert(Allele source) throws ICoreException {
		return source;
	}

}
