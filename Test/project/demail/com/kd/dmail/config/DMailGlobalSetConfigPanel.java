package demail.com.kd.dmail.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import demail.com.kd.dmail.config.pojo.ConfigParse;
import demail.com.kd.dmail.config.self.Configuration;
import demail.com.kd.dmail.tool.FrameSize;
import demail.com.kd.dmail.tool.SFTPTool;

/**
 * 
 * @author xuzhiqi
 */
public class DMailGlobalSetConfigPanel extends PaneImpl {

	private static final long serialVersionUID = 1L;

	public DMailGlobalSetConfigPanel() {
		initComponents();
		setSize(200, FrameSize.height);
		jbInit();
	}

	String flag = "[DMailGlobalSet]";

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
			jTextField1.setText(map.get("local"));
			jTextField2.setText(map.get("garbagePath"));
			jTextField3.setText(map.get("replyEhrPath"));
			jTextField4.setText(map.get("peerEhrPath"));
			jTextField5.setText(map.get("sentPath"));
			jTextField6.setText(map.get("backupPath"));
			jTextField7.setText(map.get("logPath"));
			jTextField8.setText(map.get("transferPath"));
			jTextField9.setText(map.get("AgentPort"));
			jTextField10.setText(map.get("AgentPath"));
			jTextField11.setText(map.get("AgentBuffSize"));
			jTextField12.setText(map.get("affixsize"));
			jTextField13.setText(map.get("srvbusrequestPath"));
			jTextField14.setText(map.get("proxyIP"));
			jTextField15.setText(map.get("proxyPort"));
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
			sb.append("local=" + jTextField1.getText().toString().trim() + "\n");
			sb.append("garbagePath=" + jTextField2.getText().toString().trim()
					+ "\n");
			sb.append("replyEhrPath=" + jTextField3.getText().toString().trim()
					+ "\n");
			sb.append("peerEhrPath=" + jTextField4.getText().toString().trim()
					+ "\n");
			sb.append("sentPath=" + jTextField5.getText().toString().trim()
					+ "\n");
			sb.append("backupPath=" + jTextField6.getText().toString().trim()
					+ "\n");
			sb.append("logPath=" + jTextField7.getText().toString().trim()
					+ "\n");
			sb.append("transferPath=" + jTextField8.getText().toString().trim()
					+ "\n");
			sb.append("AgentPort=" + jTextField9.getText().toString().trim()
					+ "\n");
			sb.append("AgentPath=" + jTextField10.getText().toString().trim()
					+ "\n");
			sb.append("AgentBuffSize="
					+ jTextField11.getText().toString().trim() + "\n");
			sb.append("affixsize=" + jTextField12.getText().toString().trim()
					+ "\n");
			sb.append("srvbusrequestPath="
					+ jTextField13.getText().toString().trim() + "\n");
			sb.append("proxyIP=" + jTextField14.getText().toString().trim()
					+ "\n");
			sb.append("proxyPort=" + jTextField15.getText().toString().trim()
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
					if (!line.trim().startsWith(flag) && !next) {
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
			new SFTPTool().uploadFile(Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"),
					Configuration.config.getProperty("hostpath"), "dmail.cfg");
			JOptionPane.showMessageDialog(DMailGlobalSetConfigPanel.this,
					"文件已保存并上传至服务器");
		}
	}

	private boolean check() {

		if ("".equals(jTextField1.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "本地邮局  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField2.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "回收文件路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField3.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "反向EHR路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField4.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "对端EHR路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField5.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "已发送路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField6.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "备份路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField7.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "日志路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField8.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "转发路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField9.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "客户端代理路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField10.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "代理路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField11.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "代理报文长度  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField12.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "附件大小  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField13.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "服务请求路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField14.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "代理IP  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField15.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "代理端口  字段不允许为空！！");
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
		jTextField5.setText("");
		jTextField6.setText("");
		jTextField7.setText("");
		jTextField8.setText("");
		jTextField9.setText("");
		jTextField10.setText("");
		jTextField11.setText("");
		jTextField12.setText("");
		jTextField13.setText("");
		jTextField14.setText("");
		jTextField15.setText("");
	}

	private void initComponents() {
		jPanel1 = new JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();
		jTextField7 = new javax.swing.JTextField();
		jTextField8 = new javax.swing.JTextField();
		jTextField9 = new javax.swing.JTextField();
		jTextField10 = new javax.swing.JTextField();
		jTextField11 = new javax.swing.JTextField();
		jTextField12 = new javax.swing.JTextField();
		jTextField13 = new javax.swing.JTextField();
		jTextField14 = new javax.swing.JTextField();
		jTextField15 = new javax.swing.JTextField();
		jScrollPane1 = new JScrollPane();
		setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		setPreferredSize(new java.awt.Dimension(600, 400));
		jLabel1.setText("本地邮局：");
		jLabel2.setText("回收文件路径：");
		jLabel3.setText("反向EHR路径：");
		jLabel4.setText("对端EHR路径：");
		jLabel5.setText("已发送路径：");
		jLabel6.setText("备份路径：");
		jLabel7.setText("日志路径：");
		jLabel8.setText("转发路径：");
		jLabel9.setText("客户端代理路径：");
		jLabel10.setText("代理路径：");
		jLabel11.setText("代理报文长度：");
		jLabel12.setText("附件大小：");
		jLabel13.setText("服务请求路径：");
		jLabel14.setText("代理IP：");
		jLabel15.setText("代理端口：");
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
																javax.swing.GroupLayout.Alignment.CENTER)
														.addComponent(
																jLabel15,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel14,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel13,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel12,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel11,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel10,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel9,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel8,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel7,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel4,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel3,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																208,
																Short.MAX_VALUE))
										.addGap(4, 4, 4)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																jTextField15)
														.addComponent(
																jTextField14)
														.addComponent(
																jTextField13)
														.addComponent(
																jTextField12)
														.addComponent(
																jTextField11)
														.addComponent(
																jTextField10)
														.addComponent(
																jTextField9)
														.addComponent(
																jTextField8)
														.addComponent(
																jTextField7)
														.addComponent(
																jTextField6)
														.addComponent(
																jTextField5)
														.addComponent(
																jTextField4)
														.addComponent(
																jTextField3)
														.addComponent(
																jTextField2)
														.addComponent(
																jTextField1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																395,
																Short.MAX_VALUE))
										.addGap(275, 275, 275)));
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
														.addComponent(jLabel2)
														.addComponent(
																jTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																jTextField4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																jTextField5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																jTextField6,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																jTextField7,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																jTextField8,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel9)
														.addComponent(
																jTextField9,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel10)
														.addComponent(
																jTextField10,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel11)
														.addComponent(
																jTextField11,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel12)
														.addComponent(
																jTextField12,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel13)
														.addComponent(
																jTextField13,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel14)
														.addComponent(
																jTextField14,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel15)
														.addComponent(
																jTextField15,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(91, 91, 91)));

		jScrollPane1.setViewportView(jPanel1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 894,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592,
				javax.swing.GroupLayout.PREFERRED_SIZE));
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField10;
	private javax.swing.JTextField jTextField11;
	private javax.swing.JTextField jTextField12;
	private javax.swing.JTextField jTextField13;
	private javax.swing.JTextField jTextField14;
	private javax.swing.JTextField jTextField15;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	private javax.swing.JTextField jTextField7;
	private javax.swing.JTextField jTextField8;
	private javax.swing.JTextField jTextField9;
}