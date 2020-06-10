package com.pids.core.filter.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;

/**
 * 文件名过滤器，依据文件名将文件过滤出来
 *
 * @author jiangbin
 * @date 2019-08-14 08:42
 **/
public class FileNameFilter implements FileFilter {
    private final String[] names;

    public FileNameFilter(String[] names) {
        this.names = names;
    }

    @Override
    public boolean accept(File file) {
        //若未指定文件名则默认为匹配上
        if (ArrayUtils.isEmpty(names)) {
            return true;
        }
        String fileName = FilenameUtils.getName(file.getName());
        if (StringUtils.isBlank(fileName)) {
            return false;
        }
        for (String name : names) {
            if (name.equalsIgnoreCase(fileName)) {
                return true;
            }
        }
        return false;
    }
}
