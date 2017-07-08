package demail.com.kd.test;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ThreadPanel implements Runnable {
	Thread thread = null;
	JFrame frame = null;
	String title = null;

	public ThreadPanel(JFrame frame, Thread thread, String title) {
		this.frame = frame;
		this.thread = thread;
		this.title = title;
	}

	public void run() {
		JDialog dialog = new JDialog();
		dialog.show();
		dialog.setSize(400, 500);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialog.dispose();

	}
}
