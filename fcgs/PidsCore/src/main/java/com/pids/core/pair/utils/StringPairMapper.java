package com.pids.core.pair.utils;

import com.pids.core.mapper.MapperTemplate;
import com.pids.core.pair.StringPair;
import com.pids.core.utils.StringEx;

/**
 * 字符对Mapper,该Mapper的Key是由StringPair对象的source和target的组合组成的，
 * 通常本Mapper可以用于去重，Value是StringPair对象实例本身
 * @author jiangbin
 * @date 2013-1-15下午3:28:58
 */
public class StringPairMapper extends MapperTemplate<StringPair, StringPair> {

	private static final long serialVersionUID = 4282518851629731197L;

	@Override
	protected String getMapperKey(StringPair object) {
		String source=StringEx.sNull(object.getSource());
		String target=StringEx.sNull(object.getTarget());
		if(source.isEmpty()||target.isEmpty()){
			return null;
		}
		if(source.compareToIgnoreCase(target)>0){
			return String.format("%s-%s", source,target);
		}else{
			return String.format("%s-%s", target,source);
		}
	}

	@Override
	protected StringPair getMapperValue(StringPair object) {
		return object;
	}
	
}
