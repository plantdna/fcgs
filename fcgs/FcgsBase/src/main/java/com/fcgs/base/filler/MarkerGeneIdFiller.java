/**
 * 
 */
package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 位点的指纹ID填充器
 * @author jiang
 * @date 2018年5月30日下午9:36:52
 */

public class MarkerGeneIdFiller extends GeneIdFiller<Marker> {
	/**
	 * 填充指纹对象中指纹ID到其包含的各位点中
	 * @author jiang
	 * @param genes
	 * @throws ICoreException
	 * @date 2018年5月30日下午9:39:00
	 */
	public void fill(List<Gene> genes) throws ICoreException {
		if (CollectionUtils.isEmpty(genes)) {
			return;
		}
		for (Gene gene : genes) {
			if (gene.isEmpty()) {
				continue;
			}
			List<Marker> markers = gene.getMarkers();
			this.fill(markers, gene.getGeneId());
		}
	}
}
