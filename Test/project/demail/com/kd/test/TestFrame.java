package demail.com.kd.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

//import KD.IDP.basic.WaitPaneThread;

import KD.IDP.basic.WaitPaneThread;
import demail.com.kd.dmail.config.ConfigMain;
import demail.com.kd.dmail.oper.WaitPanel;

public class TestFrame {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() - 100;
		int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight() - 100;
		frame.setSize(width, heigth);
		frame.setVisible(true);

		// final JLabel entry = new JLabel(new javax.swing.ImageIcon(
		// "E:\\人机开发\\image\\DefaultTitleBackground.jpg"));
		// entry.setPreferredSize(new Dimension(50, 50));
		// entry.setBackground(frame.getBackground().brighter());
		// entry.setFont(new Font(entry.getFont().getFamily(), 0, 10));
		//
		// entry.setVerticalTextPosition(JLabel.BOTTOM);
		// entry.setHorizontalTextPosition(JLabel.CENTER);
		// entry.setIconTextGap(0);
		//
		// entry.setToolTipText("nihao");
		// entry.setText("徐志奇");
		// JScrollPane pane = new JScrollPane();
		// pane.getViewport().add(new test());
		// frame.getContentPane().add(new test());
		JPanel a = new JPanel();
		Border b = BorderFactory.createLineBorder(Color.red, 2);
		Border b1 = BorderFactory.createEtchedBorder();
		JLabel be = new JLabel("徐志奇");
		be.setBorder(BorderFactory.createCompoundBorder(b, b1));
		a.add(be);
		// frame.getContentPane().add(new ConfigMain());
		Thread t = new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(i);
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
		WaitPaneThread th = new WaitPaneThread(null, t, "测试");
		new Thread(th).start();
	}
}
