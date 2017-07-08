/**
 * 
 */
package lang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author HMI-Lenovo
 * 
 */
public class Callable_Thread {
	static ExecutorService service = Executors.newFixedThreadPool(5);
	static ExecutorService clientService = Executors.newFixedThreadPool(3);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		 try {
//		 Thread.sleep(8000);
//		 } catch (InterruptedException e1) {
//		 e1.printStackTrace();
//		 }
		long time = System.currentTimeMillis();
		final List<Future> list = new ArrayList();
		for (int i = 0; i < 50; i++) {
//			list.add(runCallable(i));

			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
		for (int i = 0; i < 50; i++) {
			final int count = i;
			clientService.execute(new Runnable() {
				public void run() {
					Object obj;
					try {
//						runCallable(i);
						obj = runCallable(count).get();
						System.out.println(obj);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}

				};
			});
		}

		System.out.println("total time " + (System.currentTimeMillis() - time));
	}

	private static Future runCallable(int count) {
		Future future = service.submit(new CallableThreadInner(count));
		return future;
	}

	static int a = 0;
	static Object object = new Object();

	static class CallableThreadInner implements Callable {
		int count = 0;

		CallableThreadInner(int count) {
			this.count = count;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Object call() throws Exception {
//			if (count % 2 == 0) {
//				Thread.sleep(2000);
//			}
			return count;
		}

	}

}
