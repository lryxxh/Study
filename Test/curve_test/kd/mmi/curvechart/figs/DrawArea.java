package kd.mmi.curvechart.figs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;

import kd.mmi.curvechart.beans.DrawInformation;
import kd.mmi.curvechart.enums.LineStyleEnum;
import kd.mmi.curvechart.tools.CalculateTool;
import kd.mmi.curvechart.tools.StrokeTool;

/**
 * 绘制区域.
 * @author LRY
 *
 */
public class DrawArea extends JComponent implements PropertyChangeListener{
	
	/** 绘制信息*/
	private DrawInformation information = null;
	
	/**曲线集合 */
	private List<Series> seriesList = new ArrayList<Series>();
	
	/** 横坐标轴*/
	private Axis domainAxis = null;
	
	/** 纵坐标轴*/
	private Axis rangeAxis = null;
	
	/** 曲线绘制*/
	private CurveRender render = new CurveRender();
	
	/** 点击点 */
	private Point point = new Point();
	
	/** 拖动查看*/
	private boolean view = false;
	
	/**
	 * 构造方法.
	 * @param information
	 */
	public DrawArea() {
		super();
		addListener();
	}
	
	public void setInformation(DrawInformation information) {
		this.information = information;
		this.information.addPropertyChangeListener(this);
		setBounds(information.getDrawArea());
	}
	
	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				point = e.getPoint();
				if(contains(point)) {
					view = !view;
				}

			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				if(contains(e.getPoint())) {
					point = e.getPoint();
					repaint();
				}
			}
			
		});
	}

	//添加曲线并给纵轴添加监听器.
	public void addSeries(Series series) {
		this.seriesList.add(series);
		Collections.sort(seriesList);
		series.addPropertyChangeListener(rangeAxis);
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g.create();
		graphics2d.translate(-information.getDraw_Area_x(), -information.getDraw_Area_y());
		graphics2d.setColor(Color.RED);
		graphics2d.fillRect(0, 0, getWidth(),getHeight());
		Rectangle2D curveArea = information.getCurveArea();
		Rectangle2D drawArea = information.getDrawArea();
		drawArea(graphics2d, curveArea, drawArea);
		drawCurves(graphics2d, curveArea, drawArea);
		drawCrosshair(graphics2d, curveArea, drawArea);
	}
	
	/**
	 * 画标尺.
	 * @param graphics2d
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawCrosshair(Graphics2D graphics2d, Rectangle2D curveArea, Rectangle2D drawArea) {
		if(view) {
			graphics2d.setColor(Color.yellow);
			graphics2d.setClip(-1000, -1000, 3000, 3000);
			int pointx = (int) point.getX() + information.getDraw_Area_x();
			int pointy = (int) (point.getY() + information.getDraw_Area_y());
			double xValue = domainAxis.java2DToValue(pointx, information.getDrawArea());
			double axisMinValue = domainAxis.getMinValue();
			String label = "";
			int offsetY = 2;
			Rectangle2D rectangle2d = new Rectangle2D.Double();
			FontMetrics fontMetrics = graphics2d.getFontMetrics();
			for(Series series : seriesList) {
				double offset = axisMinValue - series.getItemDatas().get(0).getXValue();
				label = getCurveLabel(series, xValue - offset);
				Rectangle2D rectangle = calculateRotatedStringBounds(graphics2d, label, pointx, pointy, drawArea);
				drawCrosshairValue(graphics2d, rectangle, series, label, offsetY);
				offsetY += rectangle.getHeight();
				if(!rectangle2d.contains(rectangle)) {
					rectangle2d = rectangle;
				}
			}
			rectangle2d.setRect(rectangle2d.getX(), rectangle2d.getY() - fontMetrics.getAscent(), rectangle2d.getWidth(), rectangle2d.getHeight() + fontMetrics.getHeight());
			graphics2d.setColor(new Color(175, 175, 230, 100));
			graphics2d.draw(rectangle2d);
			graphics2d.fill(rectangle2d);
			graphics2d.setColor(Color.red);
			graphics2d.drawLine(pointx, information.getDraw_Area_y(), pointx, information.getDraw_Area_h() +information.getDraw_Area_y());
		}
	}
	
	
	private String getCurveLabel(Series series, double value){
		CurveItem item = series.binarySearchByXValue(value);
		String label = domainAxis.format(item.getXValue()) + "  " + rangeAxis.format(item.getYValue());
		return label;
	}

	/**
	 * 画值.
	 * @param graphics2d
	 * @param curveArea
	 * @param drawArea
	 * @param items
	 */
	private void drawCrosshairValue(Graphics2D graphics2d, Rectangle2D drawBounds, Series series, String label, int offsetY) {
		if(view && series.isShow()) {
			graphics2d.setColor(series.getColor());
			graphics2d.drawString(label, (float)drawBounds.getX(), (float)drawBounds.getY() + offsetY);
		}
	}
	
	/**
	 * 计算绘制范围.
	 * @param labels
	 * @param g2
	 * @param pointX
	 * @param pointY
	 * @param d
	 * @param drawArea
	 * @return
	 */
	private Rectangle2D calculateRotatedStringBounds(Graphics2D g2, String label, 
			float pointX, float pointY, Rectangle2D drawArea) {
		Rectangle2D shape = new Rectangle2D.Double();
    	int width = 0;
    	int height = 0;
    	FontMetrics fontMetrics = g2.getFontMetrics();
		height += fontMetrics.getHeight() ;
		int strWidth = fontMetrics.stringWidth(label);
		width = strWidth > width ? strWidth : width;
    	if (domainAxis.getPosition()==AxisPosition.BOTTOM || domainAxis.getPosition() == AxisPosition.TOP) {
    		if ((pointX + width + 2) >= drawArea.getMaxX()) {
    			pointX = pointX - width - 12;
    		} else {
    			pointX = pointX -2;
    		}
    		
    	} else {
    		if ((pointX + width + 10) > (drawArea.getWidth() + drawArea.getX())) {
        		pointX = pointX - width - 15;
        	}
    	}
    	pointY = (float) (drawArea.getMinX() + drawArea.getHeight() * 3/4);
    	shape = new Rectangle2D.Double(pointX + 5, pointY + 5, width + 5, height);
    		
		return shape;
	}

	/**
	 * 绘制区域绘制.
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawArea(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		drawBackGround(g, curveArea, drawArea);
		drawMainGrids(g, curveArea, drawArea);
		drawSubGrids(g,curveArea, drawArea);
	}
	
	/**
	 * 画主网格.
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawMainGrids(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		if(information.isMainGridXShow()) {
			drawHorizontalMainGrid(g, curveArea, drawArea);
		}
		if(information.isMainGridYShow()) {
			drawVerticalMainGrid(g, curveArea, drawArea);
		}
	}
	
	/**
	 * 画副网格.
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawSubGrids(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		if(information.isSubGridXShow()) {
			drawHorizontalSubGrid(g, curveArea, drawArea);
		}
		if(information.isSubGridYShow()) {
			drawVerticalSubGrid(g, curveArea, drawArea);
		}
	}
	
	/**
	 * 画纵子网格.
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawVerticalSubGrid(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		int mainGridCount = domainAxis.getMainTickCount();
		int subGridCount = domainAxis.getSubTickCount();
		int totalGridCount = subGridCount * mainGridCount;
		int length = (int) drawArea.getHeight();
		Rectangle2D rectangle2d = null;
		Stroke stroke = StrokeTool.createStroke(information.getSubGridLineWidth(), information.getSubGridLineStyle());
		g.setColor(new Color(information.getSubGridColor()));
		((Graphics2D)g).setStroke(stroke);
		Line2D line = new Line2D.Double();
		for(int i = 0; i <= totalGridCount; i++) {
			if(i % subGridCount != 0) {
				rectangle2d = CalculateTool.calculateTickMarkBounds(drawArea, AxisPosition.BOTTOM, totalGridCount, i,length);
				line.setLine(rectangle2d.getMinX(), rectangle2d.getMinY(), rectangle2d.getMaxX(), rectangle2d.getMaxY());
				drawLine(g, line);
			}
		}
	}

	/**
	 * 画横子网格.
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawHorizontalSubGrid(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		int mainGridCount = rangeAxis.getMainTickCount();
		int subGridCount = rangeAxis.getSubTickCount();
		int totalGridCount = subGridCount * mainGridCount;
		int length = (int) drawArea.getWidth();
		Rectangle2D rectangle2d = null;
		Stroke stroke = StrokeTool.createStroke(information.getSubGridLineWidth(), information.getSubGridLineStyle());
		g.setColor(new Color(information.getSubGridColor()));
		((Graphics2D)g).setStroke(stroke);
		Line2D line = new Line2D.Double();
		for(int i = 0; i <= totalGridCount; i++) {
			if(i % subGridCount != 0) {
				rectangle2d = CalculateTool.calculateTickMarkBounds(drawArea, AxisPosition.LEFT, totalGridCount, i, -length);
				line.setLine(rectangle2d.getMinX(), rectangle2d.getMinY(), rectangle2d.getMaxX(), rectangle2d.getMaxY());
				drawLine(g, line);
			}
		}
	}
	
	private void drawVerticalMainGrid(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		int mainGridCount = domainAxis.getMainTickCount();
		AxisPosition position = domainAxis.getPosition();
		int length = (int) drawArea.getHeight();
		Rectangle2D rectangle2d = null;
		Stroke stroke = StrokeTool.createStroke(information.getMainGridLineWidth(), information.getMainGridLineStyle());
		g.setColor(new Color(information.getMainGridColor()));
		((Graphics2D)g).setStroke(stroke);
		Line2D line = new Line2D.Double();
		for(int i = 0; i <= mainGridCount; i++) {
			rectangle2d = CalculateTool.calculateTickMarkBounds(drawArea, position, mainGridCount, i, length);
			line.setLine(rectangle2d.getMinX(), rectangle2d.getMinY(), rectangle2d.getMaxX(), rectangle2d.getMaxY());
			drawLine(g, line);
		}
	}

	private void drawHorizontalMainGrid(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		int mainGridCount = rangeAxis.getMainTickCount();
		AxisPosition position = rangeAxis.getPosition();
		int length = (int) drawArea.getWidth();
		Rectangle2D rectangle2d = null;
		Stroke stroke = StrokeTool.createStroke(information.getMainGridLineWidth(), information.getMainGridLineStyle());
		g.setColor(new Color(information.getMainGridColor()));
		((Graphics2D)g).setStroke(stroke);
		Line2D line = new Line2D.Double();
		for(int i = 0; i <= mainGridCount; i++) {
			rectangle2d = CalculateTool.calculateTickMarkBounds(drawArea, position, mainGridCount, i, -length);
			line.setLine(rectangle2d.getMinX(), rectangle2d.getMinY(), rectangle2d.getMaxX(), rectangle2d.getMaxY());
			drawLine(g, line);
		}
	}


	/**
	 * 画线.
	 * @param g
	 * @param line2d
	 */
	private void drawLine(Graphics g, Line2D line2d) {
		((Graphics2D)g).draw(line2d);
	}
	
	/**
	 * 绘制区域背景设置.
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawBackGround(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		((Graphics2D)g).setColor(new Color(information.getBgc()));
		g.fillRect(information.getDraw_Area_x(), information.getDraw_Area_y(), information.getDraw_Area_w(), information.getDraw_Area_h());
	}
	
	/**
	 * 绘制曲线.
	 * @param g
	 * @param curveArea
	 * @param drawArea
	 */
	private void drawCurves(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		for(Series series : seriesList ) {
			if(series.isShow()) {
				render.drawCurve(g, drawArea, series, domainAxis, rangeAxis);
			}
		}
	}

	/**
	 * get render value
	 * @return the render
	 */
	public CurveRender getRender() {
		return render;
	}

	/**
	 * set render value
	 * @param render 
	 */
	public void setRender(CurveRender render) {
		this.render = render;
	}
	
	

	/**
	 * get domainAxis value
	 * @return the domainAxis
	 */
	public Axis getDomainAxis() {
		return domainAxis;
	}

	/**
	 * set domainAxis value
	 * @param domainAxis 
	 */
	public void setDomainAxis(Axis domainAxis) {
		this.domainAxis = domainAxis;
	}

	/**
	 * get rangeAxis value
	 * @return the rangeAxis
	 */
	public Axis getRangeAxis() {
		return rangeAxis;
	}

	/**
	 * set rangeAxis value
	 * @param rangeAxis 
	 */
	public void setRangeAxis(Axis rangeAxis) {
		this.rangeAxis = rangeAxis;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("draw_x")) {
			Rectangle rectangel = getBounds();
			setBounds(((Integer)evt.getNewValue()), (int)rectangel.getY(), (int)rectangel.getWidth(),(int)rectangel.getHeight());
		} 
	}

}
