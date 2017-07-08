package design.com.lry.jfree.chart.design.propertybean;

import java.util.ArrayList;

public class RangeAxisPropertyBeans implements PropertyBean{
	
	ArrayList<RangeAxisPropertyBean> axisPropertyBeans = new ArrayList<RangeAxisPropertyBean>();
	
	public RangeAxisPropertyBeans() {
	}
	
	public void setAxisPropertyBeans(
			ArrayList<RangeAxisPropertyBean> axisPropertyBeans) {
		this.axisPropertyBeans = axisPropertyBeans;
	}
	
	public ArrayList<RangeAxisPropertyBean> getAxisPropertyBeans() {
		return axisPropertyBeans;
	}
	
	public void addRangeAxisPropertyBean(RangeAxisPropertyBean bean) {
		axisPropertyBeans.add(bean);
	}

}
