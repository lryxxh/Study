package lang;

public class SynchronizedTest {
	private int count=0;
	
	public void print() {
		for(int i=0;i<1000000;i++) {
			if(i%1000==0) {
				synchronized (this) {
					count++;
				}
				System.out.println(i+ "  "+Thread.currentThread().getName() +"  "+count);
			}
		}
	}

	public static void main(String[] args) {
		final SynchronizedTest test = new SynchronizedTest();
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					test.print();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
//		Thread thread2 = new Thread(){
//			@Override
//			public void run() {
//				test.print();
//			}
//		};
		thread.start();
//		thread2.start();
	}
}
