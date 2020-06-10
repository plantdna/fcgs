package com.pids.core.pair.utils;

import com.pids.core.mapper.MapperTemplate;
import com.pids.core.pair.StringPair;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串对的源字符串Mapper,kye/value---source/StringPair
 * @author jiangbin
 * @date 2013-1-15下午3:28:58
 */
public class StringPairSourceMapper extends MapperTemplate<StringPair, StringPair> {

	private static final long serialVersionUID = 4282518851629731197L;

	@Override
	protected String getMapperKey(StringPair object) {
		return StringUtils.stripToEmpty(object.getSource());
	}

	@Override
	protected StringPair getMapperValue(StringPair object) {
		return object;
	}

}
