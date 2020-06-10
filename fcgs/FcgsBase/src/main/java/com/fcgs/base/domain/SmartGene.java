package com.fcgs.base.domain;

import com.fcgs.base.domain.gene.GeneId;

import java.util.Map;

/**
 * 精简版本的指纹信息对象，只包含指纹ID和位点数据数组
 * @author jiangbin
 * @date 2017年5月30日下午8:25:27
 */
public interface SmartGene extends GeneId, Map<String, int[]> {
}
