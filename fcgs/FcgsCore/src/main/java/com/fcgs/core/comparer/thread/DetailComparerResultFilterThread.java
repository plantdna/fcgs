package com.fcgs.core.comparer.thread;

import com.fcgs.core.comparer.domain.DetailComparerResult;
import com.fcgs.core.comparer.domain.DetailGeneResultSet;
import com.fcgs.core.comparer.domain.SmartComparerResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 详细比对结果过滤线程
 *
 * @author jiangbin
 * @date 2019/11/26 4:36 下午
 **/

public class DetailComparerResultFilterThread implements Callable<Boolean> {
    private DetailGeneResultSet result;
    private CountDownLatch latch;
    private List<SmartComparerResult> sources;
    private List<DetailComparerResult> targets;

    @Override
    public Boolean call() throws Exception {
        try {
            filterResults();
        } finally {
            latch.countDown();
        }
        return true;
    }

    /**
     * 过滤指定范围内的比对结果
     *
     * @return void
     * @author jiangbin
     * @date 2019/11/26 4:39 下午
     **/
    private void filterResults() {
        this.targets = new ArrayList<>();
        for (SmartComparerResult source : this.sources) {
            int row = this.result.getsPools().getIndex(source.getSourceGeneId());
            int col = this.result.gettPools().getIndex(source.getTargetGeneId());
            DetailComparerResult detail = this.result.getDetailComparerResult(row, col);
            if (detail != null) {
                targets.add(detail);
            }
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public DetailGeneResultSet getResult() {
        return result;
    }

    public void setResult(DetailGeneResultSet result) {
        this.result = result;
    }

    public List<SmartComparerResult> getSources() {
        return sources;
    }

    public void setSources(List<SmartComparerResult> sources) {
        this.sources = sources;
    }

    public List<DetailComparerResult> getTargets() {
        return targets;
    }

    public void setTargets(List<DetailComparerResult> targets) {
        this.targets = targets;
    }
}
