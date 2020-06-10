package com.fcgs.base.domain.gene;

import com.fcgs.base.domain.SimpleGeneLocation;
import com.fcgs.base.domain.SimpleGeneSupport;
import com.fcgs.base.domain.SimpleSampleDna;
import com.fcgs.base.filter.ManualMarkerFilter;
import com.fcgs.base.mapper.MarkerDyeMapper;
import com.fcgs.base.mapper.MarkerMapper;
import com.pids.core.utils.ListUtils;
import com.pids.core.utils.ObjectCopier;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>指纹的简单实现类，会自动初始化如下属性的实例：
 * {@link #markers} 位点列表
 * {@link #location} 板孔信息
 * {@link #sample} 样品信息
 * </pre>
 *
 * @author jiangbin
 * @date 2014年3月21日上午11:02:58
 */
public class SimpleGene extends SimpleGeneSupport implements Gene {
    private static final long serialVersionUID = 6206426023582695860L;
    protected String geneId;
    protected Integer geneLib;
    protected Integer geneStatus;
    protected Integer geneAuditWay;
    protected List<Marker> markers;
    protected Integer geneOrigin;
    protected Double hybridPercent;
    protected boolean isSingleGene;
    protected String user;
    protected List<String> missMarkers;
    protected int markerCount;

    public SimpleGene() {
        this.markers = new ArrayList<>();
        this.location = new SimpleGeneLocation();
        this.sample = new SimpleSampleDna();
    }

    @Override
    public String getGeneId() {
        return geneId;
    }

    @Override
    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    @Override
    public void setGeneLib(Integer geneLib) {
        this.geneLib = geneLib;
    }

    @Override
    public Integer getGeneLib() {
        return this.geneLib;
    }

    @Override
    public Integer getGeneStatus() {
        return this.geneStatus;
    }

    @Override
    public void setGeneStatus(Integer geneStatus) {
        this.geneStatus = geneStatus;
    }

    @Override
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(this.markers);
    }

    @Override
    public int size() {
        return ListUtils.size(markers);
    }

    @Override
    public void setMarkers(List<Marker> markers) {
        this.markers = markers;
    }

    @Override
    public List<Marker> getMarkers() {
        return markers;
    }

    @Override
    public void setGeneAuditWay(Integer geneAuditWay) {
        this.geneAuditWay = geneAuditWay;
    }

    @Override
    public Integer getGeneAuditWay() {
        return geneAuditWay;
    }

    @Override
    public Gene getGene() {
        return this;
    }

    @Override
    public void setGene(Gene gene) {
        ObjectCopier.copy(gene, this);
    }

    @Override
    public void addMarker(Marker marker) {
        if (marker != null) {
            this.markers.add(marker);
        }
    }

    @Override
    public List<String> getMarkerNames() {
        return new MarkerMapper(this.markers).getKeys();
    }

    @Override
    public Marker getMarkerByDye(String dye) {
        MarkerDyeMapper mapper = new MarkerDyeMapper();
        mapper.addAll(this.markers);
        return mapper.get(dye);
    }

    @Override
    public Marker getMarkerByName(String primerName) {
        MarkerMapper mapper = new MarkerMapper();
        mapper.addAll(this.markers);
        return mapper.get(primerName);
    }

    @Override
    public List<Marker> getMarkerByNameList(List<String> markerNames) {
        if (CollectionUtils.isEmpty(markerNames) || CollectionUtils.isEmpty(this.markers)) {
            return null;
        }
        MarkerMapper mapper = new MarkerMapper(this);
        return mapper.getValues(markerNames);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public Integer getGeneOrigin() {
        return geneOrigin;
    }

    @Override
    public void setGeneOrigin(Integer geneOrigin) {
        this.geneOrigin = geneOrigin;
    }

    @Override
    public void setSamBarcode(String samBarcode) {
        this.sample.setSamBarcode(samBarcode);
    }

    @Override
    public String getSamBarcode() {
        return this.sample.getSamBarcode();
    }

    @Override
    public void addMarkers(List<Marker> markers) {
        if (CollectionUtils.isEmpty(markers)) {
            return;
        }
        List<String> markerNames = new MarkerMapper(markers).getKeys();
        if (CollectionUtils.isEmpty(markerNames)) {
            return;
        }
        removeMarkers(markerNames);
        if (this.markers == null) {
            this.markers = new ArrayList<>();
        }
        this.markers.addAll(markers);
    }

    @Override
    public void removeMarkers(List<String> markerNames) {
        if (CollectionUtils.isEmpty(markerNames)) {
            return;
        }
        if (CollectionUtils.isEmpty(this.markers)) {
            return;
        }
        MarkerMapper mapper = new MarkerMapper();
        mapper.addAll(this.markers);
        mapper.delete(markerNames);
        this.markers = mapper.getValues();
    }

    @Override
    public void clearDnaBarcode() {
        if (this.getSample() == null) {
            return;
        }
        getSample().setDnaBarcode(null);
    }

    @Override
    public void setDnaBarcode(String dnaBarcode) {
        if (this.getSample() == null) {
            return;
        }
        getSample().setDnaBarcode(dnaBarcode);
    }

    @Override
    public String getDnaBarcode() {
        if (this.getSample() == null) {
            return null;
        }
        return getSample().getDnaBarcode();
    }

    @Override
    public boolean isSingleGene() {
        return isSingleGene;
    }

    @Override
    public void setSingleGene(boolean isSingleGene) {
        this.isSingleGene = isSingleGene;
    }

    @Override
    public void setHybridPercent(Double hybridPercent) {
        this.hybridPercent = hybridPercent;
    }

    @Override
    public Double getHybridPercent() {
        return hybridPercent;
    }

    @Override
    public void setManualMarker(String markerName, boolean isManual) {
        if (StringUtils.isBlank(markerName)) {
            return;
        }
        Marker marker = getMarkerByName(markerName);
        if (marker != null) {
            marker.setManual(isManual);
        }
    }

    @Override
    public void setManaulMarkers(List<String> markerNames, boolean isManual) {
        if (CollectionUtils.isEmpty(markerNames)) {
            return;
        }
        List<Marker> markerList = getMarkerByNameList(markerNames);
        if (CollectionUtils.isEmpty(markerList)) {
            return;
        }
        for (Marker marker : markerList) {
            if (marker != null) {
                marker.setManual(isManual);
            }
        }
    }

    @Override
    public List<String> getManualMarkerNames() {
        List<Marker> markers = this.getManualMarkers();
        if (CollectionUtils.isEmpty(markers)) {
            return null;
        }
        return new MarkerMapper(markers).getKeys();
    }

    @Override
    public void setManualMarkerNames(List<String> manualMarkerNames) {
        setManaulMarkers(manualMarkerNames, true);
    }

    @Override
    public List<Marker> getManualMarkers() {
        if (CollectionUtils.isEmpty(markers)) {
            return null;
        }
        return new ManualMarkerFilter<Marker>().filter(this.markers);
    }

    @Override
    public void clearAllManualTag() {
        List<String> markerNames = this.getMarkerNames();
        this.setManaulMarkers(markerNames, false);
    }

    @Override
    public boolean isManualGene() {
        return CollectionUtils.isNotEmpty(this.getManualMarkerNames());
    }

    @Override
    public boolean isManualMarker(String markerName) {
        Marker marker = this.getMarkerByName(markerName);
        return marker == null ? false : marker.isManual();
    }

    @Override
    public String getId() {
        return this.getGeneId();
    }

    @Override
    public void setId(String id) {
        this.setGeneId(id);
    }

    @Override
    public String getSamSpecies() {
        if (this.sample != null && StringUtils.isNotBlank(this.sample.getSamSpecies())) {
            return this.sample.getSamSpecies();
        }
        return null;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public void removeMarker(String primerName) {
        if (StringUtils.isBlank(primerName)) {
            return;
        }
        Marker marker = this.getMarkerByName(primerName);
        if (marker != null) {
            this.markers.remove(marker);
        }
    }

    @Override
    public void setMissMarkers(List<String> missMarkers) {
        this.missMarkers = missMarkers;
    }

    @Override
    public List<String> getMissMarkers() {
        return missMarkers;
    }

    @Override
    public void addMissMarker(String missMarker) {
        if (StringUtils.isBlank(missMarker)) {
            return;
        }
        if (this.missMarkers == null) {
            this.missMarkers.add(missMarker);
        }
        this.missMarkers.add(missMarker);
    }

    @Override
    public void setMarkerCount(int markerCount) {
        this.markerCount = markerCount;
    }

    @Override
    public int getMarkerCount() {
        return markerCount;
    }
}
