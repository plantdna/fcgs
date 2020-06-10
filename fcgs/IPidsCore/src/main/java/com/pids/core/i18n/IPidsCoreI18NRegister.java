package com.pids.core.i18n;


/**
 * @program: ssr-deploy->ICore3I18NRegister
 * @description: ICore3国际化自动注册
 * @author: WUHAOTIAN
 * @email nghsky@foxmail.com
 * @date: 2020-01-16 13:56:05
 **/
public class IPidsCoreI18NRegister extends I18NRegister {

    // 此注册器在com.viathink.core.i18n.ViaCore3I18NRegister处实现
    public IPidsCoreI18NRegister() {
        add(IPidsCoreI18N.values());
    }
}
