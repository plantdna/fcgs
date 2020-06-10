package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹库类型分组Mapper，key/value--指纹库类型/分组指纹列表
 * @author Jiangbin
 * @date 2013-7-3上午2:32:49
 */
public class GeneLibTypeGroupMapper extends AbstractGeneGrouperMapper {

	private static final long serialVersionUID = -8604715484772504839L;

	@Override
	protected String getMapperKey(Gene object) {
		return StringUtils.stripToEmpty(object.getGeneLib() == null ? "0" : object.getGeneLib() + "");
	}

}
