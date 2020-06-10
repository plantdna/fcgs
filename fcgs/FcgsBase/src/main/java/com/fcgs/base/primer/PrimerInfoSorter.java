package com.fcgs.base.primer;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

/**
 * 引物信息的引物编号进行排序器
 * @author Andory
 * @date 2013-7-25下午2:32:28
 */
public class PrimerInfoSorter<S extends PrimerInfo> implements Comparator<S> {

	@Override
	public int compare(S source, S target) {
		if (source == null || target == null) {
			return 0;
		}
		String sPrimerCode = StringUtils.stripToEmpty(source.getPrimerCode());
		String tPrimerCode = StringUtils.stripToEmpty(target.getPrimerCode());
		return sPrimerCode.compareToIgnoreCase(tPrimerCode);
	}

}
