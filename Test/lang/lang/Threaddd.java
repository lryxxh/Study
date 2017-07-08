/**
 * 
 */
package lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author HMI-Lenovo
 *
 */
public class Threaddd {
	static ExecutorService _executor = new ThreadPoolExecutor(2, 50, 1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0;i<10;i++) {
			System.out.println(":"+i);
			_executor.execute(new Runnable() {
				
				@Override
				public void run() {
					synchronized (Thread.currentThread()) {
						try {
							Thread.currentThread().wait(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("....final");
					}
				}
			});
		}
	

	}

}
