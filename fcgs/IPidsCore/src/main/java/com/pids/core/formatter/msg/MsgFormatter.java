package com.pids.core.formatter.msg;

import com.pids.core.exception.ICoreException;
import com.pids.core.formatter.Formatter;

import java.util.List;

/**
 * 消息字符串格式化工具接口，该接口支持将任意给定数据列表格式化成某种格式的字符串，
 * 这通常应用于需要在业务以一种标准格式来显示某类信息时用到
 * @Author Andory
 * @Date 2012-8-15下午11:40:34
 */
public interface MsgFormatter<S> extends Formatter<S, String> {
	/**
	 * 格式化列表数据为字符串
	 * @Author Andory
	 * @param sources
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-15下午11:44:42
	 */
	public String format(List<S> sources) throws ICoreException;
}
