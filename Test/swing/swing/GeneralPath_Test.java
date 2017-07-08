package swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jndi.url.ldaps.ldapsURLContextFactory;

public class GeneralPath_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 600);
		JPanel panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				GeneralPath path = new GeneralPath();
				path.moveTo(10, 10);
				path.lineTo(100, 10);
				path.moveTo(10, 20);
				path.lineTo(100, 20);
				path.moveTo(10, 30);
				path.lineTo(100, 30);
				((Graphics2D)g).draw(path);
				
			}
		};
		frame.getContentPane().add(panel);
		frame.setVisible(true);

	}

}
