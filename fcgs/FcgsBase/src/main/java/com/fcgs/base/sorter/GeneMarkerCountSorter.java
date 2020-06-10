package com.fcgs.base.sorter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.handle.GeneHandle;
import com.pids.core.utils.ListUtils;

import java.util.Comparator;

/**
 * 指纹信息句柄对象排序器,该排序器按照Marker数目由大到小排序
 * @author Andory
 * @date 2012-6-16下午04:39:32
 */

public class GeneMarkerCountSorter implements Comparator<GeneHandle> {

	@Override
	public int compare(GeneHandle o1, GeneHandle o2) {
		try {
			if (o1 == null || o2 == null) {
				return 0;
			}
			Gene gene = o1.getGene();
			Gene gene2 = o2.getGene();
			if (gene == null || gene2 == null) {
				return 0;
			}
			return ListUtils.size(gene.getMarkers()) > ListUtils.size(gene2.getMarkers()) ? 0 : 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
