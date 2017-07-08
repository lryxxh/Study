package design.com.lry.jfree.chart.design.ui;

import javax.swing.JPanel;

import design.com.lry.jfree.chart.design.propertybean.PropertyBean;

public abstract class PropertyPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9210017240871217626L;

	public void setValues(){};

	public abstract void initComponents();
	
	public abstract PropertyBean getPropertyBean();
	
	public void setPropertyBeanToEntity(){
		
	}
}
