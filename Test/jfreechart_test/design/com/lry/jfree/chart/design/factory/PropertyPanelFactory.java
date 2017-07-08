package design.com.lry.jfree.chart.design.factory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.entity.PlotEntity;
import org.jfree.chart.plot.XYPlot;

import design.com.lry.jfree.chart.design.propertybean.JFreeChartPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PlotPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.propertybean.RangeAxisPropertyBeans;
import design.com.lry.jfree.chart.design.propertypanel.JFreeChartPropertyPanel;
import design.com.lry.jfree.chart.design.propertypanel.PlotPropertyPanel;
import design.com.lry.jfree.chart.design.propertypanel.RangeAxisPropertyPanel;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;

public class PropertyPanelFactory {
	
	public static PropertyPanel getPropertyPanel(ChartEntity entity, ChartPanel chartPanel) {
		PropertyPanel propertyPanel = null;
		PropertyBean bean = EntityParseFactory.parseEntity(entity, chartPanel);
		if(entity instanceof PlotEntity) {
			propertyPanel = new PlotPropertyPanel((PlotPropertyBean) bean);
		} else if (entity instanceof JFreeChartEntity) {
			propertyPanel = new JFreeChartPropertyPanel((JFreeChartPropertyBean) bean);
		} else if (entity instanceof AxisEntity ) {
			XYPlot plot = chartPanel.getChart().getXYPlot();
			ValueAxis axis = (ValueAxis) ((AxisEntity) entity).getAxis();
			if(plot.getDomainAxisIndex(axis) >= 0) {
				propertyPanel = new RangeAxisPropertyPanel(((RangeAxisPropertyBeans) bean).getAxisPropertyBeans().get(0));
			} else if (plot.getRangeAxisIndex(axis) >= 0) {
				propertyPanel = new RangeAxisPropertyPanel(((RangeAxisPropertyBeans) bean).getAxisPropertyBeans().get(0));
			}
		}
		return propertyPanel;
	}
}