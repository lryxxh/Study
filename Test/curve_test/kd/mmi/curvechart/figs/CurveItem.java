package kd.mmi.curvechart.figs;

import java.io.Serializable;
import java.util.Date;

/**
 * ���ߵ����.
 * @author LRY
 *
 */
public class CurveItem implements Serializable, Comparable<CurveItem>{
	
	/** */
	private static final long serialVersionUID = 1L;

	/** ���ߵ�xֵ*/
	protected double xValue;
	
	/** ���ߵ�yֵ*/
	protected double yValue;

	/**����λ */
	private int quality;

	/** �Ƿ���Ч*/
	private int isValid;
	
	public void setXValue(double xValue) {
		this.xValue = xValue;
	}
	
	public double getXValue() {
		return xValue;
	}
	
	public void setYValue(double yValue) {
		this.yValue = yValue;
	}
	
	public double getYValue() {
		return yValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if(obj.getClass().getName().equals(getClass().getName())) {
			double offset = this.xValue/1000 - ((CurveItem)(obj)).getXValue()/1000;
			flag = offset == 0 ? true : false;
		}
		return flag;
	}
	
	/**
	 * get quality value
	 * @return the quality
	 */
	public int getQuality() {
		return quality;
	}

	/**
	 * set quality value
	 * @param quality 
	 */
	public void setQuality(int quality) {
		this.quality = quality;
	}

	/**
	 * get isValid value
	 * @return the isValid
	 */
	public int getIsValid() {
		return isValid;
	}

	/**
	 * set isValid value
	 * @param isValid 
	 */
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	@Override
	public int hashCode() {
		return Double.valueOf(xValue).hashCode();
	}
	
	@Override
	public int compareTo(CurveItem o) {
		return (int) (getXValue() - o.getXValue());
	}

	@Override
	public String toString() {
		return new Date((long)xValue).toLocaleString() + " : " + yValue;
	}
	
}
