package kd.mmi.curvechart.beans;

/**
 * 坐标轴基本信息.
 * @author LRY
 *
 */
public class AxisInfo {
	
	/** 坐标轴开始值 */
	protected int begin = 0;
	
	/** 坐标轴结束值*/
	protected int end = 24;
	
	/** 类型*/
	protected int type = 1;
	
	/** 数据精度 */
	protected int decimal = 2;
	
	/**
	 * get begin value
	 * @return the begin
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * set begin value
	 * @param begin 
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * get end value
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * set end value
	 * @param end 
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * get type value
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * set type value
	 * @param type 
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * get decimal value
	 * @return the decimal
	 */
	public int getDecimal() {
		return decimal;
	}

	/**
	 * set decimal value
	 * @param decimal 
	 */
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}
	
}
