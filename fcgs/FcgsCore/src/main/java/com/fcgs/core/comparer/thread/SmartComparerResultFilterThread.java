package com.fcgs.core.comparer.thread;

import com.fcgs.core.comparer.domain.FastGeneResultSet;
import com.fcgs.core.comparer.domain.SimpleSmartComaprerResult;
import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.pids.core.checker.number.NumberChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 快速比对结果过滤线程
 *
 * @author jiangbin
 * @date 2019/11/26 4:36 下午
 **/

public class SmartComparerResultFilterThread implements Callable<Boolean> {
    private FastGeneResultSet result;
    private int sCol;
    private int tCol;
    private CountDownLatch latch;
    private NumberChecker checker;
    private List<SmartComparerResult> targets;

    @Override
    public Boolean call() throws Exception {
        try {
            if (checker == null) {
                filterResults1();
            } else {
                filterResults2();
            }
        } finally {
            latch.countDown();
        }
        return true;
    }

    /**
     * 过滤全部的比对结果
     *
     * @return void
     * @throws
     * @author jiang
     * @date 2020/1/4 15:38
     **/
    private void filterResults1() {
        this.targets = new ArrayList<>();
        for (int col = sCol; col < tCol; col++) {
            String tId = this.result.gettIdList().get(col);
            for (int row = 0; row < this.result.getRow(); row++) {
                String sId = this.result.getsIdList().get(row);
                if (sId.equals(tId)) {
                    continue;
                }
                int diff = this.result.getDiff(row, col);
                if (this.result.valid(diff)) {
                    SmartComparerResult result = new SimpleSmartComaprerResult();
                    result.setSourceGeneId(sId);
                    result.setTargetGeneId(tId);
                    result.setDiffCount(diff);
                    this.targets.add(result);
                }
            }
        }
    }

    /**
     * 过滤指定范围内的比对结果
     *
     * @return void
     * @author jiangbin
     * @date 2019/11/26 4:39 下午
     **/
    private void filterResults2() {
        this.targets = new ArrayList<>();
        for (int col = sCol; col < tCol; col++) {
            String tId = this.result.gettIdList().get(col);
            for (int row = 0; row < this.result.getRow(); row++) {
                String sId = this.result.getsIdList().get(row);
                if (sId.equals(tId)) {
                    continue;
                }
                int diff = this.result.getDiff(row, col);
                if (this.result.valid(diff) && checker.check(diff)) {
                    SmartComparerResult result = new SimpleSmartComaprerResult();
                    result.setSourceGeneId(sId);
                    result.setTargetGeneId(tId);
                    result.setDiffCount(diff);
                    this.targets.add(result);
                }
            }
        }
    }

    public FastGeneResultSet getResult() {
        return result;
    }

    public void setResult(FastGeneResultSet result) {
        this.result = result;
    }

    public int getsCol() {
        return sCol;
    }

    public void setsCol(int sCol) {
        this.sCol = sCol;
    }

    public int gettCol() {
        return tCol;
    }

    public void settCol(int tCol) {
        this.tCol = tCol;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public NumberChecker getChecker() {
        return checker;
    }

    public void setChecker(NumberChecker checker) {
        this.checker = checker;
    }

    public List<SmartComparerResult> getTargets() {
        return targets;
    }

    public void setTargets(List<SmartComparerResult> targets) {
        this.targets = targets;
    }

}
