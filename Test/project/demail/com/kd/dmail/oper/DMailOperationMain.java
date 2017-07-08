package demail.com.kd.dmail.oper;

import java.awt.Component;

import java.awt.Font;
import java.awt.Image;
import java.util.Hashtable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.consts.DMailConstants;
import demail.com.kd.dmail.main.Login;
import demail.com.kd.dmail.tool.FrameSize;

/**
 * 
 * @author xuzhiqi
 */
public class DMailOperationMain extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	public DMailOperationMain() {
		initComponents();
		initTree();
		addListener();
		jSplitPane1.setRightComponent(new WelcomePanel());
		jTree1.setRowHeight(25);
		setSize(FrameSize.width - 125, FrameSize.height - 320);
	}

	public void addListener() {
		jTree1.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				Object obj = jTree1.getSelectionPath().getLastPathComponent();
				if (obj.toString().equals("发送邮件")) {
					jSplitPane1.setRightComponent(new DMailSend());
					jSplitPane1.setDividerLocation(200);
				}
				if (obj.toString().equals("D5000邮件系统")) {
					jSplitPane1.setRightComponent(new WelcomePanel());
					jSplitPane1.setDividerLocation(200);
				}
				if (obj.toString().equals("收 件 箱")) {
					String path = Login.dMailUserPath;
					if (!path.endsWith("/"))
						path = path + "/";
					jSplitPane1.setRightComponent(new DMailReceive("接收邮件",
							path, "eh"));
					jSplitPane1.setDividerLocation(200);
				}
				if (obj.toString().equals("已发送")) {
					String path = Login.dMailUserPath;
					if (!path.endsWith("/"))
						path = path + "/";
					path = path.substring(0, path.length() - 1);
					path = path.substring(0, path.lastIndexOf("/"));
					path = path + "/sent/";
					jSplitPane1.setRightComponent(new DMailSent("已发送邮件", path,
							"ehr"));
					jSplitPane1.setDividerLocation(200);
				}
				if (obj.toString().equals("已接收")) {
					String path = Login.dMailUserPath;
					path = path.substring(0, path.length() - 1) + "d/";
					jSplitPane1.setRightComponent(new DMailReceived("已接收邮件",
							path, "eh"));
					jSplitPane1.setDividerLocation(200);
				}
				if (obj.toString().equals("发送日志")) {
					String path = Login.dMailUserPath;
					if (!path.endsWith("/"))
						path = path + "/";
					path = path.substring(0, path.length() - 1);
					path = path.substring(0, path.lastIndexOf("/"));
					path = path + "/sent/";
					jSplitPane1.setRightComponent(new DMailReceiveLog("发送日志",
							path));
					jSplitPane1.setDividerLocation(200);
				}
				if (obj.toString().equals("中转日志")) {
					String path = Configuration.config.getProperty("transfer");
					if (!path.endsWith("/"))
						path = path + "/";
					jSplitPane1.setRightComponent(new DMailReceiveLog("中转日志",
							path));
					jSplitPane1.setDividerLocation(200);
				}
			}
		});
	}

	public void initTree() {
		IconNode root = new IconNode("D5000邮件系统");
		root.setIcon(new ImageIcon(DMailConstants.imageFilePath + "world2.gif"));
		DefaultTreeModel model = new DefaultTreeModel(root);
		IconNode userMailbox = new IconNode("用户邮箱");
		userMailbox.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "email.gif"));
		IconNode sendMailbox = new IconNode("发送邮件");
		sendMailbox.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "DataStore.gif"));
		IconNode receiveMailbox = new IconNode("收 件 箱");
		receiveMailbox.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "DataExtract.gif"));
		userMailbox.add(sendMailbox);
		userMailbox.add(receiveMailbox);
		root.add(userMailbox);

		IconNode mailBox = new IconNode("邮箱");
		mailBox.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "email.gif"));
		IconNode sendedMail = new IconNode("已发送");
		sendedMail.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "DataStore.gif"));
		IconNode receivedMail = new IconNode("已接收");
		receivedMail.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "DataExtract.gif"));
		mailBox.add(sendedMail);
//		mailBox.add(receivedMail);
		root.add(mailBox);
		IconNode sendLog = new IconNode("发送日志");
		sendLog.setIcon(new ImageIcon(DMailConstants.imageFilePath + "Box.gif"));
		IconNode receiveLog = new IconNode("中转日志");
		receiveLog.setIcon(new ImageIcon(DMailConstants.imageFilePath
				+ "Box.gif"));
		root.add(sendLog);
		root.add(receiveLog);
		jTree1.setModel(model);
		for (int i = 0; i < jTree1.getRowCount(); i++)
			jTree1.expandRow(i);
		jTree1.setCellRenderer(new IconNodeRenderer());
	}

	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jSplitPane1 = new javax.swing.JSplitPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTree1 = new javax.swing.JTree();
		ImageIcon icon = new javax.swing.ImageIcon(DMailConstants.imageFilePath
				+ "bg13.png");
		icon = new ImageIcon(icon.getImage().getScaledInstance(
				FrameSize.width - 10, 80, Image.SCALE_DEFAULT));
		jLabel1.setVerticalTextPosition(JLabel.CENTER);
		jLabel1.setHorizontalTextPosition(JLabel.CENTER);
		jLabel1.setIcon(icon);
		jLabel1.setFont(new Font("Serif", Font.BOLD, 23));
		jLabel1.setText("智能电网调度技术支持系统-消息邮件客户端");

		jSplitPane1.setDividerLocation(200);
		jSplitPane1.setDividerSize(1);

		jScrollPane1.setViewportView(jTree1);

		jSplitPane1.setLeftComponent(jScrollPane1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						903, Short.MAX_VALUE)
				.addComponent(jSplitPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 903,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										87,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSplitPane1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										580, Short.MAX_VALUE)));
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JTree jTree1;

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