package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.MarkerMapper;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.Filter;
import com.pids.core.mapper.utils.LetterCaseMapper;
import com.pids.core.mapper.utils.StringItemCountMapper;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 * 指纹公共位点名过滤器，本功能会过滤出每个指纹中都包含的公共位点名列表，若某个指纹无位点数则视为无公共位点
 * @author Andory
 * @date 2013-6-26下午6:26:53
 */

public class ShareMarkerNameFilter implements Filter<List<Gene>, List<String>> {
	@Override
	public List<String> filter(List<Gene> genes) throws ICoreException {
		if (CollectionUtils.isEmpty(genes)) {
			return null;
		}
		StringItemCountMapper mapper = new StringItemCountMapper();
		LetterCaseMapper caseMapper = new LetterCaseMapper();
		for (Gene gene : genes) {
			// 无位点数据视为无公共位点
			if (gene == null || gene.isEmpty()) {
				return null;
			}
			MarkerMapper mMapper = new MarkerMapper();
			mMapper.addAll(gene.getMarkers());
			mapper.addAll(mMapper.getKeys());
			caseMapper.addAll(mMapper.getKeys());
		}
		//获取与指纹对象序列长度相同的引物名，此即公共位点引物名，只是全为大写格式
		List<String> primers = mapper.getKeys(genes.size());
		//转换成原字符串格式
		return caseMapper.getValues(primers);
	}

	/**
	 * 过滤指纹公共位点名列表
	 * @author Jiangbin
	 * @param genes
	 * @return
	 * @throws ICoreException
	 * @date 2015年1月8日下午1:39:37
	 */
	public List<String> filter(Gene... genes) throws ICoreException {
		if (ArrayUtils.isEmpty(genes)) {
			return null;
		}
		List<Gene> geneList = ListUtils.array2List(genes);
		return filter(geneList);
	}
}
