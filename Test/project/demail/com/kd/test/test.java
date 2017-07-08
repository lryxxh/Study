package demail.com.kd.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import demail.com.kd.dmail.config.PaneImpl;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.tool.FrameSize;

/**
 * 
 * @author XYer
 */
public class test extends javax.swing.JPanel {
	private JScrollPane pane = null;
	private PaneImpl paneImpl = null;

	public test(PaneImpl pane, String title) {
		JScrollPane p = new JScrollPane();
		p.getViewport().add(pane);
		this.paneImpl = pane;
		this.pane = p;
		initComponents();
		int width = FrameSize.width - 18;
		int heigth = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight() - 100;
		ImageIcon icon = new javax.swing.ImageIcon(DMailConstants.imageFilePath
				+ "DefaultTitleBackground.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(width, 120,
				Image.SCALE_DEFAULT));
		jLabel1.setVerticalTextPosition(JLabel.CENTER);
		jLabel1.setHorizontalTextPosition(JLabel.CENTER);
		jLabel1.setIcon(icon);
		jLabel1.setFont(new Font("Serif", Font.BOLD, 15));
		jLabel1.setText(title);

		ImageIcon icon2 = new javax.swing.ImageIcon(
				DMailConstants.imageFilePath + "DefaultDecorativeImage.jpg");
		icon2 = new ImageIcon(icon2.getImage().getScaledInstance(400, 530,
				Image.SCALE_DEFAULT));
		jLabel2.setVerticalTextPosition(JLabel.CENTER);
		jLabel2.setHorizontalTextPosition(JLabel.CENTER);
		jLabel2.setIcon(icon2);
		jLabel2.setText("");
		// Border b = BorderFactory.createLineBorder(Color.red,2);
		Border b1 = BorderFactory.createEtchedBorder();
		jPanel2.setBorder(b1);
		// init();
		jButton1.setText("重置");
		jButton2.setText("取消");
		jButton3.setText("保存");
		jButton4.setVisible(false);
		// repaint();
		setSize(width, FrameSize.height - 500);
		addListener();
	}

	public void addListener() {
		jButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					paneImpl.saveAndUpload();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();

		jLabel1.setText("jLabel1");

		jLabel2.setText("jLabel2");
		//
		// this.jPanel1 = pane; javax.swing.GroupLayout jPanel1Layout = new
		// javax.swing.GroupLayout(
		// jPanel1);

		// jPanel1.setLayout(jPanel1Layout);
		// jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
		// javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 506,
		// Short.MAX_VALUE));
		// jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
		// javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 492,
		// Short.MAX_VALUE));
		this.jPanel1 = pane;
		jButton1.setText("jButton1");

		jButton2.setText("jButton2");

		jButton3.setText("jButton3");

		jButton4.setText("jButton4");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel2Layout.createSequentialGroup()
						.addContainerGap(508, Short.MAX_VALUE)
						.addComponent(jButton4).addGap(18, 18, 18)
						.addComponent(jButton3).addGap(18, 18, 18)
						.addComponent(jButton2).addGap(18, 18, 18)
						.addComponent(jButton1)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1)
														.addComponent(jButton2)
														.addComponent(jButton3)
														.addComponent(jButton4))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						886, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										176,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										106,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jPanel1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														492, Short.MAX_VALUE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jPanel1;
	private javax.swing.JPanel jPanel2;
	// End of variables declaration

}
