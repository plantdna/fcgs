package com.fcgs.tester;

import com.pids.core.utils.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指纹缺失位点自动填充器
 *
 * @author jiangbin
 * @date 2020/5/29 10:23
 **/
public class GeneMissMarkerAutoFiller {
    private SnpGeneCsvParser snpGeneCsvParser = new SnpGeneCsvParser();
    private SnpGeneCsvCreator snpGeneCsvCreator = new SnpGeneCsvCreator();
    private String snp_kasp_csv = "/Users/jiangbin/Desktop/Genes/kasp.csv";
    private String snp_kasp_csv2 = "/Users/jiangbin/Desktop/Genes/kasp2.csv";
    private String ssr_csv = "/Users/jiangbin/Desktop/Genes/ssr.csv";
    private String ssr_csv2 = "/Users/jiangbin/Desktop/Genes/ssr2.csv";
    private boolean isSsr = false;
    private List<String> snp_loci = ListUtils.createList("A/A", "A/T", "T/T");
    private List<String> ssr_loci = ListUtils.createList("265/281", "265/265", "281/281");

    @Test
    public void fillSnp() throws IOException {
        isSsr = false;
        snpGeneCsvParser.isSsr = false;
        snpGeneCsvCreator.isSsr = false;
        this.fillMissMarkers(snp_kasp_csv, snp_kasp_csv2);
    }

    @Test
    public void fillSsr() throws IOException {
        this.isSsr = true;
        snpGeneCsvParser.isSsr = true;
        snpGeneCsvCreator.isSsr = true;
        this.fillMissMarkers(ssr_csv, ssr_csv2);
    }

    private void fillMissMarkers(String gene_csv, String gene_csv2) throws IOException {
        List<String[]> rows = parserRows(gene_csv);
        String[] titles = snpGeneCsvParser.getDefaultTitles();
        Map<String, List<String>> mmapper = getMarkerValMapper(titles, rows);
        for (int j = 0; j < rows.size(); j++) {
            String[] cols = rows.get(j);
            for (int i = 2; i < titles.length; i++) {
                String title = titles[i];
                String val = ListUtils.randomSelect(mmapper.get(title));
                if (StringUtils.isBlank(cols[i]) || cols[i].equals("/")) {
                    cols[i] = val;
                }
            }
        }
        String contents = snpGeneCsvCreator.create(rows);
        FileUtils.write(new File(gene_csv2), contents, "UTF-8");
    }

    /**
     * 获取所有位点上的A/B杂合带型
     *
     * @param titles
     * @param rows
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author jiangbin
     * @date 2020/5/29 14:50
     **/
    private Map<String, List<String>> getMarkerValMapper(String[] titles, List<String[]> rows) {
        Map<String, List<String>> mmapper = new HashMap<>();
        for (int j = 0; j < rows.size(); j++) {
            String[] cols = rows.get(j);
            for (int i = 2; i < titles.length; i++) {
                String title = titles[i];
                String colVal = StringUtils.stripToEmpty(cols[i]);
                if (!colVal.isEmpty() && !cols[i].equals("/")) {
                    String[] arrs = colVal.split("/");
                    if (!StringUtils.equals(arrs[0], arrs[1])) {
                        mmapper.put(title, getAllLoci(colVal));
                    }
                }
            }
        }
        List<String> all = ListUtils.array2List(titles);
        List<String> exists = new ArrayList<>(mmapper.keySet());
        all.removeAll(exists);
        if (all.isEmpty()) {
            return mmapper;
        }

        System.out.println("位点无数据信息==>" + ListUtils.list2Str(all));

        //将完全无数据的列设置上数据
        for (String title : all) {
            mmapper.put(title, getAllLoci(getLocus()));
        }

        return mmapper;
    }

    /**
     * 获取随机位点数据
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2020/5/30 10:59
     **/
    private String getLocus() {
        return isSsr ? "265/281" : "A/T";
    }

    /**
     * 构建分型的所有可能组合数据:AA/AB/BB
     *
     * @param item
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2020/5/30 11:16
     **/
    private List<String> getAllLoci(String item) {
        String[] items = item.split("/");
        return ListUtils.createList(items[0] + "/" + items[0], items[0] + "/" + items[1], items[1] + "/" + items[1]);
    }

    /**
     * 解析指纹数据文件
     *
     * @param gene_csv
     * @return java.util.List<java.lang.String [ ]>
     * @author jiangbin
     * @date 2020/5/30 11:06
     **/
    private List<String[]> parserRows(String gene_csv) {
        return snpGeneCsvParser.parserFile(new File(gene_csv));
    }
}
