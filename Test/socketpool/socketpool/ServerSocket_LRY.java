package socketpool;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocket_LRY {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(50000,10);
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
