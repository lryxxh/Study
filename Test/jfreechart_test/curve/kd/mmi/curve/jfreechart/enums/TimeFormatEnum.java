package curve.kd.mmi.curve.jfreechart.enums;

/**
 * 用于保存时间格式的枚举。
 * @author 刘仁勇
 *
 */
public enum TimeFormatEnum {

	SECOND_FORMAT(0, "ss"), //秒
	MINUTE_FORMAT(1, "mm"),//分钟
	HOUR_FORMAT(2, "HH"),//小时
	MINUTE_SECOND_FORMAT(3, "mm:ss"),//分钟：秒
	HOUR_MINUTE_FORMAT(4, "HH:mm"),//小时：分钟
	YEAR_MONTH_DAY_FORMAT(5, "yyyy-MM-dd"),//年-月-日
	YEAR_MONTH_FORMAT(6, "yyyy-MM"),//年-月
	YEAR_FORMAT(7,"yyyy"),//年
	MONTH_DAY_FORMAT(8, "MM-dd"),//月-日
	DAY_FORMAT(9, "dd"),//日
	HOUR_MINUTE_SECOND_FORMAT(10,"HH:mm:ss");//小时：分钟：秒

	/**
	 * 构造器.
	 * @param formatNo 格式对应的数字
	 * @param formatter 格式化的字符串
	 */
	private TimeFormatEnum(int formatNo, String formatter) {
		this.formatNo = formatNo;
		this.formatter = formatter;
	}

	/**
	 * 取得格式化字符串.
	 * @return
	 */
	public String getFormatterString() {
		return formatter;
	}
	
	/**
	 * 根据数字去得对应的对象或者格式化字符串.
	 * @param numNo
	 * @return
	 */
	public static TimeFormatEnum geTimeFormatEmnu(int numNo){
		TimeFormatEnum formatEmnu = null;
		switch (numNo) {
		case 0:
			formatEmnu = TimeFormatEnum.SECOND_FORMAT;
			break;
		case 1:
			formatEmnu = TimeFormatEnum.MINUTE_FORMAT;
			break;
		case 2:
			formatEmnu = TimeFormatEnum.HOUR_FORMAT;
			break;
		case 3:
			formatEmnu = TimeFormatEnum.MINUTE_SECOND_FORMAT;
			break;
		case 4:
			formatEmnu = TimeFormatEnum.HOUR_MINUTE_FORMAT;
			break;
		case 5:
			formatEmnu = TimeFormatEnum.YEAR_MONTH_DAY_FORMAT;
			break;
		case 6:
			formatEmnu = TimeFormatEnum.YEAR_MONTH_FORMAT;
			break;
		case 7:
			formatEmnu = TimeFormatEnum.YEAR_FORMAT;
			break;
		case 8:
			formatEmnu = TimeFormatEnum.MONTH_DAY_FORMAT;
			break;
		case 9:
			formatEmnu = TimeFormatEnum.DAY_FORMAT;
			break;
		default:
			formatEmnu = TimeFormatEnum.HOUR_MINUTE_FORMAT;
			break;
		}
		return formatEmnu;
	}
	
	/**
	 * 复写toString.
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "数字" + formatNo + "对应的格式化字符串是" + formatter;
	}
	
	
	private int formatNo;
	private String formatter;
}
