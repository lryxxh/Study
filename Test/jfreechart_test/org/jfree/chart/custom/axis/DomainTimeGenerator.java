package org.jfree.chart.custom.axis;

import java.util.Calendar;

public class DomainTimeGenerator {
	
	public static Calendar[] getDomainDate(RunProperties runProperties) {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		return getDomainDate(runProperties, startCalendar, endCalendar);
	}
	
	public static Calendar[] getDomainDate(RunProperties runProperties,Calendar startCalendar, Calendar endCalendar) {
		int domainStart = runProperties.getDomainStart();
		int domainEnd = runProperties.getDomainEnd();
		boolean isInt = runProperties.isInt();
		int calendarField = runProperties.getCalendarField();
		int intTime = runProperties.getIntTimeValue();
		if (domainStart < 0 || domainEnd < 0) {
			startCalendar.add(calendarField, domainStart);
			endCalendar.add(calendarField, domainEnd);
		} else {
			startCalendar.set(calendarField, domainStart);
			endCalendar.set(calendarField, domainEnd);
		}
//		System.out.println(new Date(startCalendar.getTimeInMillis()));
//		System.out.println(new Date(endCalendar.getTimeInMillis()));
		if (isInt) {
			Calendar calendar = Calendar.getInstance();
			switch (calendarField) {
			case Calendar.HOUR_OF_DAY:
			{	
				if (calendarField == Calendar.HOUR_OF_DAY) {
					int starthour = calendar.get(calendarField);
					starthour = starthour / intTime * intTime;
					startCalendar.set(calendarField, starthour);
					int endthour = starthour + intTime;
					endCalendar.set(calendarField, endthour);
					
				}
				startCalendar.set(Calendar.MINUTE, 0);
				endCalendar.set(Calendar.MINUTE, 0);
			}
			case Calendar.MINUTE:
			{
				if (calendarField == Calendar.MINUTE) {
					int startminute = calendar.get(calendarField);
					startminute = startminute / intTime * intTime;
					startCalendar.set(calendarField, startminute);
					int endtminute = startminute + intTime;
					endCalendar.set(calendarField, endtminute);
				}
				startCalendar.set(Calendar.SECOND, 0);
				endCalendar.set(Calendar.SECOND, 0);
			}
			case Calendar.SECOND:
			{	
				if (calendarField == Calendar.SECOND) {
					int startsecond = calendar.get(calendarField);
					startsecond = startsecond / intTime * intTime;
					startCalendar.set(calendarField, startsecond);
					int endtminute = startsecond + intTime;
					endCalendar.set(calendarField, endtminute);
				}
				
				startCalendar.set(Calendar.MILLISECOND, 0);
				endCalendar.set(Calendar.MILLISECOND, 0);
				break;
			}
			default:
				break;
			}
		} else {
			switch (calendarField) {
			case Calendar.HOUR_OF_DAY:
			{	
				startCalendar.set(Calendar.MINUTE, 0);
				endCalendar.set(Calendar.MINUTE, 0);
			}
			case Calendar.MINUTE:
			{
				startCalendar.set(Calendar.SECOND, 0);
				endCalendar.set(Calendar.SECOND, 0);
			}
			case Calendar.SECOND:
			{	
				startCalendar.set(Calendar.MILLISECOND, 0);
				endCalendar.set(Calendar.MILLISECOND, 0);
				break;
			}
			default:
				break;
			}
		}

		Calendar[] calendars = new Calendar[2];
		calendars[0] = startCalendar;
		calendars[1] = endCalendar;
		System.out.println(calendars[0].getTime()+"  " + calendars[1].getTime());
		return calendars;
	}
	
//	public static Calendar[] getSeriesDate(Calendar startCalendar, Calendar endCalendar) {
//		int domainStart = runProperties.getDomainStart();
//		int domainEnd = runProperties.getDomainEnd();
//		boolean isInt = runProperties.isInt();
//		int calendarField = runProperties.getCalendarField();
//		int intTime = runProperties.getIntTimeValue();
//		if (domainStart < 0 || domainEnd < 0) {
//			startCalendar.add(calendarField, domainStart);
//			endCalendar.add(calendarField, domainEnd);
//		} else {
//			startCalendar.set(calendarField, domainStart);
//			endCalendar.set(calendarField, domainEnd);
//		}
////		System.out.println(new Date(startCalendar.getTimeInMillis()));
////		System.out.println(new Date(endCalendar.getTimeInMillis()));
//		if (isInt) {
//			Calendar calendar = Calendar.getInstance();
//			switch (calendarField) {
//			case Calendar.HOUR_OF_DAY:
//			{	
//				if (calendarField == Calendar.HOUR_OF_DAY) {
//					int starthour = calendar.get(calendarField);
//					starthour = starthour / intTime * intTime;
//					startCalendar.set(calendarField, starthour);
//					int endthour = starthour + intTime;
//					endCalendar.set(calendarField, endthour);
//					
//				}
//				startCalendar.set(Calendar.MINUTE, 0);
//				endCalendar.set(Calendar.MINUTE, 0);
//			}
//			case Calendar.MINUTE:
//			{
//				if (calendarField == Calendar.MINUTE) {
//					int startminute = calendar.get(calendarField);
//					startminute = startminute / intTime * intTime;
//					startCalendar.set(calendarField, startminute);
//					int endtminute = startminute + intTime;
//					endCalendar.set(calendarField, endtminute);
//				}
//				startCalendar.set(Calendar.SECOND, 0);
//				endCalendar.set(Calendar.SECOND, 0);
//			}
//			case Calendar.SECOND:
//			{	
//				if (calendarField == Calendar.SECOND) {
//					int startsecond = calendar.get(calendarField);
//					startsecond = startsecond / intTime * intTime;
//					startCalendar.set(calendarField, startsecond);
//					int endtminute = startsecond + intTime;
//					endCalendar.set(calendarField, endtminute);
//				}
//				
//				startCalendar.set(Calendar.MILLISECOND, 0);
//				endCalendar.set(Calendar.MILLISECOND, 0);
//				break;
//			}
//			default:
//				break;
//			}
//		} else {
//			switch (calendarField) {
//			case Calendar.HOUR_OF_DAY:
//			{	
//				startCalendar.set(Calendar.MINUTE, 0);
//				endCalendar.set(Calendar.MINUTE, 0);
//			}
//			case Calendar.MINUTE:
//			{
//				startCalendar.set(Calendar.SECOND, 0);
//				endCalendar.set(Calendar.SECOND, 0);
//			}
//			case Calendar.SECOND:
//			{	
//				startCalendar.set(Calendar.MILLISECOND, 0);
//				endCalendar.set(Calendar.MILLISECOND, 0);
//				break;
//			}
//			default:
//				break;
//			}
//		}
//
//		Calendar[] calendars = new Calendar[2];
//		calendars[0] = startCalendar;
//		calendars[1] = endCalendar;
//		System.out.println(calendars[0].getTime()+"  " + calendars[1].getTime());
//		return calendars;
//	}
}
