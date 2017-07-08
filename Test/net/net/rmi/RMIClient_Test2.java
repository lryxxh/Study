/**
 * RMIClient_Test.java
 * Created by liurenyong at 2013-9-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;

/**
 * 
 * @author liurenyong 2013-9-24
 */
public class RMIClient_Test2 {
    
    public static void main(String[] args) {
        
        try {
//            Context context = new InitialContext();
//            HelloService service = (HelloService) context.lookup("rmi://localhost:9900/helloservice");
            final HelloService service = (HelloService) LocateRegistry.getRegistry(1009).lookup("test");
            new Thread(){
            	public void run() {
            		try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            		try {
						service.cancelTask();
					} catch (IOException e) {
						e.printStackTrace();
					}
            	};
            }.start();
            for(int i = 0; i < 1;i++) {
            	String value = service.say("World!");
            	System.err.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
