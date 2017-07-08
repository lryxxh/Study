package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketBufferTest {

	static Socket socket = null;

	public static void main(String[] args) {
		try {
			new Thread() {
			public void run() {
				try {
					ServerSocket serverSocket = new ServerSocket(55555);
					while(true) {
						Socket socket = serverSocket.accept();
						System.out.println(socket);
						while(true) {
							InputStream inputStream = socket.getInputStream();
							byte[] data = new byte[100];
							int length = inputStream.read(data);
							System.err.println(new String(data).trim());
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			};				
			}.start();
			socket = new Socket("localhost", 55555);
			final BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));

			new Thread() {
				public void run() {
					while (true) {
						try {
							socket.getOutputStream().write(
									reader.readLine().getBytes());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				};
			}.start();

			new Thread() {
				public void run() {
					try {
						while(true) {
							byte[] data = new byte[10];
							int op = socket.getInputStream().read(data);
							System.out.println(new String(data).trim());
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				};
			}.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
