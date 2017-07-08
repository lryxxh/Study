package socketpool;

import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		class DefaultThreadFactory implements ThreadFactory {
			final AtomicInteger poolNumber = new AtomicInteger(1);
			final ThreadGroup group;
			final AtomicInteger threadNumber = new AtomicInteger(1);
			final String namePrefix;

			DefaultThreadFactory() {
				SecurityManager s = System.getSecurityManager();
				group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
				namePrefix = "lry-pool-" + poolNumber.getAndIncrement() + "-thread-";
			}

			public Thread newThread(Runnable r) {
				SocketThread t = new SocketThread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
				if (t.isDaemon())
					t.setDaemon(false);
				if (t.getPriority() != Thread.NORM_PRIORITY)
					t.setPriority(Thread.NORM_PRIORITY);
				return t;
			}
		}

		final ThreadPoolExecutor service = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new DefaultThreadFactory());
		// ExecutorService service = new ThreadPoolExecutor(0,
		// Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new
		// SynchronousQueue<Runnable>());
//		new Thread(){
//			public void run() {
//				while(true) {
//					try {
//						int activeCount = service.getActiveCount();
//						long completedCount = service.getCompletedTaskCount();
//						long taskCount = service.getTaskCount();
//						System.err.println(activeCount + " ," + completedCount + " ," + taskCount);
//						Thread.sleep(50);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			};
//		}.start();
		for (int i = 0; i < 40; i++) {
			
			final int t = i;
			
			final Future<Vector> future = service.submit(new Callable<Vector>() {

				@Override
				public Vector call() throws Exception {
//					SocketThread thread = (SocketThread) Thread.currentThread();
//					ByteBuffer buffer = ByteBuffer.allocate(4);
//					buffer.putInt(t);
//					thread.send(buffer.array());
//					thread.receive();
					return new Vector();
				}

			});
			try {
				System.out.println("future £º " + future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		new JFrame().setVisible(true);

	}

}
