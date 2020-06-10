package com.pids.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 序列化和反序列化对象，要求被序列化的对象扩展接口:{@link java.io.Serializable}
 * @author Jiangbin
 * @date 2014-1-10上午12:25:42
 */
public class SerializeUtil {
	private static Logger log = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 序列化对象，若序列化对象失败将返回null
	 * @author Jiangbin
	 * @param object
	 * @return
	 * @date 2014-1-10上午12:26:04
	 */
	public static <S extends Serializable> byte[] serialize(S object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			return baos.toByteArray();
		} catch (Exception e) {
			log.error("序列化对象失败!", e);
		}
		return null;
	}

	/**
	 * 反序列化对象，若反序列化对象失败将返回null
	 * @author Jiangbin
	 * @param bytes
	 * @return
	 * @date 2014-1-10上午12:26:02
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {
			log.error("反序列化对象失败!", e);
		}
		return null;
	}

}
