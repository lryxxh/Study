package swing;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class JFileChooserTest extends JFrame {
	JFileChooser chooser = new JFileChooser();
	JDialog dialog;
	JButton button = new JButton("show file chooser ...");

	public JFileChooserTest() {
		super("Simple File Chooser Application");
		Container contentPane = getContentPane();

		contentPane.setLayout(new FlowLayout());
		contentPane.add(button);		

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = chooser.getDialogTitle();

				if(title == null)
					chooser.getUI().getDialogTitle(chooser);

        		dialog = new JDialog((Frame)null, title, false);
				
        		Container dialogContentPane = 
								dialog.getContentPane();

        		dialogContentPane.setLayout(new BorderLayout());
        		dialogContentPane.add(chooser, 
								      BorderLayout.CENTER);
 
				dialog.setTitle("Non-Modal File Chooser");

        		dialog.pack();
        		dialog.setLocationRelativeTo(JFileChooserTest.this);
 
        		dialog.setVisible(true);			
			}
		});
		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String state = (String)e.getActionCommand();

				if(state.equals(JFileChooser.APPROVE_SELECTION)) {
					File file = chooser.getSelectedFile();

					JOptionPane.showMessageDialog(
											null, file.getPath());
				}
				else if(
					state.equals(JFileChooser.CANCEL_SELECTION)) {
					JOptionPane.showMessageDialog(
											null, "Canceled");
				}
				// JFileChooser action listeners are notified
				// when one either the approve button or
				// cancel button is activated
				dialog.setVisible(false);

			}
		});
	}
	public static void main(String args[]) {
		JFrame f = new JFileChooserTest();
		f.setBounds(300,300,350,100);
		f.setVisible(true);

		f.setDefaultCloseOperation(
			WindowConstants.DISPOSE_ON_CLOSE);
	
		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);	
			}
		});
	}
}
