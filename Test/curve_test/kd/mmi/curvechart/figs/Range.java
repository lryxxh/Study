package kd.mmi.curvechart.figs;

/**
 * 
 * ��Χ.
 * @author LRY
 *
 */
public class Range {
	
	/** ��Сֵ */
	protected double minValue = 0;
	
	/** ���ֵ*/
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
	 * ���÷�Χ
	 * @param minValue
	 * @param maxValue
	 */
	public void setRange(double minValue, double maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	/**
	 * ���÷�Χ.
	 * @param range
	 */
	public void setRange(Range range) {
		this.minValue = range.getMinValue();
		this.maxValue = range.maxValue;
	}

}
