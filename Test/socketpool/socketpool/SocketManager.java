package socketpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SocketManager {
	
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(50000);
			while(true) {
				final Socket socket = serverSocket.accept();
				new Thread() {
					public void run() {
						ByteBuffer buffer = ByteBuffer.allocate(4);
						try {
							socket.getInputStream().read(buffer.array());
//							int a = buffer.getInt();
							socket.getOutputStream().write(buffer.array());
						} catch (IOException e) {
							e.printStackTrace();
						}
					};
				}.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
