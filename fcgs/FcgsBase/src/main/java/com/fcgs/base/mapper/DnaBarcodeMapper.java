package com.fcgs.base.mapper;

import com.fcgs.base.domain.DnaBarcode;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * Dna条码号分组映射关系Mapper,key/value--Dna条码号/数据对象
 * @author Jiangbin
 * @date 2014年9月20日上午3:37:17
 */
public class DnaBarcodeMapper<S extends DnaBarcode> extends MapperTemplate<S, S> {

	private static final long serialVersionUID = 6834630114812224382L;

	@Override
	protected String getMapperKey(S object) {
		return StringUtils.upperCase(object.getDnaBarcode());
	}

	@Override
	protected S getMapperValue(S object) {
		return object;
	}

}
