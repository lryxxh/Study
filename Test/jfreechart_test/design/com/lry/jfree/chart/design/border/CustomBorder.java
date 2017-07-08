package design.com.lry.jfree.chart.design.border;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class CustomBorder extends AbstractBorder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6150014562369957553L;

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		
		g.translate(x, y);
		
		g.fillRect(-8, -8, 8, 8);
		g.fillRect(-8 + width, -8, 8, 8);
		g.fillRect(-8, -8 + height, 8, 8);
		g.fillRect(-8 +width, height - 8, 8, 8);
		
		g.translate(-x, -y);		
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(0, 0, 0, 0);
	}
	
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		return super.getBorderInsets(c, insets);
	}
	
	@Override
	public boolean isBorderOpaque() {
		return false;
	}

}
