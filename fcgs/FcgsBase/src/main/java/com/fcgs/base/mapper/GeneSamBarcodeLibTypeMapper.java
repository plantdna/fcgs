package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹库类型及样品条码号分组Mapper，key/value--指纹库类型-样品条码号/分组指纹列表
 * @author Jiangbin
 * @date 2013-7-3上午2:32:49
 */
public class GeneSamBarcodeLibTypeMapper extends AbstractGeneMapper {

	private static final long serialVersionUID = -8604715484772504839L;

	@Override
	protected String getMapperKey(Gene object) {
		String libType = StringUtils.stripToEmpty(object.getGeneLib() == null ? "0" : object.getGeneLib() + "");
		String samBarcode = StringUtils.stripToEmpty(object.getSamBarcode()).toUpperCase();
		return String.format("%s-%s", libType, samBarcode);
	}

	/**
	 * 获取Mapper的Key
	 * @author jiang
	 * @param libType
	 * @param samBarcode
	 * @return
	 * @date 2018年7月13日下午3:25:59
	 */
	public String getMapperKey(Integer libType, String samBarcode) {
		String libTypeStr = StringUtils.stripToEmpty(libType == null ? "0" : libType + "");
		samBarcode = StringUtils.stripToEmpty(samBarcode).toUpperCase();
		return String.format("%s-%s", libTypeStr, samBarcode);
	}

}
