/**
 * 
 */
package net.multicast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class MulticastClient{
	String groupHost="192.168.20.1"; //�鲥������IP
	int port=5678; //�˿�
	public MulticastClient(){
		try{
			byte[] message = "Hello,This is Client.".getBytes(); //������Ϣ
      		InetAddress inetAddress = InetAddress.getByName(groupHost); //�鲥��ַ
      		DatagramPacket datagramPacket= new DatagramPacket(message, message.length, inetAddress, port); //�������ݱ�
      		DatagramSocket socket = new DatagramSocket(); //DatagramSocketʵ��
      		socket.send(datagramPacket); //��������
      		socket.close(); //�رն˿�
    	}
    	catch (Exception exception) {
      		exception.printStackTrace();  //���������Ϣ
    	} 
	}
  	public static void main(String[] args){
		new MulticastClient();
	}
}