package demail.com.kd.dmail.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {
	public static String getDate(String type) {
		String time = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd", Locale.CHINA);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		if ("day".equals(type)) {
			calendar.setTimeInMillis(System.currentTimeMillis());
			time = sdf.format(calendar.getTime());
		}
		if ("week".equals(type)) {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			time = sdf.format(calendar.getTime());
		}
		return time;
	}

	public static void main(String args[]) {
		String str1 = "2012-01-01 11:11:11";
		String str2 = "2012-01-08 11:11:23";

	}

	public static String getDate1(String type) {
		String time = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		if ("day".equals(type)) {
			calendar.setTimeInMillis(System.currentTimeMillis());
			time = sdf.format(calendar.getTime());
		}
		if ("week".equals(type)) {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			time = sdf.format(calendar.getTime());
		}
		return time;
	}

	public static String getDate_delay(long delay) {
		String time = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTimeInMillis(System.currentTimeMillis() + delay);
		time = sdf.format(calendar.getTime());
		return time;
	}
}
