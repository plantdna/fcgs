package com.fcgs.core.comparer.mapper;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.core.comparer.domain.MarkerItem;
import com.fcgs.core.comparer.domain.SimpleMarkerItem;
import com.pids.core.mapper.MapperTemplate;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Key/Value--位点分型字符串/位点分型信息分组列表
 *
 * @author jiangbin
 * @date 2019/10/28 3:46 下午
 **/
public class MarkerItemMapper extends MapperTemplate<MarkerItem, Marker> {
    private String primerName;

    public void setPrimerName(String primerName) {
        this.primerName = primerName;
    }

    public String getPrimerName() {
        return primerName;
    }

    @Override
    protected MarkerItem getMapperValue(Marker object) {
        String markerStr = this.getMapperKey(object);
        MarkerItem item = this.get(markerStr);
        if (item == null) {
            item = createMarkerItem(object);
        }
        item.add(object.getGeneId());
        return item;
    }

    /**
     * 创建位点分型项对象
     *
     * @param source
     * @return com.viathink.ssr.core4.statistics.comparer.domain.MarkerItem
     * @author jiangbin
     * @date 2019/11/6 12:50 下午
     **/
    private MarkerItem createMarkerItem(Marker source) {
        MarkerItem item = new SimpleMarkerItem();
        item.setPrimerName(source.getPrimerName());
        List<Allele> alleles = source.getAlleles();
        int allele1 = (int) alleles.get(0).getAllele();
        int allele2 = (int) alleles.get(1).getAllele();
        item.setAllele1(allele1);
        item.setAllele2(allele2);
        item.setMarkerStr(allele1 + "/" + allele2);
        item.setIsozygote(allele1 == allele2);
        return item;
    }

    @Override
    protected String getMapperKey(Marker object) {
        return object.getMarkerStr();
    }

    /**
     * 获取所有分型的位点列表总数
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/14 4:29 下午
     **/
    public int getMarkerCount() {
        List<MarkerItem> items = getValues();
        if (CollectionUtils.isEmpty(items)) {
            return 0;
        }
        int count = 0;
        for (MarkerItem item : items) {
            count += ListUtils.size(item.getGeneIdList());
        }
        return count;
    }

}
