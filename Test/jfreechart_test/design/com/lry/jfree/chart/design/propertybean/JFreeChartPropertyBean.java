package design.com.lry.jfree.chart.design.propertybean;

/**
 * Jfreechart属性bean.
 * @author LRY
 *
 */
public class JFreeChartPropertyBean implements PropertyBean{
	
	/**
	 * 背景属性.
	 */
	private BackgroundPropertyBean bean = new BackgroundPropertyBean();
	
	/**
	 * 刷新周期.
	 */
	private int refreshPeriod = 60;
	
	/**
	 * 宽.
	 */
	private int width = 1024;
	
	/**
	 * 高.
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
