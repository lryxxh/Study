/**
 * Host_Test.java
 * Created by liurenyong at 2013-11-11
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author liurenyong 2013-11-11
 */
public class Host_Test {
    
    public static void main(String[] args) {
//        try {
//            InetAddress address = InetAddress.getByAddress(new byte[]{(byte)192,(byte)168,60,20});
//            System.out.println("ip"+address.getHostName());
//        } catch (IOException e) {
//            System.err.println("");
//        }catch (Exception e) {
//            e.printStackTrace();
//        } 
    	try {
			Socket socket = new Socket("192.168.60.21",51012);
			System.out.println(socket);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
