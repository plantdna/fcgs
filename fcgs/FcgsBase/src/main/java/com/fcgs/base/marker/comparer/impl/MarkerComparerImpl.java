package com.fcgs.base.marker.comparer.impl;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.marker.comparer.MarkerComparer;
import com.pids.core.exception.ICoreException;

/**
 * 基于二倍体的位点比较器算法，此算法是本SSR系统的的核心
 * @author Andory
 * @date 2013-6-9下午12:14:16
 */
public class MarkerComparerImpl implements MarkerComparer {
	private int maxSizeOffset;//碱基偏移量
	private int diffAlleleCount;//差异Allele数

	@Override
	public int getMaxSizeOffset() {
		return maxSizeOffset;
	}

	@Override
	public void setMaxSizeOffset(int maxSizeOffset) {
		this.maxSizeOffset = maxSizeOffset;
	}

	@Override
	public int getDiffAlleleCount() {
		return diffAlleleCount;
	}

	@Override
	public void setDiffAlleleCount(int diffAlleleCount) {
		this.diffAlleleCount = diffAlleleCount;
	}

	@Override
	public boolean comparer(Marker source, Marker target) throws ICoreException {
		Allele sAllele1 = source.getAlleles().get(0);
		Allele sAllele2 = source.getAlleles().get(1);
		Allele tAllele1 = target.getAlleles().get(0);
		Allele tAllele2 = target.getAlleles().get(1);

		boolean diff11 = isDiffAllele(sAllele1.getAllele(), tAllele1.getAllele());
		boolean diff12 = isDiffAllele(sAllele1.getAllele(), tAllele2.getAllele());
		boolean diff21 = isDiffAllele(sAllele2.getAllele(), tAllele1.getAllele());
		boolean diff22 = isDiffAllele(sAllele2.getAllele(), tAllele2.getAllele());

		int countA = 0, countB = 0;

		if (diff11)
			countA++;
		if (diff22)
			countA++;

		if (diff12)
			countB++;
		if (diff21)
			countB++;
		this.diffAlleleCount = countA > countB ? countB : countA;
		return this.diffAlleleCount == 0;
	}

	/**
	 * @Description 判定是否在碱基偏移量范围内
	 * @Author jiangbin
	 * @param source
	 * @param target
	 * @return
	 * @Date 2011-12-30下午3:58:23
	 */
	protected boolean isDiffAllele(float source, float target) {
		return (Math.abs(source - target) >= maxSizeOffset); //大于等于即为不同。
	}
}
