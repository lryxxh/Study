package demail.com.kd.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestImage {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setVisible(true);

		BufferedImage image = new BufferedImage(20, 20,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.setColor(Color.red);
		g.fillOval(0, 0, 20, 20);
		g.dispose();
		try {
			ImageIO.write(image, "jpg", new FileOutputStream("C:\\ss"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.getContentPane().add(new JLabel("ss"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
