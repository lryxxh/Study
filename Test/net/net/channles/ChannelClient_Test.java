/**
 * ChannelClient_Test.java
 * Created by liurenyong at 2014-1-3
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
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author liurenyong 2014-1-3
 */
public class ChannelClient_Test {

    public ChannelClient_Test() {
        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 12345));
            socketChannel.configureBlocking(true);
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            ByteBuffer buffer = ByteBuffer.allocate(20000000);
            while(true) {
            	
            	
            	while(selector.select() > 0) {
            		Set<SelectionKey> selectionKeys = selector.selectedKeys();
            		Iterator<SelectionKey> it = selectionKeys.iterator();
            		SelectionKey selectionKey = null;
            		while(it.hasNext()) {
            			selectionKey = it.next();
            			if(selectionKey.isReadable()) {
            				((SocketChannel)selectionKey.channel()).read(buffer);
            				System.out.println(buffer.position() + "receive "+new String(buffer.array()));
            			}
            			buffer.clear();
            			
//            			if(selectionKey.isWritable()) {
//            				line = reader.readLine();
//            				((SocketChannel)selectionKey.channel()).write(ByteBuffer.wrap(line.getBytes()));
//            			}
            		}
            	}
//            	ByteBuffer buffer = ByteBuffer.allocate(4);
//            	socketChannel.read(buffer);
//            	
//            	System.out.println("Client receive"+new String(buffer.array()));
//
//            	line = reader.readLine();
//            	buffer.put(line.getBytes());
//            	socketChannel.write(buffer);
//            	
//            	buffer.rewind();
            	
            }
            
//            selector.wakeup();
//            while (true) {
//                int select= selector.select();
//                while (select > 0) {
//                    System.out.println("..............." +select);
//                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
//                    Iterator<SelectionKey> item = selectionKeys.iterator();
//                    while (item.hasNext()) {
//                        SelectionKey key = item.next();
//                        item.remove();
//                        if(key.isConnectable()) {
//                            socketChannel.finishConnect();
//                            socketChannel.write(ByteBuffer.wrap(new byte[]{1,3,5}));
//                            System.err.println("........................");
//                        }
//                        System.out.println(key.isConnectable() + "  " + key.isReadable() + " " + key.isWritable());
//                        if(key.isWritable()) {
//                            ((SocketChannel)key.channel()).write(ByteBuffer.wrap("1234".getBytes()));
//                            System.out.println("222readable:" + key.isReadable() + " writeable:" + key.isWritable() + " connectioned:" + key.isConnectable() + " " + key.isValid());
////                            break outter;
//                        }
//                        if(key.isReadable()) {
//                            ByteBuffer buffer = ByteBuffer.allocate(100);
//                            ((SocketChannel)key.channel()).read(buffer); 
//                            System.out.println("lllllllllllllllllllllllllllllllllll"+buffer.array());
//                        }
//                        System.out.println("111readable:" + key.isReadable() + " writeable:" + key.isWritable() + " connectioned:" + key.isConnectable() + " " + key.isValid());
//                       
//                    }
//                    select = selector.select();
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		new ChannelClient_Test();
	}

}
