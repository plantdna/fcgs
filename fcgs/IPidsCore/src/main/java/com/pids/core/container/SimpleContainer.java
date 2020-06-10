package com.pids.core.container;

import com.pids.core.id.ID;
import com.pids.core.id.utils.IdFilter;
import com.pids.core.id.utils.IdMapper;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器接口实现类
 */
@SuppressWarnings("serial")
public class SimpleContainer<S extends ID> implements Container<S> {
	private List<S> sourceList;

	@Override
	public void setSourceList(List<S> sourceList) {
		this.sourceList = sourceList;
	}

	@Override
	public List<S> getSourceList() {
		return sourceList;
	}

	@Override
	public int size() {
		return ListUtils.size(sourceList);
	}

	@Override
	public void add(S dna) {
		if (dna == null) {
			return;
		}
		if (this.sourceList == null) {
			this.sourceList = new ArrayList<>();
		}
		this.sourceList.add(dna);
	}

	@Override
	public void add(List<S> sourceList) {
		if (CollectionUtils.isEmpty(sourceList)) {
			return;
		}
		if (this.sourceList == null) {
			this.sourceList = new ArrayList<>();
		}
		this.sourceList.addAll(sourceList);
	}

	@Override
	public void clear() {
		if (this.sourceList != null) {
			this.sourceList.clear();
		}
	}

	@Override
	public List<String> getIdList() {
		return new IdFilter<S>().filter(this.sourceList);
	}

	@Override
	public boolean exists(String id) {
		return this.getById(id) != null;
	}

	@Override
	public S getById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		IdMapper<S> mapper = new IdMapper<>();
		mapper.addAll(this.sourceList);
		return mapper.get(id);
	}

	@Override
	public List<S> getById(List<String> idList) {
		if (CollectionUtils.isEmpty(idList)) {
			return null;
		}
		IdMapper<S> mapper = new IdMapper<>();
		mapper.addAll(this.sourceList);
		return mapper.getValues(idList);
	}
}
