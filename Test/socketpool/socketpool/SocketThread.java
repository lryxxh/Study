package socketpool;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class SocketThread extends Thread {
	
//	ThreadLocal send = new ThreadLocal<byte[]>();
//	ThreadLocal receive = new ThreadLocal<byte[]>();

	Socket socket = null;
	public SocketThread(ThreadGroup group, Runnable target, String name,
            long stackSize) {
		super(group, target, name, stackSize);
		try {
			socket = new Socket("127.0.0.1", 50000);
			socket.setSoLinger(true, 0);
			System.err.println("new socket " + name + " hashCode" + socket.hashCode());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			super.run();
		} finally {
			try {
				socket.close();
				System.out.println(Thread.currentThread().getState() + " socket close! " + socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void send(byte[] data) {
		try {
			socket.getOutputStream().write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receive() {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		try {
			socket.getInputStream().read(buffer.array());
			System.out.println(buffer.getInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
