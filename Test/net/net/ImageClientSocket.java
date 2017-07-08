package net;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageClientSocket {

	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 30000);
			Image image = new ImageIcon("E:/Program Files/WorkSpace/Work/eclipse_test/WorkSpace/Test/Bodie_small.png").getImage();
			ImageIO.write((RenderedImage)image, "", socket.getOutputStream());
			System.out.println("flush");
			Thread.sleep(3000);
			System.out.println("-------client end");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
