package com.pids.core.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

/**
 * 字符串zip和gzip压缩和解压缩功能
 * @author jiang
 * @date 2018年9月26日下午8:15:15
 */
public class StringZipUtils {

	/**
	 * 使用gzip进行压缩字符串
	 * @author jiang
	 * @param sourceStr 待压缩字符串
	 * @return
	 * @date 2018年9月26日下午8:31:00
	 */
	public static String gzip(String sourceStr) {
		if (StringUtils.isBlank(sourceStr)) {
			return sourceStr;
		}

		return gzip(sourceStr.getBytes());
	}

	/**
	 * gzip压缩字节数组内容
	 * @author jiang
	 * @param sourceBytes 待压缩字节内容
	 * @return
	 * @date 2018年9月29日下午12:08:46
	 */
	public static String gzip(byte[] sourceBytes) {
		if (ArrayUtils.isEmpty(sourceBytes)) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(sourceBytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return encode(out.toByteArray());
	}

	/**
	 * 使用gzip进行解压缩字符串
	 * @author jiang
	 * @param compressedStr 待解压字符串
	 * @return
	 * @date 2018年9月26日下午8:30:15
	 */
	public static String gunzip(String compressedStr) {
		if (StringUtils.isBlank(compressedStr)) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = decodeBuffer(compressedStr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}

	/**
	 * 使用zip进行压缩字符串
	 * @author jiang
	 * @param sourceStr 压缩前的文本
	 * @return 返回压缩后的文本
	 * @date 2018年9月26日下午8:29:52
	 */
	public static final String zip(String sourceStr) {
		if (StringUtils.isBlank(sourceStr)) {
			return null;
		}

		return zip(sourceStr.getBytes());
	}

	/**
	 * 压缩给定字节数组内容
	 * @author jiang
	 * @param sourceBytes 待压缩字节数组
	 * @return
	 * @date 2018年9月29日下午12:10:53
	 */
	public static final String zip(byte[] sourceBytes) {
		if (ArrayUtils.isEmpty(sourceBytes)) {
			return null;
		}

		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String target = null;

		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(sourceBytes);
			zout.closeEntry();
			compressed = out.toByteArray();
			target = encodeBuffer(compressed);
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return target;
	}

	/**
	 * 使用zip进行解压缩
	 * @author jiang
	 * @param compressedStr 压缩后的文本
	 * @return 解压后的字符串
	 * @date 2018年9月26日下午8:32:35
	 */
	public static final String unzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = decodeBuffer(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}

	@SuppressWarnings("restriction")
	private static String encode(byte[] bytes) {
		return new sun.misc.BASE64Encoder().encode(bytes);
	}

	@SuppressWarnings({ "restriction" })
	private static byte[] decodeBuffer(String str) throws IOException {
		return new sun.misc.BASE64Decoder().decodeBuffer(str);
	}

	@SuppressWarnings({ "restriction" })
	private static String encodeBuffer(byte[] bytes) throws IOException {
		return new sun.misc.BASE64Encoder().encodeBuffer(bytes);
	}
}
