package design.com.lry.jfree.chart.design.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.propertybean.FontPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;


public class FontPreViewPanel extends PropertyPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2113386546952931384L;
	FontPropertyBean bean = new FontPropertyBean();
	
	public FontPreViewPanel() {
		this(new FontPropertyBean());
	}
	
	public FontPreViewPanel(FontPropertyBean bean) {
		this.bean = bean;
		this.setBorder(BorderFactory.createTitledBorder(FinalStaticFactory.PREVIEW_LABEL));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		super.paintBorder(g2);
		Font font = bean.getFont();
		Color fontColor = bean.getFontColor();
		FontMetrics metrics = g2.getFontMetrics();
		int width = metrics.stringWidth(FinalStaticFactory.FONT_TEST);
		int height = metrics.getHeight();
		int x = (getWidth() - width) / 2;
		int y = (getHeight() - height)/2 + metrics.getAscent();
		g2.setFont(font);
		g2.setPaint(fontColor);
		g2.drawString(FinalStaticFactory.FONT_TEST, x, y);
		g2.dispose();
	}
	
	@Override
	public void initComponents() {
		
	}

	@Override
	public PropertyBean getPropertyBean() {
		return bean;
	}

}
