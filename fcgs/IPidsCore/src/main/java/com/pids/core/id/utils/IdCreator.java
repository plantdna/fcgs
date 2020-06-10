package com.pids.core.id.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.IPidsCoreI18N;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 创建36进制的ID号字符串
 * @author jiang
 * @date 2019-08-14 21:44
 */
public class IdCreator {
    private AtomicLong id = new AtomicLong(0);
    private AtomicInteger width = new AtomicInteger(6);

    /**
     * <pre>创建ID号，步骤如下：
     * 1、创建一个Long整数并转换成36进制字符串
     * 2、判定字符串宽度是否合法，否则抛出异常信息</pre>
     * @return java.lang.String
     * @throws
     * @author jiang
     * @date 2019/8/14 21:45
     **/
    public String create() {
        long num = id.incrementAndGet();
        String idStr = Long.toString(num, 36);
        if (idStr.length() > width.get()) {
            throw new ICoreException(String.format(IPidsCoreI18N.SSR_IDCREATOR_ERROR02.get(), num));
        }
        return StringUtils.leftPad(idStr, width.get(), "0");
    }

    /**
     * 构建函数,默认从0开始计数
     * @author jiang
     * @date 2019/8/14 22:26
     **/
    public IdCreator() {
    }

    /**
     * 构建函数
     * @param start 起始ID号
     * @author jiang
     * @date 2019/8/14 22:26
     **/
    public IdCreator(long start) {
        id.getAndSet(start);
    }

    /**
     * 获取生成的ID号宽度限制
     * @return int
     * @throws
     * @author jiang
     * @date 2019/8/14 22:28
     **/
    public int getWidth() {
        return width.get();
    }

    /**
     * 设置生成的ID号宽度限制
     * @return void
     * @throws
     * @author jiang
     * @date 2019/8/14 22:29
     **/
    public void setWidth(int width) {
        this.width.set(width);
    }
}
