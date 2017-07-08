package kd.mmi.curvechart.beans;

/**
 * 时间轴信息.
 * @author LRY
 *
 */
public class TimeAxisInfo extends AxisInfo{
	
	/** 时间轴是否跑动*/
	private boolean isRun = false;
	
	/** 跑动步长 */
	private int runPace = 4;
	
	/** 跑动单位*/
	private int runUnit = 2;
	
	/** 坐标轴单位 */
	private int unit = 2;
	
	/** 是否规整 */
	private boolean isIntTime = true;
	
	/** 规整值*/
	private int intTimeValue = 4;
	
	/** 格式化方式*/
	private int formatType = 4;

	/**
	 * get isRun value
	 * @return the isRun
	 */
	public boolean isRun() {
		return isRun;
	}

	/**
	 * set isRun value
	 * @param isRun 
	 */
	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	/**
	 * get runPace value
	 * @return the runPace
	 */
	public int getRunPace() {
		return runPace;
	}

	/**
	 * set runPace value
	 * @param runPace 
	 */
	public void setRunPace(int runPace) {
		this.runPace = runPace;
	}

	/**
	 * get runUnit value
	 * @return the runUnit
	 */
	public int getRunUnit() {
		return runUnit;
	}

	/**
	 * set runUnit value
	 * @param runUnit 
	 */
	public void setRunUnit(int runUnit) {
		this.runUnit = runUnit;
	}

	/**
	 * get unit value
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * set unit value
	 * @param unit 
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}

	/**
	 * get isIntTime value
	 * @return the isIntTime
	 */
	public boolean isIntTime() {
		return isIntTime;
	}

	/**
	 * set isIntTime value
	 * @param isIntTime 
	 */
	public void setIntTime(boolean isIntTime) {
		this.isIntTime = isIntTime;
	}

	/**
	 * get intTimeValue value
	 * @return the intTimeValue
	 */
	public int getIntTimeValue() {
		return intTimeValue;
	}

	/**
	 * set intTimeValue value
	 * @param intTimeValue 
	 */
	public void setIntTimeValue(int intTimeValue) {
		this.intTimeValue = intTimeValue;
	}

	/**
	 * get formatType value
	 * @return the formatType
	 */
	public int getFormatType() {
		return formatType;
	}

	/**
	 * set formatType value
	 * @param formatType 
	 */
	public void setFormatType(int formatType) {
		this.formatType = formatType;
	}


}
