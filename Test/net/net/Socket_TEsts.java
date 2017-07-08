/**
 * Socket_TEsts.java
 * Created by liurenyong at 2013-7-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author liurenyong 2013-7-29
 */
public class Socket_TEsts {

    /**
     * 
     * @param args
     * @author liurenyong 2013-7-29
     */
    public static void main(String[] args) {
        
        try {
            Socket socket = new Socket("192.168.60.21", 51012);
            socket.setSoLinger(true, 20);
//            System.out.println(socket.isConnected());
            System.out.println(socket.getSoLinger());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
