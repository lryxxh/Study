package net.socketwrite_test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(50000);
			Socket socket = serverSocket.accept();
			
			OutputStream os = socket.getOutputStream();
			byte[] data = new byte[1024 * 1024 * 100];
			os.write(data);
			System.out.println("over");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
