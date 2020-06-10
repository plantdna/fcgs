package com.pids.core.template.utils;

import com.pids.core.mapper.MapperTemplate;
import com.pids.core.template.domain.Template;

/**
 * 模板名映射器,key/value--模板名/Excel模板信息
 * @author jiang
 * @date 2018年6月11日下午10:55:03
 */
public class TemplateNameMapper<S extends Template> extends MapperTemplate<S, S> {

	private static final long serialVersionUID = -5307193163247837794L;

	@Override
	protected String getMapperKey(S object) {
		return object.getTemplateName();
	}

	@Override
	protected S getMapperValue(S object) {
		return object;
	}

}
