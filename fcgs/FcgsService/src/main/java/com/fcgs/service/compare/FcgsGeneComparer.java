package com.fcgs.service.compare;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.core.comparer.FastGeneComparer;
import com.fcgs.core.comparer.SimpleFastGeneComparer;
import com.fcgs.core.comparer.csv.GeneResultSetCsvCreator;
import com.fcgs.core.comparer.domain.*;
import com.fcgs.i18n.FcgsServiceI18N;
import com.fcgs.service.csv.GeneCsvParser;
import com.fcgs.service.domain.FcgsComparerParams;
import com.fcgs.service.domain.TagTypeEnum;
import com.fcgs.service.util.GCResults;
import com.pids.core.exception.ExceptionUtils;
import com.pids.core.message.MessageContext;
import com.pids.core.pair.Pair;
import com.pids.core.pathcreator.PathCreator;
import com.pids.core.pathcreator.utils.SimplePathCreator;
import com.pids.core.utils.CpuUtils;
import com.pids.core.utils.PathUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * FCGS指纹比对
 *
 * @author WUHAOTIAN
 * @date 2020/3/3 17:59
 **/
public class FcgsGeneComparer {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private GeneCsvParser geneCsvParser = new GeneCsvParser();
    private FastGeneComparer fastGeneComparer = new SimpleFastGeneComparer();
    private GeneResultSetCsvCreator geneResultSetCsvCreator = new GeneResultSetCsvCreator();

    /**
     * FCGS指纹数据比对算法
     *
     * @return com.viathink.ssr.fcgs.util.GCResults
     * @author jiangbin
     * @date 2020/3/3 23:52
     **/
    public GCResults compare(FcgsComparerParams params) {
        try {
            //解析指纹数据
            List<Gene> sgenes = parserGenes(params.getTestCompareGeneFile(), params.getTagType());
            List<Gene> tgenes = parserGenes(params.getRefCompareGeneFile(), params.getTagType());
            if (CollectionUtils.isEmpty(sgenes) || CollectionUtils.isEmpty(tgenes)) {
                MessageContext.error(FcgsServiceI18N.SSR_FCGS_GENE_COMPARER.get());
                return null;
            }

            return getGcResults(params, sgenes, tgenes);
        } catch (IOException e) {
            log.error("FCGS指纹数据比对出错==>" + ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * 获取指纹比对结果信息
     *
     * @param params 比对信息参数
     * @param sgenes 待比指纹
     * @param tgenes 对比指纹
     * @return com.viathink.ssr.fcgs.util.GCResults
     * @author: WUHAOTIAN
     * @date: 2020/3/18 下午5:51
     **/
    private GCResults getGcResults(FcgsComparerParams params, List<Gene> sgenes, List<Gene> tgenes) {
        GCResults count = new GCResults();

        StopWatch watch = new StopWatch();
        watch.start();

        //构建比对参数对象
        StatisticsGeneContainer container = createStatisticsGeneContainer(sgenes, tgenes, params);

        //指纹比对
        GeneResultSet resultSet = fastGeneComparer.compare(container);
        resultSet.invalid();

        //标记出符合差异位点范围的比对结果
        if (params.hasDiffChecker()) {
            resultSet.markDiff(params.getDiffChecker());
        }

        watch.stop();

        //统计全部比对结果记录数、全部差异位点数、平均差异位点数
        StopWatch watch2 = new StopWatch();
        watch2.start();
        Pair<Long, Long> pair = resultSet.getAllResultCountInfo(CpuUtils.getCpuNum());
        count.setAvgDiffCount(pair.getSource(), pair.getTarget());
        watch2.stop();
        log.info("获取全部比对结果信息耗时(ms)==>" + watch2.getTime());

        //保存成矩阵格式
        saveMatrix(resultSet, params, count);

        //记录比对时间等基本信息
        count.setResultCount(getResultCount(resultSet, params));
        count.setsGeneCount(sgenes.size());
        count.settGeneCount(tgenes.size());
        count.setTimes(watch.getTime());
        count.setMaxDiff(params.getMaxDiffLociInt());
        count.setMinDiff(params.getMinDiffLociInt());
        count.setTagType(params.getTagType().label);

        return count;
    }

    /**
     * 获取比对结果数
     *
     * @return long
     * @author jiangbin
     * @date 2020/5/25 10:29
     **/
    private long getResultCount(GeneResultSet resultSet, FcgsComparerParams params) {
        return resultSet.getSmartComparerResultCount(params.getDiffChecker(), CpuUtils.getCpuNum());
    }

    /**
     * 保存成矩阵格式数据
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2020/3/4 16:24
     **/
    private void saveMatrix(GeneResultSet resultSet, FcgsComparerParams params, GCResults count) {
        PathCreator creator = getCsvFilePath(params, "Matrix");
        String csvPath = creator.create();
        geneResultSetCsvCreator.create(resultSet, csvPath);
        count.setMatrixCsvPath(creator.getRelativePath());
    }

    /**
     * @return com.viathink.ssr.core4.statistics.comparer.domain.StatisticsGeneContainer
     * @author jiangbin
     * @date 2020/3/4 00:02
     **/
    private StatisticsGeneContainer createStatisticsGeneContainer(List<Gene> testGenes, List<Gene> refGenes, FcgsComparerParams params) {
        StatisticsGeneContainer container = new SimpleStatisticsGeneContainer(testGenes, refGenes);
        if (params.getTagType() == TagTypeEnum.MICROSATELLITE) {
            StatisticsContext.setIsSsr(true);
            container.setMaxSizeOffset(2);
        } else if (params.getTagType() == TagTypeEnum.KASP) {
            StatisticsContext.setIsSsr(true);
            container.setMaxSizeOffset(1);
        } else {
            StatisticsContext.setIsSsr(false);
            container.setMaxSizeOffset(1);
        }
        return container;
    }

    /**
     * 解析指纹数据文件
     *
     * @return java.util.List<com.viathink.ssr.core.domain.gene.Gene>
     * @author jiangbin
     * @date 2020/3/3 23:50
     **/
    private List<Gene> parserGenes(File testCompareGeneFile, TagTypeEnum tagType) throws IOException {
        return geneCsvParser.parser(testCompareGeneFile, tagType);
    }

    /**
     * 构建比对结果csv文件路径
     *
     * @param params 比对参数对象
     * @param suffix 文件拓展名
     * @return com.pids.core.pathcreator.PathCreator
     * <br/><br/>
     * Create by WuHaotian on 2020-05-21 16:35:27
     **/
    private PathCreator getCsvFilePath(FcgsComparerParams params, String suffix) {
        PathCreator creator = new SimplePathCreator();

        try {
            String classesPath = PathUtils.getClassesPath();
            creator.setFolder(classesPath.substring(0, classesPath.length() - 1));
            creator.setFileSuffix(params.getTagType() + "/FcgsCompareResult-" + suffix);
            creator.setExt(".csv");
        } catch (Exception e) {
            creator = new SimplePathCreator() {
                @Override
                public String createDatePart() {
                    return null;
                }

                @Override
                public String createRelativePath() {
                    return getFileName();
                }
            };
            String currentFilePath = PathUtils.getCurrentFilePath(this.getClass());
            //currentFilePath.lastIndexOf(File.separator))，解决windows电脑执行报告问题；https://gitlab.com/acdna/maize/-/issues/8152
            creator.setFolder(currentFilePath.substring(0, currentFilePath.lastIndexOf("/")));
        }

        return creator;
    }

    /**
     * 获取带时间后缀的CSV文件名
     *
     * @return java.lang.String 文件名
     * <br/><br/>
     * Create by WuHaotian on 2020-05-25 16:45:01
     **/
    private String getFileName() {
        String datePrefix = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        return String.format("%s%s.%s", "CompareResult-", datePrefix, "csv");
    }

    public static void main(String[] args) {
        String path = "/C:/Users/WUHAOTIAN/Desktop/fcgs.jar";
        path = path.substring(0, path.lastIndexOf(File.separator));
        System.out.println("currentFilePath:" + path);
    }
}
