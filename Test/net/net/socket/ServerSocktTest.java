package net.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocktTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(40000);
			while(true) {
				final Socket socket = server.accept();
				new Thread() {
					public void run() {
						for(int i = 0; i < 10;i++) {
							try {
								socket.getOutputStream().write(new byte[]{1});
							} catch (IOException e) {
								e.printStackTrace();
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("sdfsdfs stop");
					};
				}.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
