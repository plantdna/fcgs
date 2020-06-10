package com.fcgs.base.provider.domain;

/**
 * 指纹比较器参数支持接口
 * @author jiangbin
 * @date 2012-11-9下午12:23:59
 */
public interface ComparerProviderParams extends ProviderParams {
	/**
	 * 获取最大遗传相似度限定值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:53
	 */
	public Double getMaxSimilarity();
	
	/**
	 * 设置最大遗传相似度限定值
	 * @author jiangbin
	 * @param maxSimilarity
	 * @date 2012-11-9下午12:24:54
	 */
	public void setMaxSimilarity(Double maxSimilarity);
	
	/**
	 * 获取最小遗传相似度限定值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午12:24:56
	 */
	public Double getMinSimilarity();
	
	/**
	 * 设置最小遗传相似度限定值
	 * @author jiangbin
	 * @param minSimilarity
	 * @date 2012-11-9下午12:24:57
	 */
	public void setMinSimilarity(Double minSimilarity);
	
	/**
	 * 设置指纹比对所需参数支持信息
	 * @author jiangbin
	 * @param params
	 * @date 2012-11-9下午1:43:25
	 */
	public void setComparerProviderParams(ComparerProviderParams params);
	
	/**
	 * 设置最小无差异位点数
	 * @author Andory
	 * @param minNoDiffMarkerCount
	 * @date 2013年9月13日上午12:53:52
	 */
	public void setMinNoDiffMarkerCount(Integer minNoDiffMarkerCount);
	
	/**
	 * 获取最小无差异位点数
	 * @author Andory
	 * @return
	 * @date 2013年9月13日上午12:53:54
	 */
	public Integer getMinNoDiffMarkerCount();
	
	/**
	 * 设置最大无差异位点数
	 * @author Andory
	 * @param maxNoDiffMarkerCount
	 * @date 2013年9月13日上午12:53:55
	 */
	public void setMaxNoDiffMarkerCount(Integer maxNoDiffMarkerCount);
	
	/**
	 * 获取最大无差异位点数
	 * @author Andory
	 * @return
	 * @date 2013年9月13日上午12:53:56
	 */
	public Integer getMaxNoDiffMarkerCount();
	
	/**
	 * 设置最大差异杂合率
	 * @author ANDORY
	 * @param maxDiffHybridPercent
	 * @date 2016年1月4日下午5:11:31
	 */
	public void setMaxDiffHybridPercent(Double maxDiffHybridPercent);
	
	/**
	 * 获取最大差异杂合率
	 * @author ANDORY
	 * @return
	 * @date 2016年1月4日下午5:11:29
	 */
	public Double getMaxDiffHybridPercent();
	
	/**
	 * 设置最小差异杂合率
	 * @author ANDORY
	 * @param minDiffHybridPercent
	 * @date 2016年1月4日下午5:59:07
	 */
	public void setMinDiffHybridPercent(Double minDiffHybridPercent);
	
	/**
	 * 获取最小差异杂合率
	 * @author ANDORY
	 * @return
	 * @date 2016年1月4日下午5:59:10
	 */
	public Double getMinDiffHybridPercent();
}