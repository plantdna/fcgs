package com.pids.core.formatter.msg;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 消息字符串格式化模板类
 * @Author Andory
 * @Date 2012-8-15下午11:49:27
 */
public abstract class MsgFormatterTemplate<S> implements MsgFormatter<S> {
	@Override
	public String format(List<S> sources) throws ICoreException {
		if (sources == null || sources.isEmpty()) {
			return "";
		}
		StringBuilder rawStr = new StringBuilder();
		rawStr.append(formatHead());
		for (S source : sources) {
			rawStr.append(format(source));
		}
		return formatRawStr(rawStr.toString());
	}

	/**
	 * 格式化字符串之前做一些初始化工作，例如添加一行标题
	 * @author LiuJunGuang
	 * @return 返回要添加的字符串
	 * @throws ICoreException
	 * @date 2012-10-31下午2:39:19
	 */
	protected String formatHead() throws ICoreException {
		return "";
	}

	/**
	 * 处理组织好的数据，如去掉末尾可能的一些多余分隔符号或作进一步处理
	 * @Author Andory
	 * @param rawStr 初步组织好的字符串
	 * @return 处理后的字符串
	 * @throws ICoreException
	 * @Date 2012-8-15下午11:47:36
	 */
	protected String formatRawStr(String rawStr) throws ICoreException {
		return rawStr;
	}
}
