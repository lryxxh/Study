/**
 * Frame_Test.java
 * Created by liurenyong at 2013-10-15
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author liurenyong 2013-10-15
 */
public class Frame_Test extends JFrame {

	static int a = 1;

	/**
	 * 
	 * @author liurenyong 2013-10-15
	 */
	public Frame_Test() {
		init();
		setName("frame" + (a++));
	}

	public void init() {
		setSize(500, 350);

		System.out.println("................" + this.getDefaultCloseOperation());
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new Frame_Test();
				frame.setLocationRelativeTo(null);
			}
		});
		add(button);
		final JButton button2 = new JButton("sd");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(SwingUtilities.getAncestorOfClass(Frame.class, button2).getName());
			}
		});
		add(button2, BorderLayout.NORTH);
		// Timer timer = new Timer(5000, new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("----------------");
		// setVisible(true);
		// }
		// });
		// timer.setRepeats(false);
		// timer.start();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Frame_Test();
	}

}

class Singleton {
	private static Singleton instance = new Singleton();

	private static Hashtable<Frame, Singleton> instanceMap = new Hashtable<Frame, Singleton>();

	private Singleton() {
	}

	public static Singleton getInstance(Frame frame) {
		Singleton singleton = instanceMap.get(frame);
		synchronized (instanceMap) {
			if (singleton == null) {
				singleton = new Singleton();
				instanceMap.put(frame, singleton);
			}
		}
		return singleton;
	}
}
