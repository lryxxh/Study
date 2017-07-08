package design.com.lry.jfree.chart.design.title;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.block.BlockFrame;
import org.jfree.chart.block.BlockResult;
import org.jfree.chart.block.EntityBlockParams;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.title.TextTitle;

import design.com.lry.jfree.chart.design.entity.TextFieldEntity;

public class TextFieldTitle extends TextTitle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6604529623280583831L;
	private Rectangle2D area = new Rectangle2D.Double();//矩形区域
	private String text = "";//文本
	public TextFieldTitle(Rectangle2D area, String text) {
		this(area, text, Color.BLACK);
	}
	
	public TextFieldTitle(Rectangle2D area, String text, Paint color) {
		super(text);
		this.area = area;
		this.text = text;
		setPaint(color);
	}
	
	public void setArea(Rectangle2D area) {
		this.area = area;
		notifyListeners(new TitleChangeEvent(this));
	}
	public Rectangle2D getArea() {
		return area;
	}

	@Override
	public void setFrame(BlockFrame frame) {
		super.setFrame(frame);
		notifyListeners(new TitleChangeEvent(this));
	}

	@Override
	public Object draw(Graphics2D graphics2d, Rectangle2D area2, Object params) {
		Graphics2D g2 = (Graphics2D) graphics2d.create();
		 ChartEntity entity0 = null;
//	        area = trimBorder(area);
		  
//	        System.out.println(area);
	        if (params instanceof EntityBlockParams) {
	            EntityBlockParams p = (EntityBlockParams) params;
	            if (p.getGenerateEntities()) {
	            	entity0 = new TextFieldEntity(area, this);
	            }
	        }
	        FontMetrics fontMetrics = g2.getFontMetrics();
	        int width = fontMetrics.stringWidth(text);
	        int height = fontMetrics.getHeight();
	        g2.setPaint(getPaint());
//	        System.out.println(fontMetrics.getAscent() + " " + fontMetrics.getDescent() + " " 
//	        		+ fontMetrics.getHeight() +" " + fontMetrics.getLeading() + "  "+width);
	        g2.drawString(text,(float)(area.getMinX()+ (area.getWidth() - width)/2),
	        		(float) ( area.getMinY() +(area.getHeight()- height)/2 + fontMetrics.getAscent()));
//	        g2.drawString("HelloWorld",(int)area.getCenterX(),(int) area.getCenterY());
	        drawBorder(g2, area);
	        BlockResult result = new BlockResult();
	        if (entity0 != null) {
	            StandardEntityCollection sec = new StandardEntityCollection();
	            sec.add(entity0);
	            result.setEntityCollection(sec);
	        }
	        g2.dispose();
	        return result;
	}


}
