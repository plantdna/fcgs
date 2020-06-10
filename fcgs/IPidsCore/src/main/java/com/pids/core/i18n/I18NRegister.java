package com.pids.core.i18n;

import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ssr-deploy->I18NRegister
 * @description: 国际化注册器
 * @author: WUHAOTIAN
 * @email nghsky@foxmail.com
 * @date: 2020-01-16 11:42:15
 **/
public class I18NRegister {
    private static final List<Ii18n> ii18ns = new ArrayList<>();
    private static boolean isEn = false;

    public static void add(Ii18n ii18n) {
        ii18ns.add(ii18n);
    }

    public static void add(List<Ii18n> ii18ns) {
        ii18ns.addAll(ii18ns);
    }

    public static void add(Ii18n[] ii18n) {
        ii18ns.addAll(ListUtils.array2List(ii18n));
    }

    public static List<Ii18n> getIi18ns() {
        return ii18ns;
    }

    public static boolean getLanguage() {
        return isEn;
    }

    public static void setLanguage(boolean isEn) {
        I18NRegister.isEn = isEn;
        if (CollectionUtils.isEmpty(ii18ns)) {
            return;
        }
        for (Ii18n ii18n : ii18ns) {
            ii18n.setLanguage(isEn);
        }
    }
}
