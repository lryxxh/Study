package net.channles;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class ServerSocketTime_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(6666));
			final ByteBuffer src = ByteBuffer.allocate(1024 * 1024 * 200);
			for(int i = 0; i < src.capacity(); i++){
				if(i == 1000 || i == src.capacity() - 1) {
					src.put((byte)127);
					System.out.println((byte)127);
				} else {
					src.put((byte)i);
				}
			}
			src.flip();
			while(true) {
				final SocketChannel socketChannel = serverSocketChannel.accept();
				new Thread() {
					public void run() {
						try {
							socketChannel.write(src.duplicate());
							socketChannel.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
			 
				}.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
