package com.pids.core.base64;

import com.pids.core.utils.SerializeUtil;
import com.pids.core.utils.StringEx;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Base64编码解码工具方法
 * @author Andory
 * @date 2012-5-24下午08:54:49
 */
public class Base64Util {
	private static Base64Decoder decoder = new Base64Decoder();
	private static Base64Encoder encoder = new Base64Encoder();

	/**
	 * 编码字节数组为Base64字符串
	 * @author Andory
	 * @param bytes 源字节数组
	 * @return 编码失败则返回null
	 * @date 2012-5-24下午08:56:34
	 */
	public static String encode(byte[] bytes) {
		try {
			if (bytes != null) {
				return encoder.convert(bytes);
			}
			return "";
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 编码字符串为Base64字符串
	 * @author Andory
	 * @param source 源字符串
	 * @return 编码失败则返回null
	 * @date 2012-5-24下午08:59:09
	 */
	public static String encode(String source) {
		try {
			return encode(source.getBytes());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解码Base64格式字符串为字节流
	 * @author Andory 
	 * @param source Base64格式字符串
	 * @return 解码失败则返回null
	 * @date 2012-5-24下午08:56:52
	 */
	public static byte[] decode(String source) {
		try {
			if (!StringEx.isNull(source)) {
				return decoder.convert(source);
			}
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 解码Base64格式字符串,会自动删除回车换行和空格字符串
	 * @author Andory
	 * @param source Base64格式字符串
	 * @return
	 * @date 2012-5-25上午08:01:44
	 */
	public static String decode2Str(String source) {
		try {
			String target = new String(decode(source));
			if (!StringEx.isNull(target)) {
				target = target.replaceAll("\r|\n| ", "");
			}
			return target;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将对象序列化后转换成Base64格式字符串
	 * @author jiangbin
	 * @param source 若对象为null则返回null
	 * @return
	 * @date 2016年8月20日下午3:01:32
	 */
	public static <S extends Serializable> String toBase64(S source) {
		if (source == null) {
			return null;
		}
		byte[] contents = SerializeUtil.serialize(source);
		return encode(contents);
	}

	/**
	 * 从Base64格式字符串中反序列化出对象
	 * @author jiangbin
	 * @param base64Str 若字符串为null或空则返回null
	 * @return 
	 * @date 2016年8月20日下午3:05:09
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T toObject(String base64Str) {
		if (StringUtils.isBlank(base64Str)) {
			return null;
		}
		byte[] contents = decode(base64Str);
		return (T) SerializeUtil.deserialize(contents);
	}
}
