package com.fcgs.base.mapper;

import com.fcgs.base.domain.handle.GeneLocationHandle;
import com.fcgs.base.formatter.PlateLocationFormatter;
import com.pids.core.mapper.MapperTemplate;

/**
 * 指纹电泳板位置信息Mapper,key/value--GelRecordId,Well/指纹位置信息对象
 * @author jiangbin
 * @date 2014年5月13日下午4:46:31
 */
public class GeneGelLocationMapper<S extends GeneLocationHandle> extends MapperTemplate<S, S> {

	private static final long serialVersionUID = 6207780839224830634L;
	private final PlateLocationFormatter formatter;

	public GeneGelLocationMapper() {
		formatter = new PlateLocationFormatter();
	}

	@Override
	protected String getMapperKey(S object) {
		return formatter.getGelLocationKey(object.getLocation());
	}

	@Override
	protected S getMapperValue(S object) {
		return object;
	}

}
