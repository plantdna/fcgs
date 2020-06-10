package com.fcgs.base.constants;

/**
 * 指纹标记类型字典,与{@link com.fcgs.sys.dict.SysMarkerTypeEnum}定义必需同步
 * @author DMT3工具自动生成
 */
public enum MarkerTypeEnum {
	/**
	 * @fields SSR 0
	 */
	SSR(0), //

	/**
	 * @fields INDEL 1
	 */
	INDEL(1), //
	/**
	 * @fields SNP 1
	 */
	SNP(2),//

	;

	public final int value;//字典项值

	private MarkerTypeEnum(int value) {
		this.value = value;
	}

}
