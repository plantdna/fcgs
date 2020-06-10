package com.pids.core.mapper.utils;

import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 字母大小写匹配Mapper,key/value--大写字符串/原字符串数据
 * @author jiangbin
 * @date 2015年3月12日上午11:26:36
 */
public class LetterCaseMapper extends MapperTemplate<String, String> {
	
	private static final long serialVersionUID = 8992459014532694915L;
	
	@Override
	protected String getMapperKey(String object) {
		return StringUtils.upperCase(object);
	}
	
	@Override
	protected String getMapperValue(String object) {
		return object;
	}
	
}
