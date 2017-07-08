package org.jfree.data.xy;

import org.jfree.data.xy.XYDataItem;

/**
 * Ϊ����������״̬.
 * @author ������
 *
 */
public class XYItem extends XYDataItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4843986084313644042L;

	/**
	 * ����λ.
	 */
	private int quality = 0;
	
	/**
	 * ���캯��.
	 * @param x
	 * @param y
	 * @param quality
	 */
	public XYItem(Number x, Number y, int quality) {
		super(x, y);
		this.quality = quality;
	}

	/**
	 * ���캯��.
	 * @param x
	 * @param y
	 * @param quality
	 */
	public XYItem(double x, double y, int quality) {
		super(x, y);
		this.quality = quality;
	}
	
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

}
