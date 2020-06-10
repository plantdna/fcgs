package com.pids.core.checker.number;

import com.pids.core.exception.ICoreException;

/**
 * Short数据边界检验器接口
 * @Author Andory
 * @Date 2012-9-24上午11:18:49
 */
public interface ShortRangeChecker {
	/**
	 * 检测Short类型数据是否在范围内
	 * @author jiangbin
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @date 2012-11-6下午3:28:49
	 */
	boolean checkShort(short source) throws ICoreException;

}
