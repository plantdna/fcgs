package com.fcgs.core.comparer.thread;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.core.comparer.mapper.MarkerItemMapper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 创建指定引物名对应位点列表的分型数据分组映射表，主要解决超大规模时分型分组耗时过长的问题
 *
 * @author jiangbin
 * @date 2019/11/20 5:36 下午
 **/
public class MarkerItemMapperThread implements Runnable {
    private List<Marker> markers;
    private MarkerItemMapper mapper;
    private CountDownLatch latch;
    private String primerName;

    @Override
    public void run() {
        try {
            mapper = new MarkerItemMapper();
            mapper.setPrimerName(primerName);
            mapper.addAll(markers);
        } finally {
            latch.countDown();
        }
    }

    /**
     * 获取位点列表
     *
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Marker>
     * @author jiangbin
     * @date 2019/11/21 8:58 上午
     **/
    public List<Marker> getMarkers() {
        return markers;
    }

    /**
     * 设置位点列表
     *
     * @param markers
     * @return void
     * @author jiangbin
     * @date 2019/11/21 8:58 上午
     **/
    public void setMarkers(List<Marker> markers) {
        this.markers = markers;
    }

    /**
     * 获取位点分型映射表
     *
     * @return com.viathink.ssr.core4.statistics.comparer.mapper.MarkerItemMapper
     * @author jiangbin
     * @date 2019/11/21 8:58 上午
     **/
    public MarkerItemMapper getMapper() {
        return mapper;
    }

    /**
     * 设置位点分型映射表
     *
     * @param mapper
     * @return void
     * @author jiangbin
     * @date 2019/11/21 8:58 上午
     **/
    public void setMapper(MarkerItemMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取线程计数器
     *
     * @return java.util.concurrent.CountDownLatch
     * @author jiangbin
     * @date 2019/11/21 8:59 上午
     **/
    public CountDownLatch getLatch() {
        return latch;
    }

    /**
     * 设置线程计数器
     *
     * @param latch
     * @return void
     * @author jiangbin
     * @date 2019/11/21 8:59 上午
     **/
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 获取引物名称
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2019/11/21 8:59 上午
     **/
    public String getPrimerName() {
        return primerName;
    }

    /**
     * 设置引物名称
     *
     * @param primerName
     * @return void
     * @author jiangbin
     * @date 2019/11/21 8:59 上午
     **/
    public void setPrimerName(String primerName) {
        this.primerName = primerName;
    }
}
