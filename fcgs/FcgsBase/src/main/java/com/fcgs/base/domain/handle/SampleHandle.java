package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.Sample;
import com.fcgs.base.domain.SampleBarcode;

import java.io.Serializable;

/**
 * 样品信息句柄对象
 * @author jiangbin
 * @date 2014年3月21日上午11:58:33
 */
public interface SampleHandle<S extends Sample> extends Serializable, SampleBarcode {
	/**
	 * 获取样品信息对象
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:59:38
	 */
	public S getSample();

	/**
	 * 设置样品信息对象
	 * @author jiangbin
	 * @param sample
	 * @date 2014年3月21日上午11:59:39
	 */
	public void setSample(S sample);
}
