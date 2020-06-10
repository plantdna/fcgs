package com.pids.core.i18n;

/**
 * Internationalization(i18n)
 * @author: WUHAOTIAN
 * @date: 2020/1/14 13:35
 **/
public enum IPidsCoreI18N implements Ii18n {

    // CONFIGUREPROPERTIES
    SSR_CONFIGUREPROPERTIES_ERROR01("加载属性文件%s失败", "Failed to load property file%s"),
    SSR_CONFIGUREPROPERTIES_ERROR02("保存属性文件失败", "Failed to save properties file"),
    // CONFIGUREPROPERTIES

    // ABSTRACTXMLCONFIGURE
    SSR_ABSTRACTXMLCONFIGURE_ERROR01("加载XML属性文件失败", "Failed to load XML properties file"),
    SSR_ABSTRACTXMLCONFIGURE_ERROR02("保存XML属性文件失败", "Failed to save XML property file"),
    // ABSTRACTXMLCONFIGURE

    // FILEBUILDER
    SSR_FILEBUILDER_ERROR01("创建新文件'%s'失败!", "Failed to create new file '%s'!"),
    //FILEBUILDER

    //IDCREATOR
    SSR_IDCREATOR_ERROR02("ID号最大值[%s]超限，请联系管理员!", "The maximum number of ID numbers [%s] has exceeded the limit, please contact the administrator!"),
    //IDCREATOR

    //XMLSAVER
    SSR_XMLSAVER_ERROR01("保存XML文件失败", "Failed to save XML file"),
    //XMLSAVER

    //START:
    //END:

    ;

    private String cn;
    private String en;
    private boolean isEn; // true/false - is English/not English

    IPidsCoreI18N(String cn, String en) {
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
