package socketpool;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Socket_LRY {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0; i< 10000;i++) {
			try {
				Socket socket = new Socket("127.0.0.1",50000);
				Thread.sleep(1000);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
