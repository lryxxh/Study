package net;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * SocketClient_Test.java
 * Created by liurenyong at 2013-7-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2013-7-19
 */
public class SocketClient_Test {

    public static void main(String[] args) {
        try {
            long time = System.currentTimeMillis();
            //建立ip为127.0.0.1,端口号为20000的Socket客户端
            Socket socket = new Socket();
            socket.setReceiveBufferSize(655360);

            socket.connect(new InetSocketAddress("127.0.0.1", 20000));
//            Socket socket = new Socket("localhost", 20000);
            //得到Socket的输入流
            InputStream is = socket.getInputStream();
            //得到Socket的输出流
            OutputStream os = socket.getOutputStream();
            //向Socket的输出流中输入字符串"oodb_locator_srv"
            os.write("oodb_locator_srv".getBytes());
            System.out.println(socket.getReceiveBufferSize());
            byte[] temp = null;
            while(true) {
 
            		System.err.println(".............loop");
                	temp = new byte[1024];
                	int tmp = is.read(temp);
                	System.out.println(tmp);
                	Thread.sleep(1000);
//            	}catch(Exception e) {
//            		
//            	}
            	
            }
//            /********************************/
//            byte[] array = new byte[65536];
//            int offset = 0;
//            while (offset != 1024 * 1024 * 80) {
//                int read = is.read(array);
//                offset += read;
//                System.out.println("read " + read +" " + "offset:" + offset + " " + (System.currentTimeMillis() - time));
//            }
//            /********************************/
//            socket.setSoLinger(true, 0);
//            //从Socket的输入流中读取4个字节的长度
//            byte[] successFlag = new byte[4];
//            is.read(successFlag);
//            int success = successFlag[3] + (successFlag[2] << 8) + (successFlag[1] << 16) + (successFlag[0] << 24);
//            System.out.println("locator定位oodb_srv服务:  " + success);
//            
//            //从Socket的输入流中读取40个字节的长度
//            byte[] hosetName = new byte[9];
//            is.read(hosetName);
//            System.out.println("oodb_srv服务的主机节点名为: " + new String(hosetName).trim());
//            
//            //从Socket的输入流中读取16个字节的长度
//            byte[] ip = new byte[9];
//            is.read(ip);
//            System.out.println("oodb_srv服务的ip为:  " + new String(ip).trim());
//            
//            //从Socket的输入流中读取4个字节的长度
//            byte[] portBytes = new byte[4];
//            is.read(portBytes);
//            int port = portBytes[3] + (portBytes[2] << 8) + (portBytes[1] << 16) + (portBytes[0] << 24);
//            System.out.println("oodb_srv服务的端口号 为: "+port);
//            //关闭Socket的输入流
//            is.close();
//            //关闭Socket的输出流
//            os.close();
//            //关闭socket
//            socket.close();
//            System.err.println("total time = " + (System.currentTimeMillis() - time));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
