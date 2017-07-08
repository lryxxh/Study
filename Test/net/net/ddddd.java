/**
 * ddddd.java
 * Created by liurenyong at 2013-8-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author liurenyong 2013-8-20
 */
public class ddddd {

    public static void main(String[] args) {
    	try{
    		int a = 1/0;
    	}catch(Exception e) {
    		System.out.println(e.toString());
    	}
    	String localName = "public";
    	InetAddress[] local;
		try {
			local = InetAddress.getAllByName(localName);
			for(InetAddress address : local) {
				System.out.println("address:" + address);
	    	}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
    	
    	
    	
    	
    	
    	
//        System.out.println(Integer.toBinaryString(8054));
//        System.out.println(Integer.toBinaryString(0xE8));
//        System.out.println(Integer.toBinaryString(0x81));
//        System.out.println(Integer.toBinaryString(0x94));
        try {
            byte[] btes = "啊".getBytes("GBK");
            for (byte t : btes) {
                System.out.println(Integer.toHexString(t));
            }
            System.out.println();
//            String tempStr = new String("中国人");
//            System.out.println(tempStr.getBytes().length);
//            String tempStr =  new String("中国人".getBytes("UTF-8"));
//            char[] array = tempStr.toCharArray();
//            for(byte  t : tempStr.getBytes()) {
//                System.err.println(t);
//            }
//            for(int i = 0; i<array.length;i++) {
//                System.out.println( (int)array[i] + " " + (byte)array[i] + "  " + (byte)(array[i] >> 8 ));
//            }
//            Charset.forName("GBK").newDecoder();
//            for(byte tt : new String(array).getBytes()) {
//                System.out.println(tt);
//            }
//            System.out.println(tempStr.getBytes().length + "  " + tempStr.toCharArray().length);
//            char temp = tempStr.toCharArray()[0];
//            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        try {
//            Runtime.getRuntime().exec("rundll32  url.dll,FileProtocolHandler  http://192.168.60.22/mmiconsole/index.html");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } 
//        String[] listeners = new String[10];
//        for(int i= 0; i < listeners.length; i++) {
//            listeners[i] = "sss" + i;
//        }
//        
//        int index = Arrays.binarySearch(listeners, listeners[1]);
//        System.out.println(index);
//        new Thread() {
//            public void run() {
//                DatagramSocket_Server server = new DatagramSocket_Server();
//            };
//            
//        }.start();
//       
//        new Thread() {
//            public void run() {
//                DatagramSocket_Client client1 = new DatagramSocket_Client();
//            };
//        }.start();
//       
//        new Thread() {
//            public void run() {
//                DatagramSocket_Client client2 = new DatagramSocket_Client();
//            };
//        }.start();
//        
//        new Thread() {
//            /* (non-Javadoc)
//             * @see java.lang.Thread#run()
//             */
//            @Override
//            public void run() {
//                DatagramSocket socket = null;
//                try {
//                    socket = new DatagramSocket(8001);
//                } catch (SocketException e1) {
//                    e1.printStackTrace();
//                }
//                while (true) {
//                    try {
//                        System.out.println(socket);
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//        
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    DatagramSocket socket = new DatagramSocket();
//                    DatagramPacket packet = new DatagramPacket(new byte[]{1}, 1, InetAddress.getLocalHost(), 8001);
//                    socket.send(packet);
//                } catch (SocketException e) {
//                    e.printStackTrace();
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        
//        int a = 3;
//        int b = 4;
//        System.out.println(Integer.toBinaryString(3) + " - "+ Integer.toBinaryString(4));
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//        System.out.println(a + " : " + b);
      
    }
}
