package net.doublermi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TestModel extends UnicastRemoteObject implements ModelInterface{
	public TestModel() throws RemoteException {
	}
}
