package design.com.lry.jfree.chart.design.factory;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Shape;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.entity.PlotEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import design.com.lry.jfree.chart.design.propertybean.BackgroundPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.FontPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.JFreeChartPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.LinePropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PlotPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.propertybean.RangeAxisPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.RangeAxisPropertyBeans;

public class EntityParseFactory {
	
	private EntityParseFactory() {
		
	}
	
	public static PlotPropertyBean parsePlotEntity(PlotEntity entity, ChartPanel chartPanel) {
		PlotPropertyBean plotBean = new PlotPropertyBean();
		BackgroundPropertyBean bgPropertyBean = new BackgroundPropertyBean();
		LinePropertyBean bgBorderBean = new LinePropertyBean();
		LinePropertyBean mainGridLineBean = new LinePropertyBean();
		LinePropertyBean subGridLineBean = new LinePropertyBean();
		XYPlot plot = (XYPlot) entity.getPlot();
		bgBorderBean.setLineColor(plot.getOutlinePaint());
		bgBorderBean.setStroke(plot.getOutlineStroke());
		bgPropertyBean.setBgColor(plot.getBackgroundPaint());
		bgPropertyBean.setLineProperty(bgBorderBean);
		if (null != plot.getBackgroundImage()) {
			bgPropertyBean.setFillType(FinalStaticFactory.FILL_IMAGE_BG_COLOR_NO);//Í¼Æ¬Ìî³ä
			bgPropertyBean.setBGImage(plot.getBackgroundImage());
		} else if (plot.getBackgroundAlpha() == 0.0f) {
			bgPropertyBean.setFillType(FinalStaticFactory.FILL_TRANSPAREND_BG_COLOR_NO);//Í¸Ã÷Ìî³ä
		} else if (plot.getBackgroundPaint() instanceof Color) {
			bgPropertyBean.setFillType(FinalStaticFactory.FILL_SINGLE_BG_COLOR_NO);//µ¥É«Ìî³ä
			bgPropertyBean.setBgColor(plot.getBackgroundPaint());
		} else {
			bgPropertyBean.setFillType(FinalStaticFactory.FILL_STEP_BY_STEP_BG_COLOR_NO);//½¥½øÑÕÉ«Ìî³ä
			bgPropertyBean.setBgColor(plot.getBackgroundPaint());
		}
		mainGridLineBean.setLineColor(plot.getDomainGridlinePaint());
		mainGridLineBean.setStroke(plot.getDomainGridlineStroke());
		
		subGridLineBean.setLineColor(plot.getRangeMinorGridlinePaint());
		subGridLineBean.setStroke(plot.getRangeMinorGridlineStroke());
		plotBean.setBgPropertyBean(bgPropertyBean);
		plotBean.setMainGridLinePropertyBean(mainGridLineBean);
		plotBean.setSubGridLinePropertyBean(subGridLineBean);
		plotBean.setH_SubGridLineVisible(plot.isDomainMinorGridlinesVisible());
		plotBean.setV_MainGridLineVisible(plot.isRangeGridlinesVisible());
		plotBean.setV_SubGridLineVisible(plot.isRangeMinorGridlinesVisible());
		return plotBean;
	}
	
	public static void setBeanToPlotEntity(PlotPropertyBean plotBean, PlotEntity entity, ChartPanel chartPanel) {
		XYPlot plot = (XYPlot) entity.getPlot();
		BackgroundPropertyBean bgBean = plotBean.getBgPropertyBean();
		LinePropertyBean mainGridBean = plotBean.getMainGridLinePropertyBean();
		LinePropertyBean subGridBean = plotBean.getSubGridLinePropertyBean();
		boolean isHMainGridVisible = plotBean.isH_MainGridLineVisible();
		boolean isVMainGridVisible = plotBean.isV_MainGridLineVisible();
		boolean isHSubGridVisible = plotBean.isH_SubGridLineVisible();
		boolean isVSubGridVisible = plotBean.isV_SubGridLineVisible();
		
		plot.setDomainGridlinePaint(mainGridBean.getLineColor());
		plot.setDomainGridlineStroke(mainGridBean.getStroke());
		plot.setDomainGridlinesVisible(isHMainGridVisible);
		plot.setRangeGridlinePaint(mainGridBean.getLineColor());
		plot.setRangeGridlineStroke(mainGridBean.getStroke());
		plot.setRangeGridlinesVisible(isVMainGridVisible);
		
		plot.setDomainMinorGridlinePaint(subGridBean.getLineColor());
		plot.setDomainMinorGridlineStroke(subGridBean.getStroke());
		plot.setDomainMinorGridlinesVisible(isHSubGridVisible);
		plot.setRangeMinorGridlinePaint(subGridBean.getLineColor());
		plot.setRangeMinorGridlineStroke(subGridBean.getStroke());
		plot.setRangeMinorGridlinesVisible(isVSubGridVisible);
		switch (bgBean.getFillType()) {
		case 0:
			plot.setBackgroundPaint(bgBean.getBgColor());
			plot.setBackgroundImage(null);
			plot.setBackgroundAlpha(1.0f);
			plot.setOutlineVisible(false);
			break;
		case 1:
			Shape shape = chartPanel.getChartRenderingInfo().getPlotInfo().getDataArea();
			int type = bgBean.getStep_by_step_type();
			Paint _color1 = bgBean.getStep_by_step_color1();
			Paint _color2 = bgBean.getStep_by_step_color2();
			Paint paint = GradientPaintFactory.getGradientPaint(shape, type, (Color)_color1, (Color)_color2);
			plot.setBackgroundPaint(paint);
			plot.setBackgroundImage(null);
			plot.setBackgroundAlpha(1.0f);
			plot.setOutlineVisible(false);
			break;
		case 2: 
			plot.setBackgroundAlpha(0.0f);
			plot.setBackgroundImage(bgBean.getBGImage());
			plot.setOutlineVisible(false);
			break;
		case 3:
			plot.setBackgroundAlpha(0.0f);
			plot.setBackgroundImage(null);
			LinePropertyBean outLineBean = bgBean.getLineProperty();
			if (outLineBean.getLineWidth() == 0.0f) {
				plot.setOutlineVisible(false);
			} else {
				plot.setOutlineVisible(true);
				plot.setOutlinePaint(outLineBean.getLineColor());
				plot.setOutlineStroke(outLineBean.getStroke());
			}
			break;
		}
		
	}

	public static void setBeanToEntity(PropertyBean propertyBean,
			ChartEntity entity, ChartPanel chartPanel) {
		if(entity instanceof PlotEntity) {
			setBeanToPlotEntity((PlotPropertyBean)propertyBean, (PlotEntity) entity, chartPanel);
		} else if (entity instanceof JFreeChartEntity) {
			setBeanToJFreeChartEntity((JFreeChartPropertyBean)propertyBean, (JFreeChartEntity) entity, chartPanel);
		}
	}
	
	private static void setBeanToJFreeChartEntity(
			JFreeChartPropertyBean propertyBean, JFreeChartEntity entity,
			ChartPanel chartPanel) {
		chartPanel.setSize(propertyBean.getWidth(), propertyBean.getHeight());
		BackgroundPropertyBean bgBean = propertyBean.getBackgroundPropertyBean();
		JFreeChart chart = entity.getChart();
		switch (bgBean.getFillType()) {
		case 0:
			chart.setBackgroundPaint(bgBean.getBgColor());
			chart.setBackgroundImage(null);
			break;
		case 1:
			Shape shape = chartPanel.getChartRenderingInfo().getChartArea();
			int type = bgBean.getStep_by_step_type();
			Paint _color1 = bgBean.getStep_by_step_color1();
			Paint _color2 = bgBean.getStep_by_step_color2();
			Paint paint = GradientPaintFactory.getGradientPaint(shape, type, (Color)_color1, (Color)_color2);
			chart.setBackgroundPaint(paint);
			chart.setBackgroundImage(null);
			break;
		case 2: 
			chart.setBackgroundImage(bgBean.getBGImage());
			chart.setBackgroundPaint(new Color(0,0,0,1));
			break;
		case 3:
			chart.setBackgroundImage(null);
			chart.setBackgroundPaint(new Color(0,0,0,0));
			chart.setBorderPaint(bgBean.getLineProperty().getLineColor());
			chart.setBorderStroke(bgBean.getLineProperty().getStroke());
			break;
		}
	}

	public static PropertyBean parseEntity(ChartEntity entity, ChartPanel chartPanel) {
		PropertyBean bean = null;
		if (entity instanceof PlotEntity) {
			bean = parsePlotEntity((PlotEntity) entity, chartPanel);
		} else if(entity instanceof JFreeChartEntity) {
			bean = parseJFreeChartEntity((JFreeChartEntity)entity, chartPanel);
		} else if(entity instanceof AxisEntity) {
			bean = parseAxisEntity((AxisEntity)entity, chartPanel);
		}
		return bean;
	}

	private static PropertyBean parseAxisEntity(AxisEntity entity,
			ChartPanel chartPanel) {
		RangeAxisPropertyBeans beans = new RangeAxisPropertyBeans();
		ValueAxis axis = (ValueAxis) entity.getAxis();
		XYPlot plot = chartPanel.getChart().getXYPlot();
		for(int i = 0; i < plot.getDatasetCount(); i++) {
			XYDataset dataset = plot.getDataset();
			if(plot.getDomainAxisIndex(axis) >= 0) {
				
			} else if(plot.getRangeAxisIndex(axis) >= 0) {
				RangeAxisPropertyBean bean = new RangeAxisPropertyBean();
				LinePropertyBean linePropertyBean = new LinePropertyBean();
				FontPropertyBean fontPropertyBean = new FontPropertyBean();
				linePropertyBean.setLineColor(plot.getRangeAxis(i).getAxisLinePaint());
				linePropertyBean.setStroke(plot.getRangeAxis(i).getAxisLineStroke());
				
				fontPropertyBean.setFont(plot.getRangeAxis(i).getLabelFont());
				fontPropertyBean.setFontColor((Color) plot.getRangeAxis(i).getLabelPaint());
				bean.setLinePropertyBean(linePropertyBean);
				bean.setFontPropertyBean(fontPropertyBean);
				beans.addRangeAxisPropertyBean(bean);
			}
		}
		return beans;
	}

	private static PropertyBean parseJFreeChartEntity(JFreeChartEntity entity,
			ChartPanel chartPanel) {
		JFreeChartPropertyBean bean = new JFreeChartPropertyBean();
		BackgroundPropertyBean bgBean = new BackgroundPropertyBean();
		JFreeChart chart = entity.getChart();
		bean.setHeight((int) chartPanel.getChartRenderingInfo().getChartArea().getHeight());
		bean.setWidth((int) chartPanel.getChartRenderingInfo().getChartArea().getWidth());
		bean.setRefreshPeriod(3600);
		if (null != chart.getBackgroundImage()) {
			bgBean.setFillType(FinalStaticFactory.FILL_IMAGE_BG_COLOR_NO);//Í¼Æ¬Ìî³ä
			bgBean.setBGImage(chart.getBackgroundImage());
		} else if (chart.getBackgroundPaint() instanceof Color) {
			bgBean.setFillType(FinalStaticFactory.FILL_SINGLE_BG_COLOR_NO);//µ¥É«Ìî³ä
			bgBean.setBgColor(chart.getBackgroundPaint());
		} else if(chart.getBackgroundPaint() instanceof GradientPaint || chart.getBackgroundPaint() instanceof RadialGradientPaint){
			bgBean.setFillType(FinalStaticFactory.FILL_STEP_BY_STEP_BG_COLOR_NO);//½¥½øÑÕÉ«Ìî³ä
			bgBean.setBgColor(chart.getBackgroundPaint());
		} else {
			bgBean.setFillType(FinalStaticFactory.FILL_TRANSPAREND_BG_COLOR_NO);//Í¸Ã÷Ìî³ä
		}
		bean.setBackgroundPropertyBean(bgBean);
		return bean;
	}

}
