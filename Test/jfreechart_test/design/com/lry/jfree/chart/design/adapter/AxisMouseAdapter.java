package design.com.lry.jfree.chart.design.adapter;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.ChartEntity;

import design.com.lry.jfree.chart.design.ui.PropertyDialog;

public class AxisMouseAdapter extends AbstractEntityAdapter{

	AxisEntity entity = null;
	boolean isDragged = false;
	private static HashMap<Integer, AxisMouseAdapter> map = new HashMap<Integer, AxisMouseAdapter>();
	private ChartPanel chartPanel = null;
	private Shape leftArrow = null;
	private Shape rightArrow = null;
	private Shape upArrow = null;
	private Shape downArrow = null;
	private AxisMouseAdapter(AxisEntity entity, ChartPanel chartPanel) {
		this.entity = entity;
		this.chartPanel = chartPanel;
		Polygon p1 = new Polygon();
        p1.addPoint(0, 0);
        p1.addPoint(-2, 2);
        p1.addPoint(2, 2);
        upArrow = p1;

        Polygon p2 = new Polygon();
        p2.addPoint(0, 0);
        p2.addPoint(-2, -2);
        p2.addPoint(2, -2);
        downArrow = p2;

        Polygon p3 = new Polygon();
        p3.addPoint(0, 0);
        p3.addPoint(-2, -2);
        p3.addPoint(-2, 2);
        rightArrow = p3;

        Polygon p4 = new Polygon();
        p4.addPoint(0, 0);
        p4.addPoint(2, -2);
        p4.addPoint(2, 2);
        leftArrow = p4;
	}
	
	public static AxisMouseAdapter getAxisMouseAdapter(AxisEntity entity, Object...args) {
		AxisMouseAdapter mouseAdapter = map.get(entity.hashCode());
		if(null != mouseAdapter) {
			mouseAdapter.setEntity(entity);
		} else {
			mouseAdapter = new AxisMouseAdapter(entity,(ChartPanel) args[0]);
			map.put(entity.hashCode(), mouseAdapter);
		}
		return mouseAdapter;
	}

	private void setEntity(AxisEntity entity2) {
		this.entity = entity2;
	}
	
	@Override
	public ChartEntity getEntity() {
		return this.entity;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			new PropertyDialog(entity, chartPanel);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		ValueAxis axis = (ValueAxis) entity.getAxis();
//		System.out.println("axisHashCode"+axis.hashCode());
//		axis.setLeftArrow(new Rectangle(-2, -2, 4, 4));
//		axis.setRightArrow(new Rectangle(-2, -2, 4, 4));
//		axis.setUpArrow(new Rectangle(-2, -2, 4, 4));
//		axis.setDownArrow(new Rectangle(-2, -2, 4, 4));
//		axis.setAxisLinePaint(Color.BLUE);
//		axis.setTickLabelPaint(Color.BLUE);
//		axis.setTickMarkPaint(Color.BLUE);
//		axis.setLabelPaint(Color.BLUE);
//		axis.setPositiveArrowVisible(true);
//		axis.setNegativeArrowVisible(true);
		axis.setAxisLinePaint(Color.RED);
	}
	
	@Override
	public void focusLosted() {
		ValueAxis axis = (ValueAxis) entity.getAxis();
		axis.setLeftArrow(leftArrow);
		axis.setRightArrow(rightArrow);
		axis.setUpArrow(upArrow);
		axis.setDownArrow(downArrow);
		axis.setPositiveArrowVisible(false);
		axis.setNegativeArrowVisible(false);
	}
}
