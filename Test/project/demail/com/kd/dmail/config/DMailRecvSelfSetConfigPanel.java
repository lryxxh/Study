package demail.com.kd.dmail.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.swing.JOptionPane;

import demail.com.kd.dmail.config.pojo.ConfigParse;
import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.tool.SFTPTool;

/**
 * 
 * @author xuzhiqi
 */
public class DMailRecvSelfSetConfigPanel extends PaneImpl {

	private static final long serialVersionUID = 1L;

	public DMailRecvSelfSetConfigPanel() {
		initComponents();
		setPreferredSize(new java.awt.Dimension(500, 400));
		jbInit();
	}

	String flag = "[DMailRecvSelfSet]";

	public void jbInit() {
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
			jTextField1.setText(map.get("Ip"));
			jTextField2.setText(map.get("UDPPort"));
			jTextField3.setText(map.get("TCPPort"));
			jTextField4.setText(map.get("RcvBufSize"));
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

	// 保存并上传配置文件方法
	public void saveAndUpload() throws Exception {
		if (check()) {
			StringBuffer sb = new StringBuffer();
			sb.append(flag + "\n");
			sb.append("Ip=" + jTextField1.getText().toString().trim() + "\n");
			sb.append("UDPPort=" + jTextField2.getText().toString().trim()
					+ "\n");
			sb.append("TCPPort=" + jTextField3.getText().toString().trim()
					+ "\n");
			sb.append("RcvBufSize=" + jTextField4.getText().toString().trim()
					+ "\n");
			InputStream input = null;
			InputStreamReader reader = null;
			BufferedReader br = null;
			String result = "";
			try {
				input = new FileInputStream("temp.cfg");
				reader = new InputStreamReader(input);
				br = new BufferedReader(reader);
				String line = null;
				StringBuffer up = new StringBuffer();
				StringBuffer down = new StringBuffer();
				boolean isContinue = false;
				boolean next = false;
				while ((line = br.readLine()) != null) {
					if (!line.startsWith(flag) && !next) {
						up.append(line + "\n");
					} else {
						next = true;
					}
					if (next && line.startsWith("[")
							&& !line.trim().startsWith(flag)) {
						isContinue = true;
					}
					if (isContinue) {
						down.append(line + "\n");
					}
				}
				result = up.toString() + sb.toString() + down.toString();
				System.err.println(result);
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
			FileOutputStream out = new FileOutputStream("dmail.cfg");
			out.write(result.getBytes());
			new SFTPTool().uploadFile(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"),
					Configuration.config.getProperty("hostpath"), "dmail.cfg");
			JOptionPane.showMessageDialog(DMailRecvSelfSetConfigPanel.this,
					"文件已保存并上传至服务器");
		}
	}

	private boolean check() {

		if ("".equals(jTextField1.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "接收端IP  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField2.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "UDP接收端口  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField3.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "TCP/IP接收端口  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField4.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "报文长度  字段不允许为空！！");
			return false;
		}
		return true;

	}

	// 取消方法
	public void cancel() {
		JOptionPane.showMessageDialog(null, "暂未实现");
	}

	// 重置方法
	public void reset() {
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField4.setText("");
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		jLabel1.setText("接收端IP：");

		jLabel2.setText("UDP接收端口：");

		jLabel3.setText("TCP/IP接收端口：");

		jLabel4.setText("报文长度：");

		jButton1.setText("测试");

		jButton2.setText("测试");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												false)
												.addComponent(
														jLabel4,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jLabel3,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														208,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(jTextField4)
												.addComponent(jTextField3)
												.addComponent(jTextField2)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														395,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jButton1)
												.addComponent(jButton2))
								.addContainerGap(246, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														jTextField2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton1))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(
														jTextField3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton2))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(
														jTextField4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(495, Short.MAX_VALUE)));
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
}
