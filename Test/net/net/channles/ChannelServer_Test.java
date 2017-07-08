/**
 * ChannelsServer_Test.java
 * Created by liurenyong at 2013-10-10
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.channles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author liurenyong 2013-10-10
 */
public class ChannelServer_Test {

	public ChannelServer_Test() {
		new Thread() {
			public void run() {

				try {
					Selector selector = Selector.open();
					ServerSocketChannel serverSocketChannel = ServerSocketChannel
							.open();
					serverSocketChannel.configureBlocking(false);
					serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
					serverSocketChannel.socket().bind(
							new InetSocketAddress("localhost", 12345));
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(System.in));
					outter: while (true) {
						while (selector.select() > 0) {
							// while(selector.select() > 0) {
							//
							// }
							System.out.println("----");
							Set<SelectionKey> keys = selector.selectedKeys();
							Iterator<SelectionKey> item = keys.iterator();
							// while(true) {
							while (item.hasNext()) {
								SelectionKey key = item.next();
								item.remove();
								System.out.println(key.isAcceptable() + " "
										+ key.isConnectable() + "  "
										+ key.isReadable() + " "
										+ key.isWritable());
								if(key.isAcceptable()) {
									SocketChannel socketChannel = ((ServerSocketChannel)key.channel()).accept();
									socketChannel.configureBlocking(false);
									socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
								}
								
								if (key.isWritable()) {
									System.out
											.println("------------------write--------------------");
//									String line = reader.readLine();
									StringBuilder line = new StringBuilder();
									for(int i= 0; i < 50000;i++) {
										line.append("10000000000000000000");
									}
									System.err.println("length "+line.toString().getBytes().length);
									((SocketChannel) key.channel())
											.write(ByteBuffer.wrap(line.toString().getBytes()));
									((SocketChannel) key.channel()).register(selector, SelectionKey.OP_READ);
								}
								if (key.isReadable()) {
									System.out
											.println("------------------read--------------------");
									ByteBuffer byteBuffer = ByteBuffer
											.allocate(10);
									int count = ((SocketChannel) key.channel())
											.read(byteBuffer);
									System.out.println("read num " + count
											+ " " + byteBuffer.array()[0]
											+ "  " + byteBuffer.array()[1]
											+ " " + byteBuffer.array()[2]);
									// break outter;
								}
								// System.out.println(key + "  " +
								// key.isReadable() + " " + key.isWritable() +
								// " " + key.isConnectable());
								// }

								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	public static void main(String[] args) {
		new ChannelServer_Test();
	}

}
