package design.com.lry.jfree.chart.design.adapter;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockFrame;

import design.com.lry.jfree.chart.design.border.CustomBlockBorder;
import design.com.lry.jfree.chart.design.entity.TextFieldEntity;

public class TextFieldMouseAdapter extends AbstractEntityAdapter{

	TextFieldEntity entity = null;
	boolean isDragged = false;
	private static HashMap<Integer, TextFieldMouseAdapter> map = new HashMap<Integer, TextFieldMouseAdapter>();
	BlockFrame defaultFrame = null;
	private TextFieldMouseAdapter(TextFieldEntity entity) {
		this.entity = entity;
		defaultFrame = entity.getTextFieldTitle().getFrame();
	}
	
	public static TextFieldMouseAdapter getTextFieldMouseAdapter(TextFieldEntity entity) {
		TextFieldMouseAdapter mouseAdapter = map.get(entity.hashCode());
		if(null != mouseAdapter) {
			mouseAdapter.setEntity(entity);//因为entity里面的area可能会变法,所以传递新的entity
		} else {
			mouseAdapter = new TextFieldMouseAdapter(entity);
			map.put(entity.hashCode(), mouseAdapter);
		}
		return mouseAdapter;
	}
	
	public void setEntity(TextFieldEntity entity) {
		this.entity = entity;
	}
	
	@Override
	public TextFieldEntity getEntity() {
		return entity;
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
//		if(entity.isResized(e.getPoint())) {
//			isDragged = true;
//		} else {
//			isDragged = false;
//		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		((TextFieldEntity) entity).getTextFieldTitle().setFrame(new CustomBlockBorder());
		if(entity.isResized(e.getPoint())) {
			isDragged = true;
		} else {
			isDragged = false;
		}
	}
	
	@Override
	public void focusLosted() {
		((TextFieldEntity) entity).getTextFieldTitle().setFrame(new BlockBorder(0,0,0,0));
	}
}
