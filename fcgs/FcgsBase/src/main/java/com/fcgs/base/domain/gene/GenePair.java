package com.fcgs.base.domain.gene;

import com.pids.core.pair.SimplePair;
import com.pids.core.utils.ListUtils;

import java.util.List;

/**
 * 指纹对
 *
 * @author Andory
 * @date 2013年9月14日下午6:25:24
 */
public class GenePair extends SimplePair<Gene, Gene> {

    private static final long serialVersionUID = 3465977743813828448L;

    public GenePair() {
    }

    public GenePair(Gene source, Gene target) {
        this.setSource(source);
        this.setTarget(target);
    }

    /**
     * 转换成指纹列表
     *
     * @return
     * @author jiangbin
     * @date 2019年6月12日下午3:00:53
     */
    public List<Gene> toList() {
        return ListUtils.createList(getSource(), getTarget());
    }

    /**
     * 是否为空，即不包含待比或对比指纹数据
     *
     * @return boolean
     * @author jiangbin
     * @date 2019-08-19 15:00
     **/
    public boolean isEmpty() {
        return this.getSource() == null || this.getTarget() == null || this.getSource().isEmpty() || this.getTarget().isEmpty();
    }
}
