package com.pids.core.utils;

import com.pids.core.builder.Builder;
import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.IPidsCoreI18N;

import java.io.File;

/**
 * 文件构建器
 * @author Andory
 * @date 2012-5-24下午11:26:00
 */
public class FileBuilder implements Builder<String, File> {
    private boolean isDirectory;

    /**
     * 构建文件是否为目录
     * @return true/false---目录/文件
     * @author Andory
     * @date 2012-5-24下午11:33:53
     */
    public boolean isDirectory() {
        return isDirectory;
    }

    /**
     * 设置构建文件是否为目录
     * @param isDirectory true/false---目录/文件
     * @author Andory
     * @date 2012-5-24下午11:32:56
     */
    public void setDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    /**
     * 新建文件，注意：当文件或目录存在时将直接返回目标文件对象
     * @param fileName    文件名
     * @param isDirectory true/false--是目录/文件
     * @return 创建的文件对象
     * @throws ICoreException
     * @author Andory
     * @date 2012-5-24下午11:38:53
     */
    public static File build(String fileName, boolean isDirectory) throws ICoreException {
        FileBuilder builder = getInstance();
        builder.setDirectory(isDirectory);
        return builder.build(fileName);
    }

    @Override
    public File build(String fileName) throws ICoreException {
        File newFile = new File(fileName);
        if (isDirectory) {
            createDirectory(newFile);
        } else {
            File parent = newFile.getParentFile();
            createDirectory(parent);
            createFile(newFile);
        }
        return newFile;
    }

    /**
     * 创建目录
     * @author Andory
     * @date 2012-5-24下午11:21:13
     */
    protected void createDirectory(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 判定文件是否存在，不存在则创建
     * @throws Exception
     * @author Andory
     * @date 2012-5-24下午11:21:16
     */
    protected void createFile(File file) throws ICoreException {
        file = new File(file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new ICoreException(String.format(IPidsCoreI18N.SSR_FILEBUILDER_ERROR01.get(), file.getAbsolutePath()));
            }
        }
    }

    public static FileBuilder getInstance() {
        return new FileBuilder();
    }
}
