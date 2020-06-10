package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;

/**
 * 样品号+用户ID与指纹的分组Mapper，key/value--(SamNum-UserId)/Gene分组列表
 * @author Jiangbin
 * @date 2013-8-23下午8:36:46
 */
public class GeneSamNumUserIdGroupMapper extends AbstractGeneGrouperMapper {

	private static final long serialVersionUID = 7792634209410991929L;

	@Override
	protected String getMapperKey(Gene object) {
		if (object == null) {
			return null;
		}
		return getMapperKey(object.getSample().getSamBarcode(), object.getSample().getDnaManager());
	}

	/**
	 * 构建Key值
	 * @author Jiangbin
	 * @param samNum
	 * @param userId
	 * @return
	 * @date 2013-8-23下午8:39:17
	 */
	public String getMapperKey(String samNum, String userId) {
		return String.format("%s-%s", StringUtils.stripToEmpty(samNum), StringUtils.stripToEmpty(userId));
	}

	@Override
	public Gene convert(Gene source) throws ICoreException {
		return source;
	}

}
