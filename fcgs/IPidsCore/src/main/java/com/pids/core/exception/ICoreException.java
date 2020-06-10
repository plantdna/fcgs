package com.pids.core.exception;

/**
 * 核心部分异常信息处理
 * @author jiangbin
 * @date 2014年3月12日下午4:34:59
 */
public class ICoreException extends RuntimeException {

	private static final long serialVersionUID = -4485426320617022113L;

	public ICoreException() {
		super();
	}

	public ICoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ICoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public ICoreException(Object object, Throwable cause) {
		super(object.getClass().getName(), cause);
	}

	public ICoreException(Object object, String message, Throwable cause) {
		super(object.getClass().getName() + ":" + message, cause);
	}

	public ICoreException(String message) {
		super(message);
	}

	public ICoreException(Throwable cause) {
		super(cause);
	}

}
