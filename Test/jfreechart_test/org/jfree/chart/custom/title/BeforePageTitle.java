package org.jfree.chart.custom.title;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Observer;

import org.jfree.chart.block.BlockResult;
import org.jfree.chart.block.EntityBlockParams;
import org.jfree.chart.custom.entity.BeforePageEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.title.TextTitle;

import curve.kd.mmi.curve.jfreechart.refresher.TurnDateObservable;

public class BeforePageTitle extends TextTitle{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439957836169671664L;
	TurnDateObservable observable = new TurnDateObservable();
	
	 /**
     * Draws the block within the specified area.
     *
     * @param g2  the graphics device.
     * @param area  the area.
     * @param params  if this is an instance of {@link EntityBlockParams} it
     *                is used to determine whether or not an
     *                {@link EntityCollection} is returned by this method.
     *
     * @return An {@link EntityCollection} containing a chart entity for the
     *         title, or <code>null</code>.
     */
    public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
        ChartEntity entity0 = null;
        area = trimBorder(area);
        Rectangle2D beforeRectangle2d = new Rectangle2D.Double(area.getX() -22,area.getY(),20,20);
        if (params instanceof EntityBlockParams) {
            EntityBlockParams p = (EntityBlockParams) params;
            if (p.getGenerateEntities()) {
            	entity0 = new BeforePageEntity(beforeRectangle2d, this);
            }
        }
        GeneralPath beforeShape = new GeneralPath();
        beforeShape.moveTo(area.getX() - 22, area.getY() + 10);
        beforeShape.lineTo(area.getX() - 2, area.getY());
        beforeShape.lineTo(area.getX() - 2, area.getY() + 20);
        beforeShape.closePath();
        
        g2.setPaint(Color.RED);
        g2.fill(beforeShape);
        area = trimPadding(area);
        BlockResult result = new BlockResult();
        if (entity0 != null) {
            StandardEntityCollection sec = new StandardEntityCollection();
            sec.add(entity0);
            result.setEntityCollection(sec);
        }
        return result;
    }
    
    public void addObserver(Observer observer){
    	observable.addObserver(observer);
    }
    
    public void beforeTime() {
    	observable.changed();
    	observable.notifyObservers("-");
    }
	
}
