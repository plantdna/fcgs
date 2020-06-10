package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * DNA条码号字段接口
 * @author jiangbin
 * @date 2015年6月11日下午4:33:46
 */
public interface DnaBarcode extends Serializable {
	/**
	 * 设置DNA条码号
	 * @author jiangbin
	 * @param dnaBarcode
	 * @date 2015年6月11日下午4:35:18
	 */
	public void setDnaBarcode(String dnaBarcode);

	/**
	 * 获取DNA条码号
	 * @author jiangbin
	 * @return
	 * @date 2015年6月11日下午4:35:19
	 */
	public String getDnaBarcode();
}
