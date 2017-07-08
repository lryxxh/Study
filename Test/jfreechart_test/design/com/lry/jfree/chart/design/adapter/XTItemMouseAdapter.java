package design.com.lry.jfree.chart.design.adapter;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import design.com.lry.jfree.chart.design.entity.TextFieldEntity;

public class XTItemMouseAdapter extends AbstractEntityAdapter{

	TextFieldEntity entity = null;
	boolean isDragged = false;
	private static HashMap<Integer, XTItemMouseAdapter> map = new HashMap<Integer, XTItemMouseAdapter>();
	private XTItemMouseAdapter(TextFieldEntity entity) {
		this.entity = entity;
	}
	
	public static XTItemMouseAdapter getTextFieldMouseAdapter(TextFieldEntity entity) {
		if(map.containsKey(entity.hashCode())) {
			return map.get(entity.hashCode());
		} else {
			XTItemMouseAdapter mouseAdapter = new XTItemMouseAdapter(entity);
			map.put(entity.hashCode(), mouseAdapter);
			return mouseAdapter;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Rectangle2D rectangle2d = ((TextFieldEntity) entity).getTextFieldTitle().getArea();
		double x = rectangle2d.getMinX();
		double y = rectangle2d.getMinY();
		double width = rectangle2d.getWidth();
		double height = rectangle2d.getHeight();
		if(isDragged) {
			((TextFieldEntity) entity).getTextFieldTitle().setArea(new Rectangle2D.Double(x, y ,e.getX() - x, e.getY() - y));
		} else {
			((TextFieldEntity) entity).getTextFieldTitle().setArea(new Rectangle2D.Double(e.getX() - width/2, e.getY() - height/2 ,width, height));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		((XYLineAndShapeRenderer)chart.getXYPlot().getRenderer()).setSeriesShapesVisible(0, true);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(isDragged) {
			isDragged = false;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if(entity.isResized(e.getPoint())) {
			isDragged = true;
		} else {
			isDragged = false;
		}
	}
}
