package kd.mmi.curvechart.enums;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * 用于保存时间格式的枚举。
 * 
 * @author LRY
 * 
 */
public enum FormatEnum {

	NUMBER_FORMAT(FormatEnum.NUMBER_TYPE, 2, ""), // 数字轴格式
	TIME_FORMAT_SECOND(FormatEnum.TIME_TYPE, 0, "ss"), // 秒
	TIME_FORMAT_MINUTE(FormatEnum.TIME_TYPE, 1, "mm"), // 分钟
	TIME_FORMAT_HOUR(FormatEnum.TIME_TYPE, 2, "HH"), // 小时
	TIME_FORMAT_MINUTE_SECOND(FormatEnum.TIME_TYPE, 3, "mm:ss"), // 分钟：秒
	TIME_FORMAT_HOUR_MINUTE(FormatEnum.TIME_TYPE, 4, "HH:mm"), // 小时：分钟
	TIME_FORMAT_YEAR_MONTH_DAY(FormatEnum.TIME_TYPE, 5, "yyyy-MM-dd"), // 年-月-日
	TIME_FORMAT_YEAR_MONTH(FormatEnum.TIME_TYPE, 6, "yyyy-MM"), // 年-月
	TIME_FORMAT_YEAR(FormatEnum.TIME_TYPE, 7, "yyyy"), // 年
	TIME_FORMAT_MONTH_DAY(FormatEnum.TIME_TYPE, 8, "MM-dd"), // 月-日
	TIME_FORMAT_DAY(FormatEnum.TIME_TYPE, 9, "dd"), // 日
	TIME_FORMAT_HOUR_MINUTE_SECOND(FormatEnum.TIME_TYPE, 10, "HH:mm:ss");// 小时：分钟：秒

	/**
	 * 构造器.
	 * 
	 * @param formatNo
	 *            格式对应的数字
	 * @param formatter
	 *            格式化的字符串
	 */
	private FormatEnum(int type, int formatNo, String formatter) {
		this.type = type;
		this.formatNo = formatNo;
		this.formatter = formatter;
	}

	/**
	 * 取得格式化字符串.
	 * 
	 * @return
	 */
	public String getFormatterString() {
		return formatter;
	}

	/**
	 * 根据数字去得对应的对象或者格式化字符串.
	 * 
	 * @param numNo
	 * @return
	 */
	public static FormatEnum geTimeFormatEmnu(int numNo) {
		FormatEnum formatEnum = null;
		switch (numNo) {
		case 0:
			formatEnum = FormatEnum.TIME_FORMAT_SECOND;
			break;
		case 1:
			formatEnum = FormatEnum.TIME_FORMAT_MINUTE;
			break;
		case 2:
			formatEnum = FormatEnum.TIME_FORMAT_HOUR;
			break;
		case 3:
			formatEnum = FormatEnum.TIME_FORMAT_MINUTE_SECOND;
			break;
		case 4:
			formatEnum = FormatEnum.TIME_FORMAT_HOUR_MINUTE;
			break;
		case 5:
			formatEnum = FormatEnum.TIME_FORMAT_YEAR_MONTH_DAY;
			break;
		case 6:
			formatEnum = FormatEnum.TIME_FORMAT_YEAR_MONTH;
			break;
		case 7:
			formatEnum = FormatEnum.TIME_FORMAT_YEAR;
			break;
		case 8:
			formatEnum = FormatEnum.TIME_FORMAT_MONTH_DAY;
			break;
		case 9:
			formatEnum = FormatEnum.TIME_FORMAT_DAY;
			break;
		default:
			formatEnum = FormatEnum.TIME_FORMAT_HOUR_MINUTE;
			break;
		}

		return formatEnum;
	}

	public Format getFormat() {
		Format format = null;
		switch (type) {
		case NUMBER_TYPE:
			format = new DecimalFormat();
			break;
		case TIME_TYPE:
			format = new SimpleDateFormat(formatter);
			break;
		default:
			format = new SimpleDateFormat(formatter);
			break;
		}
		return format;
	}

	/**
	 * 复写toString.
	 */
	public String toString() {
		return "数字" + formatNo + "对应的格式化字符串是" + formatter;
	}

	public static final int NUMBER_TYPE = 0;

	public static final int TIME_TYPE = 1;

	private int type = 1;
	private int formatNo = 4;
	private String formatter = "HH:mm";
}
