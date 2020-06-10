package com.fcgs.base.sorter;

import com.fcgs.base.domain.handle.GeneHandle;

import java.util.Comparator;

/**
 * 指纹按照位点数多少进行排序
 * @author Andory
 * @date 2013-6-20上午10:48:51
 */

public class GeneMarkerCountComparator implements Comparator<GeneHandle> {
	@Override
	public int compare(GeneHandle source, GeneHandle target) {
		if (source == null || target == null) {
			return 0;
		}
		return source.getGene().size() - target.getGene().size();
	}
}
