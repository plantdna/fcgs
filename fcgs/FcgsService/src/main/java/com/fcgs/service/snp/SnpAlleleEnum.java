package com.fcgs.service.snp;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.SimpleAllele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SNP位点数据工具类，用于将N/A/G/C/T/I/D转换成数字0~6，以兼容存放到SSR的数据表中
 *
 * @author jiangbin
 * @date 2018年3月19日下午4:11:49
 */
public enum SnpAlleleEnum {
    N(0), A(1), G(2), C(3), T(4), I(5), D(6);
    public final int allele;

    private SnpAlleleEnum(int allele) {
        this.allele = allele;
    }

    /**
     * 获取Allele对应的碱基名称
     *
     * @param allele
     * @return
     * @author jiangbin
     * @date 2018年3月19日下午4:19:45
     */
    public static SnpAlleleEnum valueOf(int allele) {
        SnpAlleleEnum[] alleleEnums = SnpAlleleEnum.values();
        for (SnpAlleleEnum alleleEnum : alleleEnums) {
            if (allele == alleleEnum.allele) {
                return alleleEnum;
            }
        }
        return null;
    }

    /**
     * 转换SSR存储的ALLELE值为碱基名称，示例格式为:A:G
     *
     * @param allele1
     * @param allele2
     * @return
     * @author jiangbin
     * @date 2018年3月19日下午4:22:25
     */
    public static String valueOf(int allele1, int allele2) {
        SnpAlleleEnum alleleEnum1 = SnpAlleleEnum.valueOf(allele1);
        SnpAlleleEnum alleleEnum2 = SnpAlleleEnum.valueOf(allele2);
        if (alleleEnum1 != null && alleleEnum2 != null) {
            return alleleEnum1.name() + ":" + alleleEnum2.name();
        }
        return null;
    }

    /**
     * 获取AGCT转换值
     *
     * @param alleleStr
     * @return int
     * @author jiangbin
     * @date 2019/11/28 9:37 上午
     **/
    public static int getAllele(String alleleStr) {
        SnpAlleleEnum[] enums = SnpAlleleEnum.values();
        for (SnpAlleleEnum saEnum : enums) {
            if (saEnum.name().equalsIgnoreCase(alleleStr)) {
                return saEnum.allele;
            }
        }
        return N.allele;
    }

    public static List<Allele> getAlleles(String alleleStr) {
        if (alleleStr.equals("---")) {
            return null;
        }
        return alleleMap.get(alleleStr);
    }

    private static Map<String, List<Allele>> alleleMap = new HashMap<>();

    static {
        SnpAlleleEnum[] values = SnpAlleleEnum.values();
        for (SnpAlleleEnum value : values) {
            for (SnpAlleleEnum value2 : values) {
                List<Allele> alleles = new ArrayList<>();
                String key = value.name() + "/" + value2.name();

                if (value.allele > value2.allele) {
                    Allele allele = new SimpleAllele();
                    allele.setAllele(value2.allele);
                    alleles.add(allele);
                    allele = new SimpleAllele();
                    allele.setAllele(value.allele);
                    alleles.add(allele);

                    alleleMap.put(key, alleles);
                } else {
                    Allele allele = new SimpleAllele();
                    allele.setAllele(value.allele);
                    alleles.add(allele);
                    allele = new SimpleAllele();
                    allele.setAllele(value2.allele);
                    alleles.add(allele);

                    alleleMap.put(key, alleles);
                }
            }

        }
    }
}
