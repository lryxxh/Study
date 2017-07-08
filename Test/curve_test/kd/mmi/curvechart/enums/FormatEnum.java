package kd.mmi.curvechart.enums;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * ���ڱ���ʱ���ʽ��ö�١�
 * 
 * @author LRY
 * 
 */
public enum FormatEnum {

	NUMBER_FORMAT(FormatEnum.NUMBER_TYPE, 2, ""), // �������ʽ
	TIME_FORMAT_SECOND(FormatEnum.TIME_TYPE, 0, "ss"), // ��
	TIME_FORMAT_MINUTE(FormatEnum.TIME_TYPE, 1, "mm"), // ����
	TIME_FORMAT_HOUR(FormatEnum.TIME_TYPE, 2, "HH"), // Сʱ
	TIME_FORMAT_MINUTE_SECOND(FormatEnum.TIME_TYPE, 3, "mm:ss"), // ���ӣ���
	TIME_FORMAT_HOUR_MINUTE(FormatEnum.TIME_TYPE, 4, "HH:mm"), // Сʱ������
	TIME_FORMAT_YEAR_MONTH_DAY(FormatEnum.TIME_TYPE, 5, "yyyy-MM-dd"), // ��-��-��
	TIME_FORMAT_YEAR_MONTH(FormatEnum.TIME_TYPE, 6, "yyyy-MM"), // ��-��
	TIME_FORMAT_YEAR(FormatEnum.TIME_TYPE, 7, "yyyy"), // ��
	TIME_FORMAT_MONTH_DAY(FormatEnum.TIME_TYPE, 8, "MM-dd"), // ��-��
	TIME_FORMAT_DAY(FormatEnum.TIME_TYPE, 9, "dd"), // ��
	TIME_FORMAT_HOUR_MINUTE_SECOND(FormatEnum.TIME_TYPE, 10, "HH:mm:ss");// Сʱ�����ӣ���

	/**
	 * ������.
	 * 
	 * @param formatNo
	 *            ��ʽ��Ӧ������
	 * @param formatter
	 *            ��ʽ�����ַ���
	 */
	private FormatEnum(int type, int formatNo, String formatter) {
		this.type = type;
		this.formatNo = formatNo;
		this.formatter = formatter;
	}

	/**
	 * ȡ�ø�ʽ���ַ���.
	 * 
	 * @return
	 */
	public String getFormatterString() {
		return formatter;
	}

	/**
	 * ��������ȥ�ö�Ӧ�Ķ�����߸�ʽ���ַ���.
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
	 * ��дtoString.
	 */
	public String toString() {
		return "����" + formatNo + "��Ӧ�ĸ�ʽ���ַ�����" + formatter;
	}

	public static final int NUMBER_TYPE = 0;

	public static final int TIME_TYPE = 1;

	private int type = 1;
	private int formatNo = 4;
	private String formatter = "HH:mm";
}
