package com.fcgs.templates;

import com.pids.core.template.domain.Template;

/**
 * SSR部分内置模板文件定义
 *
 * @author jiang
 * @date 2018年6月20日上午10:46:25
 */
public enum SsrTemplateEnum implements Template {
    SYS_SAM_BARCODE("ssr-sam-barcode.xls", "样品条码号模板.xls", "ssr-sam-barcode-en.xls", "SamBarcodeTemplate.xls"), // 样品条码号模板
    FCGS_SSR_GENE_CSV("fcgs-ssr-gene.csv", "FCGS-SSR指纹导入模板.csv", "fcgs-ssr-gene-en.csv", "FcgsSsrGeneTemplate.csv"), // GeneMapper指纹导入模板
    FCGS_KASP_GENE_CSV("fcgs-kasp-gene.csv", "FCGS-KASP指纹导入模板.csv", "fcgs-kasp-gene-en.csv", "FcgsKaspGeneTemplate.csv"), // GeneMapper指纹导入模板
    FCGS_CHIP_GENE_CSV("fcgs-chip-gene.csv", "FCGS-芯片指纹导入模板.csv", "fcgs-chip-gene.csv", "FcgsChipGeneTemplate.csv"), // GeneMapper指纹导入模板

    ;
    public String id;
    private String templateName;
    private String fileName;
    private String descName;
    private String fileNameEn;
    private String descNameEn;

    SsrTemplateEnum(String fileName, String descName, String fileNameEn, String descNameEn) {
        this.fileName = fileName;
        this.descName = descName;
        this.fileNameEn = fileNameEn;
        this.descNameEn = descNameEn;
        templateName = name();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTemplateName() {
        return templateName;
    }

    @Override
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String getFileName() {
        return fileNameEn;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getDescName() {
        return descNameEn;
    }

    @Override
    public void setDescName(String descName) {
        this.descName = descName;
    }

}
