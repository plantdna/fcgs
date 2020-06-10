package com.fcgs.core.comparer.domain;

import com.fcgs.base.checker.DiffMarkerCountChecker;
import com.pids.core.checker.number.IntRangeChecker;
import com.pids.core.checker.number.NumberChecker;

@SuppressWarnings("serial")
public class SimpleSsrComparerParams implements SsrComparerParams {
    private Integer maxDiffMarkerCount;
    private Integer minDiffMarkerCount;
    private Integer minSameMarkerCount;
    private Integer maxSameMarkerCount;
    private Integer maxMarkerCount;
    private Integer minMarkerCount;
    private Integer maxSizeOffset;
    private Integer maxResultsSize = 1500000;
    private DiffMarkerCountChecker diffMarkerCountChecker;
    private NumberChecker markerCountChecker;
    private NumberChecker sameMarkerCountChecker;
    private NumberChecker sizeOffsetChecker;
    private NumberChecker resultsSizeChecker;
    private boolean filterRepeatResults = true;

    @Override
    public Integer getMaxDiffMarkerCount() {
        return maxDiffMarkerCount;
    }

    @Override
    public void setMaxDiffMarkerCount(Integer maxDiffMarkerCount) {
        this.maxDiffMarkerCount = maxDiffMarkerCount;
    }

    @Override
    public Integer getMinDiffMarkerCount() {
        return minDiffMarkerCount;
    }

    @Override
    public void setMinDiffMarkerCount(Integer minDiffMarkerCount) {
        this.minDiffMarkerCount = minDiffMarkerCount;
    }

    @Override
    public Integer getMinSameMarkerCount() {
        return minSameMarkerCount;
    }

    @Override
    public void setMinSameMarkerCount(Integer minSameMarkerCount) {
        this.minSameMarkerCount = minSameMarkerCount;
    }

    @Override
    public Integer getMaxSameMarkerCount() {
        return maxSameMarkerCount;
    }

    @Override
    public void setMaxSameMarkerCount(Integer maxSameMarkerCount) {
        this.maxSameMarkerCount = maxSameMarkerCount;
    }

    public Integer getMaxMarkerCount() {
        return maxMarkerCount;
    }

    @Override
    public void setMaxMarkerCount(Integer maxMarkerCount) {
        this.maxMarkerCount = maxMarkerCount;
    }

    @Override
    public Integer getMinMarkerCount() {
        return minMarkerCount;
    }

    @Override
    public void setMinMarkerCount(Integer minMarkerCount) {
        this.minMarkerCount = minMarkerCount;
    }

    @Override
    public Integer getMaxSizeOffset() {
        return maxSizeOffset;
    }

    @Override
    public void setMaxSizeOffset(Integer maxSizeOffset) {
        this.maxSizeOffset = maxSizeOffset;
    }

    @Override
    public Integer getMaxResultsSize() {
        return maxResultsSize;
    }

    @Override
    public void setMaxResultsSize(Integer maxResultsSize) {
        this.maxResultsSize = maxResultsSize;
    }

    @Override
    public IntRangeChecker getResultsSizeChecker() {
        if (this.resultsSizeChecker == null) {
            this.resultsSizeChecker = new NumberChecker();
            this.resultsSizeChecker.setMaxValue(this.getMaxResultsSize());
        }
        return this.resultsSizeChecker;
    }

    @Override
    public IntRangeChecker getSizeOffsetChecker() {
        if (this.sizeOffsetChecker == null) {
            this.sizeOffsetChecker = new NumberChecker();
            this.sizeOffsetChecker.setMaxValue(this.getMaxSizeOffset());
        }
        return this.sizeOffsetChecker;
    }

    @Override
    public DiffMarkerCountChecker getDiffMarkerCountChecker() {
        if (this.diffMarkerCountChecker == null) {
            this.diffMarkerCountChecker = new DiffMarkerCountChecker();
            this.diffMarkerCountChecker.setMaxDiffMarkerCount(this.getMaxDiffMarkerCount());
            this.diffMarkerCountChecker.setMinDiffMarkerCount(this.getMinDiffMarkerCount());
        }
        return this.diffMarkerCountChecker;
    }

    @Override
    public IntRangeChecker getSameMarkerCountChecker() {
        if (this.sameMarkerCountChecker == null) {
            this.sameMarkerCountChecker = new NumberChecker();
            this.sameMarkerCountChecker.setMaxValue(this.getMaxSameMarkerCount());
            this.sameMarkerCountChecker.setMinValue(this.getMinSameMarkerCount());
        }
        return this.sameMarkerCountChecker;
    }

    @Override
    public IntRangeChecker getMarkerCountChecker() {
        if (this.markerCountChecker == null) {
            this.markerCountChecker = new NumberChecker();
            this.markerCountChecker.setMaxValue(this.getMaxMarkerCount());
            this.markerCountChecker.setMinValue(this.getMinMarkerCount());
        }
        return this.markerCountChecker;
    }

    @Override
    public void setDiffMarkerCountChecker(DiffMarkerCountChecker diffMarkerCountChecker) {
        this.diffMarkerCountChecker = diffMarkerCountChecker;
    }

    @Override
    public void setMarkerCountChecker(NumberChecker markerCountChecker) {
        this.markerCountChecker = markerCountChecker;
    }

    @Override
    public void setSameMarkerCountChecker(NumberChecker sameMarkerCountChecker) {
        this.sameMarkerCountChecker = sameMarkerCountChecker;
    }

    @Override
    public void setSizeOffsetChecker(NumberChecker sizeOffsetChecker) {
        this.sizeOffsetChecker = sizeOffsetChecker;
    }

    @Override
    public void setResultsSizeChecker(NumberChecker resultsSizeChecker) {
        this.resultsSizeChecker = resultsSizeChecker;
    }

    @Override
    public void setFilterRepeatResults(boolean filterRepeatResults) {
        this.filterRepeatResults = filterRepeatResults;
    }

    @Override
    public boolean isFilterRepeatResults() {
        return filterRepeatResults;
    }
}
