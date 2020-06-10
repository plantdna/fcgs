package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.filler.DataFillerTemplate;

/**
 * 指纹位点总数填充器
 *
 * @author jiangbin
 * @date 2017年5月31日下午3:58:11
 */

public class GeneMarkerCountFiller extends DataFillerTemplate<Gene, Integer> {

    @Override
    protected void setValue(Gene source, Integer markerCount) {
        source.setMarkerCount(markerCount);
    }
}
