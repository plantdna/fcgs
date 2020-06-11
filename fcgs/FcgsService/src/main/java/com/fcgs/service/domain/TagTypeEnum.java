package com.fcgs.service.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * 标记类型
 */
public enum TagTypeEnum {
    MICROSATELLITE("Microsatellite", "Microsatellite"),
    KASP("SNP-KASP", "SNP-KASP"),
    CHIP("SNP-arrays", "SNP-arrays");
    public final String tag;
    public final String label;

    TagTypeEnum(String tag, String label) {
        this.tag = tag;
        this.label = label;
    }

    /**
     * 获取字符串对应的枚举对象
     *
     * @param tag
     * @return
     */
    public static TagTypeEnum get(String tag) {
        if (StringUtils.isBlank(tag)) {
            return null;
        }
        TagTypeEnum[] tags = TagTypeEnum.values();
        for (TagTypeEnum tagEnum : tags) {
            if (tagEnum.tag.equalsIgnoreCase(tag)) {
                return tagEnum;
            }
        }
        return null;
    }
}
