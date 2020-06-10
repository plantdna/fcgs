package com.fcgs.base.filter;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 引物名过滤器，过滤方法：引物名列表与给定引物组名做差异集运算，比如可以用来获取缺失位点列表
 * @author jiangbin
 * @date 2012-5-7上午11:07:05
 */
public class MarkerNamesFilter {

	/**
	 * 过滤出缺失位点
	 * @author jiangbin
	 * @param allPrimerNames 引物组中包含所有引物名
	 * @param noDiffMarkers 无差异位点列表
	 * @param diffMarkers 差异位点列表
	 * @return 过滤出的无差异位点列表
	 * @date 2012-6-14下午3:01:36
	 */
	public List<String> filter(List<String> allPrimerNames, List<String> noDiffMarkers, List<String> diffMarkers) {
		List<String> sourceMarkerNames = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(noDiffMarkers)) {
			sourceMarkerNames.addAll(noDiffMarkers);
		}
		if (!CollectionUtils.isEmpty(diffMarkers)) {
			sourceMarkerNames.addAll(diffMarkers);
		}
		return filter(allPrimerNames, sourceMarkerNames);
	}

	/**
	 * 引物名列表过滤算法:差异运算
	 * @param allPrimerNames 引物组中包含所有引物名
	 * @param sourceMarkerNames 源引物名列表
	 * @return 过滤后的引物名列表
	 */
	public List<String> filter(List<String> allPrimerNames, List<String> sourceMarkerNames) {
		List<String> missMarkerNames = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(allPrimerNames)) {
			missMarkerNames.addAll(allPrimerNames);
		}
		if (!CollectionUtils.isEmpty(sourceMarkerNames)) {
			missMarkerNames.removeAll(sourceMarkerNames);
		}
		return missMarkerNames;
	}

}
