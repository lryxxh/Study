package org.jfree.data.xy;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeriesDataItem;

/**
 * ���Ƶ�ʱ����.��������λ.
 * @author ������
 *
 */
public class TimeSeriesItem extends TimeSeriesDataItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4020521261937682138L;
	/**
	 * ����λ.
	 */
	private int quality = 0;
	
	/**
	 * ���캯��.
	 * @param period
	 * @param value
	 * @param quality
	 */
	public TimeSeriesItem(RegularTimePeriod period, double value, int quality) {
		super(period, value);
		this.quality = quality;
	}
	
	/**
	 * ���캯��.
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
