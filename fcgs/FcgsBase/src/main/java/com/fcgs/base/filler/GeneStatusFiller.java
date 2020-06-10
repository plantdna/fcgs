package com.fcgs.base.filler;

import com.fcgs.base.domain.GeneStatus;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;

/**
 * 指纹状态填充器
 * @author Andory
 * @date 2013-6-22下午5:07:45
 */
public class GeneStatusFiller<S extends GeneStatus> extends ListFillerTemplate<S, S> {
	private Integer geneStatus;

	@Override
	protected S fill(S source, boolean isInternal) throws ICoreException {
		source.setGeneStatus(this.geneStatus);
		return source;
	}

	/**
	 * 设置指纹状态
	 * @author Andory
	 * @param geneStatus
	 * @date 2013-6-22下午5:07:03
	 */
	public void setGeneStatus(Integer geneStatus) {
		this.geneStatus = geneStatus;
	}

	/**
	 * 获取指纹状态
	 * @author Andory
	 * @return
	 * @date 2013-6-22下午5:07:04
	 */
	public Integer getGeneStatus() {
		return geneStatus;
	}
}
