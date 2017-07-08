package org.jfree.data.xy.custom;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

//import org.jfree.data.xy.XYItem;
import org.jfree.data.xy.XYSeries;

import agency.message.base.DataUnit;
import agency.message.base.KeyID;
import curve.kd.mmi.curve.jfreechart.refresher.CurveRefresherBean;
import curve.kd.mmi.curve.jfreechart.refresher.NumberCurveRefresherBean;
import curve.kd.mmi.curve.jfreechart.refresher.RefreshObservable;
import curve.kd.mmi.curvechart.graphics.glanguage.GCurveConvertFromStr;

@SuppressWarnings("rawtypes") 
public class CustomXYSeries extends XYSeries implements Observer{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3737462911782412978L;
	
	private double initStartXValue = 0;
	
	private RefreshObservable observable = null;
	private NumberCurveRefresherBean bean = null;
	public CustomXYSeries(Comparable key) {
		super(key, true , false);
	}

	
	
	@Override
	public void update(Observable o, Object arg) {
		Vector<Vector<DataUnit>> xdata = null;
		Vector<Vector<DataUnit>> ydata = null;
		Vector data = (Vector) arg;
		if (data.size() == 1) {
			ydata = (Vector) data.get(0);
		} else {
			xdata = (Vector) data.get(0);
			ydata = (Vector) data.get(1);
		}
		
		int point_mode = bean.getPoint_mode();
		long ykeyID = bean.getY_keyid();
	
		int appID = bean.getAppName();
		int point = bean.getPoint();
		int y_pace = bean.getY_pace();
		
		KeyID y_keyID = new KeyID(ykeyID);
		
		int y_beginRow = y_keyID.getRecordID() - 1;// y轴起始记录数
		int y_endRow = y_beginRow + point < ydata.size() ? y_beginRow + point : ydata.size();
		if(y_endRow < y_beginRow){
			System.err.println("y 轴"+ykeyID+" keyid选择出现错误，造成结束记录行小余开始记录行");
		}
		if(point_mode == GCurveConvertFromStr.DYNAMIC_POINT_MODE_1 || point_mode == GCurveConvertFromStr.DYNAMIC_POINT_MODE_2){
			if (xdata != null) {
				long xkeyID = bean.getX_keyid();
				int x_pace = bean.getX_pace();
				KeyID x_keyID = new KeyID(xkeyID);
				int x_beginRow = x_keyID.getRecordID() - 1;// x轴起始记录数
				int x_endRow = x_beginRow + point < xdata.size() ? x_beginRow + point : xdata.size();
				if(x_endRow < x_beginRow){
					System.err.println("x 轴"+xkeyID+" keyid选择出现错误，造成结束记录行小余开始记录行");
				}
				for (int i = 0, j = 0; i < xdata.size() && j < ydata.size();) {
					Vector<DataUnit> x_DataUnit = ydata.get(i);
					Vector<DataUnit> y_DataUnit = ydata.get(j);
					float xValue = (float) x_DataUnit.get(0).getDoubleData();
					float yValue = (float) y_DataUnit.get(0).getDoubleData();
					if (yValue != 999999f) {
						addOrUpdate(xValue, yValue);
					}
					i = i + x_pace;
					j = j + y_pace;
				}
			}
		}else{
			if (xdata == null) {
				for (int i = y_beginRow; i < y_endRow;) {
					Vector<DataUnit> y_DataUnit = ydata.get(i);
					float yValue = (float) y_DataUnit.get(0).getDoubleData();
					if (yValue != 999999f) {
						addOrUpdate(i, yValue);
					}
					i = i + y_pace;
				}
				
			}
		}
	}
	
	int count = 50;
	public void setRefresherBean(CurveRefresherBean bean) {
		this.bean = (NumberCurveRefresherBean) bean;
		observable = new RefreshObservable(bean);
    	observable.addObserver(this);
	}
	
	public Observer getObserver() {
		return observable;
	}
	
	public void setInitStartXValue(double xvalue){
		this.initStartXValue = xvalue;
	}
	
	public double getInitStartXValue() {
		return this.initStartXValue;
	}

}
