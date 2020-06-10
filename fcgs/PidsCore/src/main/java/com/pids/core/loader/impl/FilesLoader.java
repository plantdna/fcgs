package com.pids.core.loader.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.loader.ListLoaderTemplate;
import com.pids.core.utils.FileDataReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>加载给定文件对象包含的所有符合条件的文件列表:
 * 1、如果是文件则判定符合条件后返回
 * 2、如果是目录则递归加载子目录内符合条件1的文件</pre>
 *
 * @author jiangbin
 * @date 2014年11月28日下午4:43:26
 */

public class FilesLoader extends ListLoaderTemplate<File, byte[]> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected byte[] load(File source, boolean isInternal) throws ICoreException {
        try {
            return FileUtils.readFileToByteArray(source);
        } catch (IOException e) {
            logger.warn("加载文件字节数组内容失败!", e);
            return null;
        }
    }

    /**
     * 批量加载文件内容为字符串
     *
     * @param files
     * @return
     * @author jiangbin
     * @date 2018年7月2日上午10:17:04
     */
    public List<String> loadContents(List<File> files) {
        List<String> contents = new ArrayList<>();
        for (File file : files) {
            try {
                String content = FileDataReader.read(file, "utf-8");
                if (StringUtils.isNotBlank(content)) {
                    contents.add(content);
                }
            } catch (IOException e) {
                logger.warn("加载文件内容失败!", e);
            }
        }
        return contents;
    }

}
