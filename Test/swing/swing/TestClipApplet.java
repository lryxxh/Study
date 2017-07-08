package swing;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestClipApplet extends JApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		this.setSize(400, 300);
		getRootPane().setBackground(Color.red);
		setBackground(Color.red);
		super.init();
		JPanel panel = new JPanel();
		getRootPane().add(panel);
		Container container = SwingUtilities.getAncestorOfClass(Applet.class, panel);
		System.out.println("container:" + container);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("helloworld", 10, 10);
		g.setColor(Color.red);
		g.fillRect(50, 50, 50, 50);
		System.out.println(g.getClipBounds());
	}
}
