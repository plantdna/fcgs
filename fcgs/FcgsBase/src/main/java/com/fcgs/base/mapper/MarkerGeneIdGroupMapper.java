package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.filter.GeneMarkerListFilter;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;

import java.util.List;

/**
 * 位点按指纹ID分组,key/value--指纹ID/位点列表
 *
 * @author jiangbin
 * @date 2013-4-16上午10:53:53
 */
public class MarkerGeneIdGroupMapper extends GroupMapperTemplate<Marker, Marker> {

    private static final long serialVersionUID = 4643325827600052288L;

    public MarkerGeneIdGroupMapper() {
        super();
    }

    public MarkerGeneIdGroupMapper(List<Gene> genes) {
        super();
        this.addGenes(genes);
    }

    @Override
    public Marker convert(Marker source) throws ICoreException {
        return source;
    }

    @Override
    protected String getMapperKey(Marker object) {
        return object.getGeneId();
    }

    /**
     * 添加指纹列表到本Mapper中，将先过滤出位点列表再使用addAll()方法添加到本Mapper中
     *
     * @param genes
     * @throws ICoreException
     * @author jiangbin
     * @date 2013-4-16上午10:58:18
     */
    public void addGenes(List<Gene> genes) {
        GeneMarkerListFilter filter = new GeneMarkerListFilter();
        List<Marker> markers = filter.filter(genes);
        this.addAll(markers);
    }
}
