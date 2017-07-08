package demail.com.kd.dmail.oper;

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
import demail.com.kd.dmail.main.Login;
import demail.com.kd.dmail.tool.DateUtils;
import demail.com.kd.dmail.tool.SFTPTool;

import com.jcraft.jsch.SftpException;

/**
 * 
 * @author xuzhiqi
 */
public class DMailReceiveLog extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel model = null;
	Object title_table[] = new String[] { "ѡ��", "�ʼ�����", "������", "�ռ���", "����ʱ��",
			"������С", "FileName", "attachmentName", "content" };
	Vector vector = null;
	String directory = "/";
	private int index = 1;
	private int allPages = 0;
	private int pageCount = 10;
	ArrayList list = null;

	public DMailReceiveLog(String title, String directory) {
		this.title = title;
		this.directory = Login.dMailUserPath;
		this.directory = directory;
		initComponents();
		jbInit();

	}

	public void jbInit() {
		jRadioButton1.setSelected(true);
		addActionListener();
		Thread t = new Thread() {
			public void run() {
				list = getAllFile();
				if (jRadioButton4.isSelected()) {
					filterByDate(true);
				}
				if (jRadioButton5.isSelected()) {
					filterByDate(false);
				}
				filterByDate();
				display(getPage());
				hideColumn();
				displayPageNumber();
			}
		};
		t.start();
		WaitPaneThread wait = new WaitPaneThread(null, t, "ϵͳ������ȡ�ʼ������Ժ�......");
		wait.start();
	}

	private void filterByDate(boolean isSuc) {
		ArrayList l = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			if (isSuc) {
				if (map.get("״̬").toString().indexOf("ʧ��") == -1) {
					l.add(map);
				}
			} else {
				if (map.get("״̬").toString().indexOf("ʧ��") != -1) {
					l.add(map);
				}
			}
		}
		list = l;
	}

	// private void filterByDate() {
	// ArrayList l = new ArrayList();
	// if (jRadioButton3.isSelected())
	// return;
	// String type = "day";
	// if (jRadioButton1.isSelected())
	// type = "day";
	// if (jRadioButton2.isSelected())
	// type = "week";
	//
	// for (int i = 0; i < list.size(); i++) {
	// HashMap map = (HashMap) list.get(i);
	// if ("day".equals(type)
	// && map.get("ʱ��").toString()
	// .startsWith(DateUtils.getDate1(type))) {
	// l.add(map);
	// }
	// if ("week".equals(type)
	// && map.get("ʱ��").toString()
	// .compareTo(DateUtils.getDate1(type)) <= 0) {
	// l.add(map);
	// }
	// }
	// list = l;
	// }
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
					&& map.get("ʱ��").toString()
							.startsWith(DateUtils.getDate1(type))) {
				l.add(map);
			}
			if ("week".equals(type)
					&& map.get("ʱ��").toString()
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

	// �г�������ĳ��Ŀ¼�е������ļ���������ʱ������
	public ArrayList getAllFile() {
		ArrayList list = null;
		try {
			vector = new SFTPTool().listFiles(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"), directory);
			list = parseFile(vector);
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList parseFile(Vector vector) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < vector.size(); i++) {
			String file = vector.get(i).toString();
			file = file.substring(file.lastIndexOf(" ") + 1);
			if (file.endsWith("]"))
				file = file.substring(0, file.length() - 1);
			if (!file.endsWith("ehr"))
				continue;
			File f = new File("temp");
			if (!f.exists()) {
				f.mkdir();
			}
			new SFTPTool().downloadFile(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"), directory,
					file, "temp" + File.separator + "temp.ehr");
			FileInputStream input = null;
			InputStreamReader reader = null;
			BufferedReader br = null;
			try {
				input = new FileInputStream("temp" + File.separator
						+ "temp.ehr");
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
					map.put("ʱ��", time);
					map.put("fileName", file);
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

	public void display(ArrayList list) {
		Object[][] obj = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			Object[] row = new Object[9];
			// ��һ��(ѡ����)
			row[0] = new Boolean(false);
			// �ʼ�����
			row[1] = map.get("��ʶ");
			// ������
			row[2] = map.get("���͵�ַ");

			row[3] = map.get("���յ�ַ");

			// ����ʱ��
			row[4] = map.get("ʱ��");
			// ������С
			row[5] = filter(map.get("������С") == null ? "" : map.get("������С")
					.toString());
			// �ļ�����
			row[6] = filter(map.get("fileName") == null ? "" : map.get(
					"fileName").toString());
			// ��������
			row[7] = filter(map.get("�ļ�") == null ? "" : map.get("�ļ�")
					.toString());
			row[8] = map.get("����");
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
		return str;
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
				new Detail(new JFrame(), true, content, directory, false)
						.setVisible(true);
			}
		});
		// ˢ���¼�
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					public void run() {
						list = getAllFile();
						if (jRadioButton4.isSelected()) {
							filterByDate(true);
						}
						if (jRadioButton5.isSelected()) {
							filterByDate(false);
						}
						display(getPage());
						filterByDate();
						hideColumn();
						displayPageNumber();
					}
				};
				t.start();
				WaitPaneThread wait = new WaitPaneThread(null, t,
						"ϵͳ������ȡ�ʼ������Ժ�......");
				wait.start();
			}
		});
		// ɾ���¼�
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����Ĭ�ϰ��ʼ����⵱�����ļ�����
				int count = jTable1.getRowCount();
				// ����Ҫɾ�����к�
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
					JOptionPane.showMessageDialog(DMailReceiveLog.this,
							"��ѡ��Ҫɾ�����ʼ�!");
					return;
				}
				for (String fileName : fileNames) {
					new SFTPTool().downloadFile(
							Configuration.config.getProperty("hostip"), 22,
							Configuration.config.getProperty("username"),
							Configuration.config.getProperty("password"),
							Configuration.config.getProperty("receivebox"),
							fileName, fileName);
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
								Configuration.config.getProperty("receivebox"),
								fileName);
					} catch (SftpException e1) {
						e1.printStackTrace();
					}
				}
				for (int i = 0; i < removeRow.size(); i++) {
					((DefaultTableModel) jTable1.getModel())
							.removeRow(removeRow.get(i));
				}
				hideColumn();
				JOptionPane.showMessageDialog(DMailReceiveLog.this, "�ѳɹ�ɾ��");
			}
		});
		// ��һҳ
		jButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (index <= 1) {
					JOptionPane.showMessageDialog(DMailReceiveLog.this,
							"��ǰ���ǵ�һҳ��");
					return;
				}
				index--;
				display(getPage());
				hideColumn();
			}
		});
		// ��һҳ
		jButton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.size() <= (index * pageCount)) {
					JOptionPane.showMessageDialog(DMailReceiveLog.this,
							"��ǰ�������һҳ��");
					return;
				}
				index++;
				display(getPage());
				hideColumn();
			}
		});
	}

	public void displayPageNumber() {
		jLabel3.setText("��ǰ�ǵڣ� "
				+ index
				+ " ҳ���� "
				+ ((list.size() % pageCount == 0) ? (list.size() / pageCount)
						: ((list.size() / pageCount) + 1)) + " ҳ");
	}

	private String title = "�����ʼ�";

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
		jLabel2 = new javax.swing.JLabel();
		jRadioButton4 = new javax.swing.JRadioButton();
		jRadioButton5 = new javax.swing.JRadioButton();
		ButtonGroup group1 = new ButtonGroup();
		group1.add(jRadioButton4);
		group1.add(jRadioButton5);
		jRadioButton4.setSelected(true);
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
		jButton1.setText("ˢ��");
		jButton2.setText("ɾ��");
		jButton2.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "icon_del.gif"));
		jButton3.setText("��һҳ");
		jButton3.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "First17.gif"));
		jButton4.setText("��һҳ");
		jButton4.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "First16.gif"));
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("��������"));

		jLabel1.setText("����ʱ�䣺");

		jRadioButton1.setText("  ����");

		jRadioButton2.setText("  ����");

		jRadioButton3.setText("  ����");

		jLabel2.setText("״̬��");

		jRadioButton4.setText("�ɹ�");

		jRadioButton5.setText("ʧ��");
		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(jLabel2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButton4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				44,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				70,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jRadioButton5))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jRadioButton3)
														.addComponent(
																jRadioButton2)
														.addComponent(
																jRadioButton1))
										.addGap(23, 23, 23)));
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
														.addComponent(jLabel2)
														.addComponent(
																jRadioButton1)
														.addComponent(jLabel1)
														.addComponent(
																jRadioButton4))
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioButton2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jRadioButton3))
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGap(10,
																				10,
																				10)
																		.addComponent(
																				jRadioButton5)))
										.addContainerGap(17, Short.MAX_VALUE)));

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
				new String[] { "ѡ��", "�ʼ�����", "������", "�ռ���", "����ʱ��", "������С",
						"FileName" }) {
			Class[] types = new Class[] { java.lang.Boolean.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class };
			boolean[] canEdit = new boolean[] { true, false, false, false,
					false, false, false, false, false };

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
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JRadioButton jRadioButton4;
	private javax.swing.JRadioButton jRadioButton5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
}
