package com.pids.core.filter.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;

/**
 * 文件扩展名过滤器，依据文件扩展名将文件过滤出来，忽略大小写
 *
 * @author jiangbin
 * @date 2019-08-14 08:42
 **/
public class FileExtFilter implements FileFilter {
    private final String[] exts;

    public FileExtFilter(String[] exts) {
        this.exts = exts;
    }

    @Override
    public boolean accept(File file) {
        //若未指定文件扩展名则默认为匹配上
        if (ArrayUtils.isEmpty(exts)) {
            return true;
        }
        String fileExt = FilenameUtils.getExtension(file.getName());
        if (StringUtils.isBlank(fileExt)) {
            return false;
        }
        for (String ext : exts) {
            if (ext.equalsIgnoreCase(fileExt)) {
                return true;
            }
        }
        return false;
    }
}
