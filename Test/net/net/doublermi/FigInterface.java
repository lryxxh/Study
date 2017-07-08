package net.doublermi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FigInterface extends Remote{
	public void setModel(ModelInterface model) throws RemoteException;
}
