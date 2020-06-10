package com.pids.core.i18n;

/**
 * Internationalization(i18n)
 * @author: WUHAOTIAN
 * @date: 2020/1/14 13:35
 **/
public enum PidsCoreI18N implements Ii18n {

    //DIRWATCHER
    VIA_DIRWATCHER_ERROR01("%s不是目录!", "%s is not a directory!"),
    //DIRWATCHER

    //BASE64DECODER
    VIA_BASE64DECODER_ERROR01("转换Base64编码失败", "Failed to convert Base64 encoding"),
    //BASE64DECODER
    //BASE64ENCODER
    VIA_BASE64ENCODER_ERROR01("编码Base64失败", "Failed to encode Base64"),
    VIA_BASE64ENCODER_ERROR02("IO异常", "IO exception"),
    //BASE64ENCODER

    //SIMPLETEMPLATEREADER
    VIA_SIMPLETEMPLATEREADER_ERROR01("未指定模板定义信息!", "No template definition information was specified!"),
    VIA_SIMPLETEMPLATEREADER_ERROR02("未给定模板文件名!", "No template file name was given!"),
    VIA_SIMPLETEMPLATEREADER_ERROR03("暂不支持本通过ID读取模板文件功能!", "The function of reading template files by ID is not supported at this time!"),
    //SIMPLETEMPLATEREADER

    //ABSTRACTREDISSERVICECREATOR
    VIA_ABSTRACTREDISSERVICECREATOR_ERROR01("未查询到Redis服务器信息!", "No Redis server information has been queried!"),
    VIA_ABSTRACTREDISSERVICECREATOR_ERROR02("Redis服务器信息不正确!", "Redis server information is incorrect!"),
    VIA_ABSTRACTREDISSERVICECREATOR_ERROR03("Redis服务器无法连接!", "Redis server cannot connect!"),
    //ABSTRACTREDISSERVICECREATOR

    //EXCELTITLECHECKER
    VIA_EXCELTITLECHECKER_ERROR01("未设置标题行!", "No header line is set!"),
    VIA_EXCELTITLECHECKER_ERROR02("标题长度不一致!", "Title lengths are inconsistent!"),
    VIA_EXCELTITLECHECKER_ERROR03("第%s列标题不一致! 期望：\"%s\", 实际：\"%s\"", "Column headers in %s are inconsistent! Expectation: \"%s\", Actual: \"%s\""),
    //EXCELTITLECHECKER

    //EXCELPARSERDATACONVERTOR
    VIA_EXCELPARSERDATACONVERTOR_ERROR01("解析\"%s\"失败:%s", "Parse \"%s\"Failed:%s"),
    //EXCELPARSERDATACONVERTOR

    //EXCELPARSERTEMPLATE
    VIA_EXCELPARSERTEMPLATE_ERROR01("解析Excel输入流失败!", "Failed to parse Excel input stream!"),
    VIA_EXCELPARSERTEMPLATE_ERROR02("解析Excel文件失败!", "Failed to parse Excel file!"),
    //EXCELPARSERTEMPLATE

    //PROPERTIESUTILS
    VIA_PROPERTIESUTILS_ERROR01("资源文件不存在!", "The resource file does not exist!"),
    //PROPERTIESUTILS

    //WORDPAGESETUPPARAMS
    VIA_WORDPAGESETUPPARAMS_ERROR01("不支持此方法!", "This method is not supported!"),
    //WORDPAGESETUPPARAMS

    //SIMPLEHTTPCLIENTSERVICE
    VIA_SIMPLEHTTPCLIENTSERVICE_ERROR01("Http Post请求参数不正确!", "Http Post request parameters are incorrect!"),
    VIA_SIMPLEHTTPCLIENTSERVICE_ERROR02("请求失败:%s", "Request failed:%s"),
    VIA_SIMPLEHTTPCLIENTSERVICE_ERROR03("Http Get请求参数不正确!", "Http Get request parameters are incorrect!"),
    VIA_SIMPLEHTTPCLIENTSERVICE_ERROR04("文件上传参数不正确!", "The file upload parameters are incorrect!"),
    VIA_SIMPLEHTTPCLIENTSERVICE_ERROR05("Http Post Json请求参数错误!", "Http Post Json request parameter error!"),
    VIA_SIMPLEHTTPCLIENTSERVICE_ERROR06("请求失败:%s", "Request failed:%s"),
    //SIMPLEHTTPCLIENTSERVICE

    //SIMPLECOMMSERIALPORTRECEIVER
    VIA_SIMPLECOMMSERIALPORTRECEIVER_ERROR01("读取串口数据失败!", "Failed to read serial data!"),
    //SIMPLECOMMSERIALPORTRECEIVER

    //SIMPLECOMMUNICATION
    VIA_SIMPLECOMMUNICATION_ERROR01("添加监听器过多!", "Too many listeners added!"),
    VIA_SIMPLECOMMUNICATION_ERROR02("发送消息失败!", "Failed to send message!"),
    VIA_SIMPLECOMMUNICATION_ERROR03("关闭串口失败!", "Failed to close the serial port!"),
    //SIMPLECOMMUNICATION

    //WORDLOADER
    VIA_WORDLOADER_ERROR01("不能解析的Word版本", "Unparseable Word version"),
    //WORDLOADER

    //EXCELLOADER
    VIA_EXCELLOADER_ERROR01("文件\"%s\"不存在！", "File \"%s\" does not exist!"),
    VIA_EXCELLOADER_ERROR02("不能解析的excel版本", "Unparseable excel version"),
    //EXCELLOADER

    //START:
    //END:

    ;

    private String cn;
    private String en;
    private boolean isEn; // true/false - is English/not English

    PidsCoreI18N(String cn, String en) {
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
