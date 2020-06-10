package com.fcgs.core.comparer.domain;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.filler.MarkerGeneIdFiller;
import com.fcgs.base.filter.GeneIdFilter;
import com.fcgs.base.filter.GeneMarkerListFilter;
import com.fcgs.base.mapper.GeneInfoMapper;
import com.fcgs.core.comparer.mapper.MarkerItemPrimerMapper;
import com.fcgs.core.comparer.utils.MarkerItemPrimerMapperCreator;
import com.fcgs.core.comparer.utils.SmartComparerResultGroupMapper;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 指纹容器对象
 *
 * @author jiangbin
 * @date 2019/11/4 4:13 下午
 **/

public class SimpleStatisticsGeneContainer implements StatisticsGeneContainer {
	private static final long serialVersionUID = 8069622594841866315L;
	private static Logger log=LoggerFactory.getLogger(SimpleStatisticsGeneContainer.class);
	private MarkerItemPrimerMapperCreator mipmCreator;
    private GeneIdFilter<Gene> geneIdFilter;
    private GeneMarkerListFilter geneMarkerListFilter;
    private MarkerGeneIdFiller markerGeneIdFiller;
    private List<Gene> sources;
    private List<Gene> targets;
    private List<String> sourceIdList;
    private List<String> targetIdList;
    private List<String> primerNames;
    private boolean isSame;
    private int maxSizeOffset = 2;

    public SimpleStatisticsGeneContainer() {
        //初始化相关组件
        geneIdFilter = new GeneIdFilter<>();
        geneMarkerListFilter = new GeneMarkerListFilter();
        markerGeneIdFiller = new MarkerGeneIdFiller();
        mipmCreator = new MarkerItemPrimerMapperCreator();
    }

    /**
     * 构建函数
     *
     * @param sources 待比指纹列表
     * @param targets 对比指纹列表
     * @return
     * @throws
     * @author jiang
     * @date 2019/11/9 23:53
     **/
    public SimpleStatisticsGeneContainer(List<Gene> sources, List<Gene> targets) {
        this();

        this.sourceIdList = this.geneIdFilter.filter(sources);
        this.targetIdList = this.geneIdFilter.filter(targets);
        if (ListUtils.isEquals(this.sourceIdList, this.targetIdList)) {
            this.isSame = true;
        }

        this.sources = sources;
        this.markerGeneIdFiller.fill(this.sources);

        if (this.isSame) {
            this.targets = sources;
            this.targetIdList = this.sourceIdList;
        } else {
            this.targets = targets;
            this.markerGeneIdFiller.fill(this.targets);
        }
    }

    @Override
    public boolean valid() {
        return CollectionUtils.isNotEmpty(sources) && CollectionUtils.isNotEmpty(targets);
    }

    @Override
    public List<String> getSourceIdList() {
        return sourceIdList;
    }

    @Override
    public List<String> getTargetIdList() {
        return targetIdList;
    }

    @Override
    public void setPrimerNames(List<String> primerNames) {
        this.primerNames = primerNames;
    }

    @Override
    public List<String> getPrimerNames() {
        return primerNames;
    }

    @Override
    public FastGeneResultSet createFastResult() {
        return new FastGeneResultSet(this.getSourceIdList(), this.getTargetIdList());
    }

    @Override
    public DetailGeneResultSet createDetailResult(List<String> primerNames) {
        return new DetailGeneResultSet(this.getSourceIdList(), this.getTargetIdList(), primerNames);
    }

    @Override
    public DetailGeneResultSet createDetailResult(List<String> primerNames, SmartComparerResultGroupMapper mapper) {
        return new DetailGeneResultSet(this.getSourceIdList(), this.getTargetIdList(), primerNames, mapper);
    }

    @Override
    public MarkerItemContainer createMarkerItemContainer() {
        StopWatch watch = new StopWatch();
        watch.start();

        try {
            MarkerItemContainer container = new MarkerItemContainer();

            //待比指纹位点列表
            List<Marker> smarkers = this.filterMarkers(this.sources);
            MarkerItemPrimerMapper sMapper = mipmCreator.create(smarkers);
            container.setSource(sMapper);

            //范围内指纹互比情况下
            if (isSame) {
                container.setTarget(sMapper);
                container.setSame(true);
                return container;
            }

            //对比指纹位点列表
            List<Marker> tmarkers = filterMarkers(this.targets);
            MarkerItemPrimerMapper tMapper = mipmCreator.create(tmarkers);
            container.setTarget(tMapper);
            return container;
        } finally {
            watch.stop();
            log.info("创建位点分型按引物名分组映射表耗时(ms)==>" + watch.getTime());
        }
    }

    @Override
    public List<Gene> getSources() {
        return sources;
    }

    @Override
    public List<Gene> getTargets() {
        return targets;
    }

    @Override
    public int getMaxSizeOffset() {
        return maxSizeOffset;
    }

    @Override
    public void setMaxSizeOffset(int maxSizeOffset) {
        this.maxSizeOffset = maxSizeOffset;
    }

    @Override
    public List<Gene> getGenes(List<ComparerResult> results) {
        List<Gene> targets = new ArrayList<>();
        GeneInfoMapper mapper = getGeneMapper();
        for (ComparerResult result : results) {
            targets.add(mapper.get(result.getSourceGeneId()));
            targets.add(mapper.get(result.getTargetGeneId()));
        }
        return targets;
    }

    @Override
    public GeneInfoMapper getGeneMapper() {
        GeneInfoMapper mapper = GeneInfoMapper.createMapper(sources);
        mapper.addAll(targets);
        return mapper;
    }

    /**
     * 获取指纹位点列表
     *
     * @param genes
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Marker>
     * @author jiangbin
     * @date 2019/11/20 1:55 下午
     **/
    private List<Marker> filterMarkers(List<Gene> genes) {
        return this.geneMarkerListFilter.filter(genes);
    }

}
