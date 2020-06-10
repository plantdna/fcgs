package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹ID与指纹审核状态的映射关系，key/value--指纹ID/指纹审核状态
 * @author Andory
 * @date 2013-6-20下午4:32:37
 */
public class GeneIdStatusMapper extends MapperTemplate<String, Gene> {

	private static final long serialVersionUID = -7673136836626489179L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

	@Override
	protected String getMapperValue(Gene object) {
		Integer number = object.getGeneStatus();
		number = number == null ? 0 : number.intValue();
		return StringUtils.stripToEmpty(number + "");
	}

}
