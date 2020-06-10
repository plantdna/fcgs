package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;

import java.util.List;

/**
 * 指纹库库类型填充器，本类需要先设置库类型再调用填充方法
 * @author LiuJunGuang
 * @date 2013-1-28下午5:42:30
 */
public class GeneLibTypeFiller extends ListFillerTemplate<Gene, Gene> {
	private Integer libType;

	@Override
	protected Gene fill(Gene source, boolean isInternal) throws ICoreException {
		if (libType != null) {
			source.setGeneLib(libType);
		}
		return source;
	}

	/**
	 * 填充库类型到指定指纹库中
	 * @author Jiangbin
	 * @param gene
	 * @param libType
	 * @return
	 * @throws ICoreException
	 * @date 2013-8-27上午12:49:19
	 */
	public Gene fill(Gene gene, Integer libType) throws ICoreException {
		if (libType != null) {
			gene.setGeneLib(libType);
		}
		return gene;
	}

	/**
	 * 填充指纹的库类型值
	 * @author Jiangbin
	 * @param sources
	 * @param libType
	 * @return
	 * @throws ICoreException
	 * @date 2013-8-27上午12:49:38
	 */
	public List<Gene> fill(List<Gene> sources, Integer libType) throws ICoreException {
		this.libType = libType;
		return this.fill(sources);
	}

	/**
	 * 获取指纹库类型
	 * @author Jiangbin
	 * @return
	 * @date 2013-8-27上午12:48:59
	 */
	public Integer getLibType() {
		return libType;
	}

	/**
	 * 设置指纹库库类型
	 * @author Jiangbin
	 * @param libType
	 * @date 2013-8-27上午12:49:01
	 */
	public void setLibType(Integer libType) {
		this.libType = libType;
	}

}
