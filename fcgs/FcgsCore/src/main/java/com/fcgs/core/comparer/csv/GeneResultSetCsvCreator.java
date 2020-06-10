package com.fcgs.core.comparer.csv;

import com.fcgs.core.comparer.domain.GeneResultSet;
import com.fcgs.core.comparer.domain.HasResultIndexMapper;
import com.pids.core.csv.utils.CsvUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * 构建比对结果集信息文件
 */

public class GeneResultSetCsvCreator {
	private Logger log=LoggerFactory.getLogger(this.getClass());
    private static String SEMICOLON = "\"";
    private static String CSV_SEPARATOR = "\",\"";
    private static String ENTER = "\n";

    /**
     * 构建比对结果CSV文件内容
     *
     * @param details
     * @param csvPath
     * @return java.lang.String
     * @throws
     * @author jiang
     * @date 2019/11/2 23:47
     **/
    public void create(GeneResultSet details, String csvPath) {
        if (details == null || StringUtils.isBlank(csvPath)) {
            return;
        }

        StopWatch watch = new StopWatch();
        watch.start();

        //过滤出有数据的行和列
        HasResultIndexMapper mapper = new HasResultIndexMapper();
        mapper.add(details);

        //获取包含有效指纹数据信息的行和列索引号列表
        List<Integer> rows = mapper.getRowList();
        List<Integer> cols = mapper.getColList();
        if (CollectionUtils.isEmpty(rows) || CollectionUtils.isEmpty(cols)) {
            log.info("未有效指纹比对结果数据信息!");
        }

        //写标题行数据
        writeTitleDatas(details, rows, cols, csvPath);

        //写指纹统计数据
        writeRowDatas(details, rows, cols, csvPath);

        watch.stop();
        log.info("写文件" + csvPath + "共耗时(ms)==>" + watch.getTime());
    }

    /**
     * 写标题数据到文件中
     *
     * @param details
     * @param csvPath
     * @return void
     * @author jiangbin
     * @date 2019/11/7 5:09 下午
     **/
    private void writeTitleDatas(final GeneResultSet details, List<Integer> rows, List<Integer> cols, final String csvPath) {
        try {
            StringBuilder titles = new StringBuilder();

            //构建标题行
            titles.append(SEMICOLON).append("SOURCE/TARGET").append(CSV_SEPARATOR);
            for (int index = 0; index < cols.size(); index++) {
                String tGeneId = details.gettIdList().get(cols.get(index));
                titles.append(tGeneId);
                if (index == cols.size() - 1) {
                    titles.append(SEMICOLON);
                } else {
                    titles.append(CSV_SEPARATOR);
                }
            }
            titles.append(ENTER);

            //加CSV标志头
            String targetStr = CsvUtils.addCsvBOM(titles.toString());

            FileUtils.writeStringToFile(new File(csvPath), targetStr, "UTF-8");
        } catch (Exception e) {
        }
    }

    /**
     * 写行数据
     *
     * @param details
     * @param csvPath
     * @return void
     * @author jiangbin
     * @date 2019/11/6 9:58 上午
     **/
    private void writeRowDatas(final GeneResultSet details, List<Integer> rows, List<Integer> cols, final String csvPath) {
        //构建数据行
        StringBuilder row;
        for (int i = 0; i < rows.size(); i++) {
            int sIndex = rows.get(i);
            String sGeneId = details.getsPools().getId(sIndex);

            //行数据内容
            row = new StringBuilder();

            //写文件
            try {
                row.append(SEMICOLON).append(sGeneId).append(CSV_SEPARATOR);

                for (int j = 0; j < cols.size(); j++) {
                    String tGeneId = details.gettIdList().get(cols.get(j));
                    int tIndex = details.gettPools().getIndex(tGeneId);

                    int diff = details.getDiff(sIndex, tIndex);

                    //指纹ID不同且差异和无差异均不为无效占位符时构建差异和无差异信息
                    if (details.valid(diff)) {
                        row.append(diff);
                    }

                    //判定是否为行尾
                    if (j == cols.size() - 1) {
                        row.append(SEMICOLON);
                    } else {
                        row.append(CSV_SEPARATOR);
                    }
                }
                row.append(ENTER);

                //写磁盘文件
                FileUtils.writeStringToFile(new File(csvPath), row.toString(), true);
            } catch (Exception e) {
            }
        }

    }
}
