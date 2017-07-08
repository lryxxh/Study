package net.channles;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTime_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			new Thread() {
//				public void run() {
//					try {
//						ByteBuffer buffer = ByteBuffer
//								.allocate(1024 * 1024 * 50);
//						long time = System.currentTimeMillis();
//						for (int i = 0; i < 10; i++) {
//							SocketChannel socketChannel = SocketChannel.open();
//							socketChannel.connect(new InetSocketAddress(
//									"192.168.200.211", 6666));
//							// "127.0.0.1", 6666));
//
//							while (buffer.position() < buffer.capacity()) {
//								socketChannel.read(buffer);
//							}
//							System.err.println(i + "  "
//									+ (System.currentTimeMillis() - time)
//									+ " :" + buffer.get(1000) + " "
//									+ buffer.get(buffer.capacity() - 1));
//							printMemory();
//							buffer.clear();
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				};
//			}.start();

			new Thread() {
				public void run() {
					byte[] tt = new byte[1024 * 1024 * 50];
					byte[] data = new byte[1024 * 1024];
					long time = System.currentTimeMillis();
					try {
						for (int i = 0; i < 10; i++) {
							Socket socket;
							socket = new Socket("192.168.200.211", 6666);
							// socket = new Socket("127.0.0.1", 6666);
							InputStream inputStream = socket.getInputStream();
					
							int offset = 0;
							int count = 1;
							while (offset < 1024 * 1024 * 50) {
								int read = inputStream.read(data);
								System.arraycopy(data, 0, tt, offset, read);
								offset += read;
							}
							printMemory();
							System.out
									.println(i
											+ " "
											+ (System.currentTimeMillis() - time)
											+ " :" + tt[1000] + " "
											+ tt[tt.length - 1]);

						}
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printMemory() {
		Runtime runtime = Runtime.getRuntime();
		long totalMemory = runtime.totalMemory();
		long maxMemory = runtime.maxMemory();
		long freeMemory = runtime.freeMemory();
		System.out.println("max:" + maxMemory + "  total:" + totalMemory
				+ " use:" + (totalMemory - freeMemory));
	}
}
