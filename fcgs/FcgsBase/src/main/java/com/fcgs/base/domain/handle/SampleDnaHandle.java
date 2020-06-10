package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.DnaBarcode;
import com.fcgs.base.domain.SampleBarcode;
import com.fcgs.base.domain.SampleDna;

import java.io.Serializable;

/**
 * 样品Dna信息句柄对象
 * @author jiangbin
 * @date 2014年3月21日上午11:58:33
 */
public interface SampleDnaHandle<S extends SampleDna> extends Serializable, DnaBarcode, SampleBarcode {
	/**
	 * 获取样品Dna信息对象
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:59:38
	 */
	public S getSample();

	/**
	 * 设置样品Dna信息对象
	 * @author jiangbin
	 * @param SampleDna
	 * @date 2014年3月21日上午11:59:39
	 */
	public void setSample(S sampleDna);
}
