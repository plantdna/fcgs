package com.fcgs.i18n;

import com.pids.core.i18n.I18NRegister;

/**
 * @program: ssr-deploy->sysServiceI18NRegister
 * @description: sysService国际化自动注册
 * @author: WUHAOTIAN
 * @email nghsky@foxmail.com
 * @date: 2020-01-16 13:56:05
 **/

public class FcgsServiceI18NRegister extends I18NRegister {
    public FcgsServiceI18NRegister() {
        add(FcgsServiceI18N.values());
    }
}
