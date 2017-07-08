/**
 * 
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author HMI-Lenovo
 *
 */
public class Graphics_Clip_Test {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(){
			/* (non-Javadoc)
			 * @see java.awt.Container#paintComponents(java.awt.Graphics)
			 */
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponents(g);
				Graphics2D graphics2d = (Graphics2D) g.create();
				graphics2d.setClip(0,0,200,200);
				graphics2d.setColor(Color.BLACK);
				graphics2d.fillOval(0, 0, 500, 500);
//				g.drawString("HelloWorld", 10, 10);
			}
		};
		frame.setSize(800,600);
		frame.setLayout(new BorderLayout());
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

}
