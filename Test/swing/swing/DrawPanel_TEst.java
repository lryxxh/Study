package swing;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DrawPanel_TEst extends JPanel{

	T t = new T();
	Image image = Toolkit.getDefaultToolkit().getImage("./image.jpg");
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform aTransform = g2.getTransform();
		aTransform.setToRotation(Math.PI/3);
		g2.setTransform(aTransform);
		t.paint(g2, this);
//		g2.drawImage(image, 0, 0, this);
		updateUI();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
//		frame.setIconImage(new T().image);
		JPanel panel = new DrawPanel_TEst();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,768);
		frame.setVisible(true);
	}
	
}

class T {
	Image image = Toolkit.getDefaultToolkit().getImage("./image.jpg");

	public void paint(Graphics g, JPanel panel) {
		g.drawImage(image, 0, 0, panel);
	}
}