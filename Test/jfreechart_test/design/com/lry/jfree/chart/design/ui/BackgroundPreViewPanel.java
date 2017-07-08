package design.com.lry.jfree.chart.design.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.factory.GradientPaintFactory;
import design.com.lry.jfree.chart.design.propertybean.BackgroundPropertyBean;

public class BackgroundPreViewPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 517842379419912933L;
	BackgroundPropertyBean bean = new BackgroundPropertyBean();
	public BackgroundPreViewPanel() {
		this(new BackgroundPropertyBean());
	}
	
	public BackgroundPreViewPanel(BackgroundPropertyBean bean) {
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), FinalStaticFactory.PREVIEW_LABEL));
		this.bean = bean;
	}
	
	public void setBackgroundBeanColor(Color bg) {
		bean.setBgColor(bg);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		switch (bean.getFillType()) {
		case 0:
			paintSingleColorBg(g2);
			break;
		case 1:
			paintStepbyStepColorBg(g2);
			break;
		case 2:
			paintImageBg(g2);
		default:
			break;
		}
		g2.dispose();
	}
	
	private void paintStepbyStepColorBg(Graphics2D g2) {
		Graphics2D graphics2d = (Graphics2D) g2.create();
		super.paint(graphics2d);
		Insets insets = getInsets();
		int left = insets.left;
		int top = insets.top;
		int right = insets.right;
		int bottom = insets.bottom;
		Shape shape = new Rectangle(left, top, getWidth() - left - right, getHeight() - top - bottom);
		int type = bean.getStep_by_step_type();
		Color _color1 = (Color) bean.getStep_by_step_color1();
		Color _color2 = (Color) bean.getStep_by_step_color2();
		Paint paint = GradientPaintFactory.getGradientPaint(shape, type, _color1, _color2);
		graphics2d.setPaint(paint);
		graphics2d.fill(shape);
		graphics2d.dispose();
	}

	private void paintImageBg(Graphics2D g2) {
		super.paint(g2);
		Image image = bean.getBGImage();
		Insets insets = getInsets();
		int left = insets.left;
		int top = insets.top;
		g2.drawImage(image, left, top, getWidth() - 2 * left, getHeight() - 2 * top, this);
	}

	private void paintSingleColorBg(Graphics2D g2) {
		super.paint(g2);
		Insets insets = getInsets();
		int left = insets.left;
		int top = insets.top;
		g2.setPaint(bean.getBgColor());
		g2.fillRect(left, top, getWidth() - 2 * left, getHeight() - 2 * top);
	}
	
	public static void main(String[] args) {
		BackgroundPropertyBean bean = new BackgroundPropertyBean();
		bean.setFillType(2);
		bean.setBGImage(Toolkit.getDefaultToolkit().getImage("C:/Users/LRY/Desktop/1.JPG"));
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.add(new JLabel(""));
		frame.add(panel, BorderLayout.CENTER);
		frame.add(new BackgroundPreViewPanel(bean), BorderLayout.SOUTH);
		frame.setVisible(true);
	}

}
