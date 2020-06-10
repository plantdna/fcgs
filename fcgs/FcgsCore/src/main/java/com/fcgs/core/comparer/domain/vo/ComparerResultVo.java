package com.fcgs.core.comparer.domain.vo;

import com.fcgs.base.domain.Sample;
import com.fcgs.base.marker.utils.MarkerNamesViwer;
import com.fcgs.core.comparer.domain.DetailComparerResult;

import java.util.List;

/**
 * 指纹比对结果视图对象
 *
 * @author Jiangbin
 * @date 2014-3-4上午12:35:44
 */
public interface ComparerResultVo extends DetailComparerResult, MarkerNamesViwer {
    /**
     * 获取源样品信息
     *
     * @return
     * @author Jiangbin
     * @date 2014-3-4上午12:38:10
     */
    public Sample getSourceSample();

    /**
     * 设置源样品信息
     *
     * @param sourceSample
     * @author Jiangbin
     * @date 2014-3-4上午12:38:12
     */
    public void setSourceSample(Sample sourceSample);

    /**
     * 获取目标样品信息
     *
     * @return
     * @author Jiangbin
     * @date 2014-3-4上午12:38:11
     */
    public Sample getTargetSample();

    /**
     * 设置目标样品信息
     *
     * @param targetSample
     * @author Jiangbin
     * @date 2014-3-4上午12:38:14
     */
    public void setTargetSample(Sample targetSample);

    /**
     * 获取源指纹状态
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:06:46
     */
    public Integer getSourceGeneStatus();

    /**
     * 设置源指纹状态
     *
     * @param sourceGeneStatus
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:06:48
     */
    public void setSourceGeneStatus(Integer sourceGeneStatus);

    /**
     * 获取目标指纹状态
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:06:50
     */
    public Integer getTargetGeneStatus();

    /**
     * 设置目标指纹状态
     *
     * @param targetGeneStatus
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:06:52
     */
    public void setTargetGeneStatus(Integer targetGeneStatus);

    /**
     * 获取源指纹板孔位置
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:19:41
     */
    public String getSourceGeneLocation();

    /**
     * 设置源指纹板孔未知
     *
     * @param sourceGeneLocation
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:20:03
     */
    public void setSourceGeneLocation(String sourceGeneLocation);

    /**
     * 获取目标指纹板孔位置
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:20:20
     */
    public String getTargetGeneLocation();

    /**
     * 设置目标指纹板孔位置
     *
     * @param targetGeneLocation
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:20:33
     */
    public void setTargetGeneLocation(String targetGeneLocation);

    /**
     * 设置源指纹库类型
     *
     * @param geneLib
     * @author LiuJunGuang
     */
    public void setSourceGeneLib(Integer geneLib);

    /**
     * 设置目标指纹库类型
     *
     * @param geneLib
     * @author LiuJunGuang
     */
    public void setTargetGeneLib(Integer geneLib);

    /**
     * 获取源指纹库类型
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:26:10
     */
    public Integer getSourceGeneLib();

    /**
     * 获取目标指纹库类型
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月18日下午5:26:12
     */
    public Integer getTargetGeneLib();

    /**
     * 获取源指纹库名称
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:42:26
     */
    public String getSourceGeneLibName();

    /**
     * 设置源指纹库名称
     *
     * @param sourceGeneLibName
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:42:29
     */
    public void setSourceGeneLibName(String sourceGeneLibName);

    /**
     * 获取目标指纹库名称
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:42:31
     */
    public String getTargetGeneLibName();

    /**
     * 设置目标指纹库名称
     *
     * @param targetGeneLibName
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:42:33
     */
    public void setTargetGeneLibName(String targetGeneLibName);

    /**
     * 获取源指纹状态名称
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:48:21
     */
    public String getSourceGeneStatusName();

    /**
     * 设置源指纹状态名称
     *
     * @param sourceGeneStatusName
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:48:23
     */
    public void setSourceGeneStatusName(String sourceGeneStatusName);

    /**
     * 获取目标指纹状态名称
     *
     * @return
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:48:24
     */
    public String getTargetGeneStatusName();

    /**
     * 设置目标指纹状态名称
     *
     * @param targetGeneStatusName
     * @author LiuJunGuang
     * @date 2014年4月22日上午10:48:26
     */
    public void setTargetGeneStatusName(String targetGeneStatusName);

    /**
     * 获取待比和对比指纹ID号
     *
     * @return
     * @author jiangbin
     * @date 2015年2月6日上午2:01:11
     */
    public List<String> getGeneIds();

    /**
     * 获取参与比对的位点数
     *
     * @return
     * @author jiangbin
     * @date 2018年3月26日下午8:19:48
     */
    public int getComparerMarkerCount();

    /**
     * 获取差异位点占比
     *
     * @return
     * @author jiangbin
     * @date 2018年3月26日下午8:15:17
     */
    public float getDiffMarkerPercent();

    /**
     * 获取无差异位点占比
     *
     * @return
     * @author jiangbin
     * @date 2018年3月26日下午8:23:52
     */
    public float getNoDiffMarkerPercent();

    /**
     * <pre>交换待比和对比信息:
     * 样品信息
     * 指纹信息
     * <b>注意：
     * 	本方法不会造成比对结果的问题，因为比对时正反比对出的结果是一致的，
     * 	本方法一般用于比对结果的排序功能上，以保证按照用户需要进行数据显示,
     *  如果扩展实现接口需要重新实现本方法，否则可能造成新增属性未被正确交换</b></pre>
     *
     * @author jiangbin
     * @date 2016年7月31日下午12:39:24
     */
    public void swap();

    /**
     * 获取位点差异率，计算方法为：(差异位点*1.0/比对总位点数);
     *
     * @return
     * @author jiang
     * @date 2019年5月30日下午8:49:57
     */
    public double getDiffRate();

    /**
     * 获取位点差异率的整数表示，即：位点差异率乘以100后再四舍五入
     *
     * @return
     * @author jiang
     * @date 2019年6月7日下午2:30:31
     */
    public int getIntDiffRate();

    /**
     * 获取差异位点的字符串表示
     *
     * @return
     */
    public String getDiffMarkerNamesStr();

    /**
     * 获取无差异位点的字符串表示
     *
     * @return
     */
    public String getNoDiffMarkerNamesStr();

}
