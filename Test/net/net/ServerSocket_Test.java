package net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerSocket_Test.java
 * Created by liurenyong at 2013-7-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2013-7-19
 */
public class ServerSocket_Test {
    
    public static void main(String[] args) {
        try {
            //在端口20000上建立Socket服务端,默认连接地址为127.0.0.1
            ServerSocket serverSocket = new ServerSocket(20000);
            //侦听并接受客户端Socket的连接
            Socket socket = serverSocket.accept();
            //得到Socket的输入流
            InputStream is = socket.getInputStream();
            //得到Socket的输出流
            OutputStream os = socket.getOutputStream();
           
            //从Socket的输入流中读取3个字节的长度
            byte[] bytes = new byte[16];
            is.read(bytes);
            System.out.println("客户端请求locator为 " + new String(bytes));
            System.out.println(socket.getPort());
            System.out.println(socket.isBound() +" " + socket.isClosed() + " "+ socket.isConnected());
            socket.close();
            boolean flag = true;
            if(flag) {
            	return;
            }
            String line = null;
            boolean falg = true;
//            while(falg) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            	while((line = reader.readLine())!= null) {
            		if(line.equals("bye")) {
            			os.close();
            			System.out.println(socket.isConnected() + "  " + socket.isClosed());
            		}
            		else if (line.equals("con")) {
            			os = socket.getOutputStream();
            		} 
//            		else {
            			os.write(line.getBytes());
//            		}
            	}
//            }
//            if(true) {
//                //向Socket的输出流中写入是否成功(此处为int类型,值1为成功)
//                
//                /*******************测试发送大数据*****************************/
//                byte[] array = new byte[1024 * 1024 * 20 * 4];
//                os.write(array);
//                /************************************************/
//                System.err.println(".....................");
//                os.write(new byte[]{0,0,0,1});
//                
//                //向Socket的输出流中写入字符串"scd-sun-2"
//                os.write("scd-sun-2".getBytes());
//                
//                //向Socket的输出流中输入ip
//                os.write("127.0.0.1".getBytes());
//                
//                //向Socket的输出流中输入端口号,解析为整形后位11012
//                byte[] port = new byte[]{0,0,43,4};
//                os.write(port);
//            }
           
            
            //关闭socket的输出流
            os.close();
            //关闭socket的输入流
            is.close();
            //关闭客户端socket
            socket.close();
            //关闭socket服务端
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
