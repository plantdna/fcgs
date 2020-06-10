package com.pids.core.utils;

/**
 * 进行bit位操作工具类,bit位的索引号0~7表示低位到高位，如2表示2的2次方位，
 * 而输出时我们一般左边为高位，右边为低位，如:00110110表示的是54
 *
 * @author jiangbin
 * @date 2020/1/2 3:03 下午
 **/
public class BitUtils {
    /**
     * 判定指定位上是否为0
     *
     * @param sbyte 源字节
     * @param index 索引号
     * @return boolean
     * @author jiangbin
     * @date 2020/1/2 3:15 下午
     **/
    public static boolean isZero(final byte sbyte, int index) {
        return getBit(sbyte, index) == 0;
    }

    /**
     * 获取指定索引号上对应位bit值
     *
     * @param sbyte 源字节
     * @param index
     * @return int
     * @author jiangbin
     * @date 2020/1/2 3:19 下午
     **/
    public static byte getBit(final byte sbyte, int index) {
        return (byte) (sbyte & (byte) (Math.pow(2, index)));
    }

    /**
     * 从字节数组中取出指定索引位上的bit数据
     *
     * @param sbytes
     * @param index
     * @return byte
     * @author jiangbin
     * @date 2020/1/2 4:46 下午
     **/
    public static byte getBit(final byte[] sbytes, int index) {
        int byteCount = getByteCount(index + 1);
        return getBit(sbytes[byteCount], index % 8);
    }

    /**
     * 设置字节数组指定byte位的数据为0或1
     *
     * @param sbytes 源字节数组列表
     * @param index  索引号
     * @param zero   true/false--设置为0/1
     * @return byte[]
     * @author jiangbin
     * @date 2020/1/2 4:48 下午
     **/
    public static byte[] setBit(final byte[] sbytes, int index, boolean zero) {
        int byteCount = getByteCount(index + 1);
        byte tbyte = setBit(sbytes[byteCount], index % 8, zero);
        sbytes[byteCount] = tbyte;
        return sbytes;
    }

    /**
     * 设置指定bit位数据值为0或1
     *
     * @param sbyte 源字节
     * @param index 字节索引号
     * @param zero  true/false--0/1
     * @return void
     * @author jiangbin
     * @date 2020/1/2 3:21 下午
     **/
    public static byte setBit(final byte sbyte, int index, boolean zero) {
        byte bit = getBit(sbyte, index);
        if (bit > 0) {//现有bit位值为0
            if (zero) {//要设置bit位成0
                return (byte) (sbyte | (byte) (Math.pow(2, index)));
            } else {//要设置bit位成1
                return sbyte;
            }
        } else {//现有bit位值为1
            if (zero) {//要设置bit位成0
                return sbyte;
            } else {//要设置bit位成1
                return (byte) (sbyte | (byte) (Math.pow(2, index)));
            }
        }
    }

    /**
     * 将byte转换成二进制字符串，如：54==>00110110
     *
     * @param sbyte 源字节
     * @return java.lang.String
     * @author jiangbin
     * @date 2020/1/2 3:20 下午
     **/
    public static String toBinary(final byte sbyte) {
        StringBuilder sb = new StringBuilder();
        for (int index = 7; index >= 0; index--) {
            int j = getBit(sbyte, index);
            if (j > 0) {
                sb.append(1);
            } else {
                sb.append(0);
            }
        }
        return sb.toString();
    }

    /**
     * 获取存储指定bit位所需的byte字节数
     *
     * @param bitCount
     * @return int
     * @author jiangbin
     * @date 2020/1/2 3:47 下午
     **/
    public static int getByteCount(int bitCount) {
        return bitCount / 8 + (bitCount % 8 > 0 ? 1 : 0);
    }
}
