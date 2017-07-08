package runtime;
public class Test {
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			MultipleMainProcess.lanuchApplicationInProcess();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
