/**
 * 
 */
package net.multicast;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
public class MulticastServer{
	String groupHost="192.168.20.1";  //�鲥������IP
	int port=5678;	//�˿�
	public MulticastServer(){
		try{
			MulticastSocket multicastSocket = new MulticastSocket(port); //MulticastSocketʵ��
		    InetAddress inetAddress = InetAddress.getByName(groupHost); //���ַ
		    System.out.println(inetAddress);
		    multicastSocket.joinGroup(inetAddress); //���뵽�鲥����
		    while (true){
		        byte[] received = new byte[128]; //�������ݻ���
		        DatagramPacket datagramPacket = new DatagramPacket(received, received.length); //�������ݵ����ݱ�
		        multicastSocket.receive(datagramPacket); //��������
		        System.out.println(new String(received)); //������յ�������
		    }
		}
		catch (Exception exception){
			exception.printStackTrace(); //���������Ϣ
		}
	}
  	public static void main(String [] arstring){
		new  MulticastServer();   
  	}
}
