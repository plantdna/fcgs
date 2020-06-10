package com.fcgs.base.sorter;

import java.util.Comparator;

/**
 * 包含样品条码号的对象比较器抽象接口类
 * @author Andory
 * @date 2013年8月26日下午7:04:25
 */
public abstract class ObjSamBarcodeComparer<S> implements Comparator<S> {
	private final BarcodeComparer _samBarcodeComparer;

	public ObjSamBarcodeComparer() {
		this._samBarcodeComparer = new BarcodeComparer();
	}

	@Override
	public int compare(S source, S target) {
		if (source == null && target == null) {
			return 0;
		}
		if (source == null) {
			return -1;
		}
		if (target == null) {
			return 1;
		}
		String sSamBarcode = getSamBarcode(source);
		String tSamBarcode = getSamBarcode(target);
		return compare(sSamBarcode, tSamBarcode);
	}

	/**
	 * 获取给定对象包含的样品条码号
	 * @author Andory
	 * @param source
	 * @return
	 * @date 2013年8月26日下午7:04:08
	 */
	protected abstract String getSamBarcode(S source);

	/**
	 * 比较样品条码号字符串
	 * @author Andory
	 * @param sSamBarcode
	 * @param tSamBarcode
	 * @return
	 * @date 2013年8月26日下午7:03:55
	 */
	protected int compare(String sSamBarcode, String tSamBarcode) {
		return this._samBarcodeComparer.compare(sSamBarcode, tSamBarcode);
	}
}
