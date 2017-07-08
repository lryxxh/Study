package demail.com.kd.test;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 class ThreadDiag extends Thread {
	private Thread currentThread = null;// ʵ�ʵ���ʱ����TestThread�������߳�
	private String messages = "";// ��ʾ�����ʾ��Ϣ
	private JFrame parentFrame = null;// ��ʾ��ĸ�����
	private JDialog clueDiag = null;// ���߳��������С���ʾ��
	private Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = dimensions.width / 4, height = 60;
	private int left = 0, top = 0;

	public ThreadDiag(JFrame parentFrame, Thread currentThread, String messages) {
		this.parentFrame = parentFrame;
		this.currentThread = currentThread;
		this.messages = messages;
		initDiag();// ��ʼ����ʾ��
	}

	protected void initDiag() {
		clueDiag = new JDialog(parentFrame, "����ִ�У���ȴ�...", true);
		clueDiag.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		JPanel testPanel = new JPanel();
		JLabel testLabel = new JLabel(messages);
		clueDiag.getContentPane().add(testPanel);
		testPanel.add(testLabel);
		(new DisposeDiag()).start();// �����ر���ʾ���߳�
	}

	public void run() {
		// ��ʾ��ʾ��
		int left = (dimensions.width - width) / 2;
		int top = (dimensions.height - height) / 2;
		clueDiag.setSize(new Dimension(width, height));
		clueDiag.setLocation(left, top);
		clueDiag.show();
	}
}

// �� DisposeDiag��
// DisposeDiag�������ر���ʾ��
class DisposeDiag extends Thread {
	public void run() {
		try {
			Thread.currentThread().join();// �ȴ��������߳̽���
		} catch (InterruptedException e) {
			System.out.println("Exception:" + e);
		}
//		clueDiag.dispose();// �ر���ʾ��
	}
}
