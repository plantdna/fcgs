package com.fcgs.core.comparer.thread;

import com.fcgs.core.comparer.domain.GeneResultSet;

import java.util.concurrent.CountDownLatch;

/**
 * 合并差异位点，主要处理待比和对比指纹相同时需要合并差异数据
 *
 * @author jiangbin
 * @date 2019/11/21 11:49 上午
 **/

public class ResultSetDiffMergeThread implements Runnable {
    private GeneResultSet result;
    private int sCol;
    private int tCol;
    private CountDownLatch latch;

    @Override
    public void run() {
        try {
            for (int col = sCol; col < tCol; col++) {
                for (int row = col + 1; row < result.getRow(); row++) {
                    int sDiff = this.result.getDiffs()[row].get(col);
                    int tDiff = this.result.getDiffs()[col].get(row);
                    this.result.getDiffs()[row].set(col, sDiff + tDiff);
                }
            }
        } finally {
            latch.countDown();
        }
    }

    public GeneResultSet getResult() {
        return result;
    }

    public void setResult(GeneResultSet result) {
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
}
