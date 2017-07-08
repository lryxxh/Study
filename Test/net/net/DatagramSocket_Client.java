/**
 * DatagramSocket_Server.java
 * Created by liurenyong at 2013-9-23
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 * @author liurenyong 2013-9-23
 */
public class DatagramSocket_Client {
    
    public DatagramSocket_Client(){
        main(null);
    }
    
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte data[] = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, 256, InetAddress.getLocalHost(), 3333);
          
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = reader.readLine())!= null) {
                if ("bye".equals(line)) {
                    System.exit(0);
                } else {
                    packet.setData(line.getBytes());
                    clientSocket.send(packet);
                }
            }
            
            
            
            
            
            /*******************************************************/
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (Math.random() * Byte.MAX_VALUE);
            }
            int offset = 0;
            while (offset < data.length) {
                packet.setData(data, offset, 256);
                clientSocket.send(packet);
                offset += 256;
                Thread.sleep(2000);
            }
            offset = 0;
            while (offset < 2048) {
                packet.setData(data, 0, data.length);
                clientSocket.receive(packet);
                for (int i = 0; i < packet.getLength(); i++) {
                    System.out.println(Thread.currentThread().getName() + " client receive:" + (offset + i) + " " + data[offset + i]);
                }
                offset += packet.getLength();
            }
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }

}
