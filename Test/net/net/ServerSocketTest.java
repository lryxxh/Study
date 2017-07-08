package net;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket();
			server.bind(new InetSocketAddress("127.0.0.1", 50000));
			while (true) {
				Socket socket = server.accept();
				System.out.println(socket);
				socket.getOutputStream().write(new byte[]{1,1,1,1});
//				Thread.sleep(2000);
				socket.getOutputStream().write(new byte[]{1,1});
//				Thread.sleep(2000);
				socket.getOutputStream().write(new byte[]{1,1});
//				Thread.sleep(2000);
				socket.getOutputStream().write(new byte[]{1,1});
//				Thread.sleep(2000);
				socket.getOutputStream().write(new byte[]{1,1});
//				Thread.sleep(2000);
				socket.getOutputStream().write(new byte[]{1,1});
//				Thread.sleep(2000);
				socket.getOutputStream().write(new byte[]{1});
//				Thread.sleep(5000);
				socket.getOutputStream().write(new byte[]{1});
				byte[] dd = new byte[30];
				for(int i= 0;i<dd.length;i++) {
					dd[i] = (byte) i;
				}
				socket.getOutputStream().write(dd);
//				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
