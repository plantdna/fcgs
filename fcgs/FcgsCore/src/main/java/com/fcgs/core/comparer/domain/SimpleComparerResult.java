package com.fcgs.core.comparer.domain;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * 指纹比对结果核心参数
 * 
 * @author jiangbin
 * @date 2012-10-29上午11:27:48
 */
public class SimpleComparerResult implements ComparerResult {
	private static final long serialVersionUID = 4395389382708807355L;
	private String sourceGeneId;
	private String targetGeneId;

	@Override
	public String getSourceGeneId() {
		return sourceGeneId;
	}

	@Override
	public void setSourceGeneId(String sourceGeneId) {
		this.sourceGeneId = sourceGeneId;
	}

	@Override
	public String getTargetGeneId() {
		return targetGeneId;
	}

	@Override
	public void setTargetGeneId(String targetGeneId) {
		this.targetGeneId = targetGeneId;
	}

	@Override
	public void setComaprerResult(ComparerResult source) {
		if (source != null) {
			try {
				BeanUtils.copyProperties(source, this);
			} catch (Exception e) {
				Logger.getLogger(this.getClass()).warn(e);
			}
		}
	}
}
