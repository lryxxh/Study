package net;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

	public static void main(String[] args) {
		try {
			Registry re = LocateRegistry.createRegistry(12345);
			re.rebind("helloService", new HelloServiceImpl());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
