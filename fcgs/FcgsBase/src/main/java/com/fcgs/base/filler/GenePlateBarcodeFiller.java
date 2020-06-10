package com.fcgs.base.filler;

import com.fcgs.base.domain.SimpleGeneLocation;
import com.fcgs.base.domain.gene.Gene;
import com.pids.core.filler.DataFillerTemplate;

/**
 * 指纹的电泳板条码号填充器
 *
 * @author jiangbin
 * @date 2019/12/31 3:44 下午
 **/

public class GenePlateBarcodeFiller extends DataFillerTemplate<Gene, String> {
    @Override
    protected void setValue(Gene source, String plateBarcode) {
        if (source.getLocation() == null) {
            source.setLocation(new SimpleGeneLocation());
        }
        source.getLocation().setPlate(plateBarcode);
    }
}
