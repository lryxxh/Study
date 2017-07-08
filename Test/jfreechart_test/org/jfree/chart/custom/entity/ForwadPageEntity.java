package org.jfree.chart.custom.entity;

import java.awt.Shape;

import org.jfree.chart.custom.title.ForwadPageTitle;
import org.jfree.chart.entity.ChartEntity;

public class ForwadPageEntity extends ChartEntity{

	ForwadPageTitle forwadPageTitle = null;
	
	public ForwadPageEntity(Shape area, ForwadPageTitle title) {
		this(area, title , null);
	}

    /**
     * Creates a new chart entity.
     *
     * @param area  the area (<code>null</code> not permitted).
     * @param title  the title (<code>null</code> not permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     */
    public ForwadPageEntity(Shape area, ForwadPageTitle title, String toolTipText) {
        // defer argument checks...
        this(area, title, toolTipText, null);
    }

    /**
     * Creates a new entity.
     *
     * @param area  the area (<code>null</code> not permitted).
     * @param title  the title (<code>null</code> not permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     * @param urlText  the URL text for HTML image maps (<code>null</code>
     *                 permitted).
     */
    public ForwadPageEntity(Shape area, ForwadPageTitle title, String toolTipText,
            String urlText) {
        super(area, toolTipText, urlText);
        this.forwadPageTitle = title;
        if (title == null) {
            throw new IllegalArgumentException("Null 'title' argument.");
        }
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer("forwadChangeEntity: ");
        buf.append("tooltip = ");
        buf.append(getToolTipText());
        return buf.toString();
    }
    
    public void notifyObservers() {
    	forwadPageTitle.forwadTime();
    }
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1185956860873295904L;

}
