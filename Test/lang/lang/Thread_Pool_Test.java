/**
 * 
 */
package lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

/**
 * @author HMI-Lenovo
 *
 */
public class Thread_Pool_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new JFrame().setVisible(true);
		ExecutorService service = Executors.newFixedThreadPool(20);
		for(int i = 0; i< 100; i++) {
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					for(;;) {
						System.out.println(Thread.currentThread().getName() + "  ");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
//						if(j > 3) {
//							throw new NullPointerException("hung test");
//						}
					}
				}
			});
		}

	}

}
