package demail.com.kd.dmail.main;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import demail.com.kd.dmail.config.ConfigMain;
import demail.com.kd.dmail.help.DMailHelp;
import demail.com.kd.dmail.oper.DMailOperationMain;
import demail.com.kd.dmail.tool.FrameSize;
import demail.com.kd.dmail.watch.DMailWatchMainPanel;

/**
 * 
 * @author xuzhiqi
 */
public class MainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame() throws Exception {
		initComponents();
		addListener();
		setTitle("智能电网调度技术支持系统-消息邮件客户端");
		int width = 0;
		int height = 0;
		if (Toolkit.getDefaultToolkit().getScreenSize().getWidth() > 1280.0)
			width = 1200;
		else
			width = (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getWidth() - 5;
		if (Toolkit.getDefaultToolkit().getScreenSize().getHeight() > 830.0)
			height = 830;
		else
			height = (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getHeight() - 40;
		setSize(width, height);
		FrameSize.width = width;
		FrameSize.height = height;
		int x = ((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - width) / 2;
		int y = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - height) / 2;
		setLocation(x, y);
		jScrollPane1.getViewport().add(new DMailOperationMain());
		setResizable(false);
	}

	public void addListener() {
		// 发送接收邮件
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new DMailOperationMain());
			}
		});
		// 监控主界面
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new DMailWatchMainPanel());
			}
		});
		// 配置主界面
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new ConfigMain());
			}
		});
		// 帮助信息
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new DMailHelp());
			}
		});
		// 回到主目录
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new DMailWatchMainPanel());
			}
		});
		// 退出事件
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
			}
		});
		email.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new DMailOperationMain());
			}
		});

		config.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new ConfigMain());
			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new DMailHelp());
			}
		});
		watch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jScrollPane1.getViewport().add(new DMailWatchMainPanel());
			}
		});
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

				File f = new File(".");
				if (f.isDirectory()) {
					File[] ff = f.listFiles(new FileFilter() {

						@Override
						public boolean accept(File pathname) {
							if (pathname.getName().endsWith(".cfg")
									|| pathname.getName().endsWith(".eh")
									|| pathname.getName().endsWith("gz")) {
								return true;
							}
							return false;
						}
					});
					for (File file : ff) {
						del(file);
						// del1(file);
					}
				}
				f = new File("temp");
				if (f.isDirectory()) {
					File[] ff = f.listFiles(new FileFilter() {

						@Override
						public boolean accept(File pathname) {
							// if (pathname.getName().endsWith(".cfg")
							// || pathname.getName().endsWith(".eh")
							// || pathname.getName().endsWith("gz")) {
							// return true;
							// }
							return true;
						}
					});
					for (File file : ff) {
						del(file);
						// del1(file);
					}
				}
			}

			private void del(File f) {
				f.delete();
				// Runtime rt = Runtime.getRuntime();
				// try {
				// String str = "del " + f.getAbsoluteFile();
				// char[] chars = str.toCharArray();
				// StringBuffer sb = new StringBuffer();
				// for (char c : chars) {
				// if (c == '\\')
				// c = File.separatorChar;
				// sb.append(c);
				// }
				// System.out.println(sb.toString());
				// rt.exec(sb.toString());
				// } catch (IOException e1) {
				// e1.printStackTrace();
				// }
			}

			// private void del1(File f) {
			// Runtime rt = Runtime.getRuntime();
			// try {
			// System.out.println("rm " + f.getAbsoluteFile());
			// rt.exec("del " + f.getAbsoluteFile());
			// } catch (IOException e1) {
			// e1.printStackTrace();
			// }
			// }

			@Override
			public void windowClosed(WindowEvent e) {
				File f = new File("");
				if (f.isDirectory()) {
					File[] ff = f.listFiles(new FileFilter() {

						@Override
						public boolean accept(File pathname) {
							if (pathname.getName().endsWith(".cfg")
									|| pathname.getName().endsWith(".eh")
									|| pathname.getName().endsWith("gz")) {
								return true;
							}
							return false;
						}
					});
					for (File file : ff) {
						file.delete();
					}
				}
				f = new File("temp");
				if (f.isDirectory()) {
					f.delete();
				}
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
	}

	private void initComponents() {
		jToolBar1 = new javax.swing.JToolBar();
		jScrollPane1 = new javax.swing.JScrollPane();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		jMenu3 = new javax.swing.JMenu();
		jMenu4 = new javax.swing.JMenu();
		jMenu5 = new javax.swing.JMenu();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jToolBar1.setRollover(true);
		button1 = new javax.swing.JButton();
		ImageIcon icon1 = new ImageIcon("image/email.gif");
		button1.setIcon(icon1);
		button1.setToolTipText("邮件服务");
		button1.setFocusable(false);
		button1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		button2 = new javax.swing.JButton();
		ImageIcon icon2 = new ImageIcon("image/constraintitem.gif");
		button2.setIcon(icon2);
		button2.setToolTipText("监控服务");
		button2.setFocusable(false);
		button2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		button3 = new javax.swing.JButton();
		ImageIcon icon3 = new ImageIcon("image/action.gif");
		button3.setIcon(icon3);
		button3.setToolTipText("配置信息");
		button3.setFocusable(false);
		button3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		button4 = new javax.swing.JButton();
		ImageIcon icon4 = new ImageIcon("image/addinfoband.gif");
		button4.setIcon(icon4);
		button4.setToolTipText("帮助");
		button4.setFocusable(false);
		button4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		button5 = new javax.swing.JButton();
		ImageIcon icon5 = new ImageIcon("image/welcome_file_list_new.gif");
		button5.setIcon(icon5);
		button5.setToolTipText("回到主目录");
		button5.setFocusable(false);
		button5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar1.add(button5);
		jToolBar1.add(button1);
		jToolBar1.add(button2);
		jToolBar1.add(button3);
		jToolBar1.add(button4);

		jMenu1.setText("文件");
		exit = new JMenuItem("退出");
		jMenu1.add(exit);
		jMenuBar1.add(jMenu1);

		jMenu2.setText("邮件服务");
		email = new JMenuItem("进入邮箱");
		email.setIcon(icon1);
		jMenu2.add(email);
		jMenuBar1.add(jMenu2);
		jMenu3.setText("监控服务");
		watch = new JMenuItem("监控");
		watch.setIcon(icon2);
		jMenu3.add(watch);
		jMenuBar1.add(jMenu3);
		jMenu4.setText("配置服务");
		config = new JMenuItem("配置信息");
		config.setIcon(icon3);
		jMenu4.add(config);
//		jMenuBar1.add(jMenu4);
		jMenu5.setText("帮助");
		help = new JMenuItem("帮助");
		ImageIcon icon_help = new ImageIcon("image/RTHelp.gif");
		help.setIcon(icon_help);
		jMenu5.add(help);
		jMenuBar1.add(jMenu5);
		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE,
						924, Short.MAX_VALUE)
				.addComponent(jScrollPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 924,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jToolBar1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										616, Short.MAX_VALUE)));

		pack();
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					new MainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JMenuItem exit;
	private JMenuItem email;
	private JMenuItem watch;
	private JMenuItem help;
	private JMenuItem config;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenu jMenu5;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JButton button1;
	private javax.swing.JButton button2;
	private javax.swing.JButton button3;
	private javax.swing.JButton button4;
	private javax.swing.JButton button5;
}
