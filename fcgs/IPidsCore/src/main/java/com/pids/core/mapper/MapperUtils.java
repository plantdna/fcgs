package com.pids.core.mapper;

/**
 * 用于进行Mapper判定的工具方法类
 * @author Andory
 * @date 2013-7-8下午2:50:33
 */
public class MapperUtils {
	/**
	 * 判定Mapper是否为null或空
	 * @author Andory
	 * @param mapper
	 * @return
	 * @date 2013-7-8下午2:46:31
	 */
	public static boolean isEmpty(Mapper<?, ?> mapper) {
		return mapper == null || mapper.isEmpty();
	}

	/**
	 * 判定Mapper是否不为null且不为空
	 * @author Andory
	 * @param mapper
	 * @return
	 * @date 2013-7-8下午2:46:32
	 */
	public static boolean isNotEmpty(Mapper<?, ?> mapper) {
		return !isEmpty(mapper);
	}

	/**
	 * 判定是否只有一个Mapper项
	 * @author Andory
	 * @param mapper
	 * @return
	 * @date 2013-7-8下午2:52:06
	 */
	public static boolean hasOneItem(Mapper<?, ?> mapper) {
		if (isEmpty(mapper)) {
			return false;
		}
		if (mapper.size() == 1) {
			return true;
		}
		return false;
	}
}
