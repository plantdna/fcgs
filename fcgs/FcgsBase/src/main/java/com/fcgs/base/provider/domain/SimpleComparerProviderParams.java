package com.fcgs.base.provider.domain;

import org.apache.log4j.Logger;

/**
 * 比对算法参数支持类，其默认设置最大碱基偏移量为2
 * @author jiangbin
 * @date 2014年3月7日下午3:04:40
 */
public class SimpleComparerProviderParams implements ComparerProviderParams {
	private static final long serialVersionUID = -1195730143034565661L;
	private Integer minSizeOffset;
	private Integer maxSizeOffset = 2;
	private Integer maxDiffMarkerCount;
	private Integer minDiffMarkerCount;
	private Integer maxNoDiffMarkerCount;
	private Integer minNoDiffMarkerCount;
	private Integer maxMarkerCount;
	private Integer minMarkerCount;
	private Double maxSimilarity;
	private Double minSimilarity;
	private Double maxDiffHybridPercent;
	private Double minDiffHybridPercent;
	
	@Override
	public Integer getMinSizeOffset() {
		return this.minSizeOffset;
	}
	
	@Override
	public void setMinSizeOffset(Integer minSizeOffset) {
		this.minSizeOffset = minSizeOffset;
	}
	
	@Override
	public Integer getMaxSizeOffset() {
		return this.maxSizeOffset;
	}
	
	@Override
	public void setMaxSizeOffset(Integer maxSizeOffset) {
		this.maxSizeOffset = maxSizeOffset;
	}
	
	@Override
	public Integer getMaxDiffMarkerCount() {
		return this.maxDiffMarkerCount;
	}
	
	@Override
	public void setMaxDiffMarkerCount(Integer maxDiffMarkerCount) {
		this.maxDiffMarkerCount = maxDiffMarkerCount;
	}
	
	@Override
	public Integer getMinDiffMarkerCount() {
		return this.minDiffMarkerCount;
	}
	
	@Override
	public void setMinDiffMarkerCount(Integer minDiffMarkerCount) {
		this.minDiffMarkerCount = minDiffMarkerCount;
	}
	
	@Override
	public Integer getMaxMarkerCount() {
		return this.maxMarkerCount;
	}
	
	@Override
	public void setMaxMarkerCount(Integer maxMarkerCount) {
		this.maxMarkerCount = maxMarkerCount;
	}
	
	@Override
	public Integer getMinMarkerCount() {
		return this.minMarkerCount;
	}
	
	@Override
	public void setMinMarkerCount(Integer minMarkerCount) {
		this.minMarkerCount = minMarkerCount;
	}
	
	@Override
	public Double getMaxSimilarity() {
		return this.maxSimilarity;
	}
	
	@Override
	public void setMaxSimilarity(Double maxSimilarity) {
		this.maxSimilarity = maxSimilarity;
	}
	
	@Override
	public Double getMinSimilarity() {
		return this.minSimilarity;
	}
	
	@Override
	public void setMinSimilarity(Double minSimilarity) {
		this.minSimilarity = minSimilarity;
	}
	
	@Override
	public Integer getMaxNoDiffMarkerCount() {
		return this.maxNoDiffMarkerCount;
	}
	
	@Override
	public void setMaxNoDiffMarkerCount(Integer maxNoDiffMarkerCount) {
		this.maxNoDiffMarkerCount = maxNoDiffMarkerCount;
	}
	
	@Override
	public Integer getMinNoDiffMarkerCount() {
		return this.minNoDiffMarkerCount;
	}
	
	@Override
	public void setMinNoDiffMarkerCount(Integer minNoDiffMarkerCount) {
		this.minNoDiffMarkerCount = minNoDiffMarkerCount;
	}
	
	@Override
	public Double getMaxDiffHybridPercent() {
		return maxDiffHybridPercent;
	}
	
	@Override
	public void setMaxDiffHybridPercent(Double maxDiffHybridPercent) {
		this.maxDiffHybridPercent = maxDiffHybridPercent;
	}
	
	@Override
	public Double getMinDiffHybridPercent() {
		return minDiffHybridPercent;
	}
	
	@Override
	public void setMinDiffHybridPercent(Double minDiffHybridPercent) {
		this.minDiffHybridPercent = minDiffHybridPercent;
	}
	
	@Override
	public void setComparerProviderParams(ComparerProviderParams params) {
		if (params == null) {
			return;
		}
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(params, this);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).warn(e);
		}
	}
	
}
