/**
 * 
 */
package lang;

import javax.swing.JFrame;

/**
 * @author HMI-Lenovo
 *
 */
public class ThreadJoin_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread thread = new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("=================thread run exit");
			}
		};
		thread.start();
		try {
			thread.join(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("..................");
		
		new JFrame().setVisible(true);
	}

}
