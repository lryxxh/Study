package kd.mmi.curvechart.figs;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import kd.mmi.curvechart.beans.AxisInfo;
import kd.mmi.curvechart.tools.CalculateTool;

/**
 * 坐标轴.
 * @author LRY
 *
 */
/**
 * @author LRY
 *
 */
public class Axis implements PropertyChangeListener{
	
	/** 最小值 */
	protected double minValue = 0;
	
	/** 最大值*/
	protected double maxValue = 1;
	
	/** 是否自动调整范围 */
	protected boolean isAutoRange = true;
	
	/** 主刻度有多少 */
	protected int mainTickCount = 6;
	
	/** 每个主刻度的副刻度有多少*/
	protected int subTickCount = 2;
	
	/** 坐标轴位置*/
	protected AxisPosition position = AxisPosition.BOTTOM;
	
	/** 坐标轴和绘制区域默认范围 */
	private int DEFAULT_SPACE = 6;
	
	/** 默认主刻度的线长*/
	private int DEFAULT_MAIN_TICK_LENGTH = 5;
	
	/** 默认副刻度的线长*/
	private int DEFAULT_SUB_TICK_LENGTH = 3;
	
	/**  格式化 */
	protected Format format = null;
	
	/** 坐标轴是否显示*/
	protected boolean isShow = true;
	
	/** 时间轴信息*/
	protected AxisInfo axisInfo = null;
	
	/** */
	protected List<String> tickLabels = new ArrayList<String>();

	
	public Axis(AxisPosition position) {
		this.position = position;
	}
	
	/**
	 * 绘制方法.
	 * @param g
	 * @param curveArea 曲线绘制区域.
	 * @param drawArea	 数据绘制区域.
	 * @param position	坐标轴位置.
	 */
	public void paint(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		Rectangle2D rectangle = calculateAxisBounds(curveArea, drawArea, position, 0,DEFAULT_SPACE);
		paintAxisLine(g, rectangle);
		paintTickMarks(g, rectangle);
		paintTickLabels(g, rectangle);
	}
	
	/**
	 * 计算坐标轴范围.
	 * @param curveArea
	 * @param drawArea
	 * @return
	 */
	private Rectangle2D calculateAxisBounds(Rectangle2D curveArea, Rectangle2D drawArea, AxisPosition position, int offset,int space) {
		Rectangle2D rectangle2d = new Rectangle2D.Double();
		double leftX = drawArea.getMinX();
		double leftY = drawArea.getMinY();
		double width = drawArea.getWidth();
		double height = drawArea.getHeight();
		switch (position.type) {
		case AxisPosition.TYPE_TOP:
			leftX = drawArea.getMinX();
			leftY = drawArea.getMinY() - space - offset;
			height = 0;
			break;
		case AxisPosition.TYPE_BOTTOM:
			leftX = drawArea.getMinX();
			leftY = drawArea.getMaxY() + space + offset;
			height = 0;
			break;
		case AxisPosition.TYPE_LEFT:
			leftX = drawArea.getMinX() - space - offset;
			leftY = drawArea.getMinY();
			width = 0;
			break;

		case AxisPosition.TYPE_RIGHT:
			leftX = drawArea.getMaxX() + space + offset;
			leftY = drawArea.getMinY();
			width = 0;
			break;
		default:
			leftX = drawArea.getMinX();
			leftY = drawArea.getMaxY() + space - offset;
			height = 0;
			break;
		}
		rectangle2d = new Rectangle2D.Double(leftX, leftY, width, height);
		return rectangle2d;
	}
	
	/**
	 * 绘制刻度值.
	 */
	private void paintTickLabels(Graphics g, Rectangle2D rectangle) {
		Rectangle2D rectangle2 = new Rectangle2D.Double();
		FontMetrics fontMetrics = g.getFontMetrics();
		List<String> tickLabels = calculateTickLabels();
		int width = 20;
		int height = fontMetrics.getAscent();
		String tickLabel = "";
		for(int i = 0; i <= mainTickCount; i++) {
			tickLabel = tickLabels.get(i);
			width = fontMetrics.stringWidth(tickLabel) / 2;
			rectangle2 = calculateTickLabelBounds(rectangle, position, mainTickCount, i, width, height);
			drawTickLabel(g, rectangle2, tickLabel);
		}
	}
	
	/**
	 * 计算标签文字.
	 * @return
	 */
	protected List<String> calculateTickLabels() {
		if(tickLabels == null || tickLabels.size() == 0) {
			double spanValue = maxValue - minValue;
			double tickUnitValue = spanValue / mainTickCount;
			String labelText = "";
			for(int i = 0; i <= mainTickCount; i++) {
				labelText =format.format(minValue + i*tickUnitValue);
				tickLabels.add(labelText);
			}
		}
		return tickLabels;
	}
	
	/**
	 * 画标签.
	 * @param g
	 * @param rectangle
	 * @param text
	 */
	private void drawTickLabel(Graphics g, Rectangle2D rectangle, String text) {
		g.drawString(text, (int)rectangle.getX(), (int)rectangle.getY());
	}

	/**
	 * 绘制坐标轴.
	 */
	private void paintAxisLine(Graphics g, Rectangle2D rectangle) {
		g.setColor(Color.red);
		Line2D line2d = new Line2D.Double(rectangle.getMinX(), rectangle.getMinY(), rectangle.getMaxX(), rectangle.getMaxY());
		((Graphics2D)g).draw(line2d);
	}

	/**
	 * 绘制刻度线.
	 */
	private void paintTickMarks(Graphics g, Rectangle2D rectangle) {
		paintSubTickMarks(g, rectangle);
		paintMainTickMarks(g, rectangle);
	}

	/**
	 * 绘制副刻度线.
	 */
	private void paintSubTickMarks(Graphics g, Rectangle2D rectangle) {
		drawTickMarks(g, rectangle, subTickCount * mainTickCount, DEFAULT_SUB_TICK_LENGTH);
	}

	/**
	 * 绘制主刻度线.
	 */
	private void paintMainTickMarks(Graphics g, Rectangle2D rectangle) {
		drawTickMarks(g, rectangle, mainTickCount, DEFAULT_MAIN_TICK_LENGTH);
	}

	
	/**
	 * 画刻度线.
	 * @param g
	 * @param rectangle
	 * @param count
	 * @param height
	 */
	private void drawTickMarks(Graphics g, Rectangle2D rectangle, int count, int length) {
		Line2D line2d = new Line2D.Double();
		Rectangle2D rectangle2 = new Rectangle2D.Double();
		for(int i = 0; i <= count; i++) {
			rectangle2 = CalculateTool.calculateTickMarkBounds(rectangle, position, count, i, length);
			line2d.setLine(rectangle2.getMinX(), rectangle2.getMinY(), rectangle2.getMaxX(), rectangle2.getMaxY());
			drawTickMark(g, line2d);
		}
	}
	
	/**
	 * 计算刻度范围.
	 * @param drawArea	绘制区域
	 * @param position	坐标轴位置
	 * @param count		刻度数
	 * @param index		当前的刻度序号
	 * @param length		刻度线长度
	 * @return
	 */
	private Rectangle2D calculateTickLabelBounds(Rectangle2D drawArea, AxisPosition position, int count, int index, int widthOffset, int heigthOffset) {
		Rectangle2D rectangle2d = new Rectangle2D.Double();
		double leftX = drawArea.getMinX();
		double leftY = drawArea.getMinY();
		double width = drawArea.getWidth();
		double height = drawArea.getHeight();
		int offset = 10;
		switch (position.type) {
		case AxisPosition.TYPE_TOP:
			offset = (int) (drawArea.getWidth() / count) * index;
			leftX = drawArea.getMinX() - widthOffset + offset;
			leftY = drawArea.getMinY() - heigthOffset + DEFAULT_MAIN_TICK_LENGTH;
			width = heigthOffset * 2;
			break;
		case AxisPosition.TYPE_BOTTOM:
			offset = (int) (drawArea.getWidth() / count) * index;
			leftX = drawArea.getMinX() - widthOffset + offset;
			leftY = drawArea.getMinY() + heigthOffset + DEFAULT_MAIN_TICK_LENGTH;
			width = heigthOffset * 2;
			break;
		case AxisPosition.TYPE_LEFT:
			offset = (int) (drawArea.getHeight() / count) * (count - index);
			leftX = drawArea.getMinX() - widthOffset * 2 - DEFAULT_MAIN_TICK_LENGTH - 3;
			leftY = drawArea.getMinY() + offset + heigthOffset / 2;
			width = widthOffset * 2;
			break;
		case AxisPosition.TYPE_RIGHT:
			offset = (int) (drawArea.getHeight() / count) * (count - index);
			leftX = drawArea.getMinX() + widthOffset * 2 + DEFAULT_MAIN_TICK_LENGTH;
			leftY = drawArea.getMinY() + offset + heigthOffset / 2;
			width = widthOffset * 2;
			break;
		default:
			offset = (int) (drawArea.getWidth() / count) * index;
			leftX = drawArea.getMinX() + widthOffset;
			leftY = drawArea.getMinY() + heigthOffset * 2 + DEFAULT_MAIN_TICK_LENGTH;
			width = heigthOffset * 2;
			break;
		}
		rectangle2d = new Rectangle2D.Double(leftX, leftY, width, height);
		return rectangle2d;
	
	}
	
	
	
	/**
	 * 设置格式化方式.
	 * @param format
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
	
	/**
	 * 获取格式化方式
	 * @return
	 */
	public Format getFormat() {
		return format;
	}
	
	/**
	 * 将数据转换为坐标点.
	 * @param java2dValue
	 * @param area
	 * @return
	 */
	public double java2DToValue(double java2dValue, Rectangle2D area) {
		double axisMin = getMinValue();
		double axisMax = getMaxValue();

		double min = 0.0;
		double max = 0.0;
		// 如果坐标轴方向是横向.
		if (position.equals(AxisPosition.TOP) || position.equals(AxisPosition.BOTTOM)) {
			min = area.getX();
			max = area.getMaxX();
		} else {
			min = area.getMaxY();
			max = area.getY();
		}
		return axisMin + (java2dValue - min) / (max - min) * (axisMax - axisMin);
	}

	/**
	 * 将坐标点转换为数据.
	 * @param value
	 * @param area
	 * @return
	 */
	public double valueToJava2D(double value, Rectangle2D area) {
		double axisMax = getMaxValue();
		double axisMin = getMinValue();
		
		double min = 0.0;
		double max = 0.0;
		if (position.equals(AxisPosition.TOP) || position.equals(AxisPosition.BOTTOM)) {
			min = area.getX();
			max = area.getMaxX();
		} else {
			max = area.getMinY();
			min = area.getMaxY();
		}
		return  min + ((value - axisMin) / (axisMax - axisMin)) * (max - min);
		
	}
	
	/**
	 * 绘制刻度线.
	 * @param g
	 * @param tickMarkRectangle
	 */
	private void drawTickMark(Graphics g, Line2D line2d) {
		((Graphics2D)g).draw(line2d);
	}

	/**
	 * 设置范围.
	 * @param minValue
	 * @param maxValue
	 */
	public void setRange(double minValue, double maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	/**
	 * 设置最小值.
	 * @param minValue
	 */
	protected void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	
	/**
	 * 设置最大值.
	 * @param maxValue
	 */
	protected void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	
	/**
	 * 设置自动调整范围.
	 * @param isAutoRange
	 */
	public void setAutoRange(boolean isAutoRange) {
		this.isAutoRange = isAutoRange;
		this.maxValue = this.minValue;
	}
	
	/**
	 * 是否自动调整范围.
	 * @return
	 */
	public boolean isAutoRange() {
		return isAutoRange;
	}
	
	/**
	 * 设置主刻度数.
	 * @param mainTickCount
	 */
	public void setMainTickCount(int mainTickCount) {
		this.mainTickCount = mainTickCount;
	}
	
	/**
	 * 得到主刻度数.
	 * @return
	 */
	public int getMainTickCount() {
		return mainTickCount;
	}
	
	/**
	 * 设置副刻度数.
	 * @param subTickCount
	 */
	public void setSubTickCount(int subTickCount) {
		this.subTickCount = subTickCount;
	}
	
	/**
	 * 得到副刻度数.
	 * @return
	 */
	public int getSubTickCount() {
		return subTickCount;
	}
	
	/**
	 * 设置位置.
	 * @param position
	 */
	public void setPosition(AxisPosition position) {
		this.position = position;
	}
	
	/**
	 * 获取位置.
	 * @return
	 */
	public AxisPosition getPosition() {
		return position;
	}
	
	/**
	 * set axisInfo value.
	 * @param axisInfo
	 */
	public void setAxisInfo(AxisInfo axisInfo) {
		this.axisInfo = axisInfo;
		setupAxisInfo();
	}
	
	/**
	 * get axisInfo value
	 * @return axisInfo
	 */
	public AxisInfo getAxisInfo() {
		return axisInfo;
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//如果自动调整则设置最大最小值后自动设置范围
		if(isAutoRange) {
			if(evt.getPropertyName().equals("minValue")) {
				if(this.minValue > (Double) evt.getNewValue()) {
					this.minValue = (Double) evt.getNewValue();
					recalculateTickLabels();
				}
			} else if(evt.getPropertyName().equals("maxValue")) {
				if(this.maxValue < (Double) evt.getNewValue()) {
					this.maxValue = (Double) evt.getNewValue();
					recalculateTickLabels();
				}
			}
		}
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	
	/**
	 * 设置坐标轴属性.
	 */
	protected void setupAxisInfo() {
		if(axisInfo != null) {
			int minValue = axisInfo.getBegin();
			int maxValue = axisInfo.getEnd();
			setMinValue(minValue);
			setMaxValue(maxValue);
		}
	}
	
	private void recalculateTickLabels() {
		tickLabels.clear();
		double spanValue = maxValue - minValue;
		double tickUnitValue = spanValue / mainTickCount;
		String labelText = "";
		for(int i = 0; i <= mainTickCount; i++) {
			labelText =format.format(minValue + i*tickUnitValue);
			tickLabels.add(labelText);
		}
	}
	
	public String format(Object obj) {
		String formatString = obj.toString();
		if(format != null) {
			formatString = format.format(obj);
		}
		return formatString;
	}

	
}
