package net.doublermi;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

public class RMIClient {

	public static void main(String[] args) {
		try {
			HelloService service = (HelloService) LocateRegistry.getRegistry(9999).lookup("HelloService");
			List<BeanInterface> beans = new ArrayList<BeanInterface>();
			for(int i = 0; i< 20;i++) {
				beans.add(new Bean(i));
			}
			service.registerBeans(beans);
			System.out.println(beans);
			new Thread() {
				public void run() {
					while(true) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
			}.start();
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
