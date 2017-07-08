/**
 * 
 */
package swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 * @author HMI-Lenovo
 * 
 */
public class FigTextUI extends ComponentUI {

	public static ComponentUI createUI(JComponent c) {
		return new FigTextUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#paint(java.awt.Graphics,
	 * javax.swing.JComponent)
	 */
	@Override
	public void paint(Graphics g, JComponent c) {
		System.out.println(c);
		Graphics g2 = g.create();
		g2.setColor(Color.BLACK);
		g2.drawString("Hello World!", 10, 10);
		g2.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#installUI(javax.swing.JComponent)
	 */
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
	}

	public void uninstallUI(JComponent c) {
		super.uninstallUI(c);
	}

}
