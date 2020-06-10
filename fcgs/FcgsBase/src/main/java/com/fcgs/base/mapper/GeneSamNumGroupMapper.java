package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;

/**
 * 按指纹样品条码号进行指纹分组
 * @author Andory
 * @date 2012-8-4上午1:01:21
 */
public class GeneSamNumGroupMapper extends AbstractGeneGrouperMapper {

	private static final long serialVersionUID = -6983789374701860485L;

	@Override
	public String getMapperKey(Gene source) {
		return source.getSample().getSamBarcode();
	}

}
