package com.pids.core.creator;

/**
 * 随机字符串创建器
 * @author WUHAOTIAN
 * @email nghsky@foxmail.com
 * @date 2019年4月15日上午11:15:52
 */
public class RandomDatasCreator {
    public static final char BIG[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    public static final char SMALL[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public static final char NUM[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public static final char ALL[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };

    /**
     * 获取指定长度字符串，包含大写、小写字母、数字
     * @author WUHAOTIAN
     * @param len 字符串长度
     * @return
     * @date 2019年4月15日上午11:20:14
     */
    public static String getStr(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < len + 1; i++) {
            int index = (int) (Math.random() * (ALL.length));
            sb.append(ALL[index] + "");
        }
        return sb.toString();
    }

    /**
     * 获取指定长度大写字母
     * @author WUHAOTIAN
     * @param len
     * @return
     * @date 2019年4月15日上午11:21:18
     */
    public static String getUppercaseStr(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < len + 1; i++) {
            int index = (int) (Math.random() * (BIG.length));
            sb.append(BIG[index] + "");
        }
        return sb.toString();
    }

    /**
     * 获取指定长度小写字母
     * @author WUHAOTIAN
     * @param len
     * @return
     * @date 2019年4月15日上午11:21:18
     */
    public static String getLowercaseStr(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < len + 1; i++) {
            int index = (int) (Math.random() * (SMALL.length));
            sb.append(SMALL[index] + "");
        }
        return sb.toString();
    }

    /**
     * 获取指定长度数字
     * @author WUHAOTIAN
     * @param len
     * @return
     * @date 2019年4月15日上午11:21:18
     */
    public static String getNum(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < len + 1; i++) {
            int index = (int) (Math.random() * (NUM.length));
            sb.append(NUM[index] + "");
        }
        return sb.toString();
    }
}
