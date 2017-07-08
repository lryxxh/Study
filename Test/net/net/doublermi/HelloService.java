package net.doublermi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HelloService extends Remote{

	public List<Bean> getBeans() throws RemoteException;
	
	public void registerBeans(List<BeanInterface> beans) throws RemoteException;
}
