package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 位点 权重值填充器
 * @author jiangbin
 * @date 2014年3月21日下午3:12:01
 */

public class MarkerWeightFiller {
	/**
	 * 填充指纹列表的权重值
	 * @author jiangbin
	 * @param sources
	 * @param weight
	 * @return
	 * @throws ICoreException
	 * @date 2014年3月21日下午5:34:34
	 */
	public boolean fillGenes(List<Gene> sources, double weight) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return false;
		}
		for (Gene gene : sources) {
			this.fillGene(gene, weight);
		}
		return true;
	}

	/**
	 * 填充指纹位点权重值
	 * @author jiangbin
	 * @param source 指纹信息对象
	 * @param weight 权重值
	 * @return
	 * @throws ICoreException
	 * @date 2014年3月21日下午3:12:50
	 */
	public boolean fillGene(Gene source, double weight) throws ICoreException {
		if (source == null || source.isEmpty()) {
			return false;
		}
		return this.fillMarkers(source.getMarkers(), weight);
	}

	/**
	 * 填充位点的权重值
	 * @author jiangbin
	 * @param sources 位点列表
	 * @param weight 权重值
	 * @return
	 * @throws ICoreException
	 * @date 2014年3月21日下午3:13:50
	 */
	public boolean fillMarkers(List<Marker> sources, double weight) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return false;
		}
		weight = weight < 0 ? 0 : weight;
		for (Marker marker : sources) {
			marker.setWeight(weight);
		}
		return true;
	}
}
