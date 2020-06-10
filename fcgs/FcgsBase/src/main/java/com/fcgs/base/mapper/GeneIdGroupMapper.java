package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.GeneId;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;

/**
 * 指纹ID分组器,key/value--指纹ID/分组数据列表
 * @author jiangbin
 * @date 2017年6月1日上午9:43:41
 */
public class GeneIdGroupMapper<S extends GeneId> extends GroupMapperTemplate<S, S> {

	private static final long serialVersionUID = -2431184852556230088L;

	@Override
	public S convert(S source) throws ICoreException {
		return source;
	}

	@Override
	protected String getMapperKey(S object) {
		return object.getGeneId();
	}

}
