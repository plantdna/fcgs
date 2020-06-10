package com.pids.core.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.utils.FileExtFilter;
import com.pids.core.filter.utils.FileNameFilter;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件查找器，用于递归查找指定目录下的所有指定扩展名类型文件
 *
 * @author jiangbin
 * @date 2015年4月10日下午5:22:53
 */
public class FileFinder {

    /**
     * 递归查找指定目录下的所有指定扩展名类型文件
     *
     * @param folder 目录或文件
     * @param exts   文件扩展名，匹配时忽略大小写，若为null则查找所有文件
     * @return 文件列表
     * @throws ICoreException
     * @author jiangbin
     * @date 2015年4月10日下午5:24:28
     */
    public static List<File> find(File folder, String... exts) throws ICoreException {
        FileExtFilter filter = new FileExtFilter(exts);
        return find(folder, filter);
    }

    /**
     * 递归查找指定目录下的所有指定文件名的文件
     *
     * @param folder 目录或文件
     * @param names  文件名列表，匹配时忽略大小写，若为null则查找所有文件
     * @return
     * @throws ICoreException
     */
    public static List<File> findByName(File folder, String... names) throws ICoreException {
        FileNameFilter filter = new FileNameFilter(names);
        return find(folder, filter);
    }

    /**
     * 根据给定文件过滤规则查找目录及所有子目录内的文件
     *
     * @param folder 文件目录
     * @param filter 文件过滤规则
     * @return java.util.List<java.io.File>
     * @author jiangbin
     * @date 2019-08-14 08:49
     **/
    public static List<File> find(File folder, FileFilter filter) throws ICoreException {
        if (folder == null || !folder.exists()) {
            return null;
        }
        if (folder.isFile()) {
            return ListUtils.createList(folder);
        }
        File[] files = folder.listFiles();
        List<File> targets = new ArrayList<>();
        for (File file : files) {
            if (file.isFile() && filter.accept(file)) {
                targets.add(file);
                continue;
            }
            if (file.isDirectory()) {
                List<File> subs = find(file, filter);
                if (CollectionUtils.isNotEmpty(subs)) {
                    targets.addAll(subs);
                }
            }
        }
        return targets;
    }

}
