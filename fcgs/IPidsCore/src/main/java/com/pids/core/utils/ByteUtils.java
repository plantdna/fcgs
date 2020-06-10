package com.pids.core.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.Charset;

/**
 * <pre>字节数组工具类，本工具类采用的是low-endian模式，
 * 若想要获得big-endian模式的字节数组可以使用方法：
 * {@org.apache.commons.lang3.ArrayUtils.reverse(byte[]bytes)}
 * 对字节数组进行翻转即可</pre>
 * @author jiangbin
 * @date 2014年8月29日下午10:59:31
 */
public class ByteUtils {

	public static byte[] getBytes(short data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		return bytes;
	}

	public static byte[] getBytes(char data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data);
		bytes[1] = (byte) (data >> 8);
		return bytes;
	}

	public static byte[] getBytes(int data) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		bytes[2] = (byte) ((data & 0xff0000) >> 16);
		bytes[3] = (byte) ((data & 0xff000000) >> 24);
		return bytes;
	}

	public static byte[] getBytes(long data) {
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data >> 8) & 0xff);
		bytes[2] = (byte) ((data >> 16) & 0xff);
		bytes[3] = (byte) ((data >> 24) & 0xff);
		bytes[4] = (byte) ((data >> 32) & 0xff);
		bytes[5] = (byte) ((data >> 40) & 0xff);
		bytes[6] = (byte) ((data >> 48) & 0xff);
		bytes[7] = (byte) ((data >> 56) & 0xff);
		return bytes;
	}

	public static byte[] getBytes(float data) {
		int intBits = Float.floatToIntBits(data);
		return getBytes(intBits);
	}

	public static byte[] getBytes(double data) {
		long intBits = Double.doubleToLongBits(data);
		return getBytes(intBits);
	}

	public static byte[] getBytes(String data, String charsetName) {
		Charset charset = Charset.forName(charsetName);
		return data.getBytes(charset);
	}

	public static byte[] getBytes(String data) {
		return getBytes(data, "utf-8");
	}

	/**
	 * 转换成无符号整数，即0~255
	 * @author jiangbin
	 * @param data
	 * @return
	 * @date 2014年8月30日下午12:56:00
	 */
	public static short getUnsignedByte(byte data) {
		return (short) (data & 0x0FF);
	}

	/**
	 * 转换成无符号整型数
	 * @author jiangbin
	 * @param bytes
	 * @return
	 * @date 2014年8月30日下午12:58:20
	 */
	public static short getUnsignedShort(byte[] bytes) {
		return (short) ((0xff & bytes[0]) | (0x0ff00 & (bytes[1] << 8)));
	}

	public static short getShort(byte[] bytes) {
		return (short) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	public static char getChar(byte[] bytes) {
		return (char) ((0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)));
	}

	public static int getInt(byte[] bytes) {
		return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16))
				| (0xff000000 & (bytes[3] << 24));
	}

	/**
	 * 转换成无符号整型数
	 * @author jiangbin
	 * @param bytes
	 * @return
	 * @date 2014年8月30日下午1:34:53
	 */
	public static long getUnsignedInt(byte[] bytes) {
		return (0x0ffL & bytes[0]) | (0x0ff00L & ((long) bytes[1] << 8)) | (0x0ff0000L & ((long) bytes[2] << 16))
				| (0x0ff000000L & ((long) bytes[3] << 24));
	}

	public static long getLong(byte[] bytes) {
		return (0xffL & bytes[0]) | (0xff00L & ((long) bytes[1] << 8)) | (0xff0000L & ((long) bytes[2] << 16))
				| (0xff000000L & ((long) bytes[3] << 24)) | (0xff00000000L & ((long) bytes[4] << 32))
				| (0xff0000000000L & ((long) bytes[5] << 40)) | (0xff000000000000L & ((long) bytes[6] << 48))
				| (0xff00000000000000L & ((long) bytes[7] << 56));
	}

	public static float getFloat(byte[] bytes) {
		return Float.intBitsToFloat(getInt(bytes));
	}

	public static double getDouble(byte[] bytes) {
		long l = getLong(bytes);
		return Double.longBitsToDouble(l);
	}

	public static String getString(byte[] bytes, String charsetName) {
		return new String(bytes, Charset.forName(charsetName));
	}

	public static String getString(byte[] bytes) {
		return getString(bytes, "utf-8");
	}

	/**
	 * 合并字节数组
	 * @author jiangbin
	 * @param sources
	 * @param targets
	 * @return
	 * @date 2014年10月24日上午10:46:24
	 */
	public static byte[] merger(byte[] sources, byte[] targets) {
		if (ArrayUtils.isEmpty(sources) && ArrayUtils.isEmpty(targets)) {
			return null;
		}
		if (ArrayUtils.isEmpty(sources)) {
			return targets.clone();
		}
		if (ArrayUtils.isEmpty(targets)) {
			return sources.clone();
		}
		int sLen = ArrayUtils.getLength(sources);
		int tLen = ArrayUtils.getLength(targets);
		int count = sLen + tLen;
		byte[] results = new byte[count];
		for (int index = 0; index < sLen; index++) {
			results[index] = sources[index];
		}
		for (int index = sLen; index < count; index++) {
			results[index] = targets[index - sLen];
		}
		return results;
	}

	/**
	 * 合并字节数组列表
	 * @author jiangbin
	 * @param sources
	 * @return
	 * @date 2014年10月24日上午10:48:42
	 */
	public static byte[] merger(byte[]... sources) {
		if (ArrayUtils.isEmpty(sources)) {
			return null;
		}
		byte[] results = null;
		for (byte[] source : sources) {
			results = merger(results, source);
		}
		return results;
	}

	/**
	 * 从左向右移以给定字节补全给定位
	 * @author jiangbin
	 * @param bytes
	 * @param size
	 * @param target
	 * @return
	 * @date 2014年11月11日下午3:41:10
	 */
	public static byte[] leftPad(byte[] bytes, int size, byte target) {
		if (bytes == null) {
			return null;
		}
		if (size < 1) {
			return bytes;
		}
		if (bytes.length == 0) {
			return createArray(size, target);
		}
		if (size <= bytes.length) {
			return bytes;
		}
		int len = size - bytes.length;
		byte[] targes = createArray(len, target);
		return merger(targes, bytes);
	}

	/**
	 * 从左向右移以0字节补全给定位
	 * @author jiangbin
	 * @param bytes
	 * @param size
	 * @return
	 * @date 2014年11月11日下午3:41:10
	 */
	public static byte[] leftPad(byte[] bytes, int size) {
		return leftPad(bytes, size, (byte) 0);
	}

	/**
	 * 从右向左移以给定字节补全给定位
	 * @author jiangbin
	 * @param bytes
	 * @param size
	 * @param target
	 * @return
	 * @date 2014年11月11日下午3:41:09
	 */
	public static byte[] rightPad(byte[] bytes, int size, byte target) {
		if (bytes == null) {
			return null;
		}
		if (size < 1) {
			return bytes;
		}
		if (bytes.length == 0) {
			return createArray(size, target);
		}
		if (size <= bytes.length) {
			return bytes;
		}
		int len = size - bytes.length;
		byte[] targes = createArray(len, target);
		return merger(bytes, targes);
	}

	/**
	 * 从右向左移以0定字节补全给定位
	 * @author jiangbin
	 * @param bytes
	 * @param size
	 * @return
	 * @date 2014年11月11日下午3:41:09
	 */
	public static byte[] rightPad(byte[] bytes, int size) {
		return rightPad(bytes, size, (byte) 0);
	}

	/**
	 * 创建字节数组，内容为指定字节的重复
	 * @author jiangbin
	 * @param size
	 * @param target
	 * @return
	 * @date 2014年11月11日下午3:45:41
	 */
	public static byte[] createArray(int size, byte target) {
		if (size <= 0) {
			return null;
		}
		byte[] bytes = new byte[size];
		for (int index = 0; index < size; index++) {
			bytes[index] = target;
		}
		return bytes;
	}

	/**
	 * 创建内容为0的重复字节数组
	 * @author jiangbin
	 * @param size
	 * @return
	 * @date 2014年11月11日下午3:45:41
	 */
	public static byte[] createArray(int size) {
		return createArray(size, (byte) 0);
	}

}