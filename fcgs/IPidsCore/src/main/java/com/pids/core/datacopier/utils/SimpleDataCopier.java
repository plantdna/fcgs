package com.pids.core.datacopier.utils;

import com.pids.core.datacopier.DataCopier;
import com.pids.core.exception.ICoreException;
import com.pids.core.utils.ObjectCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * 通过序列化方式进行数据对象拷贝
 * @author jiangbin
 * @date 2014年2月17日下午12:04:31
 */
public class SimpleDataCopier<S extends Serializable> implements DataCopier<S, S> {

	@Override
	public S copy(S source) throws ICoreException {
		return ObjectCopier.copy(source);
	}

	@Override
	public boolean copy(S source, S target) throws ICoreException {
		if (source == null || target == null) {
			return false;
		}
		S tmp = ObjectCopier.copy(source);
		try {
			BeanUtils.copyProperties(target, tmp);
			return true;
		} catch (Exception e) {
			Logger.getLogger(SimpleDataCopier.class).warn(e);
			return false;
		}
	}

}
