package demail.com.kd.dmail.main;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import demail.com.kd.dmail.config.pojo.ConfigParse;
import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.tool.SFTPTool;

/**
 * 
 * @author xuzhiqi
 */
public class Login extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	String flag = "[DMailUser]";
	public static String dMailUserPath = "";
	public static String userName = "";

	public Login(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setTitle("登录系统");
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		setLocation((width - this.getWidth()) / 2,
				(height - this.getHeight()) / 2);
		addListener();

	}

	private boolean check() {
		// 截取文件
		new SFTPTool().downloadFile(Configuration.config.getProperty("hostip"),
				22, Configuration.config.getProperty("username"),
				Configuration.config.getProperty("password"),
				Configuration.config.getProperty("hostpath"), "dmail.cfg",
				"temp.cfg");
		InputStream input = null;
		InputStreamReader reader = null;
		BufferedReader br = null;
		try {
			input = new FileInputStream("temp.cfg");
			reader = new InputStreamReader(input);
			br = new BufferedReader(reader);
			HashMap<String, String> map = ConfigParse.parseFile(br, flag);
			Set<Map.Entry<String, String>> set = map.entrySet();
			for (Map.Entry<String, String> ma : set) {
				String temp[] = ma.getValue().split(",");
				if (temp.length > 1) {
					if (temp[0].equals(jTextField1.getText().toString())
							&& temp[1].equals(jPasswordField1.getText()
									.toString().trim())) {
						userName = ma.getKey();
						dMailUserPath = getOriginalContext(ma.getKey());
						if (dMailUserPath == null) {
							dMailUserPath = "/home/d5000/dmail/dmailsg/dpt/zdh/recv/";
						}
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// 取出用户对应的路径
	// 取出配置文件中的原始内容
	public static String getOriginalContext(String area) {
		String result = null;
		String flag = "[DMailUserPath]";
		HashMap<String, String> list = null;
		new SFTPTool().downloadFile(Configuration.config.getProperty("hostip"), 22,
				Configuration.config.getProperty("username"),
				Configuration.config.getProperty("password"),
				Configuration.config.getProperty("hostpath"), "dmail.cfg",
				"temp.cfg");
		InputStream input = null;
		InputStreamReader reader = null;
		BufferedReader br = null;
		try {
			input = new FileInputStream("temp.cfg");
			reader = new InputStreamReader(input);
			br = new BufferedReader(reader);
			list = ConfigParse.parseFile(br, flag);
			result = list.get(area);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void addListener() {
		jPasswordField1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					login();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

		});
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jTextField1.setText("");
				jPasswordField1.setText("");
			}
		});
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jPasswordField1 = new javax.swing.JPasswordField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon icon = new javax.swing.ImageIcon(DMailConstants.imageFilePath
				+ "DefaultTitleBackground.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(380, 90,
				Image.SCALE_DEFAULT));
		jLabel1.setIcon(icon);
		jLabel1.setVerticalTextPosition(JLabel.CENTER);
		jLabel1.setHorizontalTextPosition(JLabel.CENTER);
		ImageIcon icon1 = new javax.swing.ImageIcon(
				DMailConstants.imageFilePath + "logo.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(200, 80,
				Image.SCALE_DEFAULT));
		// jLabel1.setIcon(icon1);
		jLabel1.setFont(new Font("Serif", Font.BOLD, 15));
		jLabel1.setText("欢迎使用邮件管理登录系统");
		jLabel2.setText("用户名：");
		jLabel3.setText("密  码：");
		jButton1.setText("登录");
		jButton2.setText("重置");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE,
						378, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												false)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jLabel3,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGap(10, 10,
																		10)
																.addComponent(
																		jLabel2,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		95,
																		Short.MAX_VALUE)))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jButton1)
																.addGap(26, 26,
																		26)
																.addComponent(
																		jButton2))
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
																.addComponent(
																		jPasswordField1)
																.addComponent(
																		jTextField1,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		214,
																		Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										67,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(
														jPasswordField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										77, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton2)
												.addComponent(jButton1))
								.addGap(27, 27, 27)));

		pack();
	}

	private void login() {
		if (jTextField1.getText() == null
				|| "".equals(jTextField1.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "请输入用户名!");
			return;
		}
		if (jPasswordField1.getText() == null
				|| "".equals(jPasswordField1.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "请输入密码!");
			return;
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			if (check()) {
				new MainFrame().setVisible(true);
				Login.this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码不正确!");
				return;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Login dialog = new Login(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextField jTextField1;
}
