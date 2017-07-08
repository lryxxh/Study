package org.jfree.chart.custom.title;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Observer;

import org.jfree.chart.block.BlockResult;
import org.jfree.chart.block.EntityBlockParams;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.title.TextTitle;

public class ChangePageTitle extends TextTitle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2067491996025537989L;
	BeforePageTitle beforeTitle =  null;
    ForwadPageTitle forwadTitle = null;
	
	public ChangePageTitle() {
		this("");
	}
	
	public ChangePageTitle(String str, List<Observer> observers) {
		super(str);
		addObserver(observers);
	}
	
	public ChangePageTitle(String str){
		this(str,null);
	}
	
	public ChangePageTitle(List<Observer> observers) {
		this("", observers);
	}
	 /**
     * Draws the title on a Java 2D graphics device (such as the screen or a
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the area allocated for the title.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        draw(g2, area, null);
    }

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
        BlockResult result = new BlockResult();
        BlockResult result1 = (BlockResult) beforeTitle.draw(g2, (Rectangle2D) area.clone(), params);
        BlockResult result2 = (BlockResult) forwadTitle.draw(g2, (Rectangle2D) area.clone(), params);
        EntityCollection collection1 = result1.getEntityCollection();
        EntityCollection collection2 = result2.getEntityCollection();
        result2.getEntityCollection();
        StandardEntityCollection sec = new StandardEntityCollection();
        if (collection1 != null) {
        	sec.add(collection1.getEntity(0));
        }
        if (collection2 != null) {
        	sec.add(collection2.getEntity(0));
        }
        result.setEntityCollection(sec);
        return result;
    }

    public void addObserver(List<Observer> observers){
    	beforeTitle = new BeforePageTitle();
    	forwadTitle = new ForwadPageTitle();
    	for (Observer observer : observers) {
    		beforeTitle.addObserver(observer);
    		forwadTitle.addObserver(observer);
    	}
    }
    
    public ForwadPageTitle getForwadPageTitle() {
    	return this.forwadTitle;
    }
    
    public void addObserver(Observer observer){
		beforeTitle.addObserver(observer);
		forwadTitle.addObserver(observer);
    }
    
}
