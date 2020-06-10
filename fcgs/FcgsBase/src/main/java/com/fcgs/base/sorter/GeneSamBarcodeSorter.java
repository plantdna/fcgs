package com.fcgs.base.sorter;

import com.fcgs.base.domain.SampleDna;
import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.sorter.Sorter;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 指纹样品条码号排序器
 * @author LiuJunGuang
 * @date 2014年5月7日下午5:39:21
 */

public class GeneSamBarcodeSorter extends ObjSamBarcodeComparer<Gene> implements Sorter<List<Gene>, Boolean> {

	@Override
	public Boolean sort(List<Gene> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return true;
		}
		Collections.sort(sources, this);
		return true;
	}

	@Override
	protected String getSamBarcode(Gene gene) {
		if (gene == null)
			return null;
		SampleDna sam = gene.getSample();
		return sam == null ? null : sam.getSamBarcode();
	}
}
