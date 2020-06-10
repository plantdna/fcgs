package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;

import java.util.List;

/**
 * 指纹对象信息Mapper:Key/Value==>String(GeneId)/Gene,
 * 通常对于需要根据指纹记录ID获取指纹信息对象本身时将使用本Mapper工具类来实现快速获取
 * @author jiangbin
 * @date 2012-6-27上午10:29:02
 */
public class GeneInfoMapper extends AbstractGeneMapper {

	private static final long serialVersionUID = -5160039355163157834L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

	/**
	 * 创建指纹信息Mapper
	 * @author Andory
	 * @param genes
	 * @return
	 * @date 2013年9月14日下午2:40:40
	 */
	public static GeneInfoMapper createMapper(List<Gene> genes) {
		GeneInfoMapper mapper = new GeneInfoMapper();
		mapper.addAll(genes);
		return mapper;
	}

}
