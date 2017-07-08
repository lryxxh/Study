/**
 * 
 */
package net.rmi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author HMI-Lenovo
 *
 */
public class Socket_Test_Open_Port {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			Socket socket = new Socket("192.168.60.26", 21000);
//			System.out.println(socket);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ExecutorService executorService = Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());
		for(;;) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					for(int i=0;i<1000000;i++){
						if(i%1000 == 0) {
							System.out.println("................." + i);
						}
					}
				}
			});
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}

}
