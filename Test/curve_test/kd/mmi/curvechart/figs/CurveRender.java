package kd.mmi.curvechart.figs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;


public class CurveRender {
	
	/**
	 * 
	 * @param g
	 * @param drawArea
	 * @param series
	 * @param domainAxis
	 * @param rangeAxis
	 */
	public void drawCurve(Graphics g, Rectangle2D drawArea, Series series, Axis domainAxis, Axis rangeAxis) {
		List<Point2D> points = calculateCurvePath(drawArea, series, domainAxis, rangeAxis);
		drawCurvePath(g, series, points);
		drawCurveItemShape(g, points, series);
	}
	
	/**
	 * 绘制曲线点形状.
	 * @param g
	 * @param points
	 */
	private void drawCurveItemShape(Graphics g, List<Point2D> points, Series series) {
		Point2D point2d = null;
		Shape shape = null;
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.blue);
		if(points != null && points.size() > 0 && series.isDrawItemShape()) {
			shape = series.getShape();
			for(int i = 0; i < points.size(); i++){
				point2d = points.get(i);
				g2.translate(point2d.getX(), point2d.getY());
				g2.setColor(series.getDrawShapeColor());
				g2.draw(shape);
				if(series.isFillItemShape()) {
					g2.setColor(series.getFillShapeColor());
					g2.fill(shape);
				}
				g2.translate(-point2d.getX(), -point2d.getY());
			}	
		}
		
	}

	/**
	 * 绘制曲线路径.
	 * @param g
	 * @param points
	 */
	private void drawCurvePath(Graphics g, Series series, List<Point2D> points) {
		((Graphics2D)g).setStroke(new BasicStroke(1));
		((Graphics2D)g).setColor(series.getColor());
		GeneralPath curvePath = createCurvePath(points);
		((Graphics2D)g).draw(curvePath);
	}
	
	/**
	 * 计算所有曲线点的绘制坐标.
	 * @param drawArea 绘制区域范围
	 * @param series	曲线
	 * @param domainAxis 横轴
	 * @param rangeAxis	  纵轴
	 * @return
	 */
	private List<Point2D> calculateCurvePath(Rectangle2D drawArea, Series series, Axis domainAxis, Axis rangeAxis) {
		List<Point2D> points = new ArrayList<Point2D>();
		List<CurveItem> items = series.getItemDatas();
		double xValue = 0;
		double yValue = 0;
		double point_x = 0;
		double point_y = 0;
		double axisMinValue = domainAxis.getMinValue();
		Point2D point2d = null;
		if(items.size() > 0) {
			double minXValue = items.get(0).getXValue();
			double offset = axisMinValue - minXValue;
			for(CurveItem item : items) {
				xValue = item.getXValue();
				xValue += offset;
				yValue = item.getYValue();
				point_x = domainAxis.valueToJava2D(xValue, drawArea);
				point_y = rangeAxis.valueToJava2D(yValue, drawArea);
				point2d = new Point2D.Double(point_x, point_y);
				points.add(point2d);
			}
		}
		return points;
		
	}
	
	/**
	 * 根据坐标点生成曲线路径.
	 * @param points
	 * @return
	 */
	private GeneralPath createCurvePath(List<Point2D> points) {
		GeneralPath path = new GeneralPath();
		Point2D point2d = null;
		if(points != null && points.size() > 0) {
			for(int i = 0; i < points.size(); i++){
				point2d = points.get(i);
				if(i == 0) {
					path.moveTo(point2d.getX(), point2d.getY());
				} else {
					path.lineTo(point2d.getX(), point2d.getY());
				}
			}	
		}
		return path;
		
	}

}
