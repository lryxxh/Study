/**
 * DatagramSocket_Server.java
 * Created by liurenyong at 2013-9-23
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 
 * @author liurenyong 2013-9-23
 */
public class DatagramSocket_Server {
    public DatagramSocket_Server() {
        main(null);
    }
    
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(8009);
            byte[] buf = new byte[1024];
            int offset = 0;
            serverSocket.setSoTimeout(6000);
            Thread.sleep(5000);
            DatagramPacket packet = new DatagramPacket(buf, 1024);
            while (offset < buf.length || serverSocket.isConnected()) {
                serverSocket.receive(packet);
                packet.setData(buf, offset, packet.getLength());
                for (int i = 0; i < packet.getLength(); i++) {
                    System.out.println("server receive :" + (offset + i) + " " +buf[i]);
                }
                offset += packet.getLength();
            }

            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (Math.random() * Byte.MAX_VALUE);
            }
            offset = 0;
            while(true) {
            	while (serverSocket.isConnected()) {
            		while (offset < buf.length ) {
            			packet.setData(buf, offset, 256);
            			serverSocket.send(packet);
            			offset += packet.getLength();
            			Thread.sleep(1000);
            		}
            	}
            	
            }
           
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
