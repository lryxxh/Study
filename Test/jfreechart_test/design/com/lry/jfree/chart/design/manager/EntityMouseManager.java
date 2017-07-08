package design.com.lry.jfree.chart.design.manager;

import java.awt.event.MouseEvent;

import org.jfree.chart.entity.ChartEntity;

import design.com.lry.jfree.chart.design.adapter.AbstractEntityAdapter;

public class EntityMouseManager{
	private EntityMouseManager(){}
	
	private static AbstractEntityAdapter last_entityAdapter = null;
	
	private static AbstractEntityAdapter getAbstractAdapterByEntity(ChartEntity entity, Object... args) {
//		if(entity instanceof TextFieldEntity) {
//			entityAdapter = TextFieldMouseAdapter.getTextFieldMouseAdapter((TextFieldEntity)entity);
//		} else if(entity instanceof PlotEntity) {
//			entityAdapter = PlotMouseAdapter.getPlotMouseAdapter((PlotEntity)entity, args);
//		} else if(entity instanceof JFreeChartEntity) {
//			entityAdapter = JFreeChartEntityMouseAdapter.getJFreeChartMouseAdapter((JFreeChartEntity)entity, args);
//		} else if(entity instanceof AxisEntity) {
//			entityAdapter = AxisMouseAdapter.getAxisMouseAdapter((AxisEntity)entity, args);
//		}
//		return entityAdapter;
		return AbstractEntityAdapter.getAbstractEntityAdapterByEntity(entity, args);
	}
	
	
	
	public static void mouseDragged(ChartEntity entity, MouseEvent e, Object... args) {
		getAbstractAdapterByEntity(entity,args).mouseDragged(e);
	}

	
	public static void mouseMoved(ChartEntity entity, MouseEvent e, Object... args) {
		getAbstractAdapterByEntity(entity,args).mouseMoved(e);
	}

	
	public static void mouseClicked(ChartEntity entity, MouseEvent e, Object... args) {
		getAbstractAdapterByEntity(entity,args).mouseClicked(e);
	}

	
	public static void mousePressed(ChartEntity entity, MouseEvent e, Object... args) {
		if(null != last_entityAdapter && null != last_entityAdapter.getEntity() && !last_entityAdapter.getEntity().equals(entity)) {
			last_entityAdapter.focusLosted();
		}
		getAbstractAdapterByEntity(entity,args).mousePressed(e);
	}

	
	public static void mouseReleased(ChartEntity entity, MouseEvent e, Object... args) {
		getAbstractAdapterByEntity(entity,args).mouseReleased(e);
		if(null != getAbstractAdapterByEntity(entity,args)) {
			try {
				last_entityAdapter = (AbstractEntityAdapter) getAbstractAdapterByEntity(entity,args).clone();
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	public static void mouseEntered(ChartEntity entity, MouseEvent e, Object... args) {
		getAbstractAdapterByEntity(entity,args).mouseEntered(e);
	}

	
	public static void mouseExited(ChartEntity entity, MouseEvent e, Object... args) {
		getAbstractAdapterByEntity(entity,args).mouseExited(e);
	}

}
