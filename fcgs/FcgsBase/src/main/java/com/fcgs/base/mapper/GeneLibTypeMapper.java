package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹与库类型映射关系表，key/value--指纹ID/库类型
 * @author Jiangbin
 * @date 2013-7-3上午2:32:49
 */
public class GeneLibTypeMapper extends MapperTemplate<String, Gene> {

	private static final long serialVersionUID = -8604715484772504839L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

	@Override
	protected String getMapperValue(Gene object) {
		if (object.getGeneLib() == null) {
			return "";
		}
		return StringUtils.stripToEmpty(object.getGeneLib() + "");
	}

}
