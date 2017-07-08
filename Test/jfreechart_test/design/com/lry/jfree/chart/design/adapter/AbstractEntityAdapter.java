package design.com.lry.jfree.chart.design.adapter;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jfree.chart.entity.AxisEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.entity.PlotEntity;

import design.com.lry.jfree.chart.design.entity.TextFieldEntity;

public abstract class AbstractEntityAdapter implements MouseListener , MouseMotionListener, Cloneable{

	protected Point point = new Point();
	private static AbstractEntityAdapter entityAdapter = null;
	
	public static AbstractEntityAdapter getAbstractEntityAdapterByEntity(ChartEntity entity, Object... args) {
		if(entity instanceof TextFieldEntity) {
			entityAdapter = TextFieldMouseAdapter.getTextFieldMouseAdapter((TextFieldEntity)entity);
		}  else if(entity instanceof JFreeChartEntity) {
			entityAdapter = JFreeChartEntityMouseAdapter.getJFreeChartMouseAdapter((JFreeChartEntity)entity, args);
		} else if(entity instanceof AxisEntity) {
			entityAdapter = AxisMouseAdapter.getAxisMouseAdapter((AxisEntity)entity, args);
		}else  {
			entityAdapter = PlotMouseAdapter.getPlotMouseAdapter((PlotEntity)entity, args);
		}
		return entityAdapter;
	}
	
	public ChartEntity getEntity() {
		return null;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.point = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void focusLosted() {
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
