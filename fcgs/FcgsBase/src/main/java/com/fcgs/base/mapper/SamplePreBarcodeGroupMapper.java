package com.fcgs.base.mapper;

import com.fcgs.base.domain.SampleBarcode;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 样品条码号前缀映射分组关系Mapper,key/value--样品条码号前缀(如BGG1-01的BGG1部分)/数据对象分组列表
 * @author Jiangbin
 * @date 2014年9月20日上午3:37:17
 */
public class SamplePreBarcodeGroupMapper<S extends SampleBarcode> extends GroupMapperTemplate<S, S> {

	private static final long serialVersionUID = 6834630114812224382L;

	@Override
	public S convert(S source) throws ICoreException {
		return source;
	}

	@Override
	protected String getMapperKey(S object) {
		String samBarcode = StringUtils.upperCase(object.getSamBarcode());
		if (samBarcode.contains("-")) {
			return samBarcode.split("-")[0];
		}
		return samBarcode;
	}

}
