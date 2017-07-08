package net;

import java.rmi.Remote;

public interface HelloService extends Remote{

	public String sayHell(String str) throws java.rmi.RemoteException;;
}
