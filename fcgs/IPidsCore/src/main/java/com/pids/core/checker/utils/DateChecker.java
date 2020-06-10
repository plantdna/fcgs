package com.pids.core.checker.utils;

import com.pids.core.checker.Checker;
import com.pids.core.exception.ICoreException;

import java.util.Date;

/**
 * <pre>日期范围检测器，该较验器作用在于可自动检测左右两个边界，
 * 且若边界未设置时则自动认为符合要求，这在多数情况下用于检测
 * 一些需要动态判定左右边界是否给定的情况，该实现类并未实现标准的
 * 检测接口{@link Checker}，只是依据检测接口的命名规范来实现相关功能，
 * 这种做法是合适的，因为标准接口适用于标准操作情况，对于一些
 * 可能使代码过于复杂的情况下，可以使用自定义接口或方法来做，
 * 但通常都需要参考标准方法的命名</pre>
 * @author jiangbin
 * @date 2012-11-6下午3:30:13
 */
public class DateChecker implements Checker<Date, Boolean> {
	private Date star;
	private Date end;

	public DateChecker() {
		super();
	}

	public DateChecker(Date star) {
		super();
		this.star = star;
	}

	public DateChecker(Date star, Date end) {
		super();
		this.star = star;
		this.end = end;
	}

	@Override
	public Boolean check(Date source) throws ICoreException {
		boolean rs0 = this.star == null;
		boolean rs1 = this.end == null;
		boolean rs2 = rs0 || (!rs0 && star.before(source));
		boolean rs3 = rs1 || (!rs1 && end.after(source));
		return rs2 && rs3;
	}

	/**
	 * 检查今天是否在给定时间范围内
	 * @author jiangbin
	 * @return
	 * @throws ICoreException
	 * @date 2016年7月1日上午10:25:39
	 */
	public boolean check() throws ICoreException {
		return this.check(new Date());
	}

	/**
	 * 获取日期区间起始值
	 * @author jiangbin
	 * @return
	 * @date 2016年6月29日下午2:53:47
	 */
	public Date getStar() {
		return star;
	}

	/**
	 * 设置日期区间起始值
	 * @author jiangbin
	 * @param star
	 * @date 2016年6月29日下午2:53:45
	 */
	public void setStar(Date star) {
		this.star = star;
	}

	/**
	 * 获取日期区间结束值
	 * @author jiangbin
	 * @return
	 * @date 2016年6月29日下午2:53:43
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * 设置日期区间结束值
	 * @author jiangbin
	 * @param end
	 * @date 2016年6月29日下午2:53:42
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

}
