package com.pids.core.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生成UUID号
 * @author Andory
 * @date 2013年9月17日下午1:45:13
 */
public class UuidUtil {

	private static final long leastSigBits;
	private static final ReentrantLock lock = new ReentrantLock();
	private static long lastTime;

	static {
		byte[] seed = new SecureRandom().generateSeed(8);
		leastSigBits = new BigInteger(seed).longValue();
	}

	private UuidUtil() {
	}

	/**
	 * Create a new time-based UUID.
	 *
	 * @return the new UUID
	 */
	public static String getUuid() {
		long timeMillis = (System.currentTimeMillis() * 10000) + 0x01B21DD213814000L;

		lock.lock();
		try {
			if (timeMillis > lastTime) {
				lastTime = timeMillis;
			} else {
				timeMillis = ++lastTime;
			}
		} finally {
			lock.unlock();
		}

		// time low
		long mostSigBits = timeMillis << 32;

		// time mid
		mostSigBits |= (timeMillis & 0xFFFF00000000L) >> 16;

		// time hi and version
		mostSigBits |= 0x1000 | ((timeMillis >> 48) & 0x0FFF); // version 1

		return new UUID(mostSigBits, leastSigBits).toString().replaceAll("-", "");
	}

}
