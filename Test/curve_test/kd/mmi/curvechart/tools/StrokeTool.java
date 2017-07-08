package kd.mmi.curvechart.tools;

import java.awt.BasicStroke;
import java.awt.Stroke;

import kd.mmi.curvechart.enums.LineStyleEnum;

public class StrokeTool {
	
	/**
	 * ´´½¨»­±Ê.
	 * @param width
	 * @param lineStyle
	 * @return
	 */
	public static Stroke createStroke(float width, int lineStyle) {
		float[]  dash = LineStyleEnum.getEnumByNo(lineStyle).getDash();
		Stroke stroke = new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0);
		return stroke;
	}

}
