package demail.com.kd.dmail.oper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.tool.FrameSize;
import kd.idp.dmail.DMCommon;

/**
 * 
 * @author xuzhiqi
 */
public class WelcomePanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	public WelcomePanel() {
		initComponents();
		DMCommon dmcommon = new DMCommon(
				Configuration.config.getProperty("hostip"),
				Configuration.config.getProperty("port"));
		jProgressBar1.setMinimum(0);
		jProgressBar1.setMaximum(100);
		jProgressBar1.setStringPainted(true);
		jProgressBar1.setPreferredSize(new Dimension(300, 20));
		jProgressBar1.setBorderPainted(true);
		jProgressBar1.setBackground(Color.pink);
		try {
//			String space = dmcommon
//					.getDMailServerSpaceInfor(Configuration.config
//							.getProperty("userdisk"));
//			if (space.endsWith("%"))
//				space = space.substring(0, space.length() - 1);
		    String space = String.valueOf(50);
			Integer space_value = Integer.valueOf(space);
			jProgressBar1.setValue(space_value);
			// jProgressBar1.setValue(50);
			// jProgressBar1.setToolTipText("50%");
			jProgressBar2.setMinimum(0);
			jProgressBar2.setMaximum(100);
			jProgressBar2.setStringPainted(true);
			jProgressBar2.setPreferredSize(new Dimension(300, 20));
			jProgressBar2.setBorderPainted(true);
			jProgressBar2.setBackground(Color.pink);
			// String postbox = dmcommon.getDMailServerPostboxInfor("zdh",
			// "dpt");
			// Integer postbox_value = Integer.valueOf(postbox);
			// double x = postbox_value/(1000*1024);
			// System.err.println(x);
			jProgressBar2.setValue(35);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		ImageIcon icon = new javax.swing.ImageIcon(DMailConstants.imageFilePath
				+ "background.gif");
		icon = new ImageIcon(icon.getImage().getScaledInstance(
				FrameSize.width - 218, 280, Image.SCALE_DEFAULT));
		jLabel1.setVerticalTextPosition(JLabel.CENTER);
		jLabel1.setHorizontalTextPosition(JLabel.CENTER);
		jLabel1.setIcon(icon);
		jLabel1.setFont(new Font("Serif", Font.BOLD, 23));
		jLabel1.setText("欢迎使用消息邮件管理系统(DMail)客户端");
		jProgressBar1 = new javax.swing.JProgressBar();

		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jProgressBar2 = new javax.swing.JProgressBar();
		jSeparator1 = new javax.swing.JSeparator();

		jLabel2.setText("邮箱服务器总使用率：");

		jLabel3.setText("用户邮箱空间使用率：");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jSeparator1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 778,
						Short.MAX_VALUE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														768, Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jLabel2,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jLabel3,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						153,
																						Short.MAX_VALUE))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jProgressBar1,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						587,
																						Short.MAX_VALUE)
																				.addComponent(
																						jProgressBar2,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						587,
																						Short.MAX_VALUE))))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										191,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(
														jProgressBar1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(55, 55, 55)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jLabel3)
												.addComponent(
														jProgressBar2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(328, Short.MAX_VALUE)));
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JProgressBar jProgressBar1;
	private javax.swing.JProgressBar jProgressBar2;
	private javax.swing.JSeparator jSeparator1;
	// End of variables declaration

}
