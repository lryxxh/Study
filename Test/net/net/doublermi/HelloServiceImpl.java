package net.doublermi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HelloServiceImpl() throws RemoteException {
		super();
	}
	@Override
	public List<Bean> getBeans() throws RemoteException {
		return null;
	}
	@Override
	public void registerBeans(List<BeanInterface> beans) throws RemoteException {
		 CacheManager.GetInstance().setBeans(beans);
	}

}
