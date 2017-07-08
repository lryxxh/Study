package curve.kd.mmi.curve.jfreechart.enums;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.custom.renderer.CustomXYLineShapeRenderer;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;

/**
 * ��������.
 * @author ������
 *
 */
public enum CurveStyleEnum {
	
	NULL_STYLE(0),//��
	NORMAL_LINE_STYLE(1),//ֱ��
	BIAS_LINE_STYLE(2),//ƫ����
	STEP_LINE_STYLE(3),//̨����
	DOT_STYLE(4),//��
	GRADUAL__LINESTYLE(5);//������
	
	/**
	 * ������
	 * @param style.
	 */
	private CurveStyleEnum(int style){
		this.style = style;
	}
	
	/**
	 * ͨ��������ʽNo�õ�������ʽ.
	 * @param style
	 * @return
	 */
	public static XYItemRenderer getRendererByStyle(int style, int type) {
		XYItemRenderer renderer = null;
		Shape shape = new Area(new Rectangle2D.Double(-4.0D, -3.0D, 8.0D, 6.0D));
		switch (style) {
		case 0:
			renderer = new XYLineAndShapeRenderer();
			((XYLineAndShapeRenderer)renderer).setBaseShapesVisible(false);
			((XYLineAndShapeRenderer)renderer).setLegendLine(shape);
			((XYLineAndShapeRenderer)renderer).setBaseShapesFilled(true);
			
			break;
		case 1:
			renderer = new XYLineAndShapeRenderer();
			((XYLineAndShapeRenderer)renderer).setBaseShapesVisible(false);
			((XYLineAndShapeRenderer)renderer).setLegendLine(shape);
			((XYLineAndShapeRenderer) renderer).setBaseShapesFilled(true);
			break;
		case 2:
			renderer = new StandardXYItemRenderer();//��δ ʵ��
			((StandardXYItemRenderer)renderer).setBaseShapesVisible(false);
			((StandardXYItemRenderer)renderer).setLegendLine(shape);
			((StandardXYItemRenderer) renderer).setBaseShapesFilled(true);
			break;
		case 3:
			renderer = new XYStepRenderer();
			((XYStepRenderer)renderer).setBaseShapesVisible(false);
			((XYStepRenderer)renderer).setLegendLine(shape);
			((XYStepRenderer) renderer).setBaseShapesFilled(true);
			break;
		case 4:
			renderer = new StandardXYItemRenderer();
			((StandardXYItemRenderer)renderer).setBaseShapesVisible(false);
			((StandardXYItemRenderer)renderer).setLegendLine(shape);
			((StandardXYItemRenderer) renderer).setBaseShapesFilled(true);
			break;
		case 5:
			renderer = new StandardXYItemRenderer();
			((StandardXYItemRenderer)renderer).setBaseShapesVisible(false);
			((StandardXYItemRenderer)renderer).setLegendLine(shape);
			((StandardXYItemRenderer) renderer).setBaseShapesFilled(true);
			break;
		default :
			renderer = new XYLineAndShapeRenderer();
			((XYLineAndShapeRenderer)renderer).setBaseShapesVisible(false);
			((XYLineAndShapeRenderer)renderer).setLegendLine(shape);
			((XYLineAndShapeRenderer) renderer).setBaseShapesFilled(true);
			break;
		
		}
		renderer = new CustomXYLineShapeRenderer();
		((CustomXYLineShapeRenderer)renderer).setBaseShapesVisible(false);
		((CustomXYLineShapeRenderer)renderer).setLegendLine(shape);
		((CustomXYLineShapeRenderer) renderer).setBaseShapesFilled(true);
		if (type == 0) {
			renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		} else {
			renderer.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
		}
		return renderer;
	}
	
	private int style;

}
