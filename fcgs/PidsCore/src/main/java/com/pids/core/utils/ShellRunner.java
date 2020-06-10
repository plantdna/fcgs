package com.pids.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * <pre>用于执行Shell脚本，
 * 注意：本功能只支持shell脚本文件的执行，给定的shell命令必需给定shell脚本路径，如:/usr/bin/java</pre>
 * @author ANDORY
 * @date 2016年3月25日下午3:58:06
 */
public class ShellRunner {
	private static Logger logger = LoggerFactory.getLogger(ShellRunner.class);

	/**
	 * 执行Shell命令
	 * @author jiangbin
	 * @param command shell命令,必需给定shell脚本具体文件路径，如:/usr/bin/java
	 * @return
	 * @date 2016年7月22日下午4:00:32
	 */
	public static String run(String command) {
		logger.info("开始执行Shell命令:" + command);
		InputStreamReader ir = null;
		LineNumberReader input = null;
		StringBuilder sb = new StringBuilder();
		try {
			Process process = Runtime.getRuntime().exec(command);
			ir = new InputStreamReader(process.getInputStream());
			input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
			if (ir != null) {
				try {
					ir.close();
				} catch (IOException e) {
				}
			}
			logger.info("执行Shell命令" + command + "结束!");
		}
		return sb.toString();
	}
}
