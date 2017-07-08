package demail.com.kd.dmail.config;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import demail.com.kd.test.test;

/**
 * 
 * @author xuzhiqi
 */
public class ConfigMain extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	public ConfigMain() {
		initComponents();
		JPanel pane = new JPanel();
		pane.add(new JButton("Hello"));
		jTabbedPane1.add("全局设置", new test(new DMailGlobalSetConfigPanel(),
				"全局设置"));
		jTabbedPane1.add("发送端配置", new test(new DMailSendSelfSetConfigPanel(),
				"发送端配置"));
		jTabbedPane1.add("接收端配置", new test(new DMailRecvSelfSetConfigPanel(),
				"接收端配置"));
		jTabbedPane1.add("备用接收端配置", new test(new DMailRecvPeerSetConfigPanel(),
				"备用接收端配置"));
//		jTabbedPane1.add("接收用户IP配置", new test(new DMailUserIPConfigPanel(),
//				"接收用户IP配置"));
//		jTabbedPane1.add("用户信息", new test(new DMailUserConfigPanel(), "用户信息"));
//		jTabbedPane1.add("用户及路径", new test(new DMailUserPathConfigPanel(),
//				"用户及路径"));
	}

	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 907,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 669,
				Short.MAX_VALUE));
	}

	private javax.swing.JTabbedPane jTabbedPane1;

}
