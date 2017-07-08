/**
 * 
 */
package net.multicast;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
public class MulticastServer{
	String groupHost="192.168.20.1";  //组播组虚拟IP
	int port=5678;	//端口
	public MulticastServer(){
		try{
			MulticastSocket multicastSocket = new MulticastSocket(port); //MulticastSocket实例
		    InetAddress inetAddress = InetAddress.getByName(groupHost); //组地址
		    System.out.println(inetAddress);
		    multicastSocket.joinGroup(inetAddress); //加入到组播组中
		    while (true){
		        byte[] received = new byte[128]; //接收数据缓冲
		        DatagramPacket datagramPacket = new DatagramPacket(received, received.length); //接收数据的数据报
		        multicastSocket.receive(datagramPacket); //接收数据
		        System.out.println(new String(received)); //输出接收到的数据
		    }
		}
		catch (Exception exception){
			exception.printStackTrace(); //输出出错信息
		}
	}
  	public static void main(String [] arstring){
		new  MulticastServer();   
  	}
}
