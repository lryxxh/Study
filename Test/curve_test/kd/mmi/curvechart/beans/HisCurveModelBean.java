package kd.mmi.curvechart.beans;

import java.util.Calendar;
import java.util.Date;

import kd.mmi.curvechart.engine.CustomUtility;
import kd.mmi.curvechart.figs.Axis;
import kd.mmi.curvechart.figs.TimeAxis;

/**
 * 关系库取数bean.
 * 
 * @author LRY
 * 
 */
public class HisCurveModelBean extends CurveModelBean {


	/** 取数方式 */
	private int mode = 4;

	/** 是否绝对时间 */
	private boolean abstime = false;

	/** 绝对时间间隔 */
	private int distance = 0;

	/** 绝对时间单位 */
	private int distanceUnit = 1;

	/** 开始年 */
	private int startYear = 0;

	/** 开始月 */
	private int startMonth = 0;

	/** 开始周 */
	private int startWeek = 0;

	/** 开始周天 */
	private int startWeekDay = 0;

	/** 开始日 */
	private int startDay = 0;

	/** 开始时 */
	private int startHour = 0;

	/** 开始分 */
	private int startMinute = 0;

	/** 开始秒 */
	private int startSecond = 0;

	/** 开始年 */
	private int endYear = 0;

	/** 开始月 */
	private int endMonth = 0;

	/** 结束周 */
	private int endWeek = 0;

	/** 结束周天 */
	private int endWeekDay = 0;

	/** 开始日 */
	private int endDay = 0;

	/** 开始时 */
	private int endHour = 0;

	/** 开始分 */
	private int endMinute = 0;

	/** 开始秒 */
	private int endSecond = 0;

	/** 采样间隔 */
	private int period = 60;

	/** 采样单位 */
	private int periodUnit = 4;

	/** 开始时间 */
	private long startTime = -1;

	/** 结束时间 */
	private long endTime = -1;

	public HisCurveModelBean(Axis axis) {
		super(axis);
	}
	
	/**
	 * get startYear value
	 * 
	 * @return the startYear
	 */
	public int getStartYear() {
		return startYear;
	}

	/**
	 * set startYear value
	 * 
	 * @param startYear
	 */
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	/**
	 * get startMonth value
	 * 
	 * @return the startMonth
	 */
	public int getStartMonth() {
		return startMonth;
	}

	/**
	 * set startMonth value
	 * 
	 * @param startMonth
	 */
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	/**
	 * get startWeek value
	 * 
	 * @return the startWeek
	 */
	public int getStartWeek() {
		return startWeek;
	}

	/**
	 * set startWeek value
	 * 
	 * @param startWeek
	 */
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}

	/**
	 * get startWeekDay value
	 * 
	 * @return the startWeekDay
	 */
	public int getStartWeekDay() {
		return startWeekDay;
	}

	/**
	 * set startWeekDay value
	 * 
	 * @param startWeekDay
	 */
	public void setStartWeekDay(int startWeekDay) {
		this.startWeekDay = startWeekDay;
	}

	/**
	 * get startDay value
	 * 
	 * @return the startDay
	 */
	public int getStartDay() {
		return startDay;
	}

	/**
	 * set startDay value
	 * 
	 * @param startDay
	 */
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	/**
	 * get startHour value
	 * 
	 * @return the startHour
	 */
	public int getStartHour() {
		return startHour;
	}

	/**
	 * set startHour value
	 * 
	 * @param startHour
	 */
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	/**
	 * get startMinute value
	 * 
	 * @return the startMinute
	 */
	public int getStartMinute() {
		return startMinute;
	}

	/**
	 * set startMinute value
	 * 
	 * @param startMinute
	 */
	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	/**
	 * get startSecond value
	 * 
	 * @return the startSecond
	 */
	public int getStartSecond() {
		return startSecond;
	}

	/**
	 * set startSecond value
	 * 
	 * @param startSecond
	 */
	public void setStartSecond(int startSecond) {
		this.startSecond = startSecond;
	}

	/**
	 * get endYear value
	 * 
	 * @return the endYear
	 */
	public int getEndYear() {
		return endYear;
	}

	/**
	 * set endYear value
	 * 
	 * @param endYear
	 */
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	/**
	 * get endMonth value
	 * 
	 * @return the endMonth
	 */
	public int getEndMonth() {
		return endMonth;
	}

	/**
	 * set endMonth value
	 * 
	 * @param endMonth
	 */
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	/**
	 * get endWeek value
	 * 
	 * @return the endWeek
	 */
	public int getEndWeek() {
		return endWeek;
	}

	/**
	 * set endWeek value
	 * 
	 * @param endWeek
	 */
	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}

	/**
	 * get endWeekDay value
	 * 
	 * @return the endWeekDay
	 */
	public int getEndWeekDay() {
		return endWeekDay;
	}

	/**
	 * set endWeekDay value
	 * 
	 * @param endWeekDay
	 */
	public void setEndWeekDay(int endWeekDay) {
		this.endWeekDay = endWeekDay;
	}

	/**
	 * get endDay value
	 * 
	 * @return the endDay
	 */
	public int getEndDay() {
		return endDay;
	}

	/**
	 * set endDay value
	 * 
	 * @param endDay
	 */
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	/**
	 * get endHour value
	 * 
	 * @return the endHour
	 */
	public int getEndHour() {
		return endHour;
	}

	/**
	 * set endHour value
	 * 
	 * @param endHour
	 */
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	/**
	 * get endMinute value
	 * 
	 * @return the endMinute
	 */
	public int getEndMinute() {
		return endMinute;
	}

	/**
	 * set endMinute value
	 * 
	 * @param endMinute
	 */
	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	/**
	 * get endSecond value
	 * 
	 * @return the endSecond
	 */
	public int getEndSecond() {
		return endSecond;
	}

	/**
	 * set endSecond value
	 * 
	 * @param endSecond
	 */
	public void setEndSecond(int endSecond) {
		this.endSecond = endSecond;
	}

	/**
	 * get query_unit value
	 * 
	 * @return the query_unit
	 */
	public int getPeriodUnit() {
		return periodUnit;
	}

	/**
	 * set query_unit value
	 * 
	 * @param query_unit
	 */
	public void setPeriodUnit(int query_unit) {
		this.periodUnit = query_unit;
	}

	/**
	 * get abstime value
	 * 
	 * @return the abstime
	 */
	public boolean isAbstime() {
		return abstime;
	}

	/**
	 * set abstime value
	 * 
	 * @param abstime
	 */
	public void setAbstime(boolean abstime) {
		this.abstime = abstime;
	}

	/**
	 * get distance value
	 * 
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * set distance value
	 * 
	 * @param distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * get distanceUnit value
	 * 
	 * @return the distanceUnit
	 */
	public int getDistanceUnit() {
		return distanceUnit;
	}

	/**
	 * set distanceUnit value
	 * 
	 * @param distanceUnit
	 */
	public void setDistanceUnit(int distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	/**
	 * get mode value
	 * 
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * set mode value
	 * 
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	/**
	 * get period value
	 * 
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * set period value
	 * 
	 * @param period
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * 得到开始时间.
	 * @return
	 */
	public long getStartTime() {
		if(abstime) {
			startTime = CustomUtility.convertTime(startYear, startMonth, startDay, startHour, startMinute, startSecond, startWeek, startWeekDay); 
		} else {
			startTime = ((TimeAxis)axis).getStartTime(Calendar.getInstance());
			startTime = CustomUtility.convertGivenRelativeDate(startTime, distance, distanceUnit);
		}
		return startTime;
	}

	/**
	 * 得到结束时间
	 * @return
	 */
	public long getEndTime() {
		if(abstime) {
			endTime = CustomUtility.convertTime(endYear, endMonth, endDay, endHour, endMinute, endSecond, endWeek, endWeekDay);
		} else {
			endTime = (long) (startTime + (axis.getMaxValue() - axis.getMinValue()));
		}
//		System.out.println(abstime + " " + new Date(startTime).toLocaleString() +" - " + new Date(endTime).toLocaleString());
		return endTime;
	}
}
