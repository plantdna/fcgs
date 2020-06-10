package com.fcgs.service.csv;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.service.domain.TagTypeEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * 指纹数据CSV文件
 *
 * @author WUHAOTIAN
 * @date 2020/03/3 11:30 上午
 **/
public class GeneCsvParser {
    private SsrGeneCsvParser ssrGeneCsvParser = new SsrGeneCsvParser();
    private SnpKaspGeneCsvParser snpKaspGeneCsvParser = new SnpKaspGeneCsvParser();
    private SnpChipGeneCsvParser snpChipGeneCsvParser = new SnpChipGeneCsvParser();

    public List<Gene> parser(String csvFile, TagTypeEnum tagType) throws FileNotFoundException {
        return parser(new File(csvFile), tagType);
    }

    public List<Gene> parser(File csvFile, TagTypeEnum tagType) throws FileNotFoundException {
        return parser(new FileInputStream(csvFile), tagType);
    }

    /**
     * 根据给定标记类型进行数据解析
     * @param inputStream
     * @param tagType
     * @return
     */
    public List<Gene> parser(InputStream inputStream, TagTypeEnum tagType) {
        switch (tagType) {
            case MICROSATELLITE:
                return ssrGeneCsvParser.parse(inputStream);
            case KASP:
                return snpKaspGeneCsvParser.parse(inputStream);
            case CHIP:
                return snpChipGeneCsvParser.parse(inputStream);
        }
        return null;
    }
}
