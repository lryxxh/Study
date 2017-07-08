package curve.kd.mmi.curvechart.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import agency.MidLayerAccessException;
import agency.message.base.DataUnit;
import context.Context;
import curve.kd.mmi.curvechart.graphics.glanguage.GCurveConvertFromStr;
import support.DataClient;

/**
 * This class provides lot of useful tools(methods) userd by the application.
 * 
 * @author zhou lei
 * 
 */

public class CustomUtility {

	public CustomUtility() {
	}


	/**
	 * 转换时间
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param week
	 * @param week_day
	 * 
	 * @return date
	 */
	public static Date convertTime(int year, int month, int day, int hour,
			int minute, int second, int week, int week_day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// 年
		if (year == GCurveConvertFromStr.THIS_YEAR) {
			year = calendar.get(Calendar.YEAR);
		} else if (year == GCurveConvertFromStr.LAST_YEAR) {
			year = calendar.get(Calendar.YEAR) - 1;
		} else if (year == GCurveConvertFromStr.LAST_TWO_YEAR) {
			year = calendar.get(Calendar.YEAR) - 2;
		} else if (year == GCurveConvertFromStr.LAST_THREE_YEAR) {
			year = calendar.get(Calendar.YEAR) - 3;
		} else if (year == GCurveConvertFromStr.LAST_FOUR_YEAR) {
			year = calendar.get(Calendar.YEAR) - 4;
		} else if (year == GCurveConvertFromStr.NEXT_ONE_YEAR) {
			year = calendar.get(Calendar.YEAR) + 1;
		} else if (year == GCurveConvertFromStr.NEXT_TWO_YEAR) {
			year = calendar.get(Calendar.YEAR) + 2;
		}
		// 月
		if (month == GCurveConvertFromStr.THIS_MONTH) {
			month = calendar.get(Calendar.MONTH);
		} else if (month == GCurveConvertFromStr.LAST_MONTH) {
			month = calendar.get(Calendar.MONTH) - 1;
		} else if (month == GCurveConvertFromStr.LAST_TWO_MONTH) {
			month = calendar.get(Calendar.MONTH) - 2;
		} else if (month == GCurveConvertFromStr.LAST_THREE_MONTH) {
			month = calendar.get(Calendar.MONTH) - 3;
		} else if (month == GCurveConvertFromStr.NEXT_ONE_MONTH) {
			month = calendar.get(Calendar.MONTH) + 1;
		} else if (month == GCurveConvertFromStr.NEXT_TWO_MONTH) {
			month = calendar.get(Calendar.MONTH) + 2;
		} else {
			// 绝对月份-1
			month = month - 1;
		}

		// 日
		if (day == GCurveConvertFromStr.THIS_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH);
		} else if (day == GCurveConvertFromStr.NEXT_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
		} else if (day == GCurveConvertFromStr.NEXT_TWO_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) + 2;
		} else if (day == GCurveConvertFromStr.LAST_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) - 1;
		} else if (day == GCurveConvertFromStr.LAST_TWO_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) - 2;
		} else if (day == GCurveConvertFromStr.LAST_THREE_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) - 2;
		}
		// 时
		if (hour == GCurveConvertFromStr.THIS_HOUR) {
			hour = calendar.get(Calendar.HOUR_OF_DAY);
		} else if (hour == GCurveConvertFromStr.LAST_HOUR) {
			hour = calendar.get(Calendar.HOUR_OF_DAY) - 1;
		} else if (hour == GCurveConvertFromStr.NEXT_ONE_HOUR) {
			hour = calendar.get(Calendar.HOUR_OF_DAY) + 1;
		} else if (hour == GCurveConvertFromStr.NEXT_TWO_HOUR) {
			hour = calendar.get(Calendar.HOUR_OF_DAY) + 2;
		}
		// 分
		if (minute == GCurveConvertFromStr.THIS_MINUTE) {
			minute = calendar.get(Calendar.MINUTE);
		} else if (minute == GCurveConvertFromStr.LAST_MINUTE) {
			minute = calendar.get(Calendar.MINUTE) - 1;
		} else if (minute == GCurveConvertFromStr.LAST_TWO_MINUTE) {
			minute = calendar.get(Calendar.MINUTE) - 2;
		} else if (minute == GCurveConvertFromStr.LAST_FIVE_MINUTE) {
			minute = calendar.get(Calendar.MINUTE) - 5;
		} else if (minute == GCurveConvertFromStr.NEXT_ONE_MINUTE) {
			minute = calendar.get(Calendar.MINUTE) + 1;
		} else if (minute == GCurveConvertFromStr.NEXT_TWO_MINUTE) {
			minute = calendar.get(Calendar.MINUTE) + 2;
		} else if (minute == GCurveConvertFromStr.NEXT_FIVE_MINUTE) {
			minute = calendar.get(Calendar.MINUTE) + 5;
		}
		// 秒
		if (second == GCurveConvertFromStr.THIS_SECOND) {
			second = calendar.get(Calendar.SECOND);
		} else if (second == GCurveConvertFromStr.LAST_SECOND) {
			second = calendar.get(Calendar.SECOND) - 1;
		} else if (second == GCurveConvertFromStr.LAST_TWO_SECOND) {
			second = calendar.get(Calendar.SECOND) - 2;
		} else if (second == GCurveConvertFromStr.NEXT_ONE_SECOND) {
			second = calendar.get(Calendar.SECOND) + 1;
		} else if (second == GCurveConvertFromStr.NEXT_TWO_SECOND) {
			second = calendar.get(Calendar.SECOND) + 2;
		}
		// 周
		// if (week == GCurveConvertFromStr.THIS_WEEK) {
		// week = calendar.get(Calendar.WEEK_OF_YEAR);
		// }
		// if (week_day == GCurveConvertFromStr.){
		//			
		// }

		calendar.set(year, month, day, hour, minute, second);
		return calendar.getTime();
	}

	public static String convertTime(int year, int month, int day, int distant) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// 年
		if (year == GCurveConvertFromStr.THIS_YEAR) {
			year = calendar.get(Calendar.YEAR);
		} else if (year == GCurveConvertFromStr.LAST_YEAR) {
			year = calendar.get(Calendar.YEAR) - 1;
		} else if (year == GCurveConvertFromStr.LAST_TWO_YEAR) {
			year = calendar.get(Calendar.YEAR) - 2;
		} else if (year == GCurveConvertFromStr.LAST_THREE_YEAR) {
			year = calendar.get(Calendar.YEAR) - 3;
		} else if (year == GCurveConvertFromStr.LAST_FOUR_YEAR) {
			year = calendar.get(Calendar.YEAR) - 4;
		} else if (year == GCurveConvertFromStr.NEXT_ONE_YEAR) {
			year = calendar.get(Calendar.YEAR) + 1;
		} else if (year == GCurveConvertFromStr.NEXT_TWO_YEAR) {
			year = calendar.get(Calendar.YEAR) + 2;
		}
		// 月
		if (month == GCurveConvertFromStr.THIS_MONTH) {
			month = calendar.get(Calendar.MONTH);
		} else if (month == GCurveConvertFromStr.LAST_MONTH) {
			month = calendar.get(Calendar.MONTH) - 1;
		} else if (month == GCurveConvertFromStr.LAST_TWO_MONTH) {
			month = calendar.get(Calendar.MONTH) - 2;
		} else if (month == GCurveConvertFromStr.LAST_THREE_MONTH) {
			month = calendar.get(Calendar.MONTH) - 3;
		} else if (month == GCurveConvertFromStr.NEXT_ONE_MONTH) {
			month = calendar.get(Calendar.MONTH) + 1;
		} else if (month == GCurveConvertFromStr.NEXT_TWO_MONTH) {
			month = calendar.get(Calendar.MONTH) + 2;
		} else {
			// 绝对月份-1
			month = month - 1;
		}

		// 日
		if (day == GCurveConvertFromStr.THIS_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH);
		} else if (day == GCurveConvertFromStr.NEXT_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
		} else if (day == GCurveConvertFromStr.NEXT_TWO_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) + 2;
		} else if (day == GCurveConvertFromStr.LAST_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) - 1;
		} else if (day == GCurveConvertFromStr.LAST_TWO_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) - 2;
		} else if (day == GCurveConvertFromStr.LAST_THREE_DAY) {
			day = calendar.get(Calendar.DAY_OF_MONTH) - 2;
		}

		return String.valueOf(year) + "-" + String.valueOf(month) + "-"
				+ String.valueOf(day) + " 00:00:00";
	}
	
	/**
	 * 取描述
	 * 
	 * @param viewer
	 *            TODO
	 * @param id
	 * 
	 * @sign curve_bj_1
	 * @author mengxin
	 * @since 2006/03/22
	 */
	public static String getIDDescr(String domain, String id) {
		String sql = "select dev_name from hisdb.sys_info_scada_analog@orahis where data_id = '"
				+ id + "'";
		String descr = "";
		try {
			Vector<Vector<DataUnit>> result = DataClient.RDBCommand(domain,
					Context.REALTIME, sql, null);
			if (result != null && result.size() != 0) {
				descr = result.get(0).get(0).toString();
			} else {
				System.err.println(domain + "ID" + id + "的描述没有取出来!!");
			}
		} catch (MidLayerAccessException ex) {
			ex.printStackTrace();
		}
		return descr;
	}

	/**
	 * 按照给定的日期格式，将字符串转换为日期
	 * 
	 * @param dateFormatStr
	 *            日期格式
	 * @param dateStr
	 *            符合格式的字符串
	 * @return 转换后的日期
	 * 
	 * @sign curve_update_116
	 * @author mengxin
	 * @since 2008/01/09
	 */
	public static Date convertDateFormatStrToDate(String dateFormatStr,
			String dateStr) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	

	/**
	 * 按照给定的日期，将日期转换为字符串
	 * 
	 * @param dateFormatStr
	 *            日期格式
	 * @param date
	 *            日期
	 * @return 转换后的字符串
	 * 
	 * @sign curve_update_116
	 * @author mengxin
	 * @since 2008/01/09
	 */
	public static String convertDateToDateFormatStr(String dateFormatStr,
			Date date) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		return dateFormat.format(date);
	}
}
