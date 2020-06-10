package com.fcgs.base.domain.gene;

import java.io.Serializable;

/**
 * 指纹记录ID信息接口
 * @author jiangbin
 * @date 2015年7月5日下午2:26:07
 */
public interface GeneId extends Serializable {
	/**
	 * 设置指纹记录ID
	 * @author jiangbin
	 * @param geneId
	 * @date 2015年7月4日下午5:46:47
	 */
	public void setGeneId(String geneId);

	/**
	 * 获取指纹记录ID
	 * @author jiangbin
	 * @return
	 * @date 2015年7月4日下午5:46:47
	 */
	public String getGeneId();
}
