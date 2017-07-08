/**
 * 
 */
package lang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author HMI-Lenovo
 *
 */
public class TimerThread_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(Thread.currentThread());
			}
		});
		
		timer.start();
		
		new JFrame().setVisible(true);

	}

}
