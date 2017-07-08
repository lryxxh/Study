package swing;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import javax.swing.Timer;
 
public class SampleProgress {
 
	static ProgressMonitor monitor;
	static int progress;
	static Timer timer;
 
	static class ProgressMonitorHandler implements ActionListener {
		// Called by Timer
		public void actionPerformed(ActionEvent event) {
			if(monitor == null) {
				return ;
			}
			if(monitor.isCanceled()) {
				System.out.println("Monitor canceled");
				timer.stop();
			}
			else {
				progress += 3;
				monitor.setProgress(progress);
				monitor.setNote("Load "+progress+" files");
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		Runnable runner = new Runnable() {
			public void run() {
				JFrame frame = new JFrame("ProgressMonitor Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(0,1));
 
				// define start button
				JButton startButton = new JButton("Start");
				ActionListener startActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						Component parent = (Component)event.getSource();
						monitor = new ProgressMonitor(parent, "Loading Progress", "Getting Started...", 0, 200);
						progress = 0;
					}
				};
				startButton.addActionListener(startActionListener);
				frame.add(startButton);
 
				// define manual increase button
				// pressing this button increases progress by 5
				JButton increaseButton = new JButton("Manual Increase");
				ActionListener increaseActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if(monitor == null)
							return ;
						if(monitor.isCanceled()) {
							System.out.println("Monitor cancled");
						}
						else {
							progress += 5;
							monitor.setProgress(progress);
							monitor.setNote("Loaded "+progress+" files");
						}
					}
				};
				increaseButton.addActionListener(increaseActionListener);
				frame.add(increaseButton);
 
				// define automatic increase button
				// start timer to increase progress by 3 every 250 ms
				JButton autoIncreaseButton = new JButton("Automatic Increase");
				ActionListener autoIncreaseActionListener = new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if(monitor != null) {
							if(timer == null) {
								timer = new Timer(250, new ProgressMonitorHandler());
							}
							timer.start();
						}
					}
				};
				autoIncreaseButton.addActionListener(autoIncreaseActionListener);
				frame.add(autoIncreaseButton);
 
				frame.setSize(300, 200);
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}
 
}