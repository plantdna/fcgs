package com.pids.core.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

public class Md5Utils {
	/**
	 * 对字符串进行MD5加密
	 * @author LiuJunGuang
	 * @param str
	 * @return
	 * @date 2014年3月24日上午10:33:43
	 */
	public static String md5Hex(String str) {
		return DigestUtils.md5Hex(str);
	}

	/**
	 * MD5加密输入流，返回MD5加密码
	 * @author LiuJunGuang
	 * @param data 输入流
	 * @return MD5加密码
	 * @throws IOException
	 * @date 2014年3月24日上午10:36:25
	 */
	public static String md5Hex(InputStream data) throws IOException {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * MD5加密字节数组
	 * @author LiuJunGuang
	 * @param data
	 * @return
	 * @date 2014年3月24日上午10:37:11
	 */
	public static String md5Hex(byte[] data) {
		return DigestUtils.md5Hex(data);
	}

	public static void main(String args[]) {
		System.out.println(md5Hex("1"));
		System.out.println(md5Hex("c4ca4238a0b923820dcc509a6f75849b"));

	}

}
