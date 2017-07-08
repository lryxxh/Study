package design.com.lry.jfree.chart.design.propertybean;

/**
 * plot Ù–‘.
 * @author LRY
 *
 */
public class PlotPropertyBean implements PropertyBean{
	private BackgroundPropertyBean bgPropertyBean = new BackgroundPropertyBean();
	private LinePropertyBean mainGridLinePropertyBean = new LinePropertyBean();
	private LinePropertyBean subGridLinePropertyBean = new LinePropertyBean();
	boolean h_MainGridLineVisible = true;
	boolean v_MainGridLineVisible = true;
	boolean h_SubGridLineVisible = false;
	boolean v_SubGridLineVisible = false;
	
	public PlotPropertyBean() {
	}

	public PlotPropertyBean(BackgroundPropertyBean bgPropertyBean,
			LinePropertyBean mainGridLinePropertyBean,
			LinePropertyBean subGridLinePropertyBean,
			boolean h_MainGridLineVisible, boolean v_MainGridLineVisible,
			boolean h_SubGridLineVisible, boolean v_SubGridLineVisible) {
		super();
		this.bgPropertyBean = bgPropertyBean;
		this.mainGridLinePropertyBean = mainGridLinePropertyBean;
		this.subGridLinePropertyBean = subGridLinePropertyBean;
		this.h_MainGridLineVisible = h_MainGridLineVisible;
		this.v_MainGridLineVisible = v_MainGridLineVisible;
		this.h_SubGridLineVisible = h_SubGridLineVisible;
		this.v_SubGridLineVisible = v_SubGridLineVisible;
	}

	public BackgroundPropertyBean getBgPropertyBean() {
		return bgPropertyBean;
	}

	public void setBgPropertyBean(BackgroundPropertyBean bgPropertyBean) {
		this.bgPropertyBean = bgPropertyBean;
	}

	public boolean isH_MainGridLineVisible() {
		return h_MainGridLineVisible;
	}

	public void setH_MainGridLineVisible(boolean h_MainGridLineVisible) {
		this.h_MainGridLineVisible = h_MainGridLineVisible;
	}

	public boolean isV_MainGridLineVisible() {
		return v_MainGridLineVisible;
	}

	public void setV_MainGridLineVisible(boolean v_MainGridLineVisible) {
		this.v_MainGridLineVisible = v_MainGridLineVisible;
	}

	public boolean isH_SubGridLineVisible() {
		return h_SubGridLineVisible;
	}

	public void setH_SubGridLineVisible(boolean h_SubGridLineVisible) {
		this.h_SubGridLineVisible = h_SubGridLineVisible;
	}

	public boolean isV_SubGridLineVisible() {
		return v_SubGridLineVisible;
	}

	public void setV_SubGridLineVisible(boolean v_SubGridLineVisible) {
		this.v_SubGridLineVisible = v_SubGridLineVisible;
	}


	public LinePropertyBean getMainGridLinePropertyBean() {
		return mainGridLinePropertyBean;
	}

	public LinePropertyBean getSubGridLinePropertyBean() {
		return subGridLinePropertyBean;
	}

	public void setMainGridLinePropertyBean(
			LinePropertyBean mainGridLinePropertyBean) {
		this.mainGridLinePropertyBean = mainGridLinePropertyBean;
	}

	public void setSubGridLinePropertyBean(
			LinePropertyBean subGridLinePropertyBean) {
		this.subGridLinePropertyBean = subGridLinePropertyBean;
	}
	
	@Override
	public PlotPropertyBean clone() throws CloneNotSupportedException {
		return (PlotPropertyBean) super.clone();
	}
	
}	
