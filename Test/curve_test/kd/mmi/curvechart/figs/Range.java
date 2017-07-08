package kd.mmi.curvechart.figs;

/**
 * 
 * 范围.
 * @author LRY
 *
 */
public class Range {
	
	/** 最小值 */
	protected double minValue = 0;
	
	/** 最大值*/
	protected double maxValue = 120000;

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	
	/**
	 * 设置范围
	 * @param minValue
	 * @param maxValue
	 */
	public void setRange(double minValue, double maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	/**
	 * 设置范围.
	 * @param range
	 */
	public void setRange(Range range) {
		this.minValue = range.getMinValue();
		this.maxValue = range.maxValue;
	}

}
