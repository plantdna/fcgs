package com.pids.core.formatter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.formatter.Formatter;
import com.pids.core.initial.Initial;
import com.pids.core.utils.StringEx;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对象类型格式化，主要是对日期(可以设定日期格式)对象进行格式化，其它对象类型将调用对象的toString()进行格式化
 * @author Andory
 * @date 2012-7-24上午08:01:10
 */
public abstract class AbstractObj2StrFormatter implements Formatter<Object, String>, Initial {
	private String datePattern;

	public AbstractObj2StrFormatter() {
		try {
			this.initial();
		} catch (Exception e) {
		}
	}

	@Override
	public String format(Object source) throws ICoreException {
		if (source == null) {
			return "";
		}
		Class<?> class1 = source.getClass();
		if (Date.class.isAssignableFrom(class1)) {
			return formatDate((Date) source);
		} else {
			String str = decorate(source);
			if (str == null) {
				return source.toString();
			} else {
				return str;
			}
		}
	}

	/**
	 * 装饰方法，可以通过此方法来扩展对象格式化方式
	 * @author Andory
	 * @param source
	 * @return
	 * @date 2012-7-24上午08:32:39
	 */
	protected abstract String decorate(Object source);

	/**
	 * 格式化日期对象
	 * @author Andory
	 * @param source
	 * @return
	 * @date 2012-7-24上午08:26:42
	 */
	protected String formatDate(Date source) {
		SimpleDateFormat format = new SimpleDateFormat(getDatePattern());
		return format.format(source);
	}

	@Override
	public boolean initial() throws ICoreException {
		if (StringEx.isNull(datePattern)) {
			setDatePattern(getDefaultDatePattern());
		}
		return true;
	}

	/**
	 * 获取日期格式
	 * @author Andory
	 * @return
	 * @date 2012-7-24上午08:07:13
	 */
	public String getDatePattern() {
		return datePattern;
	}

	/**
	 * 设置日期格式
	 * @author Andory
	 * @param datePattern
	 * @date 2012-7-24上午08:07:20
	 */
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	/**
	 * 获取默认日期格式:yyyy-MM-dd hh:mm:ss
	 * @author Andory
	 * @return
	 * @date 2012-7-24上午08:06:35
	 */
	protected String getDefaultDatePattern() {
		return "yyyy-MM-dd hh:mm:ss";
	}

}
