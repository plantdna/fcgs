package com.fcgs.base.domain.gene.utils;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;

;

/**
 * 指纹属性及关联操作定义，如果需要增加指纹信息字段只需要在此处添加即可
 *
 * @author jiangbin
 * @date 2019-09-01 23:47
 **/
public enum GENE_PROPS {
    GENE_ID("geneId", new GenePropFiller() {
        @Override
        public void set(Gene gene, String value) {
            gene.setGeneId(value);
        }

        @Override
        public boolean isGene() {
            return true;
        }
    }), SAM_BARCODE("samBarcode", new GenePropFiller() {
        @Override
        public void set(Gene gene, String value) {
            gene.getSample().setSamBarcode(value);
        }

        @Override
        public boolean isGene() {
            return true;
        }
    }), SAM_NAME("samName", new GenePropFiller() {
        @Override
        public void set(Gene gene, String value) {
            gene.getSample().setSamName(value);
        }

        @Override
        public boolean isGene() {
            return true;
        }
    }), PRIMER_NAME("primerName", new GenePropFiller() {
        @Override
        public void set(Marker marker, String value) {
            marker.setPrimerName(value);
        }

        @Override
        public boolean isMarker() {
            return true;
        }
    }), DYE("dye", new GenePropFiller() {
        @Override
        public void set(Marker marker, String value) {
            marker.setDye(value);
        }

        @Override
        public boolean isMarker() {
            return true;
        }
    }), PICTURE("picture", new GenePropFiller() {
        @Override
        public void set(Marker marker, String value) {
            marker.setPicture(value);
        }

        @Override
        public boolean isMarker() {
            return true;
        }
    }), COMMENT("comment", new GenePropFiller() {//Marker备注
        @Override
        public void set(Marker marker, String value) {
            marker.setComment(value);
        }

        @Override
        public boolean isMarker() {
            return true;
        }
    }), MANUAL("manual", new GenePropFiller() {
        @Override
        public void set(Marker marker, String value) {
            marker.setManual("true".equalsIgnoreCase(value));
        }

        @Override
        public boolean isMarker() {
            return true;
        }
    }), ALLELE("allele", new GenePropFiller() {
        @Override
        public void set(Allele allele, String value) {
            allele.setAllele(NumberUtils.toFloat(value, 0));
        }

        @Override
        public boolean isAllele() {
            return true;
        }
    }), SIZE("size", new GenePropFiller() {
        @Override
        public void set(Allele allele, String value) {
            allele.setSize(NumberUtils.toFloat(value, 0));
        }

        @Override
        public boolean isAllele() {
            return true;
        }
    }), HEIGHT("height", new GenePropFiller() {
        @Override
        public void set(Allele allele, String value) {
            allele.setHeight(NumberUtils.toFloat(value, 0));
        }

        @Override
        public boolean isAllele() {
            return true;
        }
    }), AREA("area", new GenePropFiller() {
        @Override
        public void set(Allele allele, String value) {
            allele.setArea(NumberUtils.toFloat(value, 0));
        }

        @Override
        public boolean isAllele() {
            return true;
        }
    }), SCORE("score", new GenePropFiller() {
        @Override
        public void set(Allele allele, String value) {
            allele.setScore(NumberUtils.toFloat(value, 0));
        }

        @Override
        public boolean isAllele() {
            return true;
        }
    }), QUALITY("quality", new GenePropFiller() {
        @Override
        public void set(Allele allele, String value) {
            allele.setQuality(value);
        }

        @Override
        public boolean isAllele() {
            return true;
        }
    }), COMMENTS("comments", new GenePropFiller() {//Allele备注
        @Override
        public void set(Allele allele, String value) {
            allele.setComments(value);
        }

        @Override
        public boolean isAllele() {
            return true;
        }
    });

    //属性名与枚举定义映射关系表
    public final static Map<String, GENE_PROPS> mapper = GENE_PROPS.getMapper();
    public final static String[] propNames = GENE_PROPS.getPropNames();

    public String name;//属性名
    public GenePropFiller filler;//关联操作对象

    GENE_PROPS(String name, GenePropFiller filler) {
        this.name = name;
        this.filler = filler;
    }

    /**
     * 构建属性的映射关系
     *
     * @return java.util.Map<java.lang.String, com.viathink.ssr.core.domain.gene.json.SmartGeneJsonParser.PROPS>
     * @author jiangbin
     * @date 2019-09-01 22:50
     **/
    public static Map<String, GENE_PROPS> getMapper() {
        GENE_PROPS[] propsList = GENE_PROPS.values();
        Map<String, GENE_PROPS> mapper = new HashMap<>();
        for (GENE_PROPS props : propsList) {
            mapper.put(props.name, props);
        }
        return mapper;
    }

    /**
     * 获取属性名数组
     *
     * @return java.lang.String[]
     * @author jiangbin
     * @date 2019-09-01 22:59
     **/
    public static String[] getPropNames() {
        GENE_PROPS[] props = GENE_PROPS.values();
        String[] propNames = new String[props.length];
        for (int index = 0; index < props.length; index++) {
            propNames[index] = props[index].name;
        }
        return propNames;
    }

}
