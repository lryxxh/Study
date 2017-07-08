package net;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;


public class RemoteLayoutTrigger {

	private String ip;
	private int port;
	private Socket socket;

	public RemoteLayoutTrigger() {
	}
	
	/**
	 * @param ip
	 * @param port
	 */
	public RemoteLayoutTrigger(String ip, int port) {
		this.ip = ip;
		this.port = port;
		linkServer();
	}

	public void openRemoteLayout(final String layoutFileName) {
		Thread th = new Thread() {
			public void run() {
				sendMessage(layoutFileName);
			}
		};
		th.start();
	}
	
	public void openRemoteLayout(final String layoutFileName,String ip,int port) {
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void linkServer() {
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage(String msg) {
		try {
			if (socket == null) {
//				Date today = new Date();
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				DebugWindow.getWindow().appendDebugMessage(dateFormat.format(today)+ "socket is null");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				openRemoteLayout(msg,ip,port);
			}else{
				OutputStream output = socket.getOutputStream();
				output.write(msg.getBytes());
				output.flush();
//				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static RemoteLayoutTrigger leftTrigger;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn = new JButton("");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (leftTrigger == null) {
					leftTrigger = new RemoteLayoutTrigger("127.0.0.1",4321);
				}
				leftTrigger.openRemoteLayout("geo2");
			}
		});
		frame.getContentPane().add(btn);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
}