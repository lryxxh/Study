package socketmanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import agency.message.base.ByteTool;
import agency.message.base.DataUnit;
import agency.message.base.MessageToReceive;
import agency.message.base.MessageToSend;

public class SocketManager {
	
	/**不同态应用服务对应的请求队列 */
	private static HashMap<String, BlockingQueue<MessageSendReceive>> requestMap = new HashMap<String, BlockingQueue<MessageSendReceive>>();

	/** 不同态应用服务对应的链接列表 */
	private static HashMap<String, LinkedList<Socket>> socketMap = new HashMap<String, LinkedList<Socket>>();
	
	private static ExecutorService service = Executors.newCachedThreadPool();
	
	public static Vector<Vector<DataUnit>> sendAndReceive(String domain,String context,String appName,String serviceName, String serverName,MessageToSend messageToSend,MessageToReceive messageToReceive,int timeout) {
		String key = createKey(context, appName, serviceName, serverName);
		
		final MessageSendReceive sendReceive = new MessageSendReceive();
		sendReceive.setMessageToReceive(messageToReceive);
		sendReceive.setMessageToSend(messageToSend);
		
		BlockingQueue<MessageSendReceive> blockQueue = requestMap.get(key);
		if(blockQueue == null) {
			blockQueue = new SynchronousQueue<MessageSendReceive>();
			requestMap.put(key, blockQueue);
		}
		blockQueue.add(sendReceive);
		//插入成功
		if(blockQueue.offer(sendReceive)) {
			
		}else {//插入失败
			final Socket socket = getSocket(context, appName, serviceName, serverName);
			
			Future future = service.submit(new Callable<Vector>() {
				@Override
				public Vector call() throws Exception {
					InputStream inputStream = socket.getInputStream();
					
					return null;
				}
			});
			new Thread() {
				
				public void run() {
					MessageSendReceive task = null;
					  while (task != null || (task = getTask()) != null) {
		                    sendTask(task);
		                    task = null;
		                }
				}

				private void sendTask(MessageSendReceive task) {
				}

				private MessageSendReceive getTask() {
					try {
						return requestMap.get("").poll(1, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				};
				
			}.start();
		}
		
		return null;
	}
	
	private static void receive(String key, Socket socket) {
		
	}

	private static void send(String key, Socket socket) {
		try {
			OutputStream outputStream = socket.getOutputStream();
			MessageSendReceive sendReceive = requestMap.get(key).poll();
			outputStream.write(ByteTool.toByte(sendReceive.getMessageToSend().convertData()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized static Socket getSocket(String context, String appName, String serviceName, String serverName) {
		String key = createKey(context, appName, serviceName, serverName);
		LinkedList<Socket> socketList = socketMap.get(key);
		if(socketList == null) {
			createNewSocket(key);
		}
		return socketList.pop();
	}
	
	/**
	 * @param key
	 */
	private synchronized static void createNewSocket(String key) {
		LinkedList<Socket> socketList = new LinkedList<Socket>();
		Socket socket = new Socket();
		socketList.add(socket);
		socketMap.put(key, socketList);
	}
	
	private static String createKey(String context, String appName, String serviceName, String serverName){
		StringBuilder builder = new StringBuilder(context).append(",").append(appName).append(",").append(serviceName).append(",").append(serverName);
		return builder.toString();
	}
}
