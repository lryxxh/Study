package design.com.lry.jfree.chart.design.propertybean;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

/**
 * 线属性.
 * @author LRY
 *
 */
public class LinePropertyBean implements PropertyBean{

	//背景透明时默认边框线型,默认实线.
	private float[] stroke_dash = null;
	
	//背景透明时默认边框线色,默认实线.
	private Paint lineColor = new Color(0,0,0);
	
	//背景透明时默认边框线宽,默认0.
	private float lineWidth = 0;
	
	private BasicStroke stroke = new BasicStroke();
	
	public LinePropertyBean() {
		stroke = new BasicStroke();
		setStroke(stroke);
		setLineColor(Color.LIGHT_GRAY);
	}
	
	public LinePropertyBean(float[] dash, Paint LineColor,
			int LineWidth) {
		super();
		this.stroke_dash = dash;
		this.lineColor = LineColor;
		this.lineWidth = LineWidth;
	}

	public float[] getStrokeDash() {
		return stroke_dash;
	}

	public void setStrokeDash(float[] dash) {
		this.stroke_dash = dash;
	}

	public Paint getLineColor() {
		return lineColor;
	}

	public void setLineColor(Paint lineColor) {
		this.lineColor = lineColor;
	}

	public float getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public void setStroke(Stroke stroke) {
		this.stroke_dash = ((BasicStroke)stroke).getDashArray();
		this.lineWidth = ((BasicStroke)stroke).getLineWidth();
	}
	
	public Stroke getStroke() {
		stroke = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, stroke_dash, 0.0f);
		return stroke;
	}
	
	@Override
	public LinePropertyBean clone() throws CloneNotSupportedException {
		return (LinePropertyBean) super.clone();
	}
}
