package com.fcgs.core.comparer.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 位点分型信息对象
 *
 * @author jiangbin
 * @date 2019/10/31 9:10 上午
 **/
public interface MarkerItem extends Serializable {

    /**
     * 获取引物名
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    String getPrimerName();

    /**
     * 设置引物名
     *
     * @param primerName
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    void setPrimerName(String primerName);

    /**
     * 获取Allele1的值
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    int getAllele1();

    /**
     * 设置Allele1的值
     *
     * @param allele1
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    void setAllele1(int allele1);

    /**
     * 获取Allele2的值
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    int getAllele2();

    /**
     * 设置Allele2的值
     *
     * @param allele2
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    void setAllele2(int allele2);

    /**
     * 设置位点分型字符串，如:201/222
     *
     * @param markerStr
     * @return void
     * @author jiangbin
     * @date 2019/11/20 2:29 下午
     **/
    void setMarkerStr(String markerStr);

    /**
     * 获取位点分型数据字符串，如:201/222
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2019/11/20 2:29 下午
     **/
    String getMarkerStr();

    /**
     * 是否为纯合子
     *
     * @return boolean
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    boolean isIsozygote();

    /**
     * 设置是否为纯合子
     *
     * @param isozygote
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:52 下午
     **/
    void setIsozygote(boolean isozygote);

    /**
     * 获取指纹ID列表，不区分待比和对比指纹
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/9 2:51 下午
     **/
    List<String> getGeneIdList();

    /**
     * 设置指纹ID列表，不区分待比和对比指纹
     *
     * @param geneIdList
     * @return void
     * @author jiangbin
     * @date 2019/11/9 2:51 下午
     **/
    void setGeneIdList(List<String> geneIdList);

    /**
     * 指纹ID是否存在，不区分待比和对比指纹
     *
     * @param geneId 指纹ID号
     * @return boolean
     * @author jiangbin
     * @date 2019/11/8 6:23 下午
     **/
    boolean has(String geneId);

    /**
     * 添加指纹ID号，需要通过本方法来添加指纹ID，才能避免指纹ID列表中存在重复ID号
     *
     * @param geneId 指纹ID号
     * @return boolean true/false--添加指纹ID号成功/指纹ID已存在
     * @author jiangbin
     * @date 2019/11/8 6:25 下午
     **/
    boolean add(String geneId);

    /**
     * 添加指纹ID号列表，需要通过本方法来添加指纹ID，才能避免指纹ID列表中存在重复ID号
     *
     * @param geneIds
     * @author jiangbin
     * @date 2019/11/28 1:56 下午
     **/
    void add(List<String> geneIds);
}
