package demail.com.kd.dmail.watch;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Timer;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import kd.idp.dmail.DMCommon;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import demail.com.kd.dmail.config.pojo.ConfigParse;
import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.tool.DateUtils;
import demail.com.kd.dmail.tool.FrameSize;
import demail.com.kd.dmail.tool.SFTPTool;

/**
 * 
 * @author xuzhiqi
 */
public class DMailWatchMainPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	Vector vector = null;
	public static HashMap<Integer, String> fq = new HashMap<Integer, String>();
	static {
		fq.put(1, "I");
		fq.put(2, "II");
		fq.put(3, "III");
		fq.put(4, "IV");
	}

	public void setBusy() {
		super.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	// 将鼠标状态置为空闲状态
	public void freeBusy() {
		super.setCursor(null);
	}

	public DMailWatchMainPanel() {
		produceTreeConfigFile();
		initComponents();
		jbInit();
		FitTableColumns(jTable1);
	}

	Timer timer_time = null;

	public void jbInit() {
		jTable1.getColumnModel()
				.getColumn(4)
				.setCellRenderer(
						new MyTableCellRenderer(new HashMap<String, String>()));
		jTable1.getColumnModel()
				.getColumn(5)
				.setCellRenderer(
						new MyTableCellRenderer(new HashMap<String, String>()));
		initTree();
		addListener();
		jLabel11.setText("当前时间");
		timer_time = new Timer();
		timer_time.schedule(new Task1(), 0, 1);
	}

	String time = "";

	class Task1 extends java.util.TimerTask {
		public void run() {
			jLabel11.setText("     当前时间： " + DateUtils.getDate_delay(0)
					+ "    下次刷新时间： " + time);
			DMailWatchMainPanel.this.repaint();
		}
	}

	public static void main(String args[]) {
		produceTreeConfigFile();
	}

	// 取出配置文件中的原始内容
	public static ArrayList<String> getOriginalContext_route() {
		String flag = "[AddressMap]";
		ArrayList<String> list = null;
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
			list = ConfigParse.parseFile_list(br, flag);
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
		return list;
	}

	// 根据配置文件动态生成本地用的树节点配置文件
	public static void produceTreeConfigFile() {
		ArrayList<String> list = getOriginalContext();
		OutputStream out = null;
		String local = getLocal();
		try {
			StringBuffer sb = new StringBuffer();
			String title = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n";
			// sb.append("<globalset name=\""+local+"\" ");
			String global = "";
			for (int i = 2; i < list.size(); i++) {
				String line = list.get(i).toString().trim();
				if (line.startsWith(local)) {
					global = line;
					continue;
				}
				String[] temp = line.split("=");
				sb.append("<second name=\""
						+ temp[0]
						+ "\" ip=\""
						+ temp[1]
						+ "\" fq=\""
						+ fq.get(Integer.valueOf(temp[0].substring(
								temp[0].length() - 1, temp[0].length())))
						+ "\" >\n");
				sb.append(getChild(
						"third",
						getOriginalContext_route(),
						temp[0].substring(0, temp[0].length() - 1),
						fq.get(Integer.valueOf(temp[0].substring(
								temp[0].length() - 1, temp[0].length())))));
				sb.append("</second>\n");
			}
			String temp[] = global.split("=");
			global = "<globalset name=\""
					+ temp[0]
					+ "\" ip=\""
					+ temp[1]
					+ "\" fq=\""
					+ fq.get(Integer.valueOf(temp[0].substring(
							temp[0].length() - 1, temp[0].length())))
					+ "\" >\n";
			String end = "</globalset>";
			String all = title + global + sb.toString() + end;
			out = new FileOutputStream(DMailConstants.confFilePath + "test.xml");
			out.write(all.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getChild(String layer, ArrayList<String> list,
			String area, String fq) {
		String code = null;
		for (String str : list) {
			if (str.endsWith(area)) {
				code = str.split("=")[0];
				break;
			}
		}
		if (code == null)
			return "";
		if (area.equals("国调"))
			return "";
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			if (str.startsWith(code)
					&& (str.split("=")[0].length() == code.length() + 2)) {
				sb.append("<" + layer + " name=\"" + str.split("=")[1]
						+ "\" ip=\"192.168.100.221\" fq=\"" + fq + "\">\n");
				sb.append(getChild(layer + 1, list, str.split("=")[1], fq));
				sb.append("</" + layer + ">");
			}
		}
		return sb.toString();
	}

	Timer timer = null;

	public void addListener() {
		jTree1.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				setBusy();
				vector = new Vector();
				vector.add(((IconNode) jTree1.getSelectionPath()
						.getLastPathComponent()).getUserObject());
				getNode((IconNode) jTree1.getSelectionPath()
						.getLastPathComponent());
				Object[][] obj = new Object[vector.size() * 2][];
				for (int i = 0; i < vector.size(); i++) {
					addrow(obj, i, i * 2, "主");
					addrow(obj, i, (i * 2) + 1, "备");
				}
				jTable1.setModel(new DefaultTableModel(obj, new String[] {
						"分区", "邮件名称", "类别", "IP", "TCP/IP服务", "UDP服务", "详情" }));
				if (timer != null) {
					timer.cancel();
				}
				timer = new Timer();
				timer.schedule(new Task(), 0, 30000);
				freeBusy();
			}
		});
	}

	class Task extends java.util.TimerTask {
		public void run() {
			refreshTableStatus(((UserObject) ((IconNode) jTree1
					.getSelectionPath().getLastPathComponent()).getUserObject())
					.getName());
		}
	}

	private void addrow(Object[][] obj, int x, int i, String stat) {
		Object[] object = new Object[7];
		UserObject user = (UserObject) vector.get(x);
		object[0] = (user.getFq());
		object[1] = user.getName();
		object[2] = stat;
		String temp = user.getIp();
		if (temp.indexOf(";") != -1)
			object[3] = user.getIp().split(";")[0];
		else
			object[3] = user.getIp();
		object[4] = null;
		object[5] = null;
		object[6] = "";
		obj[i] = object;
	}

	public void readStatus(HashMap<String, String> map1,
			HashMap<String, String> map2, String area) {
		DMCommon dmcommon = new DMCommon(
				Configuration.config.getProperty("hostip"),
				Configuration.config.getProperty("port"));
		// DMAIL服务端状态监视
		String status = dmcommon.getDMailServerStatus(area);
		status = status.substring(area.length() + 4);
		ArrayList<String> list = new ArrayList<String>();
		int count = status.length() / 4;
		for (int i = 0; i < count; i++) {
			list.add(status.substring(i * 4, (i + 1) * 4));
		}
		ArrayList<String> oriList = getOriginalContext();
		int index = getOrder(area, oriList);
		currectOrder(list, index);
		ss(map1, map2, list);
//		System.out.println("####  status  is:" + status);
	}

	public int getOrder(String area, ArrayList<String> list) {
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).toString().trim().startsWith(area)) {
				return i - 2;
			}
		}
		return index;
	}

	// 取出配置文件中的原始内容
	public static ArrayList<String> getOriginalContext() {
		String flag = "[DMailUserIP]";
		ArrayList<String> list = null;
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
			list = ConfigParse.parseFile_list(br, flag);
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
		return list;
	}

	// 取出配置文件中的原始内容
	public static String getLocal() {
		String flag = "LOCAL";
		String result = "";
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
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.toString().trim().toUpperCase().startsWith(flag)) {
					result = line.toString().trim().split("=")[1];
					break;
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
		return result;
	}

	// 调整顺序
	public ArrayList currectOrder(ArrayList list, int order) {
		ArrayList arrayList = new ArrayList();
		arrayList.add(list.get(order));
		for (int i = 0; i < order; i++)
			arrayList.add(list.get(i));
		for (int i = order + 1; i < list.size(); i++) {
			arrayList.add(list.get(i));
		}
		return arrayList;
	}

	// 0,green,正常.....1,red,异常
	public static void ss(HashMap map1, HashMap map2, ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			String ll = list.get(i).toString();
			String first = ll.substring(0, 2);
			String second = ll.substring(2, ll.length());
			// 主，UDP
			if (Integer.valueOf(first.substring(0, 1)) == 1)
				map1.put(i * 2, "green.png");
			else
				map1.put((i * 2), "red.png");
			// 主，TCP
			if (Integer.valueOf(first.substring(1, 2)) == 1)
				map2.put(i * 2, "green.png");
			else
				map2.put(i * 2, "red.png");
			// 备，UDP
			if (Integer.valueOf(second.substring(0, 1)) == 1)
				map1.put((i * 2) + 1, "green.png");
			else
				map1.put((i * 2) + 1, "red.png");
			// 备，TCP
			if (Integer.valueOf(second.substring(1, 2)) == 1)
				map2.put((i * 2) + 1, "green.png");
			else
				map2.put((i * 2) + 1, "red.png");
		}
	}

	public void getNode(IconNode node) {
		int count = ((DefaultTreeModel) jTree1.getModel()).getChildCount(node);
		for (int i = 0; i < count; i++) {
			vector.add((((IconNode) ((DefaultTreeModel) jTree1.getModel())
					.getChild(node, i))).getUserObject());
		}
		for (int i = 0; i < count; i++) {
			getNode((IconNode) ((DefaultTreeModel) jTree1.getModel()).getChild(
					node, i));
		}
	}

	public void initTree() {
		DefaultTreeModel model = new DefaultTreeModel(parse(new File(
				DMailConstants.confFilePath + "test.xml")));
		jTree1.setModel(model);
		for (int i = 0; i < jTree1.getRowCount(); i++)
			jTree1.expandRow(i);
		jTree1.setCellRenderer(new IconNodeRenderer());
		jTree1.setRowHeight(25);
	}

	public IconNode parse(File file) {
		IconNode node = null;
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);
			if (doc != null) {
				node = parserGDomTree(doc.getDocumentElement());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return node;
	}

	private IconNode parserGDomTree(Node node) throws Exception {
		IconNode treeNode = null;
		int type = node.getNodeType();
		switch (type) {
		case Node.DOCUMENT_NODE: {
			parserGDomTree(((Document) node).getDocumentElement());
			break;
		}
		case Node.ELEMENT_NODE: {
			UserObject user = new UserObject();
			NamedNodeMap attrs = node.getAttributes();
			treeNode = new IconNode(user);
			treeNode.setIcon(new ImageIcon(DMailConstants.imageFilePath
					+ "instance.gif"));
			if (attrs.getLength() > 0) {
				user.setName(attrs.getNamedItem("name").getNodeValue());
				user.setIp(attrs.getNamedItem("ip").getNodeValue());
				user.setFq(attrs.getNamedItem("fq").getNodeValue());
			}
			NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				for (int i = 0; i < len; i++) {
					DefaultMutableTreeNode nn = null;
					if ((nn = parserGDomTree(children.item(i))) != null)
						treeNode.add(nn);
				}
			}
			break;
		}
		case Node.ATTRIBUTE_NODE: {
			break;
		}
		case Node.ENTITY_REFERENCE_NODE: {
			break;
		}
		case Node.CDATA_SECTION_NODE: {
			break;
		}
		case Node.TEXT_NODE: {
			break;
		}
		case Node.PROCESSING_INSTRUCTION_NODE: {
			break;
		}
		case Node.COMMENT_NODE: {
		}
		}
		return treeNode;
	}

	public void FitTableColumns(JTable myTable) { // 設置table的列寬隨內容調整
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(
					column.getIdentifier());
			int width = (int) myTable
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(myTable,
							column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable
						.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable,
								myTable.getValueAt(row, col), false, false,
								row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width + 40);
		}
	}

	private void initComponents() {
		jSplitPane1 = new javax.swing.JSplitPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTree1 = new javax.swing.JTree();
		jSplitPane2 = new javax.swing.JSplitPane();
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jPanel5 = new JPanel();
		jSplitPane1.setDividerLocation(200);
		jSplitPane1.setDividerSize(1);
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		ImageIcon icon = new javax.swing.ImageIcon(DMailConstants.imageFilePath
				+ "bg13.png");
		icon = new ImageIcon(icon.getImage().getScaledInstance(
				FrameSize.width - 10, 110, Image.SCALE_DEFAULT));
		jLabel5.setVerticalTextPosition(JLabel.CENTER);
		jLabel5.setHorizontalTextPosition(JLabel.CENTER);
		jLabel5.setIcon(icon);
		jLabel5.setFont(new Font("Serif", Font.BOLD, 23));
		jLabel5.setText("智能电网调度技术支持系统--监视服务");
		jScrollPane1.setViewportView(jTree1);

		jSplitPane1.setLeftComponent(jScrollPane1);

		jSplitPane2.setDividerLocation(FrameSize.width - 410);
		jSplitPane2.setDividerSize(1);

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("图例"));
		ImageIcon imageicon = new ImageIcon(DMailConstants.imageFilePath
				+ "red.png");
		imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(20,
				20, Image.SCALE_DEFAULT));
		jLabel1.setIcon(imageicon);
		imageicon = new ImageIcon(DMailConstants.imageFilePath + "green.png");
		imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(20,
				20, Image.SCALE_DEFAULT));
		jLabel2.setIcon(imageicon);
		imageicon = new ImageIcon(DMailConstants.imageFilePath + "yellow.png");
		imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(20,
				20, Image.SCALE_DEFAULT));
		jLabel3.setIcon(imageicon);
		imageicon = new ImageIcon(DMailConstants.imageFilePath + "red.png");
		imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(20,
				20, Image.SCALE_DEFAULT));
		jLabel4.setIcon(imageicon);

		jLabel7.setText("异常");

		jLabel8.setText("正常");

		jLabel9.setText("中转");

		jLabel10.setText("其他状态");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel10,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				67,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel3,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel9,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				67,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel8,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				67,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel7,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				67,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(43, 43, 43)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGap(24, 24, 24)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(jLabel7))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(jLabel8))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(jLabel9))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(jLabel10))
										.addContainerGap(42, Short.MAX_VALUE)));
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap(32, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																jLabel6,
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
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel2,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jLabel6)
						.addContainerGap(286, Short.MAX_VALUE)));
		jSplitPane2.setRightComponent(jPanel1);

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "分区", "邮件名称", "类别", "IP",
						"UDP服务", "TCP/IP服务", "详情" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setToolTipText("运行监视 ");
		jTable1.setEnabled(false);
		jTable1.setFocusable(false);
		jTable1.setRowHeight(25);
		jTable1.setShowHorizontalLines(false);
		jTable1.setShowVerticalLines(false);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jScrollPane2.setViewportView(jTable1);
		jTable1.getColumnModel().getColumn(0).setResizable(false);
		jTable1.getColumnModel().getColumn(1).setResizable(false);
		jTable1.getColumnModel().getColumn(2).setResizable(false);
		jTable1.getColumnModel().getColumn(3).setResizable(false);
		jTable1.getColumnModel().getColumn(4).setResizable(false);
		jTable1.getColumnModel().getColumn(5).setResizable(false);
		jTable1.getColumnModel().getColumn(6).setResizable(false);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane2,
								javax.swing.GroupLayout.DEFAULT_SIZE, 284,
								Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane2,
								javax.swing.GroupLayout.DEFAULT_SIZE, 498,
								Short.MAX_VALUE).addContainerGap()));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel3Layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jPanel4,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap())
				.addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE,
						501, Short.MAX_VALUE));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(jLabel11)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addContainerGap()));
		jSplitPane2.setLeftComponent(jPanel3);
		jSplitPane1.setRightComponent(jSplitPane2);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jSplitPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 848,
						Short.MAX_VALUE)
				.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
						848, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel5,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										87,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSplitPane1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										542, Short.MAX_VALUE)));
	}

	private void refreshTableStatus(String area) {
		try {
			HashMap<String, String> map1 = new HashMap<String, String>();
			HashMap<String, String> map2 = new HashMap<String, String>();
			readStatus(map1, map2, area);
			jLabel6.setText("当前与服务器连接状态：正常");
			jTable1.getColumnModel().getColumn(4)
					.setCellRenderer(new MyTableCellRenderer(map1));
			jTable1.getColumnModel().getColumn(5)
					.setCellRenderer(new MyTableCellRenderer(map2));
			time = DateUtils.getDate_delay(30000);
			jTable1.repaint();
		} catch (Exception e) {
			jLabel6.setText("当前与服务器连接状态：异常");
			e.printStackTrace();
		}
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JSplitPane jSplitPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTree jTree1;
	// End of variables declaration

}

class IconNodeRenderer extends DefaultTreeCellRenderer {

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		Icon icon = ((IconNode) value).getIcon();
		if (icon == null) {
			Hashtable icons = (Hashtable) tree.getClientProperty("title4.gif");
			String name = ((IconNode) value).getIconName();
			if ((icons != null) && (name != null)) {
				icon = (Icon) icons.get(name);
				if (icon != null) {
					setIcon(icon);
				}
			}
		} else {
			setIcon(icon);
		}
		return this;
	}
}

class IconNode extends DefaultMutableTreeNode {
	protected Icon icon;
	protected String iconName;

	public IconNode() {
		this(null);
	}

	public IconNode(Object userObject) {
		this(userObject, true, null);
	}

	public IconNode(Object userObject, boolean allowsChildren, Icon icon) {
		super(userObject, allowsChildren);
		this.icon = icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		return icon;
	}

	public String getIconName() {
		if (iconName != null) {
			return iconName;
		} else {
			String str = userObject.toString();
			int index = str.lastIndexOf(".");
			if (index != -1) {
				return str.substring(++index);
			} else {
				return null;
			}
		}
	}

	public void setIconName(String name) {
		iconName = name;
	}
}