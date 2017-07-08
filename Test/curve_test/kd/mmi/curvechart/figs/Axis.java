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
 * ������.
 * @author LRY
 *
 */
/**
 * @author LRY
 *
 */
public class Axis implements PropertyChangeListener{
	
	/** ��Сֵ */
	protected double minValue = 0;
	
	/** ���ֵ*/
	protected double maxValue = 1;
	
	/** �Ƿ��Զ�������Χ */
	protected boolean isAutoRange = true;
	
	/** ���̶��ж��� */
	protected int mainTickCount = 6;
	
	/** ÿ�����̶ȵĸ��̶��ж���*/
	protected int subTickCount = 2;
	
	/** ������λ��*/
	protected AxisPosition position = AxisPosition.BOTTOM;
	
	/** ������ͻ�������Ĭ�Ϸ�Χ */
	private int DEFAULT_SPACE = 6;
	
	/** Ĭ�����̶ȵ��߳�*/
	private int DEFAULT_MAIN_TICK_LENGTH = 5;
	
	/** Ĭ�ϸ��̶ȵ��߳�*/
	private int DEFAULT_SUB_TICK_LENGTH = 3;
	
	/**  ��ʽ�� */
	protected Format format = null;
	
	/** �������Ƿ���ʾ*/
	protected boolean isShow = true;
	
	/** ʱ������Ϣ*/
	protected AxisInfo axisInfo = null;
	
	/** */
	protected List<String> tickLabels = new ArrayList<String>();

	
	public Axis(AxisPosition position) {
		this.position = position;
	}
	
	/**
	 * ���Ʒ���.
	 * @param g
	 * @param curveArea ���߻�������.
	 * @param drawArea	 ���ݻ�������.
	 * @param position	������λ��.
	 */
	public void paint(Graphics g, Rectangle2D curveArea, Rectangle2D drawArea) {
		Rectangle2D rectangle = calculateAxisBounds(curveArea, drawArea, position, 0,DEFAULT_SPACE);
		paintAxisLine(g, rectangle);
		paintTickMarks(g, rectangle);
		paintTickLabels(g, rectangle);
	}
	
	/**
	 * ���������᷶Χ.
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
	 * ���ƿ̶�ֵ.
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
	 * �����ǩ����.
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
	 * ����ǩ.
	 * @param g
	 * @param rectangle
	 * @param text
	 */
	private void drawTickLabel(Graphics g, Rectangle2D rectangle, String text) {
		g.drawString(text, (int)rectangle.getX(), (int)rectangle.getY());
	}

	/**
	 * ����������.
	 */
	private void paintAxisLine(Graphics g, Rectangle2D rectangle) {
		g.setColor(Color.red);
		Line2D line2d = new Line2D.Double(rectangle.getMinX(), rectangle.getMinY(), rectangle.getMaxX(), rectangle.getMaxY());
		((Graphics2D)g).draw(line2d);
	}

	/**
	 * ���ƿ̶���.
	 */
	private void paintTickMarks(Graphics g, Rectangle2D rectangle) {
		paintSubTickMarks(g, rectangle);
		paintMainTickMarks(g, rectangle);
	}

	/**
	 * ���Ƹ��̶���.
	 */
	private void paintSubTickMarks(Graphics g, Rectangle2D rectangle) {
		drawTickMarks(g, rectangle, subTickCount * mainTickCount, DEFAULT_SUB_TICK_LENGTH);
	}

	/**
	 * �������̶���.
	 */
	private void paintMainTickMarks(Graphics g, Rectangle2D rectangle) {
		drawTickMarks(g, rectangle, mainTickCount, DEFAULT_MAIN_TICK_LENGTH);
	}

	
	/**
	 * ���̶���.
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
	 * ����̶ȷ�Χ.
	 * @param drawArea	��������
	 * @param position	������λ��
	 * @param count		�̶���
	 * @param index		��ǰ�Ŀ̶����
	 * @param length		�̶��߳���
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
	 * ���ø�ʽ����ʽ.
	 * @param format
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
	
	/**
	 * ��ȡ��ʽ����ʽ
	 * @return
	 */
	public Format getFormat() {
		return format;
	}
	
	/**
	 * ������ת��Ϊ�����.
	 * @param java2dValue
	 * @param area
	 * @return
	 */
	public double java2DToValue(double java2dValue, Rectangle2D area) {
		double axisMin = getMinValue();
		double axisMax = getMaxValue();

		double min = 0.0;
		double max = 0.0;
		// ��������᷽���Ǻ���.
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
	 * �������ת��Ϊ����.
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
	 * ���ƿ̶���.
	 * @param g
	 * @param tickMarkRectangle
	 */
	private void drawTickMark(Graphics g, Line2D line2d) {
		((Graphics2D)g).draw(line2d);
	}

	/**
	 * ���÷�Χ.
	 * @param minValue
	 * @param maxValue
	 */
	public void setRange(double minValue, double maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	/**
	 * ������Сֵ.
	 * @param minValue
	 */
	protected void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	
	/**
	 * �������ֵ.
	 * @param maxValue
	 */
	protected void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	
	/**
	 * �����Զ�������Χ.
	 * @param isAutoRange
	 */
	public void setAutoRange(boolean isAutoRange) {
		this.isAutoRange = isAutoRange;
		this.maxValue = this.minValue;
	}
	
	/**
	 * �Ƿ��Զ�������Χ.
	 * @return
	 */
	public boolean isAutoRange() {
		return isAutoRange;
	}
	
	/**
	 * �������̶���.
	 * @param mainTickCount
	 */
	public void setMainTickCount(int mainTickCount) {
		this.mainTickCount = mainTickCount;
	}
	
	/**
	 * �õ����̶���.
	 * @return
	 */
	public int getMainTickCount() {
		return mainTickCount;
	}
	
	/**
	 * ���ø��̶���.
	 * @param subTickCount
	 */
	public void setSubTickCount(int subTickCount) {
		this.subTickCount = subTickCount;
	}
	
	/**
	 * �õ����̶���.
	 * @return
	 */
	public int getSubTickCount() {
		return subTickCount;
	}
	
	/**
	 * ����λ��.
	 * @param position
	 */
	public void setPosition(AxisPosition position) {
		this.position = position;
	}
	
	/**
	 * ��ȡλ��.
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
		//����Զ����������������Сֵ���Զ����÷�Χ
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
	 * ��������������.
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
