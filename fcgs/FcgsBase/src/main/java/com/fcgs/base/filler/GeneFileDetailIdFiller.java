package com.fcgs.base.filler;

import com.fcgs.base.genefile.GeneFileDetail;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;
import com.pids.core.id.ID;
import com.pids.core.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 用于对指纹对象和其关联记录进行关联ID填充操作，这样格式化后的指纹文件中即包含了指纹ID参数，
 * 再通过指纹文件目录的监控即可通过ID来更新缓存中的指纹数据
 * @author jiangbin
 * @date 2017年11月27日下午6:51:23
 */

public class GeneFileDetailIdFiller extends ListFillerTemplate<GeneFileDetail, GeneFileDetail> {

	@Override
	protected GeneFileDetail fill(GeneFileDetail source, boolean isInternal) throws ICoreException {
		if (source == null || source.getGene() == null) {
			return source;
		}
		if (!source.getClass().isAssignableFrom(ID.class)) {
			return source;
		}

		ID id = (ID) source;

		//存在ID则直接关联，否则创建ID再关联
		if (StringUtils.isNotBlank(id.getId())) {
			source.getGene().setGeneId(id.getId());
		} else {
			id.setId(UuidUtil.getUuid());
			source.getGene().setGeneId(id.getId());
		}
		return source;
	}

}
