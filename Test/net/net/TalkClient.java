package net;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
public class TalkClient {
	public static void main(String[] args) {
		UDPClientFrame cf = new UDPClientFrame();
	}
}
class UDPClientFrame extends Frame implements ActionListener
{
	Label xsck = new Label("消息显示窗口：                                                                   ");
	Label fsck = new Label("消息发送窗口：                                                                   ");
	TextArea taw = new TextArea("",8,40);
	TextArea msg = new TextArea("",5,34);
	Button bt = new Button("发送");
	DatagramSocket socket = null;
	DatagramPacket packet ,packetsent;
	byte[] buffer=new byte[256];
	InetAddress iadd,badd;
	int port;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss"); //格式化时间
	UDPClientFrame()
	{
		setLayout(new FlowLayout());
		this.setTitle("UDP在线聊天--客户端");
		this.add(xsck);
		this.add(taw);
		taw.setEditable(false);
		this.add(fsck);
		this.add(msg);
		this.add(bt);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				try
				{
					buffer = "断开连接".getBytes();
					packet = new DatagramPacket(buffer,buffer.length,badd,3333);
					socket.send(packet);
					//taw.append("断开了与主机的连接" + "\n");
				}
				catch(Exception e4){}
				System.exit(0);
			}
		});
		bt.addActionListener(this);
		setSize(318,350);
		setVisible(true);
		this.setResizable(false);
		msg.requestFocus();
		
		try
		{
			socket = new DatagramSocket();
			badd = InetAddress.getByName("localhost");   //发送本机地址
			
			packet = new DatagramPacket(buffer,buffer.length,badd,3333);
			byte[] mess1 = "请求连接".getBytes();
			packetsent = new DatagramPacket(mess1,mess1.length,badd,3333);
			socket.send(packetsent);
			
		//	System.out.println(buffer.length);
			socket.receive(packet);
			iadd = packet.getAddress();             //请求的地址
			port = packet.getPort();
			String message = new String (packet.getData(),0,packet.getLength());
			taw.append( message + "\n");
			while(true)								//对话开始
			{
				socket.receive(packet);
				//System.out.println(iadd);
				message = new String (packet.getData(),0,packet.getLength());
				taw.append("服务端：" + sdf.format(new Date()) + "\n" + "  " + message + "\n");
			}
			
			
		}
		catch(Exception e1)
		{
			new JOptionPane().showMessageDialog(null, "连接失败！");
			e1.printStackTrace(); //输出出错信息
		}
	}
	public void actionPerformed (ActionEvent e)
	{
		if(e.getSource() == bt)
		{
			String message = msg.getText();
			byte[] mess = message.getBytes();
			if(!message.equals(""))
			{
				try
				{
					packetsent = new DatagramPacket(mess,mess.length,iadd,port);
					socket.send(packetsent);
					taw.append("客户端：" + sdf.format(new Date()) + "\n" + "  " + message + "\n");
					msg.setText("");
				}
				catch(Exception e2)
				{
					e2.printStackTrace(); //输出出错信息
				}
			}
			else
			{
				new JOptionPane().showMessageDialog(null, "消息不能为空！");
			}
			msg.requestFocus();
		}
	}
}
