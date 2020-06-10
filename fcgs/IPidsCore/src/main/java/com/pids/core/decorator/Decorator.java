package com.pids.core.decorator;

/**
 * 装饰器接口
 * @Author andory
 * @Date 2012-8-11下午6:28:41
 */
public interface Decorator<S,T> {
	/**
	 * 装饰器接口方法
	 * @Author andory
	 * @param source 源数据
	 * @return 装饰后的数据
	 * @throws DecoratorException
	 * @Date 2012-8-11下午6:30:09
	 */
	public T decorate(S source)throws DecoratorException;
}
