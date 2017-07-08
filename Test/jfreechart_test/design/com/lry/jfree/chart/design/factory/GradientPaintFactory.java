package design.com.lry.jfree.chart.design.factory;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.Shape;

public class GradientPaintFactory {

	public static Paint getGradientPaint(Shape shape, int type, Color _color1,
			Color _color2) {
		Paint paint = Color.LIGHT_GRAY;
		Rectangle rect = shape.getBounds();
		double radius = rect.getWidth() > rect.getHeight() ? rect.getWidth() : rect.getHeight();
		switch (type) {
		case 0:
			paint = new GradientPaint((float) rect.x, (float) rect.y, _color1, (float) rect.x + (float) rect.width, (float) rect.y, _color2);
			break;
		case 1:
			paint = new GradientPaint((float) rect.x + (float) rect.width, (float) rect.y, _color1, (float) rect.x, (float) rect.y, _color2);
			break;
		case 2:
			paint = new GradientPaint((float) rect.x, (float) rect.y, _color1, (float) rect.x, (float) rect.y + (float) rect.height, _color2);
			break;
		case 3:
			paint = new GradientPaint((float) rect.x, (float) rect.y + (float) rect.height, _color1, (float) rect.x, (float) rect.y, _color2);
			break;
		case 4:
			paint = new RadialGradientPaint((float)rect.getCenterX(), (float)rect.getCenterY(), (float)radius / 2, new float[]{0.0f, 1.0f}, new Color[]{_color1, _color2});
			break;
		case 5:
			paint = new RadialGradientPaint((float)rect.getCenterX(), (float)rect.getCenterY(), (float)radius / 2, new float[]{0.0f, 1.0f}, new Color[]{_color2, _color1});
			break;
		}
		return paint;

	}

}
