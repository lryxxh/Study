package net;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Client {

	public static void main(String[] args) {
		try {
			Registry re = LocateRegistry.getRegistry(12345);
			HelloService service = (HelloService) re.lookup("helloService");
			String str = service.sayHell("Word!");
			System.out.println(str);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
