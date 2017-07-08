package design.com.lry.jfree.chart.design.adapter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PlotEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleInsets;

import design.com.lry.jfree.chart.design.ui.PropertyDialog;

public class PlotMouseAdapter extends AbstractEntityAdapter{

	private PlotEntity entity = null;
	private static HashMap<Integer, PlotMouseAdapter> map = new HashMap<Integer, PlotMouseAdapter>();
	private ChartPanel chartPanel ;
	private JFreeChart chart = null;
	boolean isDragged = false;
	private PlotMouseAdapter(PlotEntity entity, ChartPanel chartPanel) {
		this.entity = entity;
		this.chartPanel = chartPanel;
		chart = chartPanel.getChart();
	}
	
	private void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	public void setEntity(ChartEntity entity) {
		this.entity = (PlotEntity) entity;
	}
	
	@Override
	public ChartEntity getEntity() {
		return entity;
	}
	
	private void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
		setChart(chartPanel.getChart());
	}
	
	public static PlotMouseAdapter getPlotMouseAdapter(PlotEntity entity, Object... args) {
		PlotMouseAdapter mouseAdapter = map.get(entity.hashCode());
		if(null != mouseAdapter) {
			mouseAdapter.setEntity(entity);
			mouseAdapter.setChartPanel((ChartPanel) args[0]);
		} else {
			mouseAdapter = new PlotMouseAdapter(entity, (ChartPanel) args[0]);
			map.put(entity.hashCode(), mouseAdapter);
		}
		return mouseAdapter;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(isDragged) {
			int bottom = (int) (chart.getPadding().getBottom() - e.getY() + point.y);
			int right = (int) (chart.getPadding().getRight() - e.getX() + point.x);
			bottom = bottom < 0 ? 0 : bottom;
			right = right < 0 ? 0 : right;
			System.err.println(bottom + "   " + right);
			chart.setPadding(new RectangleInsets(chart.getPadding().getTop(), chart.getPadding().getLeft(), bottom, right));
			
		}else {
			int x_offset = e.getX() - point.x;
			int y_offset = e.getY() - point.y;
			int left = (int) (chart.getPadding().getLeft() + x_offset);
			int top =  (int) (chart.getPadding().getTop() + y_offset);
			int bottom = (int) (chart.getPadding().getBottom() - y_offset);
			int right = (int) (chart.getPadding().getRight() - x_offset);
			chart.setPadding(new RectangleInsets(top,left,bottom ,right));
		}
		point = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			new PropertyDialog(entity, chartPanel);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setOutlinePaint(Color.BLUE);
		plot.setOutlineVisible(true);
		if(entity.isResized(e.getPoint())) {
			isDragged = true;
		} else {
			isDragged = false;
		}
	}
	
	@Override
	public void focusLosted() {
		XYPlot plot = (XYPlot) entity.getPlot();
		plot.setOutlineVisible(false);
	}

}
