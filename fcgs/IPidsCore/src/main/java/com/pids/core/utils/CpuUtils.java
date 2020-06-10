package com.pids.core.utils;

public class CpuUtils {

    /**
     * 获取CPU处理器数目
     *
     * @return
     */
    public static int getCpuNum() {
        return Runtime.getRuntime().availableProcessors();
    }
}
