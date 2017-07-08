package design.com.lry.jfree.chart.design.adapter;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.JFreeChartEntity;

import design.com.lry.jfree.chart.design.border.CustomBorder;
import design.com.lry.jfree.chart.design.ui.PropertyDialog;

public class JFreeChartEntityMouseAdapter extends AbstractEntityAdapter{

	JFreeChartEntity entity = null;
	private ChartPanel chartPanel = null;
	private static HashMap<Integer, JFreeChartEntityMouseAdapter> map = new HashMap<Integer, JFreeChartEntityMouseAdapter>();
	boolean isDragged = false;
	public JFreeChartEntityMouseAdapter(JFreeChartEntity entity, ChartPanel chartPanel) {
		this.entity = entity;
		this.chartPanel = chartPanel;
	}
	
	public static JFreeChartEntityMouseAdapter getJFreeChartMouseAdapter(JFreeChartEntity entity, Object... args) {
		JFreeChartEntityMouseAdapter mouseAdapter = map.get(entity.hashCode());
		if(null != mouseAdapter) {
			mouseAdapter.setEntity(entity);
		} else {
			mouseAdapter = new JFreeChartEntityMouseAdapter(entity, (ChartPanel) args[0]);
			map.put(entity.hashCode(), mouseAdapter);
		}
		return mouseAdapter;
	}
	
	private void setEntity(JFreeChartEntity entity) {
		this.entity = entity;
	}
	
	@Override
	public ChartEntity getEntity() {
		return entity;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(isDragged) {
			Rectangle rectangle = chartPanel.getBounds();
			int x = (int) rectangle.getMinX();
			int y = (int) rectangle.getMinY();
			chartPanel.setBounds(x, y, e.getX(), e.getY());
			
		} else {
			Point new_point = e.getPoint();
			Rectangle bounds = chartPanel.getBounds();
			int x = bounds.x + new_point.x - point.x;
			int y = bounds.y + new_point.y - point.y;
			chartPanel.setLocation(x, y);
		}
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
		chartPanel.setBorder(new CustomBorder());
		if(entity.isResized(e.getPoint())) {
			isDragged = true;
		} else {
			isDragged = false;
		}
	}

	@Override
	public void focusLosted() {
		chartPanel.setBorder(null);
	}
}
