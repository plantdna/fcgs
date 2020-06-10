package com.fcgs.base.genefile;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.creator.ListCreatorTemplate;
import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * <pre>指纹文件相对路径构建器，构建的前缀名格式如：SsrGeneFiles/用户帐号/年月日/时分秒/[电泳板条码号或上样板条码号/孔位号/]样品条码号/32位UUID+.XML
 * 注：[...]内的部分可能某些时候不会有，因为指纹对象数据中可能不包含这部分信息，文件路径使用全大写</pre>
 *
 * @author Jiangbin
 * @date 2014年12月10日下午11:17:17
 */

public class GeneFilePathCreator extends ListCreatorTemplate<Gene, String> {
    private static final String SEPARATOR = "/";
    private static final String SUB_FOLDER = "SsrGeneFiles";

    @Override
    protected String create(Gene source, boolean isInternal) throws ICoreException {
        StringBuilder path = new StringBuilder();

        //用户帐号
        if (source.getSample() != null) {
            String manager = source.getSample().getDnaManager();
            if (StringUtils.isBlank(manager)) {
                manager = source.getSample().getSamManager();
            }
            if (StringUtils.isNotBlank(manager)) {
                path.append(manager).append(SEPARATOR);
            }
        }

        //年月日
        path.append(DateFormatUtils.format(new Date(), "yyyy/MM/dd")).append(SEPARATOR);

        //时分秒
        path.append(DateFormatUtils.format(new Date(), "HHmmss")).append(SEPARATOR);

        //位置信息
        if (source.getLocation() != null) {
            String plateId = source.getLocation().getPlate();
            String well = source.getLocation().getWell();
            if (StringUtils.isNotBlank(plateId) && StringUtils.isNotBlank(well)) {
                path.append(plateId).append(SEPARATOR).append(well).append(SEPARATOR);
            }
        }

        //样品条码
        path.append(source.getSamBarcode()).append(SEPARATOR);

        // 指纹库类型，指纹文件缓存监听处使用，2018.5.11
        if (source.getGeneLib() != null) {
            path.append(source.getGeneLib()).append(SEPARATOR);
        }

        //构建相对存储路径
        StringBuilder target = new StringBuilder();
        target.append(SUB_FOLDER).append(SEPARATOR);//二级目录名
        target.append(path.toString().toUpperCase());
        target.append(source.getGeneId()).append(".").append(getGeneFileExt());
        return target.toString();
    }

    /**
     * 获取指纹数据文件扩展名，当前为json
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2019-08-21 08:57
     **/
    private String getGeneFileExt() {
        return "json";
    }
}