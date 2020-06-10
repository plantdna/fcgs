package com.pids.core.csv.creator;

import com.pids.core.csv.utils.CsvUtils;
import com.pids.core.exception.ICoreException;
import com.pids.core.task.manager.SimpleTaskContainer;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskManagerUtils;
import com.pids.core.task.manager.TaskProcessor;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * CSV构建器模板实现类
 *
 * @author jiang
 * @date 2018年9月1日下午11:18:25
 */
public abstract class AbstractCsvCreator<S> implements CsvCreator<S>, TaskProcessor<S, String[]> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String create(List<S> sources) throws ICoreException {
        if (CollectionUtils.isEmpty(sources)) {
            logger.debug(getClass().getName() + "=>未给定待构建的源数据列表!");
            return null;
        }

        logger.info(getClass().getName() + "=>开始构建CSV文件内容===>" + getClassName(sources));

        StopWatch watch = new StopWatch();
        watch.start();
        logger.info(getClass().getName() + "=>开始转换数据为数组列表!");

        List<String[]> rows = new ArrayList<>();

        //构建标题行
        String[] title = createTitle(sources);
        if (ArrayUtils.isNotEmpty(title)) {
            rows.add(title);
        } else {
            logger.warn(getClass().getName() + "=>未设置标题头信息!");
        }

        //构建数据行
        List<String[]> datas = this.sync(sources);
        if (CollectionUtils.isNotEmpty(datas)) {
            rows.addAll(datas);
        } else {
            logger.warn(getClass().getName() + "=>无数据被构建!");
        }

        watch.stop();
        logger.info(getClass().getName() + "=>转换数据为数组列表耗时(ms)==>" + watch.getTime());

        watch = new StopWatch();
        watch.start();
        logger.info(getClass().getName() + "=>开始转换成CSV文件内容!");

        String contents = toCsvStr(rows);

        watch.stop();
        logger.info(getClass().getName() + "=>转换成CSV文件内容耗时(ms)==>" + watch.getTime());

        return contents;
    }

    /**
     * 同步转换数据对象为行数据，采用多线程方式进行转换
     *
     * @param sources
     * @return java.util.List<java.lang.String [ ]>
     * @author jiangbin
     * @date 2019-09-07 14:17
     **/
    protected List<String[]> sync(List<S> sources) {
        TaskContainer<S, String[]> container = new SimpleTaskContainer<>();
        container.setSources(sources);
        container.setProcessor(this);
        container.setShardingCount(5000);
        return TaskManagerUtils.sync(container);
    }

    /**
     * 获取数据元素类类型
     *
     * @param sources
     * @return java.lang.String
     * @author jiangbin
     * @date 2019-09-06 10:38
     **/
    protected String getClassName(List<S> sources) {
        return ListUtils.getFirst(sources).getClass().getName();
    }

    /**
     * 转换成标准的CSV格式数据，主要加BOM头
     *
     * @param rows
     * @return
     * @author jiangbin
     * @date 2018年9月3日下午2:36:59
     */
    protected String toCsvStr(List<String[]> rows) {
        String csvStr = CsvUtils.toCsvStrSmart(rows);
        return CsvUtils.addCsvBOM(csvStr);
    }

    /**
     * 构建行数据
     *
     * @param sources
     * @return
     * @author jiang
     * @date 2018年9月2日上午12:14:13
     */
    protected List<String[]> createRows(List<S> sources) {
        List<String[]> rows = new ArrayList<>();
        for (int index = 0; index < sources.size(); index++) {
            S source = sources.get(index);
            if (source == null) {
                continue;
            }

            //构建成数据数组
            String[] row = createRow(index, source);

            //校正列宽
            if (row.length != getColCount()) {
                row = CsvUtils.reviseCol(row, getColCount());
            }

            //添加到行数据中
            if (ArrayUtils.isNotEmpty(row)) {
                rows.add(row);
            }

        }
        return rows;
    }

    /**
     * 创建CSV文件标题行
     *
     * @param sources 全部用于构建csv文件的数据信息
     * @return
     * @author jiang
     * @date 2018年9月1日下午11:17:25
     */
    protected String[] createTitle(List<S> sources) {
        String[] titles = getDefaultTitles();
        if (ArrayUtils.isEmpty(titles)) {
            return null;
        }
        return titles;
    }

    @Override
    public List<String[]> process(TaskContainer<S, String[]> container) throws ICoreException {
        return createRows(container.getSources());
    }

    /**
     * 创建行数据
     *
     * @param rowIndex 行索引号
     * @param source   行数据信息源对象
     * @return
     * @author jiang
     * @date 2018年9月1日下午11:17:23
     */
    protected abstract String[] createRow(int rowIndex, S source);

    /**
     * 获取默认标题头数组
     *
     * @return
     * @author jiang
     * @date 2018年9月2日上午12:23:30
     */
    protected abstract String[] getDefaultTitles();

    /**
     * 获取总列数
     *
     * @return
     * @author jiang
     * @date 2018年9月1日下午11:39:56
     */
    protected int getColCount() {
        return ArrayUtils.getLength(this.getDefaultTitles());
    }

}
