package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.mapper.MarkerMapper;
import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 指纹对象备注信息填充器，将给定指纹的备注信息填充到目标指纹对象中,
 * 注意：本填充器是按照引物名称进行匹配Marker的索引的，故引物名称必需设置
 * @author jiangbin
 * @date 2012-10-15下午2:20:11
 */
public class GeneCommentsFiller {

	/**
	 * 将备注指纹信息填充到数据指纹信息对象中
	 * @author Andory
	 * @param dataGene 数据指纹信息对象
	 * @param comments 备注指纹信息对象
	 * @return
	 * @throws ICoreException
	 * @date 2013-6-10上午12:36:37
	 */
	public Gene fill(Gene dataGene, Gene comments) throws ICoreException {
		if (check(dataGene, comments)) {
			return dataGene;
		}
		List<Marker> sourceMarkers = comments.getMarkers();
		//构建高速缓存Mapper
		MarkerMapper mapper = createTargetMarkerMapper(dataGene);
		fill(sourceMarkers, mapper);
		return dataGene;
	}

	/**
	 * 检查数据指纹和备注指纹的合法性
	 * @author Andory
	 * @param dataGene
	 * @param comments
	 * @return
	 * @date 2013-6-10上午12:35:54
	 */
	protected boolean check(Gene dataGene, Gene comments) {
		return dataGene == null || dataGene.size() < 1 || comments == null || comments.size() < 1;
	}

	/**
	 * 构建目标指纹对象的marker映射，用于快速查找指定引物名的Marker
	 * @author jiangbin
	 * @return
	 * @date 2012-10-17上午8:14:39
	 */
	protected MarkerMapper createTargetMarkerMapper(Gene dataGene) {
		List<Marker> targetMarkers = dataGene.getMarkers();
		MarkerMapper mapper = new MarkerMapper();
		mapper.addAll(targetMarkers);
		return mapper;
	}

	/**
	 * 填充给定源Marker列表中的备注信息到目标Mapper对应的Marker中
	 * @author jiangbin
	 * @param sources
	 * @param targetMapper
	 * @date 2012-10-15下午2:24:15
	 */
	protected void fill(List<Marker> sources, MarkerMapper targetMapper) {
		if (CollectionUtils.isEmpty(sources)) {
			return;
		}
		for (Marker sourceMarker : sources) {
			if (sourceMarker == null) {
				continue;
			}
			String primerName = sourceMarker.getPrimerName();
			if (StringUtils.isBlank(primerName)) {
				continue;
			}
			Marker targetMarker = targetMapper.get(primerName);
			if (targetMarker != null) {
				fill(sourceMarker, targetMarker);
			}
		}
	}

	/**
	 * 填充Marker备注信息
	 * @author jiangbin
	 * @param sourceMarker
	 * @param targetMarker
	 * @date 2012-10-15下午2:30:38
	 */
	protected void fill(Marker sourceMarker, Marker targetMarker) {
		List<Allele> sources = sourceMarker.getAlleles();
		List<Allele> targets = targetMarker.getAlleles();
		for (Allele target : targets) {
			float targetAllele = target.getAllele();
			for (Allele source : sources) {
				if (source.getAllele() == targetAllele) {
					target.setComments(source.getComments());
					target.setQuality(source.getQuality());
					target.setScore(source.getScore());
				}
			}
		}
	}

}
