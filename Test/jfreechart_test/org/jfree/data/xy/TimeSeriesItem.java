package org.jfree.data.xy;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeriesDataItem;

/**
 * 客制的时间项.增加质量位.
 * @author 刘仁勇
 *
 */
public class TimeSeriesItem extends TimeSeriesDataItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4020521261937682138L;
	/**
	 * 质量位.
	 */
	private int quality = 0;
	
	/**
	 * 构造函数.
	 * @param period
	 * @param value
	 * @param quality
	 */
	public TimeSeriesItem(RegularTimePeriod period, double value, int quality) {
		super(period, value);
		this.quality = quality;
	}
	
	/**
	 * 构造函数.
	 * @param period
	 * @param value
	 */
	public TimeSeriesItem(RegularTimePeriod period, Number value) {
		 super(period, value);
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	
}
