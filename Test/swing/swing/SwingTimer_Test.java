/**
 * 
 */
package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.Timer;


/**
 * @author HMI-Lenovo
 *
 */
public class SwingTimer_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SwingWorker(){
					/* (non-Javadoc)
					 * @see javax.swing.SwingWorker#doInBackground()
					 */
					@Override
					protected Object doInBackground() throws Exception {
						System.out.println("............." + Thread.currentThread());
						return null;
					}
				}.execute();
				
			}
		});
		new JFrame().setVisible(true);
		timer.start();
	}

}
