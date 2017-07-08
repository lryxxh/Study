package design.com.lry.jfree.chart.design.propertybean;

/**
 * Jfreechart����bean.
 * @author LRY
 *
 */
public class JFreeChartPropertyBean implements PropertyBean{
	
	/**
	 * ��������.
	 */
	private BackgroundPropertyBean bean = new BackgroundPropertyBean();
	
	/**
	 * ˢ������.
	 */
	private int refreshPeriod = 60;
	
	/**
	 * ��.
	 */
	private int width = 1024;
	
	/**
	 * ��.
	 */
	private int height = 768;
	
	public JFreeChartPropertyBean() {
	}
	
	public JFreeChartPropertyBean(BackgroundPropertyBean bean,
			int refreshPeriod, int width, int height) {
		super();
		this.bean = bean;
		this.refreshPeriod = refreshPeriod;
		this.width = width;
		this.height = height;
	}

	public BackgroundPropertyBean getBackgroundPropertyBean() {
		return bean;
	}

	public void setBackgroundPropertyBean(BackgroundPropertyBean bean) {
		this.bean = bean;
	}

	public int getRefreshPeriod() {
		return refreshPeriod;
	}

	public void setRefreshPeriod(int refreshPeriod) {
		this.refreshPeriod = refreshPeriod;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
