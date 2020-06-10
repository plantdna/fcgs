package com.fcgs.core.comparer.thread;

import com.fcgs.base.domain.gene.Marker;
import com.fcgs.core.comparer.mapper.MarkerItemMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 创建指定引物名对应位点列表的分型数据分组映射表，主要解决超大规模时分型分组耗时过长的问题
 *
 * @author jiangbin
 * @date 2019/11/20 5:36 下午
 **/

public class FastMarkerItemMapperThread implements Runnable {
    private List<List<Marker>> markers;
    private List<MarkerItemMapper> mappers;
    private CountDownLatch latch;

    @Override
    public void run() {
        try {
            mappers = new ArrayList<>();
            for (List<Marker> group : markers) {
                String primerName = group.get(0).getPrimerName();
                MarkerItemMapper mapper = new MarkerItemMapper();
                mapper.setPrimerName(primerName);
                mapper.addAll(group);
                mappers.add(mapper);
            }
        } finally {
            latch.countDown();
        }
    }

    public List<List<Marker>> getMarkers() {
        return markers;
    }

    public void setMarkers(List<List<Marker>> markers) {
        this.markers = markers;
    }

    public List<MarkerItemMapper> getMappers() {
        return mappers;
    }

    public void setMappers(List<MarkerItemMapper> mappers) {
        this.mappers = mappers;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
