package com.fcgs.core.comparer.domain;

import com.fcgs.core.comparer.utils.FastGeneResultMark;
import com.fcgs.core.comparer.utils.SmartComparerResultCountFilter;
import com.fcgs.core.comparer.utils.SmartComparerResultFilter;
import com.pids.core.checker.number.NumberChecker;
import com.pids.core.mapper.utils.StringItemCountMapper;
import com.pids.core.pair.Pair;
import com.pids.core.pools.IdPools;
import com.pids.core.pools.SimpleIdPools;
import com.pids.core.utils.ListUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 精简模式的指纹比对结果集详细信息对象，不包含具体的差异和无差异位点名称信息，
 * 采用数组方式定义差异和无差异位点数结果，
 * 本组件的比对结果中差异和无差异位点数均为非负数才有效，
 * 而特殊标志值均采用负数进行定义和标记
 *
 * @author jiang
 * @date 2019-11-02 15:36
 */

public class FastGeneResultSet implements GeneResultSet {
    private final AtomicIntegerArray[] diffs;//差异位点结果
    private IdPools sPools;//待比指纹ID与索引号映射表
    private IdPools tPools;//对比指纹ID与索引号映射表
    private final List<String> sIdList;//待比位点列表
    private final List<String> tIdList;//对比位点列表
    private int row; //行数
    private int col; //列数

    /**
     * 构造函数，构建后会自动标记出重复指纹信息，避免重复统计
     *
     * @param sIdList 待比指纹ID列表
     * @param tIdList 对比指纹ID列表
     */
    public FastGeneResultSet(List<String> sIdList, List<String> tIdList) {
        if (ListUtils.isEquals(sIdList, tIdList)) {
            this.sIdList = sIdList;
            this.tIdList = sIdList;
        } else {
            this.sIdList = sIdList;
            this.tIdList = tIdList;
        }
        this.row = this.sIdList.size();
        this.col = this.tIdList.size();
        this.sPools = new SimpleIdPools(this.sIdList);
        this.tPools = new SimpleIdPools(this.tIdList);

        this.diffs = new AtomicIntegerArray[row];
        for (int index = 0; index < row; index++) {
            this.diffs[index] = new AtomicIntegerArray(col);
        }
    }

    @Override
    public boolean addDiff(int row, int col, int count) {
        diffs[row].addAndGet(col, count);
        return true;
    }

    @Override
    public boolean addDiff(String sGeneId, String tGeneId, int count) {
        int row = sPools.getIndex(sGeneId);
        int col = tPools.getIndex(tGeneId);

        return addDiff(row, col, count);
    }

    @Override
    public void set(int row, int col, int value) {
        diffs[row].set(col, value);
    }

    @Override
    public void invalid(int row, int col) {
        set(row, col, INVALID);
    }

    @Override
    public void set(String sGeneId, String tGeneId, int value) {
        int row = sPools.getIndex(sGeneId);
        int col = tPools.getIndex(tGeneId);
        set(row, col, value);
    }

    @Override
    public AtomicIntegerArray[] getDiffs() {
        return diffs;
    }

    @Override
    public int getMarkerCount(int row, int col) {
        return diffs[row].get(col);
    }

    @Override
    public int getDiff(int row, int col) {
        return diffs[row].get(col);
    }

    @Override
    public int getDiff(String sGeneId, String tGeneId) {
        if (sGeneId.equals(tGeneId)) {
            return INVALID;
        }
        int row = sPools.getIndex(sGeneId);
        int col = tPools.getIndex(tGeneId);
        return diffs[row].get(col);
    }

    @Override
    public IdPools getsPools() {
        return sPools;
    }

    @Override
    public IdPools gettPools() {
        return tPools;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public List<String> getsIdList() {
        return sIdList;
    }

    @Override
    public List<String> gettIdList() {
        return tIdList;
    }

    @Override
    public List<String> getResultIds(NumberChecker diffChecker) {
        StringItemCountMapper mapper = new StringItemCountMapper();
        for (int row = 0; row < this.row; row++) {
            String sId = this.sIdList.get(row);
            for (int col = 0; col < this.col; col++) {
                String tId = this.tIdList.get(col);
                int diff = this.getDiff(row, col);
                if (valid(diff) && diffChecker.check(diff)) {
                    mapper.add(sId);
                    mapper.add(tId);
                }
            }
        }
        return mapper.getKeys();
    }

    @Override
    public Map<Integer, Integer> getDiffCount() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < this.col; col++) {
                int diff = this.getDiff(row, col);
                if (valid(diff)) {
                    int count = map.get(diff) == null ? 1 : map.get(diff) + 1;
                    map.put(diff, count);
                }
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getDiffCount(NumberChecker checker) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < this.col; col++) {
                int diff = this.getDiff(row, col);
                if (valid(diff) && checker.check(diff)) {
                    map.put(diff, map.get(diff) == null ? 1 : map.get(diff) + 1);
                }
            }
        }
        return map;
    }

    @Override
    public void invalid() {
        //标记无效数据
        FastGeneResultMark mark = new FastGeneResultMark();
        mark.mark(this);
    }

    @Override
    public int markDiff(NumberChecker checker) {
        int diffCount = 0;//符合要求指纹计数
        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < this.col; col++) {
                int diff = getDiff(row, col);
                if (!valid(diff)) {
                    continue;
                }
                if (checker.check(diff)) {
                    diffCount++;
                } else {
                    set(row, col, INVALID);
                }
            }
        }
        return diffCount;
    }

    @Override
    public boolean validIndex(int row, int col) {
        return row >= IdPools.MIN_VALID && col >= IdPools.MIN_VALID;
    }

    @Override
    public boolean valid(int result) {
        return result >= MIN_VALID;
    }

    @Override
    public List<SmartComparerResult> getSmartComparerResult(NumberChecker diffChecker, int poolSize) {
        SmartComparerResultFilter filter = new SmartComparerResultFilter();
        return filter.filter(this, diffChecker, poolSize);
    }

    @Override
    public List<SmartComparerResult> getSmartComparerResult(int poolSize) {
        SmartComparerResultFilter filter = new SmartComparerResultFilter();
        return filter.filter(this, null, poolSize);
    }

    @Override
    public long getSmartComparerResultCount(int poolSize) {
        return this.getSmartComparerResultCount(null, poolSize);
    }

    @Override
    public long getSmartComparerResultCount(NumberChecker diffChecker, int poolSize) {
        SmartComparerResultCountFilter filter = new SmartComparerResultCountFilter();
        Pair<Long, Long> pair = filter.filter(this, diffChecker, poolSize);
        return pair.getSource();
    }

    @Override
    public Pair<Long, Long> getAllResultCountInfo(int poolSize) {
        SmartComparerResultCountFilter filter = new SmartComparerResultCountFilter();
        return filter.filter(this, null, poolSize);
    }

}
