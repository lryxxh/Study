package org.jfree.data.xy.custom;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.TimeZone;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.TimeSeriesItem;

import agency.Debug;
import agency.message.midhs.MidhsData;
import curve.kd.mmi.curve.jfreechart.refresher.CurveRefresherBean;
import curve.kd.mmi.curve.jfreechart.refresher.RefreshObservable;
import curve.kd.mmi.curve.jfreechart.refresher.TimeCurveRefresherBean;

@SuppressWarnings("rawtypes") 
public class CustomTimeSeries extends TimeSeries implements Observer{
	public static final TimeZone ZONE = TimeZone.getDefault();

	/**
	 * 
	 */
	private static final long serialVersionUID = 4518815829125402812L;
	RefreshObservable observable = null;
	private double initStartXValue = 0;
	private TimeCurveRefresherBean bean = null;

	public CustomTimeSeries(Comparable key) {
		super(key);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		List data = (List) arg;
		for (int i = 2; i < data.size(); i++) {
			MidhsData curveData = (MidhsData) data.get(i);
			if (Debug.DEBUG_RECEIVE_MIDHS_CURVE) {
				System.err
						.println(curveData.getValidFlag() + "  "
								+ new Date(curveData.getXValue()) + "  "
								+ curveData.getYValue() + "  "
								+ curveData.getQuality());
			}
			int isNull = curveData.getValidFlag();
			if (isNull == 0) {
				float yValue = curveData.getYValue();
				long xValue = curveData.getXValue();
				int quality = curveData.getQuality();
				RegularTimePeriod timePeriod = RegularTimePeriod.createInstance(getTimePeriodClass(), new Date(xValue), ZONE);
				TimeSeriesItem item = new TimeSeriesItem(timePeriod, yValue, quality);
				addOrUpdate(item);
			}
		}
	}
	
	
	public void setRefresherBean(CurveRefresherBean bean) {
		this.bean = (TimeCurveRefresherBean) bean;
		observable = new RefreshObservable(bean);
    	observable.addObserver(this);
	}
	
//	public void refresherObservableStart() {
//		observable.start();
//	}
	
	public Observer getObserver() {
		return observable;
	}
	
	public void setTimePeriodClass(Class className) {
		timePeriodClass = className;
	}
	
	public void setInitStartXValue(double xvalue){
		this.initStartXValue = xvalue;
	}
	
	public double getInitStartXValue() {
		return this.initStartXValue;
	}
	
	public TimeCurveRefresherBean getRefresherBean() {
		return (TimeCurveRefresherBean) this.bean;
	}
}