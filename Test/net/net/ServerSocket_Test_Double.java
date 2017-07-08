/**
 * 
 */
package net;

import java.net.Socket;


/**
 * @author HMI-Lenovo
 *
 */
public class ServerSocket_Test_Double {

	/**
	 * @param args
	 */
	public synchronized static void main(String[] args) {
		try{
//			int a = 13;
//			Number d = a;
//			System.out.println(d);
			Socket socket = new Socket("192.168.60.21", 51012);
			System.out.println(socket);
//			int a = socket.getInputStream().read();
//			System.out.println(a);
//			ByteBuffer buffer = ByteBuffer.allocate(4);
//			buffer.putFloat(1.2343345353343f);
//			socket.getOutputStream().write(buffer.array());
//			
//			socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

}
