package com.fcgs.base.convertor;

import com.fcgs.base.domain.GeneLocation;
import com.fcgs.base.domain.SampleDna;
import com.fcgs.base.domain.SimpleGeneLocation;
import com.fcgs.base.domain.SimpleSampleDna;
import com.fcgs.base.domain.gene.Gene;
import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 指纹转换器抽象接口,该接口规定了Gene所需的相关数据内容
 * @author Andory
 * @date 2012-6-16下午03:32:18
 */
public abstract class AbstractGeneConvertor<S> extends ListConverterTemplate<S, Gene> {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Gene convert(S source, boolean isInternal) throws ICoreException {
        if (source == null) {
            log.warn("源对象为null无法转换成Gene对象!");
            return null;
        }
        Gene gene = this.parserGeneData(source);
        if (gene == null) {
            log.warn("({},{})未包含指纹信息!", this.getGeneId(source), this.getSamBarcode(source));
            return null;
        }
        gene.setGeneId(getGeneId(source));
        gene.setGeneLib(getGeneLib(source));
        gene.setGeneStatus(getGeneStatus(source));
        gene.setGeneAuditWay(getGeneAuditWay(source));
        gene.setSample(getSample(source));
        gene.setLocation(getLocation(source));
        gene.setGeneOrigin(getGeneOrigin(source));
        gene.setManualMarkerNames(getManualMarkerNames(source));
        gene.setUser(getUser(source));
        return gene;
    }

    /**
     * 获取用户
     * @author WUHAOTIAN
     * @param source
     * @return
     * @date 2019年4月25日下午2:20:40
     */
    protected abstract String getUser(S source);

    /**
     * <pre>获取指纹来源，即指纹的入库方式：
     *   0-自动入库
     *   1-Excel导入
     *   2-zip包导入
     * </pre>
     * @author Ritchieyan
     * @param source
     * @return
     * @date 2014年8月30日下午6:44:11
     */
    protected abstract Integer getGeneOrigin(S source);

    /**
     * 获取指纹审核状态
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年4月24日下午1:25:04
     */
    protected abstract Integer getGeneAuditWay(S source);

    /**
     * 获取指纹位置信息
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:13:13
     */
    protected GeneLocation getLocation(S source) {
        String well = this.getWell(source);
        String plate = this.getPlate(source);
        if (StringUtils.isBlank(plate) || StringUtils.isBlank(well)) {
            return null;
        }
        return new SimpleGeneLocation(plate, well);
    }

    /**
     * 获取样品及DNA信息
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:13:14
     */
    protected SampleDna getSample(S source) {
        SampleDna sample = new SimpleSampleDna();
        sample.setDnaBarcode(getDnaBarcode(source));
        sample.setDnaManager(getDnaManager(source));
        sample.setDnaType(getDnaType(source));
        sample.setExtractWay(getExtractWay(source));
        sample.setSamBarcode(getSamBarcode(source));
        sample.setSamManager(getSamManager(source));
        sample.setSamName(getSamName(source));
        sample.setSamRealName(getSamName(source));
        sample.setSamOrigin(getSamOrigin(source));
        sample.setSamSpecies(getSamSpecies(source));
        sample.setSamKind(getSamKind(source));
        sample.setSamNumber(getSamNumber(source));
        return sample;
    }

    /**
     * 获取样品原编号
     * @author sunqiuyun
     * @param source
     * @return
     * @date 2015年9月10日下午3:14:55
     */
    protected abstract String getSamNumber(S source);

    /**
     * 获取样品类型，如杂交种、自交系等
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年5月8日下午12:18:27
     */
    protected abstract String getSamKind(S source);

    /**
     * 获取DNA的负责人
     * @author Andory
     * @param source 源对象
     * @return
     * @date 2012-6-16下午03:40:23
     */
    protected abstract String getDnaManager(S source);

    /**
     * 获取样品编号,通常是条码号
     * @author Andory
     * @param source 源对象
     * @return
     * @date 2012-6-16下午03:40:22
     */
    protected abstract String getSamBarcode(S source);

    /**
     * 获取样品名称
     * @author Andory
     * @param source
     * @return
     * @date 2012-6-16下午03:40:21
     */
    protected abstract String getSamName(S source);

    /**
     * 获取样品种属
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:20:41
     */
    protected abstract String getSamSpecies(S source);

    /**
     * 获取样品来源
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:20:40
     */
    protected abstract String getSamOrigin(S source);

    /**
     * 获取样品负责人
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:20:40
     */
    protected abstract String getSamManager(S source);

    /**
     * 获取DNA提取方式:单株/混株
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:20:39
     */
    protected abstract Integer getExtractWay(S source);

    /**
     * 获取DNA类型:SSR/SNP
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:20:39
     */
    protected abstract Integer getDnaType(S source);

    /**
     * 获取DNA条码号
     * @author jiangbin
     * @param source
     * @return
     * @date 2014年3月21日下午6:20:38
     */
    protected abstract String getDnaBarcode(S source);

    /**
     * 获取指纹的库类型：原始库/实验员库/本地库
     * @author jiangbin
     * @return
     * @date 2013-2-20下午2:57:34
     */
    protected abstract Integer getGeneLib(S source);

    /**
     * 获取指纹审核状态信息:未审核/临时审核/正式审核
     * @author LiuJunGuang
     * @param source
     * @return
     * @date 2013-2-3下午4:25:07
     */
    public abstract Integer getGeneStatus(S source);

    /**
     * 解析指纹数据成指纹对象
     * @author Andory
     * @param source
     * @return
     * @date 2012-6-30下午12:10:38
     */
    public abstract Gene parserGeneData(S source);

    /**
     * 获取板孔号
     * @author Andory
     * @param source 源对象
     * @return
     * @date 2012-6-16下午03:40:23
     */
    public abstract String getWell(S source);

    /**
     * 获取指纹记录ID
     * @author Andory
     * @param source 源对象
     * @return
     * @date 2012-6-16下午03:40:20
     */
    public abstract String getGeneId(S source);

    /**
     * 获取电泳板或上样板号
     * @author Andory
     * @param source 源对象
     * @return
     * @date 2012-6-16下午03:40:19
     */
    public abstract String getPlate(S source);

    /**
     * 获取手动审核位点名列表
     * @author jiangbin
     * @param source
     * @return
     * @date 2015年2月13日下午11:50:43
     */
    protected abstract List<String> getManualMarkerNames(S source);
}
