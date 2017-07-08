package net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject  implements HelloService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1550662522892442952L;

	protected HelloServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHell(String str) throws java.rmi.RemoteException {
		return "Hello "+str;
	}

}
