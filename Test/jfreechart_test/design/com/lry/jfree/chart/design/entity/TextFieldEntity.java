package design.com.lry.jfree.chart.design.entity;

import java.awt.Shape;

import org.jfree.chart.entity.ChartEntity;

import design.com.lry.jfree.chart.design.title.TextFieldTitle;

public class TextFieldEntity extends ChartEntity{
	
	TextFieldTitle textFieldTitle = null;

	public TextFieldEntity(Shape area, TextFieldTitle title) {
		this(area,title,null);
	}

    /**
     * Creates a new chart entity.
     *
     * @param area  the area (<code>null</code> not permitted).
     * @param title  the title (<code>null</code> not permitted).
     * @param toolTipText  the tool tip text (<code>null</code> permitted).
     */
    public TextFieldEntity(Shape area, TextFieldTitle title, String toolTipText) {
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
    public TextFieldEntity(Shape area, TextFieldTitle title, String toolTipText,
            String urlText) {
        super(area, toolTipText, urlText);
        this.textFieldTitle = title;
        if (title == null) {
            throw new IllegalArgumentException("Null 'title' argument.");
        }
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer("TextFieldEntity: " + textFieldTitle.getText() +" " + hashCode());
        buf.append("tooltip = ");
        buf.append(getToolTipText());
        return buf.toString();
    }
    
    public TextFieldTitle getTextFieldTitle() {
		return textFieldTitle;
	}
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1185956860873295904L;

}
