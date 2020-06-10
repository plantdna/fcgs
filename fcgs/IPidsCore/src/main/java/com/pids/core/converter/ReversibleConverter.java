package com.pids.core.converter;

import com.pids.core.exception.ICoreException;

/**
 * 可送转换接口，该接口扩展了转换接口，并增加了逆向转换接口方法，对于所有需要进行双向转换时可以使用此接口来实现
 * @author jiangbin
 * @date 2012-8-16下午3:32:42
 */
public interface ReversibleConverter<S, T> extends Converter<S, T> {
	/**
	 * 反向转换接口
	 * @author jiangbin
	 * @param target 目标数据类型
	 * @return 源数据类型
	 * @throws ICoreException
	 * @date 2012-8-16下午3:31:37
	 */
	public S reconvert(T target) throws ICoreException;
}
