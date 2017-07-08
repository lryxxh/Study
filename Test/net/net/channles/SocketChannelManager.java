/**
 * 
 */
package net.channles;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;


/**
 * @author HMI-Lenovo
 *
 */
public class SocketChannelManager implements Runnable{
	
	Selector selector = null;
	
	Thread thread = null;
	
	/**
	 * 
	 */
	private SocketChannelManager() {
		init();
	}
	
	/**
	 * 
	 */
	private void init() {
		initialization();
		initThread();
		
	}
	
	/**
	 * 
	 */
	private void initThread() {
		thread = new Thread(this);
		thread.setDaemon(true);
	}
	
	/**
	 * 
	 */
	private void start() {
		thread.start();
	}

	/**
	 * 
	 */
	private void initialization() {
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向Selector注册SocketChannel的操作.
	 * @param socketChannel
	 * @param pos
	 * @param attment
	 */
	public void registerSocketChannel(SocketChannel socketChannel, int pos, Object attment) {
		try {
			socketChannel.register(selector, pos).attach(attment);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向Selector注册SocketChannel的操作.
	 * @param socketChannel 要注册的通道
	 * @param pos	操作集
	 */
	public void registerSocketChannel(SocketChannel socketChannel, int pos) {
		registerSocketChannel(socketChannel, pos, null);
	}
	
	/**
	 * 向Selector注册SocketChannel，默认关心read和write的操作.
	 * @param socketChannel
	 */
	public void registerSocketChannel(SocketChannel socketChannel) {
		registerSocketChannel(socketChannel, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
	}

	@Override
	public void run() {
	}

	public static SocketChannelManager getInstance() {
		return SocketChannelManager_Holder.instance;
	}
	
	private static class SocketChannelManager_Holder {
		private static SocketChannelManager instance = new SocketChannelManager();
	}

}
