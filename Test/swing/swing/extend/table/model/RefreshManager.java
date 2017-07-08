package swing.extend.table.model;

import java.awt.image.RescaleOp;

/**
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-一月-2014 10:47:47
 */
@SuppressWarnings("unused")
public class RefreshManager {
	private boolean running = true;
	private boolean pause = false;
	private AbstractExtendTableModelManager modelManager;
	private int refreshTime = 3000;
	
	private Object lock = new Object();

	public RefreshManager(AbstractExtendTableModelManager modelManager){
		this.modelManager = modelManager;
	}
	
	/**
	 * 设置刷新周期.
	 * @param refreshTime
	 */
	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}
	
	public void refreshData(){
		new Thread() {
			@Override
			public void run() {
				//除非设置停止，否则一直运行
				while(isRunning()) {
					//如果没有暂停，则一直取数
					if(isPause()) {
						synchronized (lock) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
					while(!isPause() && isRunning()) {
						refresh();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			
		}.start();
	}
	
	public synchronized boolean isRunning() {
		return running;
	}
	
	public synchronized void setRunning(boolean running) {
		this.running = running;
	}
	
	public synchronized boolean isPause() {
		return pause;
	}
	
	public synchronized void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public void pause() {
		setPause(true);
	}
	
	public void stop() {
		setRunning(false);
	}
	
	public void recover() {
		setPause(false);
		synchronized (lock) {
			lock.notify();
		}
	}
	
	protected void refresh() {
//		modelManager.refreshData();
		System.out.println(";;;;");
	}
	
	public static void main(String[] args) {
		RefreshManager manager = new RefreshManager(null);
		manager.refreshData();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		manager.pause();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		manager.recover();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		manager.stop();
	}
}