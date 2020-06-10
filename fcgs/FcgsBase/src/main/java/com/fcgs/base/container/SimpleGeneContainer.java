package com.fcgs.base.container;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.mapper.GeneInfoMapper;
import com.fcgs.base.mapper.GeneMapper;
import com.fcgs.base.mapper.GeneSamNumMapper;
import com.fcgs.base.mapper.MarkerMapper;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 指纹容器接口的简单实现类
 * @author Andory
 * @date 2013年9月13日上午12:38:53
 */
public class SimpleGeneContainer implements GeneContainer {
	private GeneMapper geneMapper;
	private GeneInfoMapper geneInfoMapper;
	private List<Gene> sources;
	private List<Gene> targets;

	/**
	 * 使用给定源和目标指纹列表构建指纹容器对象
	 * @param sources 源指纹数据
	 * @param targets 目标指纹数据
	 */
	public SimpleGeneContainer(List<Gene> sources, List<Gene> targets) {
		this();
		this.geneInfoMapper.addAll(sources);
		this.geneInfoMapper.addAll(targets);
		this.geneMapper.addAll(sources);
		this.geneMapper.addAll(targets);
		this.sources = sources;
		this.targets = targets;
	}

	public SimpleGeneContainer() {
		this.geneMapper = new GeneMapper();
		this.geneInfoMapper = new GeneInfoMapper();
	}

	@Override
	public GeneMapper getGeneMapper() {
		return geneMapper;
	}

	@Override
	public void setGeneMapper(GeneMapper geneMapper) {
		this.geneMapper = geneMapper;
	}

	@Override
	public GeneInfoMapper getGeneInfoMapper() {
		return geneInfoMapper;
	}

	@Override
	public void setGeneInfoMapper(GeneInfoMapper geneInfoMapper) {
		this.geneInfoMapper = geneInfoMapper;
	}

	/**
	 * 将给定的指纹列表添加到{@link GeneMapper}中
	 * @author Andory
	 * @param sources
	 * @date 2013年9月12日下午11:53:10
	 */
	@Override
	public void addGeneMapper(List<Gene> sources) {
		if (this.geneMapper == null) {
			this.geneMapper = new GeneMapper();
		}
		this.geneMapper.addAll(sources);
	}

	@Override
	public void addGeneMapper(Gene source) {
		if (this.geneMapper == null) {
			this.geneMapper = new GeneMapper();
		}
		this.geneMapper.add(source);
	}

	/**
	 * 将给定的指纹列表添加到{@link GeneInfoMapper}中
	 * @author Andory
	 * @param sources
	 * @date 2013年9月12日下午11:53:12
	 */
	@Override
	public void addGeneInfoMapper(List<Gene> sources) {
		if (this.geneInfoMapper == null) {
			this.geneInfoMapper = new GeneInfoMapper();
		}
		this.geneInfoMapper.addAll(sources);
	}

	@Override
	public void addGeneInfoMapper(Gene source) {
		if (this.geneInfoMapper == null) {
			this.geneInfoMapper = new GeneInfoMapper();
		}
		this.geneInfoMapper.add(source);
	}

	@Override
	public List<Gene> getSources() {
		return sources;
	}

	@Override
	public void setSources(List<Gene> sources) {
		this.sources = sources;
	}

	@Override
	public List<Gene> getTargets() {
		return targets;
	}

    @Override
    public Gene getTargetFirst() {
        return ListUtils.getFirst(this.targets);
    }

    @Override
	public void setTargets(List<Gene> targets) {
		this.targets = targets;
	}

	@Override
	public void addSource(Gene gene) {
		if (this.sources == null) {
			this.sources = new ArrayList<Gene>();
		}
		this.sources.add(gene);
	}

	@Override
	public void addSourcesList(List<Gene> genes) {
		if (this.sources == null) {
			this.sources = new ArrayList<Gene>();
		}
		this.sources.addAll(genes);
	}

    @Override
    public Gene getSourceFirst() {
        return ListUtils.getFirst(this.sources);
    }

    @Override
	public void addTarget(Gene gene) {
		if (this.targets == null) {
			this.targets = new ArrayList<Gene>();
		}
		this.targets.add(gene);
	}

	@Override
	public void addTargetsList(List<Gene> genes) {
		if (this.targets == null) {
			this.targets = new ArrayList<Gene>();
		}
		this.targets.addAll(genes);
	}

	@Override
	public Gene getGeneInfo(String geneId) {
		return this.geneInfoMapper.get(geneId);
	}

	@Override
	public MarkerMapper getMarkerMapper(String geneId) {
		return this.geneMapper.get(geneId);
	}

	@Override
	public List<Gene> getAllGenes() {
		GeneInfoMapper mapper = getSourceGeneInfoMapper();
		mapper.addAll(targets);
		return mapper.getValues();
	}

	@Override
	public GeneInfoMapper getSourceGeneInfoMapper() {
		return GeneInfoMapper.createMapper(sources);
	}

	@Override
	public GeneInfoMapper getTargetGeneInfoMapper() {
		return GeneInfoMapper.createMapper(targets);
	}

	@Override
	public List<String> getSourceSamBarcodes() {
		GeneSamNumMapper mapper = new GeneSamNumMapper();
		mapper.addAll(this.sources);
		return mapper.getKeys();
	}

	@Override
	public List<String> getTargetSamBarcodes() {
		GeneSamNumMapper mapper = new GeneSamNumMapper();
		mapper.addAll(this.targets);
		return mapper.getKeys();
	}

	@Override
	public List<String> getAllSamBarcodes() {
		GeneSamNumMapper mapper = new GeneSamNumMapper();
		mapper.addAll(this.sources);
		mapper.addAll(this.targets);
		return mapper.getKeys();
	}

	@Override
	public boolean isEmpty() {
		return isSourceEmpty() && isTargetEmpty();
	}

	@Override
	public int size() {
		return ListUtils.size(this.getAllGenes());
	}

	@Override
	public boolean isSourceEmpty() {
		return CollectionUtils.isEmpty(sources);
	}

	@Override
	public boolean isTargetEmpty() {
		return CollectionUtils.isEmpty(targets);
	}

	@Override
	public int getSourceCount() {
		return ListUtils.size(sources);
	}

	@Override
	public int getTargetCount() {
		return ListUtils.size(targets);
	}

}
