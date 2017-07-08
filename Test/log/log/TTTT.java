/**
 * 
 */
package log;


/**
 * @author HMI-Lenovo
 *
 */
public class TTTT {


	public static void syso() {
		Init();
		System.out.println(123);
	}
	
	public static void Init() {
		Log.info("echo where java");
	}
	
	public static void tttt() {
		new Thread() {
			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				while(true) {
					Log.info("thread------------------------------------------------------------------------ info");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
}
