package demail.com.kd.dmail.oper;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.tool.FrameSize;

/**
 * 
 * @author XYer
 */
public class WaitPanel extends javax.swing.JDialog implements Runnable {

	private static final long serialVersionUID = 1L;
	private Thread thread = null;
	private String title = null;

	public WaitPanel(java.awt.Frame parent, Thread thread, String title) {
		super(parent, true);
		this.thread = thread;
		this.title = title;
		setTitle(title);
		initComponents();
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		setSize(100, 100);
	}

	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon icon = new javax.swing.ImageIcon(DMailConstants.imageFilePath
				+ "0504316.gif");
		icon = new ImageIcon(icon.getImage().getScaledInstance(100, 20,
				Image.SCALE_DEFAULT));
		jLabel1.setVerticalTextPosition(JLabel.CENTER);
		jLabel1.setHorizontalTextPosition(JLabel.CENTER);
		jLabel1.setIcon(icon);
		jLabel1.setFont(new Font("Serif", Font.BOLD, 23));
		jLabel2.setVerticalTextPosition(JLabel.CENTER);
		jLabel2.setHorizontalTextPosition(JLabel.CENTER);
		jLabel2.setFont(new Font("Serif", Font.BOLD, 23));
		jLabel2.setText(title);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														294, Short.MAX_VALUE)
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														294, Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										83,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										21, Short.MAX_VALUE).addContainerGap()));

		pack();
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;

	@Override
	public void run() {
		try {
			thread.join();
			WaitPanel.this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
