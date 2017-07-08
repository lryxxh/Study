/**
 * RMIClient_Test.java
 * Created by liurenyong at 2013-9-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.rmi;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2013-9-24
 */
public class RMIClient_Test {
    
    public static void main(String[] args) {
        
        try {
        	new JFrame().setVisible(true);
//            Context context = new InitialContext();
//            HelloService service = (HelloService) context.lookup("rmi://localhost:9900/helloservice");
            HelloService service = (HelloService) Naming.lookup("rmi://192.168.200.75:30000/service");
//            for(int i = 0; i < 1;i++) {
            	String value = service.say("World!");
            	System.err.println(value);
            	System.out.println();
            	service.set("ÀîËÄ");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
