package com.fcgs.base.i18n;

import com.pids.core.i18n.Ii18n;

/**
 * Internationalization(i18n)
 * @author: WUHAOTIAN
 * @date: 2020/1/14 13:35
 **/
public enum FcgsBase3I18N implements Ii18n {

    // SIMPLEGENEMERGER
    SSR_SIMPLEGENEMERGER_ERROR01("检测到指纹样品条码号不一致!", "Inconsistent barcode numbers of fingerprint samples detected!"),
    //SIMPLEGENEMERGER

    //SIMPLEGENETREEMERGER
    SSR_SIMPLEGENETREEMERGER_ERROR01("未指定指纹位点合并功能组件!", "The fingerprint site merge function is not specified!"),
    //SIMPLEGENETREEMERGER

    //SIMPLEHYBRIDMARKERCACULATOR
    SSR_SIMPLEHYBRIDMARKERCACULATOR_ERROR01("未给定杂合率参数信息！", "No heterozygosity parameter information is given!"),
    SSR_SIMPLEHYBRIDMARKERCACULATOR_ERROR02("未给定指纹，无法计算杂合率！", "No fingerprint can be calculated, the heterozygosity rate cannot be calculated!"),
    //SIMPLEHYBRIDMARKERCACULATOR

    //GENECOPIER
    SSR_GENECOPIER_ERROR01("拷贝指纹数据失败!", "Failed to copy fingerprint data!"),
    //GENECOPIER

    //MARKERCOMPAREREXCEPTION
    SSR_MARKERCOMPAREREXCEPTION_ERROR01("比对Marker(引物:%s,荧光：%s)失败:%s", "Compare marker (primer:%s, fluorescence:%s) failed:%s"),
    //MARKERCOMPAREREXCEPTION

    //START:
    //END:

    ;

    private String cn;
    private String en;
    private boolean isEn; // true/false - is English/not English

    FcgsBase3I18N(String cn, String en) {
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
