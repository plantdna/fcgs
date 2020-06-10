package com.fcgs.base.filler;

import com.fcgs.base.domain.MarkerType;
import com.pids.core.exception.ICoreException;

import java.util.ArrayList;
import java.util.List;

/**
 * 位点标记类型填充，本功能只对未设置位点标记类型字段的情况进行设置，若已设置了位点标记类型字段则自动忽略，
 * 在某些情况下需要统一设置位点标记类型字段时，可以先行通过{@link EmptyMarkerTypeFiller}功能清空掉位点标记类型字段，
 * 再通过本功能来设置即可
 * @author Jiangbin
 * @date 2014-4-25下午11:19:25
 */

public class MarkerTypeFiller<S extends MarkerType> {

	/**
	 * 将给定位点标记类型填充到源数据列表中
	 * @author Jiangbin
	 * @param sources
	 * @param markerType 位点标记类型，若数据中包含了种属则不进行填充
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-25下午11:26:15
	 */
	public List<S> fill(List<S> sources, Integer markerType) throws ICoreException {
		List<S> targets = new ArrayList<>();
		for (S source : sources) {
			if (source == null) {
				continue;
			}
			Integer _markerType = source.getMarkerType();
			if (_markerType == null) {
				source.setMarkerType(markerType);
			}
			targets.add(source);
		}
		return targets;
	}

}
