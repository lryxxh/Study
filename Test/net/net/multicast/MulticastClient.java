/**
 * 
 */
package net.multicast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class MulticastClient{
	String groupHost="192.168.20.1"; //组播组虚拟IP
	int port=5678; //端口
	public MulticastClient(){
		try{
			byte[] message = "Hello,This is Client.".getBytes(); //发送信息
      		InetAddress inetAddress = InetAddress.getByName(groupHost); //组播地址
      		DatagramPacket datagramPacket= new DatagramPacket(message, message.length, inetAddress, port); //发送数据报
      		DatagramSocket socket = new DatagramSocket(); //DatagramSocket实例
      		socket.send(datagramPacket); //发送数据
      		socket.close(); //关闭端口
    	}
    	catch (Exception exception) {
      		exception.printStackTrace();  //输出错误信息
    	} 
	}
  	public static void main(String[] args){
		new MulticastClient();
	}
}