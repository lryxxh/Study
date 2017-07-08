package design.com.lry.jfree.chart.design.ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

import design.com.lry.jfree.chart.design.propertybean.LinePropertyBean;

/**
 * ‘§¿¿÷±œﬂ√Ê∞Â.
 * @author LRY
 *
 */
public class LineViewPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2521199078075262664L;
	LinePropertyBean bean = null;
	public LineViewPanel(LinePropertyBean bean) {
		this.bean = bean;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
//		super.paint(g2);
		int width = getWidth();
		int x0 = width / 4;
		int y0 = getHeight() / 2;
		int x1 = 3 * getWidth() / 4;
		int y1 = getHeight() / 2;
		float lineWidth = bean.getLineWidth();
		float[] dash = bean.getStrokeDash();
		Stroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f);
		g2.setStroke(stroke);
		g2.setPaint(bean.getLineColor());
		System.err.println(bean);
		g2.drawLine(x0, y0, x1, y1);
		
	}
	
}
