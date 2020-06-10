package com.pids.core.template.reader;

import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.PidsCoreI18N;
import com.pids.core.template.domain.Template;
import com.pids.core.utils.ResourceReader;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>模板读取功能,支持两种方式：
 * 1、由模板定义类同级包目录下读取模板
 * 2、通过ID读取模板，此处未作默认实际，根据实际需要自行扩展即可</pre>
 * @author jiang
 * @date 2018年6月11日下午10:44:55
 */
public class SimpleTemplateReader implements TemplateReader {

    @Override
    public byte[] read(Template model) throws ICoreException {
        if (model == null) {
            throw new ICoreException(PidsCoreI18N.VIA_SIMPLETEMPLATEREADER_ERROR01.get());
        }
        if (StringUtils.isBlank(model.getId())) {
            return readSource(model);
        } else {
            return readById(model);
        }
    }

    /**
     * 读取模板定义信息类同级包目录对应模板资源文件
     * @param model
     * @return
     * @author jiang
     * @date 2018年6月11日下午11:02:08
     */
    protected byte[] readSource(Template model) {
        if (StringUtils.isBlank(model.getFileName())) {
            throw new ICoreException(PidsCoreI18N.VIA_SIMPLETEMPLATEREADER_ERROR02.get());
        }
        return ResourceReader.readBytes(model.getClass(), model.getFileName());
    }

    /**
     * 读取指定ID的模板文件，若需要扩展由数据库读取时需要实现本方法
     * @param model
     * @return
     * @author jiang
     * @date 2018年6月11日下午10:59:05
     */
    protected byte[] readById(Template model) {
        throw new ICoreException(PidsCoreI18N.VIA_SIMPLETEMPLATEREADER_ERROR03.get());
    }

}
