package com.fcgs.base.mapper;

import com.fcgs.base.domain.SampleBarcode;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 样品条码号分组映射关系Mapper,key/value--样品条码号/数据对象
 * @author Jiangbin
 * @date 2014年9月20日上午3:37:17
 */
public class SampleBarcodeMapper<S extends SampleBarcode> extends MapperTemplate<S, S> {

	private static final long serialVersionUID = 6834630114812224382L;

	public SampleBarcodeMapper() {
	}

	public SampleBarcodeMapper(List<S> sources) {
		this.addAll(sources);
	}

	@Override
	protected String getMapperKey(S object) {
		return StringUtils.upperCase(object.getSamBarcode());
	}

	@Override
	protected S getMapperValue(S object) {
		return object;
	}

}
