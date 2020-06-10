package com.fcgs.base.marker.comparer;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;

/**
 * 单位点比对算法
 * @author jiangbin
 * @date 2012-10-29上午10:53:35
 */
public interface MarkerComparer {
	/**
	 * 单位点间的比对算法
	 * @author jiangbin
	 * @param source
	 * @param target
	 * @return true/false--给定Marker无/有差异
	 * @throws ICoreException
	 * @date 2013-4-16下午1:54:03
	 */
	public boolean comparer(Marker source, Marker target) throws ICoreException;

	/**
	 * 获取碱基最大偏移量
	 * @author jiangbin
	 * @return
	 * @date 2013-4-16下午1:53:56
	 */
	public int getMaxSizeOffset();

	/**
	 * 设置碱基最大偏移量
	 * @author jiangbin
	 * @param maxSizeOffset
	 * @date 2013-4-16下午1:54:04
	 */
	public void setMaxSizeOffset(int maxSizeOffset);

	/**
	 * 获取差异allele数
	 * @author jiangbin
	 * @return
	 * @date 2013-4-16下午1:54:06
	 */
	public int getDiffAlleleCount();

	/**
	 * 设置差异allele数
	 * @author jiangbin
	 * @param diffAlleleCount
	 * @date 2013-4-16下午1:54:07
	 */
	public void setDiffAlleleCount(int diffAlleleCount);
}
