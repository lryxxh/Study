package kd.mmi.curvechart.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kd.mmi.curvechart.figs.CurveItem;

public class SeriesModel {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	
	/** ��������*/
	private List<CurveItem> itemDatas = new ArrayList<CurveItem>();
	
	/** ��Сֵ*/
	private double minValue = Double.MAX_VALUE;
	
	/** ���ֵ*/
	private double maxValue = Double.MIN_VALUE;
	
	/** ��Сֵ��Ӧ��xֵ*/
	private double minValue2X = 0;
	
	/** ���ֵ��Ӧ��xֵ*/
	private double maxValue2X = 0;
	
	/**
	 * ������ߵ�.
	 * @param item
	 */
	public void addCurveItem(CurveItem item) {
		itemDatas.add(item);
		setMinMaxValue(item);
	}
	

	/**
	 * ��ӻ��������.
	 * @param item
	 */
	public void addOrUpdateItem(CurveItem item) {
		//������ݲ�����,�����
		if(!itemDatas.contains(item)) {
			itemDatas.add(item);
		} else {
			int index = itemDatas.indexOf(item);
			itemDatas.get(index).setYValue(item.getYValue());
		}
		setMinMaxValue(item);

	}
	
	/**
	 * ���������Сֵ.
	 * @param item
	 */
	private void setMinMaxValue(CurveItem item) {
		double xValue = item.getXValue();
		double yValue = item.getYValue();
		if(yValue < minValue) {
			support.firePropertyChange("minValue", minValue, yValue);
			minValue = yValue;
			minValue2X = xValue;
		}
		
		if(yValue > maxValue) {
			support.firePropertyChange("maxValue", maxValue, yValue);
			maxValue = yValue;
			maxValue2X = xValue;
		}
	}
	
	/**
	 * ������Сֵ.
	 * @param minValue
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	/**
	 * �õ����ֵ.
	 * @return
	 */
	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * �������ֵ.
	 * @param maxValue
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * �õ���Сֵ��Ӧ��Xֵ.
	 * @return
	 */
	public double getMinValue2X() {
		return minValue2X;
	}

	/**
	 * �������ֵ��Ӧ��Xֵ.
	 * @param minValue2X
	 */
	public void setMinValue2X(double minValue2X) {
		this.minValue2X = minValue2X;
	}

	/**
	 * �õ����ֵ��Ӧ��xֵ.
	 * @return
	 */
	public double getMaxValue2X() {
		return maxValue2X;
	}

	/**
	 * �������ֵ��Ӧ��xֵ.
	 * @param maxValue2X
	 */
	public void setMaxValue2X(double maxValue2X) {
		this.maxValue2X = maxValue2X;
	}
	
	/**
	 * �õ��������ݵ�.
	 * @return
	 */
	public List<CurveItem> getItemDatas() {
		return itemDatas;
	}
	
	/**
	 * ���ֲ鳭��.
	 * @param xValue
	 * @return
	 */
	public CurveItem binarySearchByXValue(double xValue) {
		int index = 0;
		CurveItem item = new CurveItem();
		item.setXValue(xValue);
		
		index = Collections.binarySearch(getItemDatas(), item);
		if(index < 0) {
			index = Math.abs(index) - 1;
			if(index == getItemDatas().size()) {
				index -= 1;
			}
		}
		item = getItemDatas().get(Math.abs(index));
		return item;
	}

	
	/**
	 * ��Ӽ�����.
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}


	public void fireRepaint() {
		support.firePropertyChange("repaint", false, true);
	}


	public void addRepaintListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}


	/**
	 * get minValue value
	 * @return the minValue
	 */
	public double getMinValue() {
		return minValue;
	}
	
}
