package com.pids.core.checker.number;

import com.pids.core.checker.Checker;
import com.pids.core.exception.ICoreException;

/**
 * <pre>通用数字范围检测器，该较验器作用在于可自动检测左右两个边界，
 * 本类实现了三个类型数字数据的检测：Interger,Double,Float。
 * 且若边界未设置时则自动认为符合要求，这在多数情况下用于检测
 * 一些需要动态判定左右边界是否给定的情况，该实现类并未实现标准的
 * 检测接口{@link Checker}，只是依据检测接口的命名规范来实现相关功能，
 * 这种做法是合适的，因为标准接口适用于标准操作情况，对于一些
 * 可能使代码过于复杂的情况下，可以使用自定义接口或方法来做，
 * 但通常都需要参考标准方法的命名</pre>
 * @author jiangbin
 * @date 2012-11-6下午3:30:13
 */
public class NumberChecker implements NumberRangeChecker {
	private static final long serialVersionUID = 5512331619847718985L;
	private Number minValue;
	private Number maxValue;

	public NumberChecker() {
		super();
	}

	public NumberChecker(Number minValue, Number maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public boolean check(Number source) throws ICoreException {
		//无限定参数即值不需过滤
		if (this.maxValue == null && this.minValue == null) {
			return true;
		}
		//至少有一个限定参数但无值则认为未匹配上
		if (source == null) {
			return false;
		}
		//至少一个限定参数且有值
		if (source.getClass().isAssignableFrom(Integer.class)) {
			return this.checkInt(source.intValue());
		}
		if (source.getClass().isAssignableFrom(Double.class)) {
			return this.checkDouble(source.doubleValue());
		}
		if (source.getClass().isAssignableFrom(Float.class)) {
			return this.checkFloat(source.floatValue());
		}
		if (source.getClass().isAssignableFrom(Short.class)) {
			return this.checkShort(source.shortValue());
		}
		if (source.getClass().isAssignableFrom(Long.class)) {
			return this.checkLong(source.longValue());
		}
		if (source.getClass().isAssignableFrom(Byte.class)) {
			return this.checkByte(source.byteValue());
		}
		//数字类型不支持
		return false;
	}

	@Override
	public boolean checkByte(byte source) throws ICoreException {
		boolean rs0 = this.minValue == null;
		boolean rs1 = this.maxValue == null;
		boolean rs2 = rs0 || (!rs0 && minValue.byteValue() <= source);
		boolean rs3 = rs1 || (!rs1 && maxValue.byteValue() >= source);
		return rs2 && rs3;
	}

	@Override
	public boolean checkInt(int source) throws ICoreException {
		boolean rs0 = this.minValue == null;
		boolean rs1 = this.maxValue == null;
		boolean rs2 = rs0 || (!rs0 && minValue.intValue() <= source);
		boolean rs3 = rs1 || (!rs1 && maxValue.intValue() >= source);
		return rs2 && rs3;
	}

	@Override
	public boolean checkLong(long source) throws ICoreException {
		boolean rs0 = this.minValue == null;
		boolean rs1 = this.maxValue == null;
		boolean rs2 = rs0 || (!rs0 && minValue.longValue() <= source);
		boolean rs3 = rs1 || (!rs1 && maxValue.longValue() >= source);
		return rs2 && rs3;
	}

	@Override
	public boolean checkShort(short source) throws ICoreException {
		boolean rs0 = this.minValue == null;
		boolean rs1 = this.maxValue == null;
		boolean rs2 = rs0 || (!rs0 && minValue.shortValue() <= source);
		boolean rs3 = rs1 || (!rs1 && maxValue.shortValue() >= source);
		return rs2 && rs3;
	}

	@Override
	public boolean checkFloat(float source) throws ICoreException {
		boolean rs0 = this.minValue == null;
		boolean rs1 = this.maxValue == null;
		boolean rs2 = rs0 || (!rs0 && minValue.floatValue() <= source);
		boolean rs3 = rs1 || (!rs1 && maxValue.floatValue() >= source);
		return rs2 && rs3;
	}

	@Override
	public boolean checkDouble(double source) throws ICoreException {
		boolean rs0 = this.minValue == null;
		boolean rs1 = this.maxValue == null;
		boolean rs2 = rs0 || (!rs0 && minValue.doubleValue() <= source);
		boolean rs3 = rs1 || (!rs1 && maxValue.doubleValue() >= source);
		return rs2 && rs3;
	}

	@Override
	public Number getMinValue() {
		return minValue;
	}

	@Override
	public void setMinValue(Number minValue) {
		this.minValue = minValue;
	}

	@Override
	public Number getMaxValue() {
		return maxValue;
	}

	@Override
	public void setMaxValue(Number maxValue) {
		this.maxValue = maxValue;
	}

}
