package kd.mmi.curvechart.figs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kd.mmi.curvechart.beans.TimeAxisInfo;
import kd.mmi.curvechart.enums.FormatEnum;



/**
 * 时间轴.
 * @author LRY
 *
 */
public class TimeAxis extends Axis{

	/** */
	private static final long serialVersionUID = 1L;
	
	/** 坐标轴单位类型 */
	private int unitType = 2;
	
	public TimeAxis() {
		this(AxisPosition.BOTTOM);
	}

	public TimeAxis(AxisPosition position) {
		super(position);
	}
	
	
	@Override
	protected List<String> calculateTickLabels() {
		if(tickLabels == null || tickLabels.size() == 0) {
			double minValue = getMinValue();
			double maxValue = getMaxValue();
			double length = maxValue - minValue;
			double timeSpan = (int) (length / mainTickCount);
			double labelValue = minValue;
			String labelText = "";
			Date date = null;
			for(int i = 0; i <= mainTickCount;i++) {
				labelValue = minValue + (timeSpan * i);
				date = new Date((long) labelValue);
				labelText = format.format(date);
				if(i == mainTickCount && labelText.equals("00:00")){
					labelText = "24:00";
				}
				tickLabels.add(labelText);
			}
		}
		return tickLabels;
	}
	
	@Override
	public void setupAxisInfo() {
		super.setupAxisInfo();
		if(axisInfo != null && axisInfo.getClass().getName().equals(TimeAxisInfo.class.getName())) {
			TimeAxisInfo timeAxisInfo = (TimeAxisInfo) axisInfo;
			setFormat(FormatEnum.geTimeFormatEmnu(timeAxisInfo.getFormatType()).getFormat());
			int length = timeAxisInfo.getEnd() - timeAxisInfo.getBegin();
			int calendarField = setAxisUnitType(timeAxisInfo);
			long startTime = getStartTime(timeAxisInfo, calendarField, Calendar.getInstance());
			long endTime = getEndTime(startTime, calendarField, length);
			setMinValue(startTime);
			setMaxValue(endTime);
		
		}
	}
	
	private long getEndTime(long startTime, int calendarField, int length) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(startTime);
		calendar.set(calendarField, length);
		return calendar.getTimeInMillis();
		
	}
	
	public long getStartTime(Calendar calendar) {
		int calendarField = setAxisUnitType((TimeAxisInfo)axisInfo);
		return getStartTime((TimeAxisInfo)axisInfo, calendarField, calendar);
	}

	/**
	 * 设置坐标轴范围.
	 * @param timeAxisInfo
	 */
	private long getStartTime(TimeAxisInfo timeAxisInfo, int calendarField, Calendar nowCalendar) {
		Calendar startCalendar = Calendar.getInstance();
		//得到坐标轴相关属性
		int beginTime = timeAxisInfo.getBegin();
		int endTime = timeAxisInfo.getEnd();
		int intTime = timeAxisInfo.getIntTimeValue();
		int length = endTime - beginTime;
		boolean isInt = timeAxisInfo.isIntTime();
		
		//设置规整值
		intTime = intTime == 0 ? 1 : intTime; 
		intTime = timeAxisInfo.isIntTime()? intTime : 1;
		if(length < intTime) {
			intTime = 1;
		}
		
		/***************设置坐标轴起始时间*****************/
		//如果滚动
		if (((TimeAxisInfo)axisInfo).isRun()) {
			startCalendar.add(calendarField, beginTime);
		} else {
			startCalendar.set(calendarField, beginTime);
		}
		
		//取整
		if(isInt) {
			beginTime = startCalendar.get(calendarField);
			int intFieldValue = beginTime / intTime * intTime;
			if(intFieldValue + length < nowCalendar.get(calendarField)) {
				intFieldValue += intTime;
			}
			beginTime = beginTime <= intTime ? 0 : intFieldValue;
			startCalendar.set(calendarField, beginTime);
		}
		setAxisBeginTime(startCalendar, calendarField);

		/***************设置坐标轴起始时间*****************/
		
		return startCalendar.getTimeInMillis();
		
	}
	
	/**
	 * 设置起始坐标轴时间.
	 * @param calendarField
	 * @return
	 */
	private void setAxisBeginTime(Calendar startCalendar, int calendarField) {
		switch (calendarField) {
		case Calendar.YEAR:
			startCalendar.set(Calendar.MONTH, 0);
		case Calendar.MONTH:
			startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		case Calendar.DAY_OF_MONTH:
			startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		case Calendar.HOUR_OF_DAY:
			startCalendar.set(Calendar.MINUTE, 0);
		case Calendar.MINUTE:
			startCalendar.set(Calendar.SECOND, 0);
		case Calendar.SECOND:
			startCalendar.set(Calendar.MILLISECOND, 0);
		default:
			break;
		}
	}

	private int setAxisUnitType(TimeAxisInfo timeAxisInfo) {
		int unit = timeAxisInfo.getUnit();
		int unitType = Calendar.HOUR_OF_DAY;
		switch (unit) {
		case 0:
			unitType = Calendar.SECOND;
			break;
		case 1:
			unitType = Calendar.MINUTE;
			break;
		case 2:
			unitType = Calendar.HOUR_OF_DAY;
			break;
		case 3:
			unitType = Calendar.DAY_OF_MONTH;
			break;
		case 4:
			unitType = Calendar.WEEK_OF_MONTH;
			break;
		case 5:
			unitType = Calendar.MONTH;
			break;
		case 6:
			unitType = Calendar.YEAR;
			break;
		default:
			unitType = Calendar.HOUR_OF_DAY;
			break;
		}
		return unitType;
	}
	
	@Override
	public String format(Object obj) {
		String formatString = obj.toString();
		if(obj.getClass().getName().equals(Double.class.getName()) && format != null) {
			formatString = format.format(new Date(((Double)obj).longValue()));
		}
		return formatString;
	}
	
	public static void main(String[] args) {
		
		TimeAxisInfo axisInfo = new TimeAxisInfo();
		axisInfo.setBegin(0);
		axisInfo.setEnd(24);
		axisInfo.setFormatType(4);
		axisInfo.setIntTime(false);
		axisInfo.setIntTimeValue(4);
		axisInfo.setUnit(2);
		axisInfo.setRun(false);
		TimeAxis timeAxis = new TimeAxis();
		timeAxis.setAxisInfo(axisInfo);
		timeAxis.setMainTickCount(6);
		timeAxis.setSubTickCount(2);
		
	}

}
