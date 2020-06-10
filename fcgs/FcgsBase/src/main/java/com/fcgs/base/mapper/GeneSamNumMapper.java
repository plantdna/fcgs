package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;

/**
 * 样品编号与指纹的映射关系表，key/value--样品编号/指纹信息，
 * 本Mapper可用于按样品编号去重指纹的功能
 * @author Andory
 * @date 2013-7-25上午9:20:31
 */
public class GeneSamNumMapper extends AbstractGeneMapper {

	private static final long serialVersionUID = 1987328862975479486L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getSample().getSamBarcode();
	}

}
