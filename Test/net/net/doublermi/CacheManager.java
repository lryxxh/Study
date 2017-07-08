package net.doublermi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CacheManager implements Runnable {
	List<BeanInterface> beans = new ArrayList<BeanInterface>();

	// {
	// for (int i = 0; i < 10; i++) {
	// try {
	// beans.add(new Bean(i));
	// } catch (RemoteException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	public void setBeans(List<BeanInterface> beans) {
		this.beans = beans;
	}

	public void changeBeans() {
		if (beans != null && beans.size() > 0) {
			System.out.println("-------");
			try {
				Fig fig = null;
				for (BeanInterface bean : beans) {
					List<FigInterface> figs = new ArrayList<FigInterface>();
					for(int i = 0; i< Math.random() * 10 + 1;i++) {
						fig = new Fig();
						fig.setIndex(i);
						figs.add(fig);
					}
					bean.setFigs(figs);
					bean.setI(figs.size());
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public List<BeanInterface> getBeans() {
		return beans;
	}

	public static CacheManager GetInstance() {
		return new CacheManager();
	}

	private CacheManager() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			changeBeans();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
