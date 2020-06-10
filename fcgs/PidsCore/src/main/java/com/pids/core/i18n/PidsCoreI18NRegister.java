package com.pids.core.i18n;

/**
 * @program: ssr-deploy->I18NSSRCore3Register
 * @description: SSRCore3国际化自动注册
 * @author: WUHAOTIAN
 * @email nghsky@foxmail.com
 * @date: 2020-01-16 13:56:05
 **/

public class PidsCoreI18NRegister extends IPidsCoreI18NRegister {
    public PidsCoreI18NRegister() {
        super();
        add(PidsCoreI18N.values());
    }
}
