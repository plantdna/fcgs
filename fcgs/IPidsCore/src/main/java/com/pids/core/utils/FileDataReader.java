package com.pids.core.utils;

import java.io.*;

/**
 * 按字节流方式读取文件数据功能，支持带"\"特殊字符的内容读取
 *
 * @author jiangbin
 * @date 2020/3/21 19:53
 **/
public class FileDataReader {
    /**
     * 读取文件内容为二进制字节数组，本功能可支持文件内容包含"\"特殊字符
     *
     * @param file
     * @return byte[]
     * @author jiangbin
     * @date 2020/3/21 19:50
     **/
    public static byte[] readBytes(File file) throws IOException {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            return out.toByteArray();
        } finally {
            in.close();
        }
    }

    /**
     * 读取文件内容为指定编码的字符串
     *
     * @param file
     * @param encoding
     * @return java.lang.String
     * @author jiangbin
     * @date 2020/3/21 19:51
     **/
    public static String read(File file, String encoding) throws IOException {
        byte[] contents = readBytes(file);
        return new String(contents, encoding);
    }

    /**
     * 读取文件内容为UTF-8编码的字符串
     *
     * @param file
     * @return java.lang.String
     * @author jiangbin
     * @date 2020/3/21 19:52
     **/
    public static String read(File file) throws IOException {
        return read(file, "UTF-8");
    }
}
