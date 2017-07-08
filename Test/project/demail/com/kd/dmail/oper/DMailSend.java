package demail.com.kd.dmail.oper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import KD.IDP.basic.WaitPaneThread;
import demail.com.kd.dmail.config.pojo.ConfigParse;
import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.main.Login;
import demail.com.kd.dmail.tool.DMailTarUtils;
import demail.com.kd.dmail.tool.SFTPTool;

/**
 * 
 * @author xuzhiqi
 */
public class DMailSend extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	public DMailSend() {
		initComponents();
		jbInit();
		jTextField2.setText(Login.userName);
		jTextField2.setEditable(false);
	}

	public void jbInit() {
		jTextArea2.setEditable(false);
		initlist();
		addListener();
	}

	public static void main(String args[]) {
		new DMailSend().initlist();
	}

	public void initlist() {
		HashMap map1 = getDmailUserPath();
		HashMap map2 = getDmailUserIP();
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<String> list = new ArrayList<String>();
		Set<String> string = map2.keySet();
		Object[] ttt = (Object[]) string.toArray();
		Set<String> map1str = map1.keySet();
		for (int i = 0; i < ttt.length; i++) {
			if (ttt[i].toString().indexOf("Port") != -1)
				continue;
			for (String st : map1str) {
				if (st.indexOf(".") != -1)
					list.add(ttt[i] + st.substring(st.indexOf(".")));
				else
					list.add(ttt[i] + "." + st);
			}
		}
		for (String str : list) {
			listModel.addElement(str);
		}
		jList1.setModel(listModel);
	}

	private HashMap<String, String> getDmailUserPath() {
		String flag = "[DMailUserPath]";
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
			return map;
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
		return null;
	}

	private HashMap<String, String> getDmailUserIP() {
		String flag = "[DMailUserIP]";
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
			return map;
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
		return null;
	}

	public void addListener() {
		// 添加附件事件
		jButton8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(new File(
						readDirectory()));
				// FileNameExtensionFilter filter = new FileNameExtensionFilter(
				// "XML & xml Files", "xml");
				// chooser.setFileFilter(filter);
				try {
					if (JFileChooser.APPROVE_OPTION == chooser
							.showOpenDialog(DMailSend.this)) {
						String filename = chooser.getSelectedFile()
								.getAbsolutePath();
						jTextArea2.append(filename + "\n");
					}
				} finally {
				}
			}

			private String readDirectory() {
				// 读取上次保存的目录
				FileInputStream input = null;
				InputStreamReader reader = null;
				BufferedReader br = null;
				String path = null;
				try {
					File file = new File(DMailConstants.confFilePath
							+ "directory.temp");
					if (!file.exists())
						return "";
					input = new FileInputStream(file);
					reader = new InputStreamReader(input);
					br = new BufferedReader(reader);
					path = br.readLine();
					if (path == null) {
						path = "";
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
				return path;
			}
		});

		// 刷新事件
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		// 取消事件
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		jButton1.setVisible(false);
		// 发送
		jButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 首先上传头文件，而后删除客户端生成的文件
				if (!check())
					return;
				Thread thread = new Thread() {
					public void run() {
						String fileName = null;
						ArrayList<String> fileList = new ArrayList<String>();
						if (!"".equals(jTextArea2.getText().toString().trim())) {
							String ss = jTextArea2.getText().toString().trim();
							String tess[] = ss.split("\n");
							for (String tt : tess)
								fileList.add(tt);
							if ("0".equals(Configuration.config
									.getProperty("iscompress"))) {
								fileName = tarFile(fileList);
								new SFTPTool().uploadFile(Configuration.config
										.getProperty("hostip"), 22,
										Configuration.config
												.getProperty("username"),
										Configuration.config
												.getProperty("password"),
										Configuration.config
												.getProperty("sendfilepath"),
										fileName);
							} else {
								for (String str : fileList) {
									new SFTPTool().uploadFile(
											Configuration.config
													.getProperty("hostip"),
											22,
											Configuration.config
													.getProperty("username"),
											Configuration.config
													.getProperty("password"),
											Configuration.config
													.getProperty("sendfilepath"),
											str);
								}
							}
						}
						String resultFile = null;
						if ("0".equals(Configuration.config
								.getProperty("iscompress"))) {
							if (fileName != null && !"".equals(fileName)) {
								File f = new File(fileName);
								long size = f.length() / 1024;
								resultFile = produceEhFile(fileName, size);
							} else
								resultFile = produceEhFile("", 0);
							new SFTPTool().uploadFile(Configuration.config
									.getProperty("hostip"), 22,
									Configuration.config
											.getProperty("username"),
									Configuration.config
											.getProperty("password"),
									Configuration.config
											.getProperty("sendfilepath"),
									resultFile);
						} else {
							if (fileList != null && fileList.size() != 0) {
								long totleSize = 0;
								StringBuffer sb = new StringBuffer();
								for (String file : fileList) {
									File f = new File(file);
									sb.append(f.getName() + ",");
									totleSize += f.length() / 1024;
								}
								String file = sb.toString();
								if (file.endsWith(",")) {
									file = file.substring(0, file.length() - 1);
								}
								resultFile = produceEhFile(file, totleSize);
							} else {
								resultFile = produceEhFile("", 0);
							}
							new SFTPTool().uploadFile(Configuration.config
									.getProperty("hostip"), 22,
									Configuration.config
											.getProperty("username"),
									Configuration.config
											.getProperty("password"),
									Configuration.config
											.getProperty("sendfilepath"),
									resultFile);
						}
					}
				};
				thread.start();
				WaitPaneThread waitPane = new WaitPaneThread(null, thread,
						"正在上传信息至服务器，请稍后...");
				JOptionPane.showMessageDialog(null, "邮件发送成功!");
			}

			private String tarFile(ArrayList<String> fileList) {
				String temp = new java.util.Date().toLocaleString();
				temp = temp.replaceAll(" ", "");
				temp = temp.replaceAll(":", "");
				temp = temp.replaceAll("-", "");
				if (temp.length() < 14)
					temp = temp.substring(0, 4) + "0" + temp.substring(4);
				String fileName = "DMail_" + temp + ".tar";
				DMailTarUtils.execute(fileList, fileName);
				return fileName + ".gz";
			}

			private boolean check() {
				if ("".equals(jTextField1.getText().toString().trim())) {
					JOptionPane.showMessageDialog(DMailSend.this, "文件标题不允许为空!");
					return false;
				}
				if ("".equals(jTextField2.getText().toString().trim())) {
					JOptionPane.showMessageDialog(DMailSend.this, "发送地址不允许为空!");
					return false;

				}
				if ("".equals(jTextArea1.getText().toString().trim())) {
					JOptionPane.showMessageDialog(DMailSend.this, "邮件内容不允许为空!");
					return false;
				}
				if ("".equals(jTextArea2.getText().toString().trim())) {
					JOptionPane.showMessageDialog(DMailSend.this, "请添加要发送的文件!");
					return false;
				}
				if (jList2.getModel().getSize() <= 0) {
					JOptionPane.showMessageDialog(DMailSend.this, "请添加接收地址!");
					return false;
				}
				return true;
			}

			// 生成头文件
			private String produceEhFile(String attachmentFileName, long size) {
				StringBuffer sb = new StringBuffer();
				String currentTime = new java.util.Date().toLocaleString();
				String data = currentTime;
				currentTime = currentTime.replaceAll(" ", "");
				currentTime = currentTime.replaceAll(":", "");
				currentTime = currentTime.replaceAll("-", "");
				if (currentTime.length() < 14)
					currentTime = currentTime.substring(0, 4) + "0"
							+ currentTime.substring(4);
				String begin = "<生产控制大区互联::传输说明  time='" + data + "'>";
				String declare = "@#顺序 属性名 属性值";
				String end = "</生产控制大区互联::传输说明>";
				// 标识
				String title = "#0 标识 "
						+ jTextField1.getText().toString().trim();
				// 内容
				String content = "#4 内容 '"
						+ jTextArea1.getText().toString().trim() + "'";
				// 发送地址
				String sendAddress = "#1 发送地址 "
						+ jTextField2.getText().toString().trim();
				// 接收地址
				String receiveAddress = "#2 接收地址 ";
				int count = jList2.getModel().getSize();
				for (int i = 0; i < count; i++) {
					receiveAddress = receiveAddress
							+ jList2.getModel().getElementAt(i);
					if (i != count - 1)
						receiveAddress = receiveAddress + " ";
				}
				receiveAddress = receiveAddress.trim();
				// 附件
				String fileName = "#5 文件 '" + attachmentFileName + "'";
				// 附件大小
				String fileSize = "#6 附件大小 '" + size + "'k";
				// 传输类型
				String transferType = "#3 传输类型 文件";
				sb.append(begin + "\n");
				sb.append(declare + "\n");
				sb.append(title + "\n");
				sb.append(sendAddress + "\n");
				sb.append(receiveAddress + "\n");
				sb.append(transferType + "\n");
				sb.append(content + "\n");
				sb.append(fileName + "\n");
				sb.append(fileSize + "\n");
				sb.append(end);
				FileOutputStream output = null;
				String file = "DMail_" + currentTime + ".eh";
				try {
					output = new FileOutputStream(file);
					output.write(sb.toString().getBytes());
					output.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (output != null) {
							output.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return file;
			}
		});
		// 清除事件
		jButton6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jTextArea2.setText("");
			}
		});
		// 上传事件
		jButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// 添加接收地址事件
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jList1.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(DMailSend.this, "请选择要发送的地址");
					return;
				}
				((DefaultListModel) jList2.getModel()).addElement(jList1
						.getSelectedValue());
				((DefaultListModel) jList1.getModel()).removeElement(jList1
						.getSelectedValue());
			}
		});
		// 删除接收地址事件
		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jList2.getSelectedValue() == null) {
					JOptionPane
							.showMessageDialog(DMailSend.this, "请选择要删除的发送地址");
					return;
				}
				((DefaultListModel) jList1.getModel()).addElement(jList2
						.getSelectedValue());
				((DefaultListModel) jList2.getModel()).removeElement(jList2
						.getSelectedValue());
			}
		});

	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList2 = new javax.swing.JList();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel5 = new javax.swing.JLabel();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jButton8 = new javax.swing.JButton();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		setBorder(javax.swing.BorderFactory.createTitledBorder("发送邮件"));
		jPanel1.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(153, 153, 255)));
		jLabel1.setText("邮件主题：");
		jLabel2.setText("发送地址：");
		jLabel3.setText("接收地址：");
		jScrollPane1.setViewportView(jList1);

		jButton4.setText("  >>  ");

		jButton5.setText("  <<  ");
		jList1.setModel(new DefaultListModel());
		jList2.setModel(new DefaultListModel());
		jScrollPane2.setViewportView(jList2);

		jLabel4.setText("邮件内容：");
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane3.setViewportView(jTextArea1);

		jLabel5.setText("附件：");

		jButton6.setText("清除");
		jButton6.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "clear_co.gif"));
		jButton7.setText("上传");
		jButton7.setVisible(false);
		jButton7.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "upload.gif"));
		jButton8.setText("添加");
		jButton8.setIcon(new ImageIcon(DMailConstants.imageFilePath + "add.gif"));

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane4.setViewportView(jTextArea2);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout.createSequentialGroup()
										.addContainerGap(268, Short.MAX_VALUE)
										.addComponent(jButton8)
										.addGap(18, 18, 18)
										.addComponent(jButton7)
										.addGap(18, 18, 18)
										.addComponent(jButton6)
										.addGap(254, 254, 254))
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(44, 44, 44)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								jLabel1))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jTextField2,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																597,
																																Short.MAX_VALUE)
																														.addComponent(
																																jTextField1,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																597,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												jScrollPane1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												213,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(18,
																												18,
																												18)
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jButton5)
																														.addComponent(
																																jButton4))
																										.addGap(18,
																												18,
																												18)
																										.addComponent(
																												jScrollPane2,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												225,
																												javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel5,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								59,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								68,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jScrollPane4,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								603,
																								Short.MAX_VALUE)
																						.addComponent(
																								jScrollPane3,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								603,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addGap(10, 10, 10)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
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
														.addComponent(
																jTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel2))
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(27,
																				27,
																				27)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								jScrollPane1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jScrollPane2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(49,
																				49,
																				49)
																		.addComponent(
																				jButton4)
																		.addGap(32,
																				32,
																				32)
																		.addComponent(
																				jButton5)))
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(26,
																				26,
																				26)
																		.addComponent(
																				jLabel4))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(27,
																				27,
																				27)
																		.addComponent(
																				jScrollPane3,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				94,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane4,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				71,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jButton8)
																						.addComponent(
																								jButton7)
																						.addComponent(
																								jButton6)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel5)
																		.addContainerGap()))));

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jButton1.setText("刷新");
		jButton1.setIcon(new ImageIcon("E:\\人机开发\\image\\refresh.gif"));
		jButton2.setText("取消");
		jButton2.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "icon_del.gif"));
		jButton3.setText("发送");
		jButton3.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "upload.gif"));
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel2Layout.createSequentialGroup()
						.addContainerGap(520, Short.MAX_VALUE)
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
										.addContainerGap(22, Short.MAX_VALUE)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1)
														.addComponent(jButton2)
														.addComponent(jButton3))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
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
														jPanel1,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jPanel2,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(18, 18, 18)
						.addComponent(jPanel2,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JList jList1;
	private javax.swing.JList jList2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;

}
