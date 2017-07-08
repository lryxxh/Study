package curve.kd.mmi.curve.jfreechart.refresher;

import java.util.Calendar;

import agency.message.base.DomainManager;
import context.Context;

public class TimeCurveRefresherBean extends CurveRefresherBean implements Cloneable{
	
	String domainString = DomainManager.LOCAL_DOMAIN;
	String contextName = Context.REALTIME;
	String curveType = "1";
	long keyid = 0;
	int period = 60;
	long startTime = Calendar.getInstance().getTimeInMillis();
	long endTime = Calendar.getInstance().getTimeInMillis();
	int mode = 4;
	double length = 0;
	int refreshTime = 5;
	

	public TimeCurveRefresherBean(){}
	
	public TimeCurveRefresherBean(String domainString, String contextName,
			String curveType, long keyid, int period, long startTime,
			long endTime, int mode) {
		super();
		this.domainString = domainString;
		this.contextName = contextName;
		this.curveType = curveType;
		this.keyid = keyid;
		this.period = period;
		this.startTime = startTime;
		this.endTime = endTime;
		this.mode = mode;
	}
	
	public String getDomainString() {
		return domainString;
	}
	public void setDomainString(String domainString) {
		this.domainString = domainString;
	}
	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	public String getCurveType() {
		return curveType;
	}
	public void setCurveType(String curveType) {
		this.curveType = curveType;
	}
	public long getKeyid() {
		return keyid;
	}
	public void setKeyid(long keyid) {
		this.keyid = keyid;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public void setLength(double length) {
		this.length = length;
	}

	public double getLength() {
		return length;
	}
	
	public int getRefreshTime() {
		return refreshTime;
	}
	
	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}

	@Override
	public TimeCurveRefresherBean clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (TimeCurveRefresherBean) super.clone();
	}
}
