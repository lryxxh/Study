package net.doublermi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Fig extends UnicastRemoteObject implements FigInterface,Serializable, Remote{
	
	public Fig() throws RemoteException{
	}
	
	
	int index = 0;
	ModelInterface model = null;
	
	public void setModel(ModelInterface model) {
		this.model = model;
		System.out.println("testmodel");
	}
	
	public ModelInterface getModel() {
		return model;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void paint() throws RemoteException{
		System.out.println(index);;
	}
}
