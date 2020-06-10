package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹的样品名称分组Mapper,key/value--样品名/指纹分组列表
 * @author jingbin
 * @date 2013-4-23下午11:41:25
 */
public class GeneSampleNameGroupMapper extends AbstractGeneGrouperMapper {

	private static final long serialVersionUID = -5324994942923704603L;

	@Override
	protected String getMapperKey(Gene object) {
		String samName = object.getSample().getSamName();
		if (StringUtils.isBlank(samName)) {
			return null;
		}
		return samName;
	}

}
