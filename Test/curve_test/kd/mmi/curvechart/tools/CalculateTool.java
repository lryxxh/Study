package kd.mmi.curvechart.tools;

import java.awt.geom.Rectangle2D;

import kd.mmi.curvechart.figs.AxisPosition;

public class CalculateTool {

	/**
	 * ���㷶Χ.
	 * @param drawArea	��������
	 * @param position	������λ��
	 * @param count		�̶���
	 * @param index		��ǰ�Ŀ̶����
	 * @param length		�̶��߳���
	 * @return
	 */
	public static Rectangle2D calculateTickMarkBounds(Rectangle2D drawArea, AxisPosition position, int count, int index, int length) {
		Rectangle2D rectangle2d = new Rectangle2D.Double();
		double leftX = drawArea.getMinX();
		double leftY = drawArea.getMinY();
		double width = drawArea.getWidth();
		double height = drawArea.getHeight();
		double offset = 0;
		switch (position.type) {
		case AxisPosition.TYPE_TOP:
			offset = (drawArea.getWidth() / count) * index;
			leftX = drawArea.getMinX() + offset;
			leftY = drawArea.getMinY() - length;
			height = length;
			width = 0;
			break;
		case AxisPosition.TYPE_BOTTOM:
			offset = (drawArea.getWidth() / count) * index;
			leftX = drawArea.getMinX()+ offset;
			leftY = drawArea.getMinY();
			height = length;
			width = 0;
			break;
		case AxisPosition.TYPE_LEFT:
			offset =  (drawArea.getHeight() / count) * index;
			leftX = drawArea.getMinX() - length;
			leftY = drawArea.getMinY() + offset;
			width = length;
			height = 0;
			break;

		case AxisPosition.TYPE_RIGHT:
			offset = (drawArea.getHeight() / count) * index;
			leftX = drawArea.getMinX();
			leftY = drawArea.getMinY() + offset;
			width = length;
			height = 0;
			break;
		default:
			offset = (drawArea.getWidth() / count) * index;
			leftX = drawArea.getMinX()+ offset;
			leftY = drawArea.getMinY();
			height = length;
			width = 0;
			break;
		}
		rectangle2d = new Rectangle2D.Double(leftX, leftY, width, height);
		return rectangle2d;
	
	}
	

}
