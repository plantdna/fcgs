package com.pids.core.mapper.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符串分组映射Mapper,key/value--大写字符串/原字符串分组列表，
 * 本功能用于将一个字符串中大写后相同的字符串分组
 * @author jiangbin
 * @date 2015年3月12日上午11:28:53
 */
public class LetterCaseGroupMapper extends GroupMapperTemplate<String, String> {

	private static final long serialVersionUID = -6407024074238858179L;

	@Override
	public String convert(String source) throws ICoreException {
		return source;
	}

	@Override
	protected String getMapperKey(String object) {
		return StringUtils.upperCase(object);
	}

}
