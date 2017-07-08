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
            //����ipΪ127.0.0.1,�˿ں�Ϊ20000��Socket�ͻ���
            Socket socket = new Socket();
            socket.setReceiveBufferSize(655360);

            socket.connect(new InetSocketAddress("127.0.0.1", 20000));
//            Socket socket = new Socket("localhost", 20000);
            //�õ�Socket��������
            InputStream is = socket.getInputStream();
            //�õ�Socket�������
            OutputStream os = socket.getOutputStream();
            //��Socket��������������ַ���"oodb_locator_srv"
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
//            //��Socket���������ж�ȡ4���ֽڵĳ���
//            byte[] successFlag = new byte[4];
//            is.read(successFlag);
//            int success = successFlag[3] + (successFlag[2] << 8) + (successFlag[1] << 16) + (successFlag[0] << 24);
//            System.out.println("locator��λoodb_srv����:  " + success);
//            
//            //��Socket���������ж�ȡ40���ֽڵĳ���
//            byte[] hosetName = new byte[9];
//            is.read(hosetName);
//            System.out.println("oodb_srv����������ڵ���Ϊ: " + new String(hosetName).trim());
//            
//            //��Socket���������ж�ȡ16���ֽڵĳ���
//            byte[] ip = new byte[9];
//            is.read(ip);
//            System.out.println("oodb_srv�����ipΪ:  " + new String(ip).trim());
//            
//            //��Socket���������ж�ȡ4���ֽڵĳ���
//            byte[] portBytes = new byte[4];
//            is.read(portBytes);
//            int port = portBytes[3] + (portBytes[2] << 8) + (portBytes[1] << 16) + (portBytes[0] << 24);
//            System.out.println("oodb_srv����Ķ˿ں� Ϊ: "+port);
//            //�ر�Socket��������
//            is.close();
//            //�ر�Socket�������
//            os.close();
//            //�ر�socket
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
