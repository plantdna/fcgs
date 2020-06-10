package com.fcgs.core.comparer.domain;

import java.io.Serializable;

/**
 * 比对结果信息对象
 *
 * @author jiangbin
 * @date 2012-10-29上午10:51:00
 */
public interface ComparerResult extends Serializable {
    /**
     * 获取源指纹ID号
     *
     * @return
     * @author jiangbin
     * @date 2012-11-1下午6:13:46
     */
    String getSourceGeneId();

    /**
     * 设置源指纹ID号
     *
     * @param sourceGeneId
     * @author jiangbin
     * @date 2012-11-1下午6:13:48
     */
    void setSourceGeneId(String sourceGeneId);

    /**
     * 获取目标指纹ID号
     *
     * @return
     * @author jiangbin
     * @date 2012-11-1下午6:13:49
     */
    String getTargetGeneId();

    /**
     * 设置目标指纹ID号
     *
     * @param targetGeneId
     * @author jiangbin
     * @date 2012-11-1下午6:13:50
     */
    void setTargetGeneId(String targetGeneId);

    /**
     * 设置比对结果对象
     *
     * @param source
     * @author jiangbin
     * @date 2012-11-1下午6:33:03
     */
    void setComaprerResult(ComparerResult source);
}
