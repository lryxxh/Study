/**
 * 
 */
package lang;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HMI-Lenovo
 * 
 */
public class Lock_Test {

	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length){
				System.out.println("...put........");
				notFull.await();
			}
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0){
				System.out.println("...take........");
				notEmpty.await();
			}
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Lock_Test test = new Lock_Test();

		new Thread() {
			public void run() {
				while(true) {
					try {
						test.put(200 * Math.random());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			};
		}.start();
		new Thread() {
			public void run() {
				while(true) {
					Object obj;
					try {
						obj = test.take();
						System.out.println(obj);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			};
		}.start();
	}
	
	Object obj = new Object();
	public void test() {
		synchronized (obj) {
			System.out.println("..11.......");
		}
	}
	
	public void test222() {
		synchronized (obj) {
			System.out.println("..22.......");
		}
	}
}
