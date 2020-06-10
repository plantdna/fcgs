package com.fcgs.base.marker.utils;

import com.fcgs.base.filter.MarkerNamesFilter;
import com.pids.core.exception.ICoreException;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 基于位点名列表视图信息接口的缺失位点填充器
 * @author jiangbin
 * @date 2012-11-24上午12:51:25
 */
public class MissMarkerNamesFiller {

	/**
	 * 填充给定位点名列表视图信息中的缺失位点
	 * @author jiangbin
	 * @param allPrimerNames 引物组中所有引物名
	 * @param sources 位点名列表视图信息
	 * @return
	 * @throws ICoreException
	 * @date 2016年7月25日下午11:47:19
	 */
	public boolean fill(List<String> allPrimerNames, List<MarkerNamesViwer> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(allPrimerNames) || CollectionUtils.isEmpty(sources)) {
			return false;
		}
		for (MarkerNamesViwer source : sources) {
			this.fill(allPrimerNames, source);
		}
		return true;
	}

	/**
	 * 填充给定位点名列表视图信息中的缺失位点
	 * @author jiangbin
	 * @param allPrimerNames 引物组中所有引物名
	 * @param source 位点名视图信息
	 * @return
	 * @throws ICoreException
	 * @date 2016年7月25日下午11:48:24
	 */
	public boolean fill(List<String> allPrimerNames, MarkerNamesViwer source) throws ICoreException {
		if (CollectionUtils.isEmpty(allPrimerNames) || source == null) {
			return false;
		}

		//差异位点
		List<String> diffMarkerNames = source.getDiffMarkerNames();
		source.setDiffMarkerCount(ListUtils.size(diffMarkerNames));

		//无差异位点
		List<String> noDiffMarkerNames = source.getNoDiffMarkerNames();
		source.setNoDiffMarkerCount(ListUtils.size(noDiffMarkerNames));

		//过滤缺失位点
		MarkerNamesFilter markerNamesFilter = new MarkerNamesFilter();
		List<String> missMarkerNames = markerNamesFilter.filter(allPrimerNames, noDiffMarkerNames, diffMarkerNames);
		source.setMissMarkerNames(missMarkerNames);
		int missMarkerCount = allPrimerNames.size() - source.getDiffMarkerCount() - source.getNoDiffMarkerCount();
		source.setMissMarkerCount(missMarkerCount);
		return true;
	}

}
