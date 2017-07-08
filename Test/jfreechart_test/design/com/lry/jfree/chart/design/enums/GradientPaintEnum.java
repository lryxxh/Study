package design.com.lry.jfree.chart.design.enums;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public enum GradientPaintEnum {
	LEFT_TO_RIGHT("0"),
	RIGHT_TO_LEFT("1"),
	TOP_TO_BOTTOM("2"),
	BOTTOM_TO_UP("3"),
	COLOR_RADIATE("4"),
	COLOR_FOCUS("5");
	
	private GradientPaintEnum(String actionCommand) {
		this.actionCommand = actionCommand;
	}
	
	private String actionCommand = "0";
	
	public GradientPaintEnum getGradientPaintEnumByActionCommand(String actionCommand) {
		GradientPaintEnum paintEnum = LEFT_TO_RIGHT;
		int no = Integer.parseInt(actionCommand);
		switch (no) {
		case 1:
			paintEnum = RIGHT_TO_LEFT;
			break;
		case 2:
			paintEnum = TOP_TO_BOTTOM;
			break;
		case 3:
			paintEnum = BOTTOM_TO_UP;
			break;
		case 4:
			paintEnum = COLOR_RADIATE;
			break;
		case 5:
			paintEnum = COLOR_FOCUS;
			break;
		}
		return paintEnum;
	}
	
	public Paint paintComponentByEnum(Graphics2D graphics2d, Shape shape, Color _color1, Color _color2, int x, int y, int width, int height) {
		Graphics2D g2 = graphics2d;
		Paint paint = Color.LIGHT_GRAY;
		Point startPoint = new Point(x, y);
		Point endPoint = new Point(x + width, y + height); 
		Rectangle rect = shape.getBounds();
		int no = Integer.parseInt(actionCommand);
		switch (no) {
		  case 0:
			  paint = new GradientPaint(startPoint, _color1, endPoint, _color2);
		        break;
		    case 1:
		        g2.setPaint(new GradientPaint((float)rect.x+(float)rect.width, (float)rect.y, _color1,
		                                      (float)rect.x, (float)rect.y, _color2));
		        break;
		      case 2:
		        g2.setPaint(new GradientPaint((float)rect.x, (float)rect.y, _color1,
		                                      (float)rect.x, (float)rect.y+(float)rect.height, _color2));
		        break;
		      case 3:
		        g2.setPaint(new GradientPaint((float)rect.x, (float)rect.y+(float)rect.height, _color1,
		                                      (float)rect.x, (float)rect.y, _color2));
		        break;
		      case 4:
		        //Paint the top-left rect.
		        g2.setPaint(new GradientPaint((float)rect.x+(float)rect.width/2, (float)rect.y+(float)rect.height/2, _color1,
		                                      (float)rect.x, (float)rect.y, _color2));
		        g2.fill(new Rectangle(rect.x, rect.y, rect.width/2, rect.height/2));
		        //Paint the top-right rect.
		        g2.setPaint(new GradientPaint((float)rect.x+(float)rect.width/2, (float)rect.y+(float)rect.height/2, _color1,
		                                      (float)rect.x+(float)rect.width, (float)rect.y, _color2));
		        g2.fill(new Rectangle(rect.x+rect.width/2, rect.y, rect.width/2, rect.height/2));
		        //Paint the bottom-left rect.
		        g2.setPaint(new GradientPaint((float)rect.x+(float)rect.width/2, (float)rect.y+(float)rect.height/2, _color1,
		                                      (float)rect.x, (float)rect.y+(float)rect.height/2, _color2));
		        g2.fill(new Rectangle(rect.x, rect.y+rect.height/2, rect.width/2, rect.height/2));
		        //Paint the bottom-right rect.
		        g2.setPaint(new GradientPaint((float)rect.x+(float)rect.width/2, (float)rect.y+(float)rect.height/2, _color1,
		                                      (float)rect.x+(float)rect.width, (float)rect.y+(float)rect.height, _color2));
		        g2.fill(new Rectangle(rect.x+rect.width/2, rect.y+rect.height/2, rect.width/2, rect.height/2));
		}
		return paint;
	}
	
}
