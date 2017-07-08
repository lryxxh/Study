package design.com.lry.jfree.chart.design.propertybean;

public class RangeAxisPropertyBean implements PropertyBean{
	
	/**
	 * y��̶�����.
	 */
	private int rangeGraduateType = 1;
	
	/**
	 * y����Сֵ.
	 */
	private double min = 0.0;
	
	/**
	 * y�����ֵ.
	 */
	private double max = 0.0;
	
	/**
	 * ���̶���.
	 */
	private int mainSpaceCount = 2;
	
	/**
	 * ���̶���.
	 */
	private int subSpaceCount = 1;
	
	/**
	 * �ϸ�����.
	 */
	private double upBalance = 0.0;
	
	/**
	 * �¸�����.
	 */
	private double downBalance = 0.0;
	
	/**
	 * С��λ��.
	 */
	private double decimalCount = 2;
	
	/**
	 * ��������.
	 */
	private double increaseBalance = 0.0;
	
	/**
	 * ������.
	 */
	private LinePropertyBean linePropertyBean = new LinePropertyBean();
	
	/**
	 * ��������.
	 */
	private FontPropertyBean fontPropertyBean = new FontPropertyBean();
	
	public RangeAxisPropertyBean() {
		
	}

	public RangeAxisPropertyBean(int rangeGraduateType, double min, double max,
			int mainSpaceCount, int subSpaceCount, double upBalance,
			double downBalance, double decimalCount, double increaseBalance) {
		super();
		this.rangeGraduateType = rangeGraduateType;
		this.min = min;
		this.max = max;
		this.mainSpaceCount = mainSpaceCount;
		this.subSpaceCount = subSpaceCount;
		this.upBalance = upBalance;
		this.downBalance = downBalance;
		this.decimalCount = decimalCount;
		this.increaseBalance = increaseBalance;
	}
	
	public RangeAxisPropertyBean(int rangeGraduateType, double min, double max,
			int mainSpaceCount, int subSpaceCount, double upBalance,
			double downBalance, double decimalCount, double increaseBalance, LinePropertyBean linePropertyBean,
			FontPropertyBean fontPropertyBean) {
		super();
		this.rangeGraduateType = rangeGraduateType;
		this.min = min;
		this.max = max;
		this.mainSpaceCount = mainSpaceCount;
		this.subSpaceCount = subSpaceCount;
		this.upBalance = upBalance;
		this.downBalance = downBalance;
		this.decimalCount = decimalCount;
		this.increaseBalance = increaseBalance;
		this.linePropertyBean = linePropertyBean;
		this.fontPropertyBean = fontPropertyBean;
	}

	public int getRangeGraduateType() {
		return rangeGraduateType;
	}

	public void setRangeGraduateType(int rangeGraduateType) {
		this.rangeGraduateType = rangeGraduateType;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public int getMainSpaceCount() {
		return mainSpaceCount;
	}

	public void setMainSpaceCount(int mainSpaceCount) {
		this.mainSpaceCount = mainSpaceCount;
	}

	public int getSubSpaceCount() {
		return subSpaceCount;
	}

	public void setSubSpaceCount(int subSpaceCount) {
		this.subSpaceCount = subSpaceCount;
	}

	public double getUpBalance() {
		return upBalance;
	}

	public void setUpBalance(double upBalance) {
		this.upBalance = upBalance;
	}

	public double getDownBalance() {
		return downBalance;
	}

	public void setDownBalance(double downBalance) {
		this.downBalance = downBalance;
	}

	public double getDecimalCount() {
		return decimalCount;
	}

	public void setDecimalCount(double decimalCount) {
		this.decimalCount = decimalCount;
	}

	public double getIncreaseBalance() {
		return increaseBalance;
	}

	public void setIncreaseBalance(double increaseBalance) {
		this.increaseBalance = increaseBalance;
	}
	
	public void setFontPropertyBean(FontPropertyBean fontPropertyBean) {
		this.fontPropertyBean = fontPropertyBean;
	}
	
	public FontPropertyBean getFontPropertyBean() {
		return fontPropertyBean;
	}
	
	public void setLinePropertyBean(LinePropertyBean linePropertyBean) {
		this.linePropertyBean = linePropertyBean;
	}
	
	public LinePropertyBean getLinePropertyBean() {
		return linePropertyBean;
	}

}
