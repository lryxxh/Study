package net.socketwrite_test;

import java.io.IOException;
import java.net.Socket;

public class Client_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",50000);
			while(true) {
				int a = socket.getInputStream().read();
				System.out.println(a);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	

	}

}
