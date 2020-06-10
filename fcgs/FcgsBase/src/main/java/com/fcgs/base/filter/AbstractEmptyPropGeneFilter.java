package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 指纹信息对象空属性值的指纹过滤器，用来过滤出某些属性值未指定的指纹信息对象
 * @author Andory
 * @date 2013-6-26下午6:26:53
 */
public abstract class AbstractEmptyPropGeneFilter extends ListFilterTemplate<Gene, Gene> {

}
