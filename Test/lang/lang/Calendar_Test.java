package lang;
import java.util.Calendar;

import kd.mmi.curvechart.beans.TimeAxisInfo;


public class Calendar_Test {
	
	public static void main(String[] args) {
		TimeAxisInfo info = new TimeAxisInfo();
		info.setBegin(-3);
		info.setEnd(3);
		info.setDecimal(2);
		info.setFormatType(4);
		info.setIntTime(true);
		info.setIntTimeValue(4);
		info.setRun(true);
		info.setRunPace(3);
		info.setRunUnit(2);
		info.setType(1);
		info.setUnit(6);
		setTimeAxisRange(info);
		
	}
	
	/**
	 * ���������᷶Χ.
	 * @param timeAxisInfo
	 */
	private static void setTimeAxisRange(TimeAxisInfo timeAxisInfo) {
		Calendar nowcalendar = Calendar.getInstance();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = (Calendar) nowcalendar.clone();
		
		//�õ��������������
		int beginTime = timeAxisInfo.getBegin();
		int endTime = timeAxisInfo.getEnd();
		int intTime = timeAxisInfo.getIntTimeValue();
		int length = endTime - beginTime;
		int calendarField = setAxisUnitType(timeAxisInfo);
		boolean isInt = timeAxisInfo.isIntTime();
		
		//���ù���ֵ
		intTime = intTime == 0 ? 1 : intTime; 
		intTime = timeAxisInfo.isIntTime()? intTime : 1;
		if(length < intTime) {
			intTime = 1;
		}
		
		/***************������������ʼʱ��*****************/
		//�������
		if (timeAxisInfo.isRun()) {
			startCalendar.add(calendarField, beginTime);
		} else {
			startCalendar.set(calendarField, beginTime);
		}
		
		//ȡ��
		if(isInt) {
			beginTime = startCalendar.get(calendarField);
			int intFieldValue = beginTime / intTime * intTime;
			if(intFieldValue + length < nowcalendar.get(calendarField)) {
				intFieldValue += intTime;
			}
			beginTime = beginTime <= intTime ? 0 : intFieldValue;
			startCalendar.set(calendarField, beginTime);
		}
		
		setAxisBeginTime(startCalendar, calendarField);
		/***************������������ʼʱ��*****************/
		
		//�������������ʱ��
		endCalendar.setTimeInMillis(startCalendar.getTimeInMillis()); 
		endCalendar.add(calendarField, length);
		
		System.out.println(startCalendar.getTime().toLocaleString());
		System.out.println(endCalendar.getTime().toLocaleString());
	
	}
	
	/**
	 * ������ʼ������ʱ��.
	 * @param calendarField
	 * @return
	 */
	private static void setAxisBeginTime(Calendar startCalendar, int calendarField) {
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

	private static int setAxisUnitType(TimeAxisInfo timeAxisInfo) {
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
	


}
