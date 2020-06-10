package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹的用户ID分组Mapper,key/values--用户ID/指纹(Gene)列表
 * @author jiangbin
 * @date 2013-4-9下午4:29:01
 */
public class GeneUpperUserIdGroupMapper extends AbstractGeneGrouperMapper {

	private static final long serialVersionUID = -7872377063760693317L;

	@Override
	protected String getMapperKey(Gene object) {
		return StringUtils.upperCase(object.getSample().getDnaManager());
	}
}
