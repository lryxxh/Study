package net;

import java.awt.Image;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class ImageServerSocket {
	
	public static void main(String[] args) {
		try {
			ServerSocket serversocket = new ServerSocket(30000);
			Socket socket = serversocket.accept();
			Image image = ImageIO.read(socket.getInputStream());
			System.out.println(image);
			Thread.sleep(3000);
			System.out.println("-------server end");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
