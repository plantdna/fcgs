package com.fcgs.service.csv;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.parser.MarkerStrParser;

import java.util.List;

/**
 * 解析BGG指纹数据CSV文件
 *
 * @author jiangbin
 * @date 2019/10/28 3:30 下午
 **/


public class SsrGeneCsvParser extends SnpKaspGeneCsvParser {
    private String titleStr = "样品条码号,样品名称,P01,P02,P03,P04,P05,P06,P07,P08,P09,P10,P11,P12,P13,P14,P15,P16,P17,P18,P19,P20,P21,P22,P23,P24,P25,P26,P27,P28,P29,P30,P31,P32,P33,P34,P35,P36,P37,P38,P39,P40";
    private MarkerStrParser markerStrParser = new MarkerStrParser();

    @Override
    protected List<Allele> parseAlleles(String markerStr) {
        return this.markerStrParser.parserAlleles(markerStr);
    }
}
