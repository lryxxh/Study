package design.com.lry.jfree.chart.design.factory;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PaintGradientComponentFactory {

	public static void paintGradientComponent(String actionCommand,
			Component component, Color _color1, Color _color2) {
		Graphics2D g2 = (Graphics2D) component.getGraphics().create();
		Rectangle rect = component.getBounds();
		int no = Integer.parseInt(actionCommand);
		switch (no) {
		case 0:
			g2.setPaint(new GradientPaint((float) rect.x, (float) rect.y,
					_color1, (float) rect.x + (float) rect.width,
					(float) rect.y, _color2));
			g2.fill(rect);
			break;
		case 1:
			g2.setPaint(new GradientPaint((float) rect.x + (float) rect.width,
					(float) rect.y, _color1, (float) rect.x, (float) rect.y,
					_color2));
			g2.fill(rect);
			break;
		case 2:
			g2.setPaint(new GradientPaint((float) rect.x, (float) rect.y,
					_color1, (float) rect.x, (float) rect.y
							+ (float) rect.height, _color2));
			g2.fill(rect);
			break;
		case 3:
			g2.setPaint(new GradientPaint((float) rect.x, (float) rect.y
					+ (float) rect.height, _color1, (float) rect.x,
					(float) rect.y, _color2));
			g2.fill(rect);
			break;
		case 4:
			// Paint the top-left rect.
			g2.setPaint(new GradientPaint((float) rect.x + (float) rect.width
					/ 2, (float) rect.y + (float) rect.height / 2, _color1,
					(float) rect.x, (float) rect.y, _color2));
			g2.fill(new Rectangle(rect.x, rect.y, rect.width / 2,
					rect.height / 2));
			// Paint the top-right rect.
			g2.setPaint(new GradientPaint((float) rect.x + (float) rect.width
					/ 2, (float) rect.y + (float) rect.height / 2, _color1,
					(float) rect.x + (float) rect.width, (float) rect.y,
					_color2));
			g2.fill(new Rectangle(rect.x + rect.width / 2, rect.y,
					rect.width / 2, rect.height / 2));
			// Paint the bottom-left rect.
			g2.setPaint(new GradientPaint((float) rect.x + (float) rect.width
					/ 2, (float) rect.y + (float) rect.height / 2, _color1,
					(float) rect.x, (float) rect.y + (float) rect.height / 2,
					_color2));
			g2.fill(new Rectangle(rect.x, rect.y + rect.height / 2,
					rect.width / 2, rect.height / 2));
			// Paint the bottom-right rect.
			g2.setPaint(new GradientPaint((float) rect.x + (float) rect.width
					/ 2, (float) rect.y + (float) rect.height / 2, _color1,
					(float) rect.x + (float) rect.width, (float) rect.y
							+ (float) rect.height, _color2));
			g2.fill(new Rectangle(rect.x + rect.width / 2, rect.y + rect.height
					/ 2, rect.width / 2, rect.height / 2));
			break;

		}
		g2.dispose();
	}
}
