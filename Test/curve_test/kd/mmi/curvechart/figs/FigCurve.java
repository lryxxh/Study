package kd.mmi.curvechart.figs;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.Format;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kd.mmi.curvechart.beans.AxisInfo;
import kd.mmi.curvechart.beans.DrawInformation;
import kd.mmi.curvechart.beans.TimeAxisInfo;

/**
 * 曲线组件.
 * 
 * @author LRY
 * 
 */
public class FigCurve extends JComponent implements PropertyChangeListener{

	/** 绘制信息 */
	private DrawInformation information = new DrawInformation();

	/** 横轴 */
	private Axis domainAxis = null;

	/** 纵轴 */
	private Axis rangeAxis = null;

	/** 绘制区域 */
	private DrawArea drawArea = new DrawArea();
	
	/** 图例*/
	private Legend legend = new Legend();

	public FigCurve() {
		initComponent();
	}

	private void initComponent() {
		setLayout(null);
		add(this.legend);
		add(this.drawArea);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		FontMetrics fontMetrics = g.getFontMetrics();
		Format format = rangeAxis.getFormat();
		double maxValue = rangeAxis.getMaxValue();
		int width = fontMetrics.stringWidth(format.format(maxValue));
		
		if(information.getDraw_Area_x() - width - 12 < information.getX()) {
			information.fireDraw_Area_xChange(width + 20);
		}
		Rectangle2D curveArea = information.getCurveArea();
		Rectangle2D drawArea = information.getDrawArea();
		g2.fillRect(0, 0, (int) curveArea.getWidth(), (int) curveArea.getHeight());
		paint(g2, curveArea, drawArea);

	}

	/**
	 * 绘制方法.
	 * 
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void paint(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		drawDomainAxis(g, curveArea, drawArea);
		drawRangeAxis(g, curveArea, drawArea);
	}

	/**
	 * 画横轴.
	 * 
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawRangeAxis(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		rangeAxis.paint(g, curveArea, drawArea);
	}

	/**
	 * 画纵轴.
	 * 
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawDomainAxis(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		domainAxis.paint(g, curveArea, drawArea);
	}

	public DrawInformation getInformation() {
		return information;
	}

	public void setInformation(DrawInformation information) {
		this.information = information;
		if(drawArea != null) {
			drawArea.setInformation(information);
		}
	}

	public Axis getDomainAxis() {
		return domainAxis;
	}

	public void setDomainAxis(Axis domainAxis) {
		this.domainAxis = domainAxis;
		if(drawArea != null) {
			drawArea.setDomainAxis(domainAxis);
		}
	}

	public Axis getRangeAxis() {
		return rangeAxis;
	}

	public void setRangeAxis(Axis rangeAxis) {
		this.rangeAxis = rangeAxis;
		if(drawArea != null) {
			drawArea.setRangeAxis(rangeAxis);
		}
	}

	// 添加曲线并给纵轴添加监听器.
	public void addSeries(Series series) {
		if (drawArea != null) {
			drawArea.addSeries(series);
		}
		
		if(legend != null) {
			legend.addFigCurve(series);
		}
		series.addRepaintListener(this);
	}
	
	/**
	 * 设置绘制区域.
	 * 
	 * @param drawArea
	 */
	public void setDrawArea(DrawArea drawArea) {
		this.drawArea = drawArea;
		add(this.drawArea);
	}

	
	/**
	 * get legend value
	 * @return the legend
	 */
	public Legend getLegend() {
		return legend;
	}

	/**
	 * set legend value
	 * @param legend 
	 */
	public void setLegend(boolean isShow, int x, int y) {
		legend.setVisible(isShow);
		legend.setX(x);
		legend.setY(y);
	}

	public static void main(String[] args) {
		DebugGraphics.setLogStream(System.out);
		JFrame frame = new JFrame();
		frame.setSize(1000, 800);

		TimeAxisInfo axisInfo = new TimeAxisInfo();
		axisInfo.setBegin(0);
		axisInfo.setEnd(24);
		axisInfo.setFormatType(4);
		axisInfo.setIntTime(false);
		axisInfo.setIntTimeValue(4);
		axisInfo.setUnit(2);
		axisInfo.setRun(false);
		final TimeAxis domainAxis = new TimeAxis();
		domainAxis.setMainTickCount(6);
		domainAxis.setSubTickCount(2);
		domainAxis.setRange(0, 24);
		domainAxis.setAxisInfo(axisInfo);
		final Axis rangeAxis = new NumberAxis();
		rangeAxis.setMainTickCount(6);
		rangeAxis.setSubTickCount(3);
		rangeAxis.setRange(0, 1200);
		rangeAxis.setPosition(AxisPosition.LEFT);
		AxisInfo rangeAxisInfo =new AxisInfo();
		rangeAxisInfo.setDecimal(2);
		rangeAxis.setAutoRange(true);
		rangeAxis.setAxisInfo(rangeAxisInfo);
		final FigCurve chart = new FigCurve();
		chart.setDomainAxis(domainAxis);
		chart.setRangeAxis(rangeAxis);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DrawInformation info = new DrawInformation();
		info.setMainGridColor(Color.green.getRGB());
		info.setMainGridLineStyle(4);
		info.setMainGridLineWidth(1);
		info.setSubGridColor(Color.blue.getRGB());
		info.setSubGridLineStyle(2);
		info.setSubGridLineWidth(1);
		info.setSubGridXShow(true);
		info.setSubGridYShow(true);
		info.setDraw_Area_x(50);
		info.setDraw_Area_y(50);
		info.setDraw_Area_w(600);
		info.setDraw_Area_h(500);
		info.setX(100);
		info.setY(100);
		info.setW(900);
		info.setH(600);
		info.setBgc(Color.gray.getRGB());
		chart.setInformation(info);

//		chart.setLayout(null);
		chart.setLegend(true,160,20);
		Series figCurve = new Series("今日谁谁谁水水水水", Color.red);
		chart.addSeries(figCurve);

		figCurve.setShape(new Ellipse2D.Double(-2, -2, 4, 4));
		 figCurve.setDrawItemShape(true);
		 figCurve.setFillItemShape(true);
		figCurve.setDrawShapeColor(Color.green);
		figCurve.setFillShapeColor(Color.yellow);
		CurveItem item = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		for (int i = 0; i <= 24; i++) {
			calendar.set(Calendar.HOUR_OF_DAY, i);
			for (int j = 0; j < 2; j++) {
				item = new CurveItem();
				calendar.set(Calendar.MINUTE, 30 * j);
				item.setXValue(calendar.getTimeInMillis());
				item.setYValue(i + 300 * Math.random());
				figCurve.addCurveItem(item);
			}
		}

		Series figCurve2 = new Series("昨日222222222222222221", Color.green);
		chart.addSeries(figCurve2);

		figCurve2.setShape(new Ellipse2D.Double(-2, -2, 4, 4));
		figCurve2.setDrawItemShape(true);
		figCurve2.setFillItemShape(true);
		figCurve2.setDrawShapeColor(Color.green);
		figCurve2.setFillShapeColor(Color.yellow);

		CurveItem item2 = null;
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		for (int i = 0; i <= 24; i++) {
			calendar.set(Calendar.HOUR_OF_DAY, i);
			for (int j = 0; j < 2; j++) {
				item2 = new CurveItem();
				calendar.set(Calendar.MINUTE, 30 * j);
				item2.setXValue(calendar.getTimeInMillis());
				item2.setYValue(i + 200 * Math.random());
				figCurve2.addCurveItem(item2);
			}
		}
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		chart.setBounds(info.getCurveArea());
		panel.add(chart);
		// panel.setBackground(Color.gray);
		panel.setBorder(BorderFactory.createEtchedBorder());
		// frame.getContentPane().setLayout(null);
		// panel.setBounds(new Rectangle(50, 50, 900, 700));
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("repaint")) {
			repaint();
		}
	}

}
