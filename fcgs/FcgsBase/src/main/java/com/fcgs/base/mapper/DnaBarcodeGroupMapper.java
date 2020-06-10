package com.fcgs.base.mapper;

import com.fcgs.base.domain.DnaBarcode;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * Dna条码号映射关系Mapper,key/value--Dna条码号/数据对象
 * @author Jiangbin
 * @date 2014年9月20日上午3:37:17
 */
public class DnaBarcodeGroupMapper<S extends DnaBarcode> extends GroupMapperTemplate<S, S> {

	private static final long serialVersionUID = 6834630114812224382L;

	@Override
	public S convert(S source) throws ICoreException {
		return source;
	}

	@Override
	protected String getMapperKey(S object) {
		return StringUtils.upperCase(object.getDnaBarcode());
	}

}
