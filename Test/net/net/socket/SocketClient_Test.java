package net.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SocketClient_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("127.0.0.1", 40000);
			while(true) {
				byte[] bytes = new byte[4];
				int a = socket.getInputStream().read(bytes);
				if(a== -1) {
					System.out.println("read -1");
					testread();
				}
				System.out.println("read length " + a + " read value " + Arrays.toString(bytes));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void testread() {
		testread();
	}

}
