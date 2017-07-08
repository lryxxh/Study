/**
 * 
 */
package lang;

import com.kedong.hmi.service.common.SyncProxy;

/**
 * @author HMI-Lenovo
 *
 */
public class Thread_Wait_Notify_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		JFrame frame = new JFrame();
//		frame.setSize(800, 600);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
		for(int i = 0; i < 50;i++) {
			new Thread() {
				public void run() {
					test();
				};
			}.start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println();
		}
		System.err.println("end");
//		for(int i = 0; i< 100;i++) {
//			final Object obj = new Object();
//			new Thread() {
//				public void run() {
//					synchronized (obj) {
//						try {
//							obj.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					System.out.println("------------line 48");
//				};
//			}.start();
//			
//			new Thread() {
//				public void run() {
//					synchronized (obj) {
//						try {
//							obj.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					System.out.println("------------line 61");
//				};
//			}.start();
//			
//			new Thread() {
//				public void run() {
//					synchronized (obj) {
//						try {
//							obj.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					System.out.println("------------line 74");
//				};
//			}.start();
//			
//			new Thread() {
//				public void run() {
//					synchronized (obj) {
//						try {
//							obj.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					System.out.println("------------line 87");
//				};
//			}.start();
//			
//			
//			new Thread() {
//				public void run() {
//					synchronized (obj) {
//						try {
//							obj.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					System.out.println("------------line 101");
//				};
//			}.start();
//			
//			try {
//				Thread.sleep(100);
//				synchronized (obj) {
//					obj.notify();
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			System.out.println();
//			System.out.println();
//		}
	}
	
	private static void test() {
		Thread thread = Thread.currentThread();
		SyncProxy proxy = new SyncProxy(thread);
		test2(proxy);
		if (proxy.getFlag()) {
            waitCurrentThread(thread);
        }
		System.out.print("test1¡¢¡¢");
	}
	
	private static void test2(final SyncProxy proxy) {
		long time = System.currentTimeMillis();
		test3(proxy);
		new Thread() {
			public void run() {
				proxy.setData("---------------");
			};
		}.start();
		
		System.out.print("test2--");
	}
	
	private static void test3(final SyncProxy proxy) {
		Thread thread = Thread.currentThread();
		new Thread() {
			public void run() {
				proxy.setData(1234l);
			};
		}.start();
		if (proxy.getFlag()) {
            waitCurrentThread(thread);
        }
		System.out.print("test3++");
	}
	
	private static void waitCurrentThread(Thread thread) {
        synchronized (thread) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
