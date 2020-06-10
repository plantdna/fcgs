package com.pids.core.spliter;

import java.util.List;

/**
 * 列表类型分割器
 * @author Andory
 * @date 2013年9月21日下午10:16:32
 */
public interface ListSpliter<S, STEP> extends Spliter<List<S>, List<List<S>>, STEP> {
}
