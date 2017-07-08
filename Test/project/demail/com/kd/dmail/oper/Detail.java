package demail.com.kd.dmail.oper;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jcraft.jsch.SftpException;

import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.tool.SFTPTool;

/**
 * 
 * @author xuzhiqi
 */
public class Detail extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> content = null;
	private String directory = null;
	private boolean flag = false;

	public Detail(java.awt.Frame parent, boolean modal,
			HashMap<String, String> content, String directory, boolean flag) {
		super(parent, modal);
		this.content = content;
		this.flag = flag;
		this.directory = directory;
		initComponents();
		jbInit();
		setSize(700, 580);
		setResizable(false);
		setTitle("查看邮件详细信息");
		addListener();
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		setLocation((width - this.getWidth()) / 2,
				(height - this.getHeight()) / 2);
	}

	private void jbInit() {
		ImageIcon icon = new javax.swing.ImageIcon(DMailConstants.imageFilePath
				+ "DefaultTitleBackground.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(700, 120,
				Image.SCALE_DEFAULT));
		jLabel1.setVerticalTextPosition(JLabel.CENTER);
		jLabel1.setHorizontalTextPosition(JLabel.CENTER);
		jLabel1.setIcon(icon);
		jLabel1.setFont(new Font("Serif", Font.BOLD, 15));
		jLabel1.setText("查看详细信息");
		ImageIcon icon2 = new javax.swing.ImageIcon(
				DMailConstants.imageFilePath + "DefaultDecorativeImage.jpg");
		icon2 = new ImageIcon(icon2.getImage().getScaledInstance(300, 430,
				Image.SCALE_DEFAULT));
		jLabel2.setVerticalTextPosition(JLabel.CENTER);
		jLabel2.setHorizontalTextPosition(JLabel.CENTER);
		jLabel2.setIcon(icon2);
		jLabel2.setText("");
		jTextField1.setText(content.get("title"));
		jTextField2.setText(content.get("send"));
		jTextField3.setText(content.get("sendTime"));
		jTextField4.setText(filter(content.get("attachmentFile")));
		jTextField5.setText(content.get("size"));
		jTextArea1.setText(filter(content.get("content")));
		jTextField1.setEditable(false);
		jTextField2.setEditable(false);
		jTextField3.setEditable(false);
		jTextField4.setEditable(false);
		jTextField5.setEditable(false);
		jTextArea1.setEditable(false);
	}

	public String filter(String str) {
		if (str == null || "".equals(str))
			return "";
		str = str.trim();
		if (str.startsWith("'"))
			str = str.substring(1);
		if (str.endsWith("'"))
			str = str.substring(0, str.length() - 1);
		return str;
	} // 将邮件放置到已接收目录

	public void setBusy() {
		super.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	// 将鼠标状态置为空闲状态
	public void freeBusy() {
		super.setCursor(null);
	}

	public void replaceFile(String fileName) {
		File f = new File("temp");
		if (!f.exists()) {
			f.mkdir();
		}
		String temp[] = fileName.trim().split(",");
		for (String str : temp) {
			new SFTPTool().downloadFile(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"), directory,
					str, "temp" + File.separator + str);
			new SFTPTool().uploadFile(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"),
					directory.substring(0, directory.length() - 1) + "d/",
					"temp" + File.separator + str);
			try {
				new SFTPTool().delete(
						Configuration.config.getProperty("hostip"), 22,
						Configuration.config.getProperty("username"),
						Configuration.config.getProperty("password"),
						directory, str);
			} catch (SftpException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void addListener() {
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				if (flag) {
					String fileName = jTextField4.getText();
					if (fileName != null && content.get("type") != null
							&& content.get("type").equals("0"))
						replaceFile(fileName);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setBusy();
				if (flag) {
					String fileName = jTextField4.getText();
					if (fileName != null && content.get("type") != null
							&& content.get("type").equals("0"))
						replaceFile(fileName);
				}
				freeBusy();
				Detail.this.dispose();
			}

		});
		jButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (null == filter(content.get("attachmentFile"))) {
					JOptionPane.showMessageDialog(null, "没有附件");
					return;
				}
				setBusy();
				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				String dir = System.getProperty("user.dir");
				File directory_down = new File(dir);
				if (directory_down != null)
					filechooser.setCurrentDirectory(directory_down);
				int r = filechooser.showDialog(Detail.this, "选择");
				if (r == JFileChooser.APPROVE_OPTION) {
					String fileName = filter(content.get("attachmentFile"));
					String file[] = fileName.split(",");
					for (String ff : file) {
						new SFTPTool().downloadFile(
								Configuration.config.getProperty("hostip"), 22,
								Configuration.config.getProperty("username"),
								Configuration.config.getProperty("password"),
								directory, ff, filechooser.getSelectedFile()
										.getPath() + File.separator + ff);
					}
					JOptionPane.showMessageDialog(Detail.this, "文件下载完毕！\n文件位置："
							+ filechooser.getSelectedFile().getPath()
							+ File.separator + fileName);
				}
				freeBusy();
			}
		});
	}

	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jButton3 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel8 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("jLabel1");

		jLabel2.setText("jLabel2");

		jPanel1.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		jButton3.setText("下载附件");

		jButton2.setText("jButton2");
		jButton2.setVisible(false);
		jButton1.setText("关闭");

		jLabel8.setText("邮件内容：");
		jLabel7.setText("附件大小：");

		jLabel5.setText("发送时间：");

		jLabel6.setText("附件名称：");

		jLabel4.setText("发件人：");

		jLabel3.setText("邮件主题：");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(3);
		jScrollPane1.setViewportView(jTextArea1);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel8,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel7,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel4,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																142,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(152,
																				152,
																				152)
																		.addComponent(
																				jButton3)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jButton2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jButton1))
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jTextField5,
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jTextField4,
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jTextField3,
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jTextField2,
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jTextField1,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				309,
																				Short.MAX_VALUE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																jTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																jTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																jTextField4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																jTextField5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel8)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																82,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												23, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton3)
														.addComponent(jButton2)
														.addComponent(jButton1))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										131,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap())
				.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						726, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										108,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jPanel1,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGap(10, 10,
																		10))
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														355, Short.MAX_VALUE))));

		pack();
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
}
