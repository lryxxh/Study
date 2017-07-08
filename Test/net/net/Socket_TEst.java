/**
 * Socket_TEst.java
 * Created by HHD at 2013-6-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author HHD 2013-6-19
 */
public class Socket_TEst {
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.60.22", 13000);
            socket.getInputStream();
            System.out.println(socket.getLocalAddress());
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
