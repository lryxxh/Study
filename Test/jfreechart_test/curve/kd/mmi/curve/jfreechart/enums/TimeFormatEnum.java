package curve.kd.mmi.curve.jfreechart.enums;

/**
 * ���ڱ���ʱ���ʽ��ö�١�
 * @author ������
 *
 */
public enum TimeFormatEnum {

	SECOND_FORMAT(0, "ss"), //��
	MINUTE_FORMAT(1, "mm"),//����
	HOUR_FORMAT(2, "HH"),//Сʱ
	MINUTE_SECOND_FORMAT(3, "mm:ss"),//���ӣ���
	HOUR_MINUTE_FORMAT(4, "HH:mm"),//Сʱ������
	YEAR_MONTH_DAY_FORMAT(5, "yyyy-MM-dd"),//��-��-��
	YEAR_MONTH_FORMAT(6, "yyyy-MM"),//��-��
	YEAR_FORMAT(7,"yyyy"),//��
	MONTH_DAY_FORMAT(8, "MM-dd"),//��-��
	DAY_FORMAT(9, "dd"),//��
	HOUR_MINUTE_SECOND_FORMAT(10,"HH:mm:ss");//Сʱ�����ӣ���

	/**
	 * ������.
	 * @param formatNo ��ʽ��Ӧ������
	 * @param formatter ��ʽ�����ַ���
	 */
	private TimeFormatEnum(int formatNo, String formatter) {
		this.formatNo = formatNo;
		this.formatter = formatter;
	}

	/**
	 * ȡ�ø�ʽ���ַ���.
	 * @return
	 */
	public String getFormatterString() {
		return formatter;
	}
	
	/**
	 * ��������ȥ�ö�Ӧ�Ķ�����߸�ʽ���ַ���.
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
	 * ��дtoString.
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "����" + formatNo + "��Ӧ�ĸ�ʽ���ַ�����" + formatter;
	}
	
	
	private int formatNo;
	private String formatter;
}
