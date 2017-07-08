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
		jTabbedPane1.add("ȫ������", new test(new DMailGlobalSetConfigPanel(),
				"ȫ������"));
		jTabbedPane1.add("���Ͷ�����", new test(new DMailSendSelfSetConfigPanel(),
				"���Ͷ�����"));
		jTabbedPane1.add("���ն�����", new test(new DMailRecvSelfSetConfigPanel(),
				"���ն�����"));
		jTabbedPane1.add("���ý��ն�����", new test(new DMailRecvPeerSetConfigPanel(),
				"���ý��ն�����"));
//		jTabbedPane1.add("�����û�IP����", new test(new DMailUserIPConfigPanel(),
//				"�����û�IP����"));
//		jTabbedPane1.add("�û���Ϣ", new test(new DMailUserConfigPanel(), "�û���Ϣ"));
//		jTabbedPane1.add("�û���·��", new test(new DMailUserPathConfigPanel(),
//				"�û���·��"));
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
