package com.fcgs.core.comparer.domain;

import com.fcgs.core.comparer.utils.DetailComparerResultFilter;
import com.fcgs.core.comparer.utils.DetailGeneResultMark;
import com.fcgs.core.comparer.utils.SmartComparerResultGroupMapper;
import com.pids.core.checker.number.NumberChecker;
import com.pids.core.pools.IdPools;
import com.pids.core.pools.SimpleIdPools;
import com.pids.core.utils.ListUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 快速模式的指纹比对结果集详细信息对象，不包含具体的差异和无差异位点名称信息，
 * 采用数组方式定义差异和无差异位点数结果，
 * 本组件的比对结果中差异和无差异位点数均为非负数才有效，
 * 而特殊标志值均采用负数进行定义和标记
 *
 * @author jiang
 * @date 2019-11-02 15:36
 */

public class DetailGeneResultSet {
	private static Logger log=LoggerFactory.getLogger(DetailGeneResultSet.class);
    private final AtomicIntegerArray[][] markers;//位点列表
    private IdPools sPools;//待比指纹ID与索引号映射表
    private IdPools tPools;//对比指纹ID与索引号映射表
    private IdPools pPools;//引物名称与索引号映射表
    private final List<String> sIdList;//待比位点列表
    private final List<String> tIdList;//对比位点列表
    private int row; //行数
    private int col; //列数
    private int primerCount;//引物数

    /**
     * 缺失位点标志
     *
     * @author jiangbin
     * @date 2019/11/22 2:57 下午
     **/
    public static final int MISS_MARKER = 0;

    /**
     * 差异位点标志
     *
     * @author jiangbin
     * @date 2019/11/22 2:57 下午
     **/
    public static final int DIFF_MARKER = 1;

    /**
     * 无差异位点标志
     *
     * @author jiangbin
     * @date 2019/11/22 2:57 下午
     **/
    public static final int SAME_MARKER = 2;

    /**
     * 构造函数，构建后会自动标记出重复指纹信息，避免重复统计
     *
     * @param sIdList     待比指纹ID列表
     * @param tIdList     对比指纹ID列表
     * @param primerNames 引物名称列表
     */
    public DetailGeneResultSet(List<String> sIdList, List<String> tIdList, List<String> primerNames) {
        if (ListUtils.isEquals(sIdList, tIdList)) {
            this.sIdList = sIdList;
            this.tIdList = sIdList;
        } else {
            this.sIdList = sIdList;
            this.tIdList = tIdList;
        }

        this.row = this.sIdList.size();
        this.col = this.tIdList.size();
        this.primerCount = primerNames.size();

        this.sPools = new SimpleIdPools(this.sIdList);
        this.tPools = new SimpleIdPools(this.tIdList);
        this.pPools = new SimpleIdPools(primerNames);

        this.markers = new AtomicIntegerArray[row][col];

        for (int row = 0; row < this.row; row++) {
            this.markers[row] = new AtomicIntegerArray[col];
            for (int col = 0; col < this.col; col++) {
                this.markers[row][col] = new AtomicIntegerArray(primerCount);
            }
        }
    }

    /**
     * 构造函数，构建后会自动标记出重复指纹信息，避免重复统计
     *
     * @param sIdList     待比指纹ID列表
     * @param tIdList     对比指纹ID列表
     * @param primerNames 引物名称列表
     * @param mapper      有效指纹对的分组映射表，该参数用于限定详细比对的指纹，以减少无效计算和提高比对性能
     */
    public DetailGeneResultSet(List<String> sIdList, List<String> tIdList, List<String> primerNames, SmartComparerResultGroupMapper mapper) {
        if (ListUtils.isEquals(sIdList, tIdList)) {
            this.sIdList = sIdList;
            this.tIdList = sIdList;
        } else {
            this.sIdList = sIdList;
            this.tIdList = tIdList;
        }

        this.row = this.sIdList.size();
        this.col = this.tIdList.size();
        this.primerCount = primerNames.size();

        this.sPools = new SimpleIdPools(this.sIdList);
        this.tPools = new SimpleIdPools(this.tIdList);
        this.pPools = new SimpleIdPools(primerNames);

        this.markers = new AtomicIntegerArray[row][col];

        for (int row = 0; row < this.row; row++) {
            this.markers[row] = new AtomicIntegerArray[col];
            String sId = this.sPools.getId(row);
            for (int col = 0; col < this.col; col++) {
                String tId = this.tPools.getId(col);
                if (mapper.has(sId, tId)) {
                    this.markers[row][col] = new AtomicIntegerArray(primerCount);
                }
            }
        }
    }

    /**
     * 添加差异位点信息
     *
     * @param row    行索引号
     * @param col    列索引号
     * @param pIndex 引物索引号
     * @return boolean
     * @author jiangbin
     * @date 2019/11/22 3:04 下午
     **/
    public boolean addDiff(int row, int col, int pIndex) {
        markers[row][col].set(pIndex, DIFF_MARKER);
        return true;
    }

    /**
     * 添加无差异位点信息
     *
     * @param row    行索引号
     * @param col    列索引号
     * @param pIndex 引物索引号
     * @return boolean
     * @author jiangbin
     * @date 2019/11/22 3:04 下午
     **/
    public boolean addSame(int row, int col, int pIndex) {
        markers[row][col].set(pIndex, SAME_MARKER);
        return true;
    }

    /**
     * 获取指定行和列索引号位置的位点数据信息
     *
     * @param row 行索引号
     * @param col 列索引号
     * @return java.util.concurrent.atomic.AtomicIntegerArray
     * @author jiangbin
     * @date 2019/11/27 5:15 下午
     **/
    public AtomicIntegerArray get(int row, int col) {
        return markers[row][col];
    }

    /**
     * 获取详细比对结果列表
     *
     * @param results 快速比对结果列表
     * @return java.util.List<com.viathink.ssr.core.comparer.domain.DetailComparerResult>
     * @author jiangbin
     * @date 2019/11/22 3:25 下午
     **/
    public List<DetailComparerResult> getDetailComparerResults(List<SmartComparerResult> results, int poolSize) {
        DetailComparerResultFilter filter = new DetailComparerResultFilter();
        return filter.filter(this, results, poolSize);
    }

    /**
     * 获取详细比对结果列表
     *
     * @param diffChecker
     * @return java.util.List<com.viathink.ssr.core.comparer.domain.DetailComparerResult>
     * @author jiangbin
     * @date 2019/11/25 6:24 下午
     **/
    public List<DetailComparerResult> getDetailComparerResults(NumberChecker diffChecker) {
        StopWatch watch = new StopWatch();
        watch.start();
        List<DetailComparerResult> targets = new ArrayList<>();
        try {
            for (int row = 0; row < getRow(); row++) {
                for (int col = 0; col < getCol(); col++) {
                    DetailComparerResult detail = getDetailComparerResult(row, col, diffChecker);
                    if (detail != null) {
                        targets.add(detail);
                    }
                }
            }
            return targets;
        } finally {
            watch.stop();
            log.info("获取" + targets.size() + "个详细比对结果列表耗时(ms)==>" + watch.getTime());
        }
    }

    /**
     * 获取详细比对结果信息
     *
     * @param row 行索引号
     * @param col 列索引号
     * @return com.viathink.ssr.core.comparer.domain.DetailComparerResult
     * @author jiangbin
     * @date 2019/11/22 3:22 下午
     **/
    public DetailComparerResult getDetailComparerResult(int row, int col) {
        DetailComparerResult result = new SimpleDetailComparerResult();
        result.setSourceGeneId(sPools.getId(row));
        result.setTargetGeneId(tPools.getId(col));
        MarkerNamesPair pair = getMarkerNames(row, col);
        if (pair == null) {
            return null;
        }
        result.setMarkerCount(pair.getMarkerCount());
        result.setDiffMarkerCount(pair.getDiffCount());
        result.setNoDiffMarkerCount(pair.getSameCount());
        result.setDiffMarkerNames(pair.getDiffs());
        result.setNoDiffMarkerNames(pair.getSames());
        result.setMissMarkerNames(pair.getMisss());
        result.setMissMarkerCount(pair.getMissCount());
        return result;
    }

    /**
     * 获取详细比对结果信息
     *
     * @param row         行索引号
     * @param col         列索引号
     * @param diffChecker 差异位点检查器
     * @return com.viathink.ssr.core.comparer.domain.DetailComparerResult
     * @author jiangbin
     * @date 2019/11/25 6:24 下午
     **/
    public DetailComparerResult getDetailComparerResult(int row, int col, NumberChecker diffChecker) {
        MarkerNamesPair pair = getMarkerNames(row, col);
        if (pair == null || !diffChecker.check(pair.getDiffCount())) {
            return null;
        }
        DetailComparerResult result = new SimpleDetailComparerResult();
        result.setSourceGeneId(sPools.getId(row));
        result.setTargetGeneId(tPools.getId(col));
        result.setMarkerCount(pair.getMarkerCount());
        result.setDiffMarkerCount(pair.getDiffCount());
        result.setNoDiffMarkerCount(pair.getSameCount());
        result.setDiffMarkerNames(pair.getDiffs());
        result.setNoDiffMarkerNames(pair.getSames());
        result.setMissMarkerNames(pair.getMisss());
        result.setMissMarkerCount(pair.getMissCount());
        return result;
    }

    /**
     * 获取差异和无差异位点名称信息
     *
     * @param row 行索引号
     * @param col 列索引号
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/22 3:11 下午
     **/
    public MarkerNamesPair getMarkerNames(int row, int col) {
        AtomicIntegerArray names = markers[row][col];
        if (names == null) {
            return null;
        }
        MarkerNamesPair pair = new MarkerNamesPair();
        for (int i = 0; i < this.primerCount; i++) {
            if (names.get(i) == MISS_MARKER) {
                pair.addMiss(pPools.getId(i));
            } else if (names.get(i) == SAME_MARKER) {
                pair.addSame(pPools.getId(i));
            } else {
                pair.addDiff(pPools.getId(i));
            }
        }
        return pair;
    }

    /**
     * 获取比对结果位点信息列表
     *
     * @return java.util.concurrent.atomic.AtomicIntegerArray[][]
     * @author jiangbin
     * @date 2019/11/25 1:20 下午
     **/
    public AtomicIntegerArray[][] getMarkers() {
        return markers;
    }

    /**
     * 获取待比指纹ID与索引号映射表
     *
     * @return com.viathink.core.pools.IdPools
     * @author jiangbin
     * @date 2019/11/25 1:20 下午
     **/
    public IdPools getsPools() {
        return sPools;
    }

    /**
     * 获取对比指纹ID与索引号映射表
     *
     * @return com.viathink.core.pools.IdPools
     * @author jiangbin
     * @date 2019/11/25 1:20 下午
     **/
    public IdPools gettPools() {
        return tPools;
    }

    /**
     * 获取引物名称与索引号映射表
     *
     * @return com.viathink.core.pools.IdPools
     * @author jiangbin
     * @date 2019/11/22 3:16 下午
     **/
    public IdPools getpPools() {
        return pPools;
    }

    /**
     * 获取行数
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/25 1:18 下午
     **/
    public int getRow() {
        return row;
    }

    /**
     * 获取列数
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/25 1:18 下午
     **/
    public int getCol() {
        return col;
    }

    /**
     * 获取待比指纹ID列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/25 1:17 下午
     **/
    public List<String> getsIdList() {
        return sIdList;
    }

    /**
     * 获取对比指纹ID列表
     *
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/25 1:17 下午
     **/
    public List<String> gettIdList() {
        return tIdList;
    }

    /**
     * 设置总列数
     *
     * @param col
     * @return void
     * @author jiangbin
     * @date 2019/11/25 1:17 下午
     **/
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * 获取引物总数
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/25 1:17 下午
     **/
    public int getPrimerCount() {
        return primerCount;
    }

    /**
     * 标记无效数据位点
     *
     * @return void
     * @author jiangbin
     * @date 2019/11/25 6:25 下午
     **/
    public void invalid() {
        StopWatch watch = new StopWatch();
        watch.start();

        DetailGeneResultMark mark = new DetailGeneResultMark();
        mark.mark(this);

        watch.stop();
        log.info("标记重复指纹比对结果耗时(ms)==>" + watch.getTime());
    }

    /**
     * 标记为无效数据
     *
     * @param row
     * @param col
     * @return void
     * @author jiangbin
     * @date 2019/11/26 3:42 下午
     **/
    public void invalid(int row, int col) {
        this.markers[row][col] = null;
    }
}
