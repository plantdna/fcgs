package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.GeneId;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;

import java.util.List;

/**
 * 指纹ID填充器
 * @author jiangbin
 * @date 2017年5月31日下午3:58:11
 */

public class GeneIdFiller<S extends GeneId> {
	/**
	 * 填充指纹记录ID
	 * @author jiangbin
	 * @param sources 目标源数据列表
	 * @param geneId 指纹记录ID
	 * @return
	 * @throws ICoreException
	 * @date 2017年5月31日下午4:01:49
	 */
	public List<S> fill(List<S> sources, String geneId) throws ICoreException {
		return new _Filler<S>(geneId).fill(sources);
	}

	private static class _Filler<S extends GeneId> extends ListFillerTemplate<S, S> {
		private String geneId;

		public _Filler(String geneId) {
			this.geneId = geneId;
		}

		@Override
		protected S fill(S source, boolean isInternal) throws ICoreException {
			source.setGeneId(geneId);
			return source;
		}

	}
}
