package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 样品条码号数据特征接口
 * @author Jiangbin
 * @date 2014年9月20日上午3:29:17
 */
public interface SampleBarcode extends Serializable {
	/**
	 * 设置样品条码号
	 * @author Jiangbin
	 * @param samBarcode
	 * @date 2014-3-4上午12:18:39
	 */
	public void setSamBarcode(String samBarcode);

	/**
	 * 获取样品条码号
	 * @author Jiangbin
	 * @return
	 * @date 2014-3-4上午12:18:40
	 */
	public String getSamBarcode();

}
