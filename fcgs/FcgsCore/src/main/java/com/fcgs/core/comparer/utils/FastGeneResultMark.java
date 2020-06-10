package com.fcgs.core.comparer.utils;

import com.fcgs.core.comparer.domain.FastGeneResultSet;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <pre>
 * 快速比对结果标记功能，该功能主要提供一种快速标记无效数据的功能，
 * 其根据待比和对比指纹ID列表的不同情况采用对应的标记算法来提高性能：
 * 1、待比列表与对比列表完成相同：标记一半
 * 2、待比列表与对比列表完全不同：不需要标记
 * 3、待比列表与对比列表存在交叉:只标记重复对的部分
 * </pre>
 *
 * @author jiangbin
 * @date 2019/11/26 2:16 下午
 **/

public class FastGeneResultMark {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 标记无效数据点
	 *
	 * @param result
	 * @return void
	 * @author jiangbin
	 * @date 2019/11/26 3:07 下午
	 **/
	public void mark(FastGeneResultSet result) {
		StopWatch watch = new StopWatch();
		watch.start();

		try {
			List<String> sIdList = result.getsIdList();
			List<String> tIdList = result.gettIdList();

			// 完成相同
			if (ListUtils.isEquals(sIdList, tIdList)) {
				markSames(result);
				return;
			}

			// 获取重复ID号列表
			List<String> rIdList = ListUtils.getRepeats(sIdList, tIdList);

			// 存在交叉部分
			if (CollectionUtils.isNotEmpty(rIdList)) {
				mark(result, rIdList);
			}
		} finally {
			watch.stop();
			log.info("标记快速比对结果耗时(ms)==>" + watch.getTime());
		}
	}

	/**
	 * <pre>
	 * 存在交叉性数据时，标记：
	 * 1、标记A==A数据
	 * 2、标记A=B与B=A中的一个数据
	 * </pre>
	 *
	 * @param result  比对结果集
	 * @param rIdList 重复的ID列表
	 * @return void
	 * @author jiangbin
	 * @date 2019/11/26 2:42 下午
	 **/
	private void mark(FastGeneResultSet result, List<String> rIdList) {
		for (int i = 0; i < rIdList.size(); i++) {
			int row = result.getsPools().getIndex(rIdList.get(i));
			for (int j = i; j < rIdList.size(); j++) {
				int col = result.gettPools().getIndex(rIdList.get(j));
				result.set(row, col, FastGeneResultSet.INVALID);
			}
		}
	}

	/**
	 * 待比和对比列表完成相同
	 *
	 * @param result 比对结果集
	 * @return void
	 * @author jiangbin
	 * @date 2019/11/26 2:29 下午
	 **/
	private void markSames(FastGeneResultSet result) {
		for (int row = 0; row < result.getRow(); row++) {
			result.set(row, row, FastGeneResultSet.SAME_ID);
			for (int col = row + 1; col < result.getCol(); col++) {
				result.set(row, col, FastGeneResultSet.INVALID);
			}
		}
	}

}
