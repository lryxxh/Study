package lang;
import java.util.Date;


public class WaitSynchronized {

	public static void main(String[] args) {
		final WaitSynchronized test = new WaitSynchronized();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (test) {
					System.out.println(11111111);;
				}
				System.out.println("ssss");
				synchronized (test) {
					test.notifyAll();
				}
			}
		}).start();
		test.waitNotify();
	}

	public void waitNotify() {
		try {
			synchronized (this) {
				System.out.println("into first synchronized");
				synchronized (this) {
					System.out.println("into two synchronized");
					this.wait();
				}
			}
			System.err.println("sssssssssssssssssss");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
