package com.pids.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 *
 * @author jiangbin
 * @date 2019-08-13 17:05
 **/
public class ExceptionUtils {

    /**
     * 获取堆栈信息内容
     *
     * @param throwable
     * @return java.lang.String
     * @author jiangbin
     * @date 2019-08-13 17:06
     **/
    public static String getStackTrace(Throwable throwable) {
        StringWriter target = new StringWriter();
        PrintWriter printWriter = new PrintWriter(target);
        try {
            throwable.printStackTrace(printWriter);
            return target.toString();
        } finally {
            printWriter.close();
        }
    }
}
