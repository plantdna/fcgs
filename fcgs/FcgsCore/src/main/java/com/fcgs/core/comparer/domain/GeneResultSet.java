package com.fcgs.core.comparer.domain;

import com.pids.core.checker.number.NumberChecker;
import com.pids.core.pair.Pair;
import com.pids.core.pools.IdPools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 指纹比对结果详细信息对象，采用数组方式定义结果，
 * 本组件的比对结果中差异和无差异位点数均为非负数才有效，
 * 而特殊标志值均采用负数进行定义和标记
 *
 * @author jiang
 * @date 2019-11-02 15:36
 */
public interface GeneResultSet extends Serializable {

    /**
     * 无效数据标志
     *
     * @author jiangbin
     * @date 2019/11/4 10:47 上午
     **/
    int INVALID = -1;

    /**
     * 指纹ID相同标志
     *
     * @author jiangbin
     * @date 2019/11/4 10:47 上午
     **/
    int SAME_ID = -2;

    /**
     * 最小有效值
     *
     * @author jiangbin
     * @date 2019/11/4 10:49 上午
     **/
    int MIN_VALID = 0;

    /**
     * 增加差异位点数
     *
     * @param row   行索引号
     * @param col   列索引号
     * @param count 差异位点数
     * @return void
     * @throws
     * @author jiang
     * @date 2019/11/2 15:49
     **/
    boolean addDiff(int row, int col, int count);

    /**
     * 增加差异位点数
     *
     * @param sGeneId 待比指纹ID号
     * @param tGeneId 对比指纹ID号
     * @param count   无差异位点数
     * @return boolean
     * @throws
     * @author jiang
     * @date 2019/11/2 15:49
     **/
    boolean addDiff(String sGeneId, String tGeneId, int count);

    /**
     * 设置位点统计结果数据
     *
     * @param row   行索引号
     * @param col   列索引号
     * @param value 数据值
     * @return void
     * @throws
     * @author jiang
     * @date 2019/11/2 15:49
     **/
    void set(int row, int col, int value);

    /**
     * 设置位点统计结果为无效数据
     *
     * @param row 行索引号
     * @param col 列索引号
     * @return void
     * @throws
     * @author jiang
     * @date 2019/11/2 15:49
     **/
    void invalid(int row, int col);

    /**
     * 设置位点统计结果数据
     *
     * @param sGeneId 行索引号
     * @param tGeneId 列索引号
     * @param value   数据值
     * @return void
     * @throws
     * @author jiang
     * @date 2019/11/2 15:49
     **/
    void set(String sGeneId, String tGeneId, int value);

    /**
     * 获取差异位点统计结果信息
     *
     * @return int[][]
     * @throws
     * @author jiang
     * @date 2019/11/2 15:55
     **/
    AtomicIntegerArray[] getDiffs();

    /**
     * 获取比对位点数
     *
     * @param row 行索引
     * @param col 列索引
     * @return int
     * @throws
     * @author jiang
     * @date 2019/11/2 15:54
     **/
    int getMarkerCount(int row, int col);

    /**
     * 获取差异位点数
     *
     * @param sGeneId 待比指纹ID
     * @param tGeneId 对比指纹ID
     * @return int
     * @author jiangbin
     * @date 2019/11/12 9:25 上午
     **/
    int getDiff(String sGeneId, String tGeneId);

    /**
     * 获取差异位点数
     *
     * @param row
     * @param col
     * @return int
     * @throws
     * @author jiang
     * @date 2019/11/2 15:56
     **/
    int getDiff(int row, int col);

    /**
     * 获取待比指纹ID与索引号映射表
     *
     * @return com.viathink.gene.comparer.domain.GeneIdPools
     * @throws
     * @author jiang
     * @date 2019/11/2 16:09
     **/
    IdPools getsPools();

    /**
     * 获取对比指纹ID与索引号映射表
     *
     * @return com.viathink.gene.comparer.domain.GeneIdPools
     * @throws
     * @author jiang
     * @date 2019/11/2 16:09
     **/
    IdPools gettPools();

    /**
     * 获取比对结果表的行数
     *
     * @return int
     * @throws
     * @author jiang
     * @date 2019/11/2 16:09
     **/
    int getRow();

    /**
     * 获取比对结果表的列数
     *
     * @return int
     * @throws
     * @author jiang
     * @date 2019/11/2 16:09
     **/
    int getCol();

    /**
     * 获取待比指纹ID列表
     *
     * @return java.util.List<java.lang.String>
     * @throws
     * @author jiang
     * @date 2019/11/2 16:20
     **/
    List<String> getsIdList();

    /**
     * 获取对比指纹ID列表
     *
     * @return java.util.List<java.lang.String>
     * @throws
     * @author jiang
     * @date 2019/11/2 16:20
     **/
    List<String> gettIdList();

    /**
     * 获取符合给定差异位点限定条件的指纹结果对中包含的指纹ID列表并去重
     *
     * @param diffChecker 差异位点检查器
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/6 9:37 上午
     **/
    List<String> getResultIds(NumberChecker diffChecker);

    /**
     * 获取差异位点统计信息，调用本方法前需要调用{@link #invalid()} 方法标记出重复结果和ID相同的结果才能得到正确数据
     *
     * @return java.util.Map<java.lang.Integer, java.lang.Integer> key/value-差异位点数/指纹对数
     * @author jiangbin
     * @date 2019/11/4 9:53 上午
     **/
    Map<Integer, Integer> getDiffCount();

    /**
     * 获取差异位点统计信息，调用本方法前需要调用{@link #invalid()}方法标记出重复结果和ID相同的结果才能得到正确数据
     *
     * @param checker 位点检查器，用于过滤无差异位点
     * @return java.util.Map<java.lang.Integer, java.lang.Integer> key/value-差异位点数/指纹对数
     * @author jiangbin
     * @date 2019/11/4 9:53 上午
     **/
    Map<Integer, Integer> getDiffCount(NumberChecker checker);

    /**
     * 标记出无效的比对结果，数据中-1为无效标记位，-2为指纹ID相同标记位
     *
     * @return void
     * @author jiangbin
     * @date 2019/11/4 10:02 上午
     **/
    void invalid();

    /**
     * 标记出符合给定差异位点条件的比对结果，对于不符合要求的位点将全部标记为-1
     *
     * @return int 返回被检测符合要求的指纹结果对数
     * @author jiangbin
     * @date 2019/11/4 10:02 上午
     **/
    int markDiff(NumberChecker checker);

    /**
     * 判定是否均有效的行列索引号
     *
     * @param row 行索引号
     * @param col 列索引号
     * @return boolean
     * @author jiangbin
     * @date 2019/11/19 5:28 下午
     **/
    boolean validIndex(int row, int col);

    /**
     * 判定给定结果是否为有效数据位，即判定为大于等于0即为有效数据位
     *
     * @param result 统计结果数
     * @return boolean true/false--可用/不可用
     * @author jiangbin
     * @date 2019/11/4 2:27 下午
     **/
    boolean valid(int result);

    /**
     * 获取指定范围内的比对结果的指纹数据对ID信息列表
     *
     * @param diffChecker
     * @param poolSize
     * @return java.util.List<com.viathink.ssr.core.comparer.domain.SmartComparerResult>
     * @author jiangbin
     * @date 2019/11/21 3:19 下午
     **/
    List<SmartComparerResult> getSmartComparerResult(NumberChecker diffChecker, int poolSize);

    /**
     * 获取全部快速比对结果列表
     *
     * @param poolSize
     * @return java.util.List<com.viathink.ssr.core.comparer.domain.SmartComparerResult>
     * @throws
     * @author jiang
     * @date 2020/1/4 15:36
     **/
    List<SmartComparerResult> getSmartComparerResult(int poolSize);

    /**
     * 获取快速比对结果数
     *
     * @param poolSize
     * @return long
     * @author jiangbin
     * @date 2020/5/25 10:19
     **/
    long getSmartComparerResultCount(int poolSize);

    /**
     * 获取指定差异位点数范围内的快速比对结果数
     *
     * @param diffChecker
     * @param poolSize
     * @return long
     * @author jiangbin
     * @date 2020/5/25 10:19
     **/
    long getSmartComparerResultCount(NumberChecker diffChecker, int poolSize);

    /**
     * 获取总记录数和总差异位点总数
     *
     * @param poolSize
     * @return com.pids.core.pair.Pair<java.lang.Long, java.lang.Long> source-总记录数,target-总差异位点数
     * @author jiangbin
     * @date 2020/5/30 14:18
     **/
    Pair<Long, Long> getAllResultCountInfo(int poolSize);

}
