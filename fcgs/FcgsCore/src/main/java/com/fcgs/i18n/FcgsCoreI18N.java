package com.fcgs.i18n;

import com.pids.core.i18n.Ii18n;

/**
 * Internationalization(i18n)
 * @author: WUHAOTIAN
 * @date: 2020/1/14 13:35
 **/
public enum FcgsCoreI18N implements Ii18n {

    // DETAILCOMPARERTASKMANAGER
    SSR_DETAILCOMPARERTASKMANAGER_ERROR01("", ""),//I18N.SSR_DETAILCOMPARERTASKMANAGER_ERROR2.get()
    SSR_DETAILCOMPARERTASKMANAGER_ERROR02("Ssr指纹详细比对执行失败!", "SSR fingerprint detailed comparison execution failed!"),
    // DETAILCOMPARERTASKMANAGER

    // SMARTCOMPARERTASKMANAGER
    SSR_SMARTCOMPARERTASKMANAGER_ERROR01("", ""),//I18N.SSR_SMARTCOMPARERTASKMANAGER_ERROR3.get()
    SSR_SMARTCOMPARERTASKMANAGER_ERROR02("Ssr指纹快速比对执行失败!", "SSR fingerprint comparison comparison execution failed!"),
    // SMARTCOMPARERTASKMANAGER

    //SMARTSAMENAMECOMPARERTASKMANAGER
    SSR_SMARTSAMENAMECOMPARERTASKMANAGER_ERROR01("", ""),//I18N.SSR_SMARTSAMENAMECOMPARERTASKMANAGER_ERROR4.get()
    SSR_SMARTSAMENAMECOMPARERTASKMANAGER_ERROR02("Ssr指纹快速同名比对执行失败!", "SSR fingerprint fast name matching comparison failed!"),
    //SMARTSAMENAMECOMPARERTASKMANAGER

    //MARKERITEMPRIMERMAPPERCREATOR
    SSR_MARKERITEMPRIMERMAPPERCREATOR_ERROR01("未给定位点列表!", "No list of loci was given!"),
    SSR_MARKERITEMPRIMERMAPPERCREATOR_ERROR02("所有位点均未设置位点名称!", "No loci name is set for all loci!"),
    //MARKERITEMPRIMERMAPPERCREATOR

    //SNPMARKERITEMPRIMERMAPPERCREATOR
    SSR_SNPMARKERITEMPRIMERMAPPERCREATOR_ERROR01("未给定位点列表!", "No loci list is given!"),
    SSR_SNPMARKERITEMPRIMERMAPPERCREATOR_ERROR02("所有位点均未设置位点名称!", "No loci name is set for all loci!"),
    //SNPMARKERITEMPRIMERMAPPERCREATOR

    //GENEDISTANCECSVCREATOR
    SSR_GENEDISTANCECSVCREATOR_ERROR01("正无穷大", "Positive infinity"),
    //GENEDISTANCECSVCREATOR

    //SMARTCOMPARERRESULTCSVCREATOR
    SSR_SMARTCOMPARERRESULTCSVCREATOR_ERROR01("待比样品,对比样品,比对位点数,差异位点数,无差异位点数", "Comparison sample, comparative sample, number of comparison loci, number of " +
            "difference loci, number of non-distance loci"),
    SSR_SMARTCOMPARERRESULTCSVPARSER_ERROR02("待比样品,对比样品,比对位点数,差异位点数,无差异位点数", "Comparison sample, comparative sample, number of comparison loci, number of " +
            "difference loci, number of non-distance loci"),
    //SMARTCOMPARERRESULTCSVCREATOR

    //START:
    //END:

    ;

    private String cn;
    private String en;
    private boolean isEn; // true/false - is English/not English

    FcgsCoreI18N(String cn, String en) {
        this.cn = cn;
        this.en = en;
    }

    /**
     * Get key value
     * @return java.lang.String
     * @author: WUHAOTIAN
     * @date: 2020/1/15 9:17
     **/
    public String get() {
        if (isEn) {
            return en;
        } else {
            return cn;
        }
    }

    @Override
    public boolean getLanguage() {
        return isEn;
    }

    @Override
    public void setLanguage(boolean isEn) {
        this.isEn = isEn;
    }
}
