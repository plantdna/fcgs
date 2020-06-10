package com.pids.core.decorator;

/**
 * 装饰器接口异常处理类
 * @Author andory
 * @Date 2012-8-11下午6:29:49
 */
public class DecoratorException extends Exception {

	private static final long serialVersionUID = -6227010359206548450L;

	public DecoratorException() {
	}

	public DecoratorException(String message) {
		super(message);
	}

	public DecoratorException(Throwable cause) {
		super(cause);
	}

	public DecoratorException(String message, Throwable cause) {
		super(message, cause);
	}

}
