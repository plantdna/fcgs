package com.fcgs.base.mapper;

import com.fcgs.base.domain.SampleBarcode;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 样品条码号映射关系Mapper,key/value--样品条码号/数据对象
 * @author Jiangbin
 * @date 2014年9月20日上午3:37:17
 */
public class SampleBarcodeGroupMapper<S extends SampleBarcode> extends GroupMapperTemplate<S, S> {

	private static final long serialVersionUID = 6834630114812224382L;

	@Override
	public S convert(S source) throws ICoreException {
		return source;
	}

	@Override
	protected String getMapperKey(S object) {
		return StringUtils.upperCase(object.getSamBarcode());
	}

}
