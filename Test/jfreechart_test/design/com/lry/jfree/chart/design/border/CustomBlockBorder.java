package design.com.lry.jfree.chart.design.border;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.block.BlockBorder;
import org.jfree.ui.RectangleInsets;

public class CustomBlockBorder extends BlockBorder{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2396319124921339899L;

	@Override
	public void draw(Graphics2D g2, Rectangle2D area) {
		Graphics2D graphics2d = (Graphics2D) g2.create();
		super.draw(graphics2d, area);
		int x = (int) area.getMinX();
		int y = (int) area.getMinY();
		int width = (int) area.getMaxX();
		int height = (int) area.getMaxY();
		graphics2d.setPaint(Color.BLUE);
//		g2.translate(x, y);
		
		graphics2d.fillRect(x, y, 8, 8);
		graphics2d.fillRect(-8+ width, y, 8, 8);
		graphics2d.fillRect(x, -8 + height, 8, 8);
		graphics2d.fillRect(-8 +width, height - 8, 8, 8);
		
//		g2.translate(-x, -y);		
		graphics2d.dispose();
	}
	
	@Override
	public RectangleInsets getInsets() {
		return new RectangleInsets(0, 0, 0, 0);
	}

}
