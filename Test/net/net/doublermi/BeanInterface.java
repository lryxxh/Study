package net.doublermi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BeanInterface extends Remote{
	
	void setFigs(List<FigInterface> figs) throws RemoteException;

	void setI(int i) throws RemoteException;
}
