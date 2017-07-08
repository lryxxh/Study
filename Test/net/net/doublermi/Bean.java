package net.doublermi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Bean extends UnicastRemoteObject implements BeanInterface ,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FigInterface> figs = null;

	int i ;
	protected Bean(int i) throws RemoteException {
		super();
		this.i = i;
	}
	
	public void change(Object obj) {
		System.out.println(obj);
	}
	@Override
	public String toString() {
		return super.toString() + i;
	}
	
	public int getI() {
		return i;
	}
	
	public void setI(int i) {
		this.i = i;
		System.out.println("i="+i);
	}
	
	public void setFigs(List<FigInterface> figs) {
		this.figs = figs;
		for(FigInterface fig: figs) {
			try {
				fig.setModel(new TestModel());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		System.out.println( figs + " =" + this);
	}
}
