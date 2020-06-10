package com.fcgs.core.comparer.domain;

import com.pids.core.utils.ListUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 差异和无差异位点名称列表
 *
 * @author jiangbin
 * @date 2019/11/22 3:12 下午
 **/
public class MarkerNamesPair implements Serializable {
    private List<String> diffs;
    private List<String> sames;
    private List<String> misss;

    public MarkerNamesPair() {
        this.diffs = new ArrayList<>();
        this.sames = new ArrayList<>();
        this.misss = new ArrayList<>();
    }

    public List<String> getDiffs() {
        return diffs;
    }

    public void setDiffs(List<String> diffs) {
        this.diffs = diffs;
    }

    public List<String> getSames() {
        return sames;
    }

    public void setSames(List<String> sames) {
        this.sames = sames;
    }

    public List<String> getMisss() {
        return misss;
    }

    public void setMisss(List<String> misss) {
        this.misss = misss;
    }

    public void addDiff(String diffName) {
        this.diffs.add(diffName);
    }

    public void addSame(String sameName) {
        this.sames.add(sameName);
    }

    public void addMiss(String missName) {
        this.misss.add(missName);
    }

    public int getDiffCount() {
        return ListUtils.size(this.diffs);
    }

    public int getSameCount() {
        return ListUtils.size(this.sames);
    }

    public int getMissCount() {
        return ListUtils.size(this.misss);
    }

    public int getMarkerCount() {
        return getDiffCount() + getSameCount();
    }
}
