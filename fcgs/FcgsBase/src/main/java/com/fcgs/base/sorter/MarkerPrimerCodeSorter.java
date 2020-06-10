package com.fcgs.base.sorter;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.MarkerPrimerCodeMapper;
import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 位点按引物编号排序功能
 * @author jiangbin
 * @date 2014年6月5日下午4:16:09
 */

public class MarkerPrimerCodeSorter {
	/**
	 * 对位点列表进行排序
	 * @author jiangbin
	 * @param sources
	 * @return
	 * @throws ICoreException
	 * @date 2014年6月5日下午4:18:28
	 */
	public List<Marker> sort(List<Marker> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return sources;
		}
		MarkerPrimerCodeMapper mapper = new MarkerPrimerCodeMapper();
		mapper.addAll(sources);
		List<String> primerCodes = mapper.getKeys();
		if (CollectionUtils.isNotEmpty(primerCodes)) {
			Collections.sort(primerCodes);
		}
		return mapper.getValues(primerCodes);
	}
}
