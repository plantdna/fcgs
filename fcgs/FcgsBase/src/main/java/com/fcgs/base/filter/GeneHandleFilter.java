package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.handle.GeneHandle;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import com.pids.core.mapper.utils.StringIndexMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 指纹句柄对象中的指纹信息对象{@link Gene}过滤器
 * @author jiangbin
 * @date 2012-12-27上午10:51:44
 */
public class GeneHandleFilter<S extends GeneHandle> extends ListFilterTemplate<S, Gene> {
	private final StringIndexMapper _removeMapper;
	private final StringIndexMapper _retainMapper;

	public GeneHandleFilter() {
		this._removeMapper = new StringIndexMapper();
		this._retainMapper = new StringIndexMapper();
	}

	@Override
	protected Gene filter(S source, boolean isInternal) throws ICoreException {
		Gene gene = source.getGene();
		if (gene == null || gene.isEmpty()) {
			return null;
		}
		if (acceptGeneId(gene.getGeneId())) {
			return gene;
		}
		return null;
	}

	/**
	 * 判定是否当前指纹ID需要过滤掉
	 * @author Andory
	 * @param geneId
	 * @return
	 * @date 2013-6-22下午1:14:06
	 */
	protected boolean acceptGeneId(String geneId) {
		if (StringUtils.isBlank(geneId)) {
			return false;
		}
		// 指定了需要删除该ID
		if (!this._removeMapper.isEmpty() && this._removeMapper.exist(geneId)) {// 需要过滤掉
			return false;
		}
		// 指定了需要保留该ID
		if (!this._retainMapper.isEmpty() && !this._retainMapper.exist(geneId)) {// 需要保留
			return false;
		}
		return true;
	}

	/**
	 * 添加需要被删除掉的指纹ID
	 * @author Andory
	 * @param geneId
	 * @date 2013-6-22下午12:58:24
	 */
	public void addRemoveGeneId(String geneId) {
		this._removeMapper.add(geneId);
	}

	/**
	 * 添加需要被删除掉的指纹ID列表
	 * @author Andory
	 * @param geneIds
	 * @date 2013-6-22下午12:58:25
	 */
	public void addRemoveGeneIds(List<String> geneIds) {
		this._removeMapper.addAll(geneIds);
	}

	/**
	 * 添加需要被保留的指纹ID
	 * @author Andory
	 * @param geneId
	 * @date 2013-6-22下午12:58:24
	 */
	public void addRetainGeneId(String geneId) {
		this._retainMapper.add(geneId);
	}

	/**
	 * 添加需要被保留的指纹ID列表
	 * @author Andory
	 * @param geneIds
	 * @date 2013-6-22下午12:58:25
	 */
	public void addRetainGeneIds(List<String> geneIds) {
		this._retainMapper.addAll(geneIds);
	}
}
