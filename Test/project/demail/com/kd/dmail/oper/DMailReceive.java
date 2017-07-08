package demail.com.kd.dmail.oper;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import KD.IDP.basic.WaitPaneThread;
import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.tool.DateUtils;
import demail.com.kd.dmail.tool.SFTPTool;

import com.jcraft.jsch.SftpException;

/**
 * 
 * @author xuzhiqi
 */
public class DMailReceive extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel model = null;
	Object title_table[] = new String[] { "选择", "邮件主题", "发件人", "收件人", "发送时间",
			"附件大小", "FileName", "attachmentName", "content", "type" };
	Vector vector = null;
	String directory = "/";
	private int index = 1;
	private int allPages = 0;
	private int pageCount = 10;
	ArrayList list = null;
	private String suffix;

	public DMailReceive(String title, String directory, String suffix) {
		this.suffix = suffix;
		this.title = title;
		this.directory = directory;
		initComponents();
		jbInit();
	}

	public void displayPageNumber() {
		jLabel3.setText("当前是第： "
				+ index
				+ " 页，共 "
				+ ((list.size() % pageCount == 0) ? (list.size() / pageCount)
						: ((list.size() / pageCount) + 1)) + " 页");
	}

	public void jbInit() {
		jRadioButton3.setSelected(true);
		addActionListener();
		hideColumn();
		Thread t = new Thread() {
			public void run() {
				list = getAllFile();
				filterByDate();
				display(getPage());
				hideColumn();
				displayPageNumber();
			}
		};
		t.start();
		WaitPaneThread wait = new WaitPaneThread(null, t, "系统正在收取邮件，请稍后......");
		wait.start();
	}

	private void filterByDate() {
		ArrayList l = new ArrayList();
		if (jRadioButton3.isSelected())
			return;

		String type = "day";
		if (jRadioButton1.isSelected())
			type = "day";
		if (jRadioButton2.isSelected())
			type = "week";

		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			if ("day".equals(type)
					&& map.get("时间").toString()
							.startsWith(DateUtils.getDate1(type))) {
				l.add(map);
			}
			if ("week".equals(type)
					&& map.get("时间").toString()
							.compareTo(DateUtils.getDate1(type)) <= 0) {
				l.add(map);
			}
		}
		if (l.size() != 0)
			list = l;
	}

	public ArrayList getPage() {
		ArrayList current = null;
		if (list.size() <= pageCount) {
			current = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				current.add(list.get(i));
			}
			return current;
		}
		current = new ArrayList();
		for (int i = (index - 1) * pageCount; i < (pageCount * index > list
				.size() ? list.size() : pageCount * index); i++) {
			current.add(list.get(i));
		}
		return current;
	}

	private void hideColumn() {
		jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
		jTable1.getColumnModel().getColumn(8).setMinWidth(0);
		jTable1.getColumnModel().getColumn(8).setWidth(0);
		jTable1.getColumnModel().getColumn(8).setPreferredWidth(0);
		jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
		jTable1.getColumnModel().getColumn(9).setMinWidth(0);
		jTable1.getColumnModel().getColumn(9).setWidth(0);
		jTable1.getColumnModel().getColumn(9).setPreferredWidth(0);
		jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
		jTable1.getColumnModel().getColumn(6).setMinWidth(0);
		jTable1.getColumnModel().getColumn(6).setWidth(0);
		jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);
		jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
		jTable1.getColumnModel().getColumn(7).setMinWidth(0);
		jTable1.getColumnModel().getColumn(7).setWidth(0);
		jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
		jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
		jTable1.getColumnModel().getColumn(0).setMinWidth(40);
		jTable1.getColumnModel().getColumn(0).setWidth(40);
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
	}

	// 列出服务器某个目录中的所有文件，并按照时间排序
	@SuppressWarnings("rawtypes")
	public ArrayList getAllFile() {
		ArrayList list = new ArrayList();
		try {
			Vector v1 = new SFTPTool().listFiles(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"), directory);
			Vector v2 = new SFTPTool().listFiles(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"),
					directory.substring(0, directory.length() - 1) + "d/");
			// vector.addAll(v);
			ArrayList list1 = parseFile(v1, "0");
			ArrayList list2 = parseFile(v2, "1");
			for (Object obj : list1)
				list.add(obj);
			for (Object obj : list2)
				list.add(obj);
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList parseFile(Vector vector, String type) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < vector.size(); i++) {
			String file = vector.get(i).toString();
			file = file.substring(file.lastIndexOf(" ") + 1);
			if (file.endsWith("]"))
				file = file.substring(0, file.length() - 1);
			if (!file.endsWith(suffix))
				continue;
			File f = new File("temp");
			if (!f.exists()) {
				f.mkdir();
			}
			if (type.equals("0"))
				new SFTPTool().downloadFile(
						Configuration.config.getProperty("hostip"), 22,
						Configuration.config.getProperty("username"),
						Configuration.config.getProperty("password"),
						directory, file, "temp" + File.separator + "temp.eh");
			else
				new SFTPTool().downloadFile(
						Configuration.config.getProperty("hostip"), 22,
						Configuration.config.getProperty("username"),
						Configuration.config.getProperty("password"),
						directory.substring(0, directory.length() - 1) + "d/",
						file, "temp" + File.separator + "temp.eh");
			FileInputStream input = null;
			InputStreamReader reader = null;
			BufferedReader br = null;
			try {
				input = new FileInputStream("temp" + File.separator + "temp.eh");
				reader = new InputStreamReader(input);
				br = new BufferedReader(reader);
				String line = null;
				String time = null;
				while ((line = br.readLine()) != null) {
					if (line.startsWith("<")) {
						time = line;
						continue;
					}
					if (!line.startsWith("#0"))
						continue;
					HashMap<String, String> map = getFile(br, line);
					time = time.substring(time.indexOf("=") + 2);
					time = time.substring(0, time.length() - 2);
					map.put("时间", time);
					map.put("fileName", file);
					map.put("type", type);
					list.add(map);
					break;
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
		}
		return list;
	}

	public HashMap<String, String> getFile(BufferedReader reader,
			String line_temp) {
		String line = null;
		HashMap<String, String> map = new HashMap<String, String>();
		line_temp = line_temp.substring(line_temp.indexOf(" ") + 1);
		String temp1[] = line_temp.split(" ");
		map.put(temp1[0], temp1[1]);
		try {
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("</"))
					break;
				line = line.substring(line.indexOf(" ") + 1);
				String temp[] = line.split(" ");
				map.put(temp[0], temp[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	// 将邮件放置到已接收目录
	public void replaceFile(String fileName) {
		File f = new File("temp");
		if (!f.exists()) {
			f.mkdir();
		}

		new SFTPTool().downloadFile(Configuration.config.getProperty("hostip"),
				22, Configuration.config.getProperty("username"),
				Configuration.config.getProperty("password"), directory,
				fileName, "temp" + File.separator + fileName);
		new SFTPTool().uploadFile(Configuration.config.getProperty("hostip"),
				22, Configuration.config.getProperty("username"),
				Configuration.config.getProperty("password"),
				directory.substring(0, directory.length() - 1) + "d/", "temp"
						+ File.separator + fileName);
		try {
			new SFTPTool().delete(Configuration.config.getProperty("hostip"),
					22, Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"), directory,
					fileName);
		} catch (SftpException e1) {
			e1.printStackTrace();
		}
	}

	public void display(ArrayList list) {
		Object[][] obj = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			Object[] row = new Object[10];
			// 第一列(选择列)
			row[0] = new Boolean(false);
			if (map.get("type") != null && "1".equals(map.get("type"))) {
				// 邮件主题
				row[1] = map.get("标识");
			} else {
				row[1] = new Font(map.get("标识").toString(), Font.BOLD, 12);
			}
			// 发件人
			row[2] = map.get("发送地址");

			row[3] = map.get("接收地址");

			// 发送时间
			row[4] = map.get("时间");
			// 附件大小
			row[5] = filter(map.get("附件大小") == null ? "" : map.get("附件大小")
					.toString());
			// 文件名称
			row[6] = filter(map.get("fileName") == null ? "" : map.get(
					"fileName").toString());
			// 附件名称
			row[7] = filter(map.get("文件") == null ? "" : map.get("文件")
					.toString());
			row[8] = map.get("内容");
			row[9] = map.get("type");
			obj[i] = row;
		}
		model.setDataVector(obj, title_table);
	}

	public String filter(String str) {
		if (str == null || "".equals(str))
			return "";
		str = str.trim();
		if (str.startsWith("'"))
			str = str.substring(1);
		if (str.endsWith("'"))
			str = str.substring(0, str.length() - 1);
		str = str.replaceAll("'", "");
		return str;
	}

	public void setBusy() {
		super.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	// 将鼠标状态置为空闲状态
	public void freeBusy() {
		super.setCursor(null);
	}

	public void addActionListener() {
		jTable1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = jTable1.getSelectedRow();
				if (selectedRow < 0) {
					return;
				}
				setBusy();
				HashMap<String, String> content = new HashMap<String, String>();
				content.put("title",
						jTable1.getValueAt(selectedRow, 1) == null ? ""
								: jTable1.getValueAt(selectedRow, 1).toString());
				content.put("send",
						jTable1.getValueAt(selectedRow, 2) == null ? ""
								: jTable1.getValueAt(selectedRow, 2).toString());
				content.put("sendTime",
						jTable1.getValueAt(selectedRow, 4) == null ? ""
								: jTable1.getValueAt(selectedRow, 4).toString());
				content.put("size",
						jTable1.getValueAt(selectedRow, 5) == null ? ""
								: jTable1.getValueAt(selectedRow, 5).toString());
				content.put("fileName",
						jTable1.getValueAt(selectedRow, 6) == null ? ""
								: jTable1.getValueAt(selectedRow, 6).toString());
				content.put("attachmentFile", jTable1
						.getValueAt(selectedRow, 7) == null ? "" : jTable1
						.getValueAt(selectedRow, 7).toString());
				content.put("content",
						jTable1.getValueAt(selectedRow, 8) == null ? ""
								: jTable1.getValueAt(selectedRow, 8).toString());
				content.put("type",
						jTable1.getValueAt(selectedRow, 9) == null ? ""
								: jTable1.getValueAt(selectedRow, 9).toString());
				if ((jTable1.getValueAt(selectedRow, 9) == null ? "" : jTable1
						.getValueAt(selectedRow, 9).toString()).equals("0")) {
					replaceFile(jTable1.getValueAt(selectedRow, 6) == null ? ""
							: jTable1.getValueAt(selectedRow, 6).toString());
					jTable1.setValueAt("1", selectedRow, 9);
				}
				freeBusy();
				new Detail(new JFrame(), true, content, directory, true)
						.setVisible(true);
			}
		});
		// 刷新事件
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					public void run() {
						list = getAllFile();
						filterByDate();
						display(getPage());
						hideColumn();
						displayPageNumber();
					}
				};
				t.start();
				WaitPaneThread wait = new WaitPaneThread(null, t,
						"系统正在收取邮件，请稍后......");
				wait.start();
			}
		});
		// 删除事件
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setBusy();
				// 这里默认把邮件主题当做了文件名称
				int count = jTable1.getRowCount();
				// 保存要删除的行号
				ArrayList<Integer> removeRow = new ArrayList<Integer>();
				ArrayList<String> fileNames = new ArrayList<String>();
				for (int i = 0; i < count; i++) {
					if (jTable1.getValueAt(i, 0) != null
							&& (Boolean) jTable1.getValueAt(i, 0)) {
						fileNames.add(jTable1.getValueAt(i, 5).toString());
						removeRow.add(i);
					}
				}
				if (fileNames.size() <= 0) {
					JOptionPane.showMessageDialog(DMailReceive.this,
							"请选择要删除的邮件!");
					return;
				}
				for (String fileName : fileNames) {
					new SFTPTool().downloadFile(
							Configuration.config.getProperty("hostip"), 22,
							Configuration.config.getProperty("username"),
							Configuration.config.getProperty("password"),
							directory, fileName, fileName);
					new SFTPTool().uploadFile(
							Configuration.config.getProperty("hostip"), 22,
							Configuration.config.getProperty("username"),
							Configuration.config.getProperty("password"),
							Configuration.config.getProperty("garbagepath"),
							fileName);
					try {
						new SFTPTool().delete(
								Configuration.config.getProperty("hostip"), 22,
								Configuration.config.getProperty("username"),
								Configuration.config.getProperty("password"),
								directory, fileName);
					} catch (SftpException e1) {
						e1.printStackTrace();
					}
				}
				for (int i = 0; i < removeRow.size(); i++) {
					((DefaultTableModel) jTable1.getModel())
							.removeRow(removeRow.get(i));
				}
				hideColumn();
				freeBusy();
				JOptionPane.showMessageDialog(DMailReceive.this, "已成功删除");
			}
		});
		// 上一页
		jButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (index <= 1) {
					JOptionPane
							.showMessageDialog(DMailReceive.this, "当前已是第一页！");
					return;
				}
				index--;
				display(getPage());
				hideColumn();
			}
		});
		// 下一页
		jButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.size() <= (index * pageCount)) {
					JOptionPane.showMessageDialog(DMailReceive.this,
							"当前已是最后一页！");
					return;
				}
				index++;
				display(getPage());
				hideColumn();
			}
		});
	}

	private String title = "接收邮件";

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jRadioButton1 = new javax.swing.JRadioButton();
		jRadioButton2 = new javax.swing.JRadioButton();
		jRadioButton3 = new javax.swing.JRadioButton();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		ButtonGroup group = new ButtonGroup();
		group.add(jRadioButton1);
		group.add(jRadioButton2);
		group.add(jRadioButton3);
		setBorder(javax.swing.BorderFactory.createTitledBorder(title));
		jPanel1.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		jButton1.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "refresh.gif"));
		jButton1.setText("刷新");
		jButton2.setText("删除");
		jButton2.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "icon_del.gif"));
		jButton3.setText("上一页");
		jButton3.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "First17.gif"));
		jButton4.setText("下一页");
		jButton4.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "First16.gif"));
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("过滤条件"));

		jLabel1.setText("发送时间：");

		jRadioButton1.setText("  今日");

		jRadioButton2.setText("  本周");

		jRadioButton3.setText("  本月");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jRadioButton3)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				99,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(59,
																				59,
																				59)
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jRadioButton2)
																						.addComponent(
																								jRadioButton1))))
										.addContainerGap(64, Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																jRadioButton1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jRadioButton2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jRadioButton3)
										.addContainerGap(15, Short.MAX_VALUE)));

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
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButton1)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jButton2)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jButton3))
														.addComponent(
																jLabel3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addComponent(jButton4)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												150, Short.MAX_VALUE)
										.addComponent(
												jPanel3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
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
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jButton1)
																						.addComponent(
																								jButton2)
																						.addComponent(
																								jButton3)
																						.addComponent(
																								jButton4))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel3))
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		jPanel2.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		model = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "选择", "邮件主题", "发件人", "收件人", "发送时间", "附件大小",
						"FileName", "attachmentName", "content", "type" }) {
			Class[] types = new Class[] { java.lang.Boolean.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class };
			boolean[] canEdit = new boolean[] { true, false, false, false,
					false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		jTable1.setModel(model);
		jTable1.setRowHeight(25);
		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 761,
								Short.MAX_VALUE).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addContainerGap(13, Short.MAX_VALUE)
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

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
														jPanel2,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jPanel1,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
}
