package com.fcgs.runner;

import com.fcgs.service.compare.FcgsGeneComparer;
import com.fcgs.service.domain.FcgsComparerParams;
import com.fcgs.service.domain.TagTypeEnum;
import com.fcgs.service.util.GCResults;
import com.pids.core.exception.ExceptionUtils;
import com.pids.core.exception.ICoreException;
import com.pids.core.message.MessageContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * FCGS比对算法命令行调用程序
 */
public class FcgsCmdRunner {
    private static Logger log = LoggerFactory.getLogger(FcgsCmdRunner.class);

    /**
     * 采用命令行方式执行比对
     *
     * @param args
     */
    public static void main(String[] args) {
        /*args = new String[5];
        args[0] = "micro";
        args[1] = "/Users/wuhaotian/Downloads/genes/SSR-Sources.csv";
        args[2] = "/Users/wuhaotian/Downloads/genes/SSR-Targets.csv";
        args[3] = "0";
        args[4] = "384";*/
        //构建参数对象
        if (args == null || args.length < 3) {
            log.error("The number of arguments must be more than three!");
            return;
        }
        FcgsComparerParams params = createParams(args);

        if (params == null) {
            log.error("Incorrect arguments!");
            return;
        }

        //执行比对
        compare(params);
    }

    /**
     * 使用界面参数创建FCGS比对参数对象
     *
     * @return
     */
    private static FcgsComparerParams createParams(String[] args) {
        int index = 0;
        String type = args[index++];
        String sourceFile = args[index++];
        String targetFile = args[index++];
        String minDiff = args[index++];
        String maxDiff = args[index++];

        TagTypeEnum tagTypeEnum = TagTypeEnum.get(type);
        if (tagTypeEnum == null) {
            log.error("Wrong molecular marker type!");
            throw new ICoreException("Wrong molecular marker type!");
        }

        FcgsComparerParams params = new FcgsComparerParams();
        params.setAlgorithm("");
        params.setTagType(tagTypeEnum);
        if (StringUtils.isNotBlank(minDiff) && StringUtils.isNotBlank(maxDiff)) {
            params.setMinDiffLoci(minDiff);
            params.setMaxDiffLoci(maxDiff);
        }
        params.setTestCompareGeneFile(new File(sourceFile));
        params.setRefCompareGeneFile(new File(targetFile));
        return params;
    }

    /**
     * 执行指纹数据比对
     *
     * @param params
     * @return
     */
    private static GCResults compare(FcgsComparerParams params) {
        try {
            GCResults result = new FcgsGeneComparer().compare(params);
            return result;
        } catch (Exception e) {
            log.error("The fingerprint compare process is faulty==>" + ExceptionUtils.getStackTrace(e));
            MessageContext.error("The fingerprint compare process is faulty:" + e.getMessage());
            throw e;
        }
    }
}
