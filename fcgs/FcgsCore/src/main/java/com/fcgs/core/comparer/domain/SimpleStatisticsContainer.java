package com.fcgs.core.comparer.domain;

import com.fcgs.core.comparer.utils.GeneSpliter;
import com.pids.core.checker.number.NumberChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计信息容器对象
 *
 * @author jiangbin
 * @date 2019/11/7 3:41 下午
 **/
public class SimpleStatisticsContainer implements StatisticsContainer {
	private static final long serialVersionUID = 1L;
	private SsrComparerParams params;
    private StatisticsGeneContainer container;
    private String resultFolder;

    @Override
    public StatisticsGeneContainer getContainer() {
        return container;
    }

    @Override
    public void setContainer(StatisticsGeneContainer container) {
        this.container = container;
    }

    @Override
    public SsrComparerParams getParams() {
        return this.params;
    }

    @Override
    public void setParams(SsrComparerParams params) {
        this.params = params;
    }

    @Override
    public List<StatisticsContainer> split(int step) {
        List<StatisticsContainer> targets = new ArrayList<>();
        List<StatisticsGeneContainer> containers = GeneSpliter.group(this.container);
        for (StatisticsGeneContainer container : containers) {
            StatisticsContainer scontainer = new SimpleStatisticsContainer();
            scontainer.setParams(this.getParams());
            scontainer.setContainer(container);
            targets.add(scontainer);
        }
        return targets;
    }

    @Override
    public void setResultFolder(String resultFolder) {
        this.resultFolder = resultFolder;
    }

    @Override
    public String getResultFolder() {
        return resultFolder;
    }

    @Override
    public int getSourceGeneCount() {
        return this.container.getTargets().size();
    }

    @Override
    public int getTargetGeneCount() {
        return this.container.getSources().size();
    }

    @Override
    public boolean valid() {
        return this.container != null
                && this.getSourceGeneCount() > 0
                && this.getTargetGeneCount() > 0
                && params != null;
    }

    @Override
    public NumberChecker getDiffChecker() {
        if (this.params.getMaxDiffMarkerCount() == null && this.params.getMinDiffMarkerCount() == null) {
            return null;
        }
        NumberChecker checker = new NumberChecker();
        checker.setMaxValue(this.params.getMaxDiffMarkerCount());
        checker.setMinValue(this.params.getMinDiffMarkerCount());
        return checker;
    }
}
