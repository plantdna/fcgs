package com.fcgs.base.i18n;

import com.pids.core.i18n.I18NRegister;

/**
 * @program: ssr-deploy->I18NSSRCore3Register
 * @description: SSRCore3国际化自动注册
 * @author: WUHAOTIAN
 * @email nghsky@foxmail.com
 * @date: 2020-01-16 13:56:05
 **/

public class FcgsBaseI18NRegister extends I18NRegister {
    public FcgsBaseI18NRegister() {
        add(FcgsBase3I18N.values());
    }
}
