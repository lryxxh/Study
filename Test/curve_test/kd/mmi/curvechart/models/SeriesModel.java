package kd.mmi.curvechart.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kd.mmi.curvechart.figs.CurveItem;

public class SeriesModel {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	
	/** 曲线数据*/
	private List<CurveItem> itemDatas = new ArrayList<CurveItem>();
	
	/** 最小值*/
	private double minValue = Double.MAX_VALUE;
	
	/** 最大值*/
	private double maxValue = Double.MIN_VALUE;
	
	/** 最小值对应的x值*/
	private double minValue2X = 0;
	
	/** 最大值对应的x值*/
	private double maxValue2X = 0;
	
	/**
	 * 添加曲线点.
	 * @param item
	 */
	public void addCurveItem(CurveItem item) {
		itemDatas.add(item);
		setMinMaxValue(item);
	}
	

	/**
	 * 添加或更新数据.
	 * @param item
	 */
	public void addOrUpdateItem(CurveItem item) {
		//如果数据不存在,则添加
		if(!itemDatas.contains(item)) {
			itemDatas.add(item);
		} else {
			int index = itemDatas.indexOf(item);
			itemDatas.get(index).setYValue(item.getYValue());
		}
		setMinMaxValue(item);

	}
	
	/**
	 * 设置最大最小值.
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
	 * 设置最小值.
	 * @param minValue
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	/**
	 * 得到最大值.
	 * @return
	 */
	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * 设置最大值.
	 * @param maxValue
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * 得到最小值对应的X值.
	 * @return
	 */
	public double getMinValue2X() {
		return minValue2X;
	}

	/**
	 * 设置最大值对应的X值.
	 * @param minValue2X
	 */
	public void setMinValue2X(double minValue2X) {
		this.minValue2X = minValue2X;
	}

	/**
	 * 得到最大值对应的x值.
	 * @return
	 */
	public double getMaxValue2X() {
		return maxValue2X;
	}

	/**
	 * 设置最大值对应的x值.
	 * @param maxValue2X
	 */
	public void setMaxValue2X(double maxValue2X) {
		this.maxValue2X = maxValue2X;
	}
	
	/**
	 * 得到所有数据点.
	 * @return
	 */
	public List<CurveItem> getItemDatas() {
		return itemDatas;
	}
	
	/**
	 * 二分查抄点.
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
	 * 添加监听器.
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
