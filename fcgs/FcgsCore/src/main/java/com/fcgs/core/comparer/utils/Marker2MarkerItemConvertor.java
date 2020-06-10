package com.fcgs.core.comparer.utils;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.core.comparer.domain.MarkerItem;
import com.fcgs.core.comparer.domain.SimpleMarkerItem;
import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 位点转换成位点分型对象
 *
 * @author jiangbin
 * @date 2019/11/28 3:00 下午
 **/
public class Marker2MarkerItemConvertor extends ListConverterTemplate<Marker, MarkerItem> {
    @Override
    protected MarkerItem convert(Marker source, boolean isInternal) throws ICoreException {
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
}
