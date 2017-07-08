/**
 * RMIServer_Test.java
 * Created by liurenyong at 2013-9-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package net.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 
 * @author liurenyong 2013-9-24
 */
public class RMIServer_Test {
    
    public static void main(String[] args) {
        try {
//            System.setProperty("java.naming.factory.initial","com.sun.jndi.rmi.registry.RegistryContextFactory");
//            System.out.println(System.getenv("java.naming.factory.initial"));
            HelloService service = new HelloServiceImpl();
//            Context context = new InitialContext();
//            context.bind("helloservice", service);
            LocateRegistry.createRegistry(30000);
            Naming.rebind("rmi://192.168.200.75:30000/service", service);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }

}
