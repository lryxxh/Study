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
public class DMailSendSelfSetConfigPanel extends PaneImpl {

	private static final long serialVersionUID = 1L;

	public DMailSendSelfSetConfigPanel() {
		initComponents();
		setPreferredSize(new java.awt.Dimension(500, 400));
		jbInit();
	}

	String flag = "[DMailSendSelfSet]";

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
			jTextField1.setText(map.get("SndBufSize"));
			jTextField2.setText(map.get("WaitTimesMax"));
			jTextField3.setText(map.get("WaitTimesMax"));
			jTextField4.setText(map.get("EhAndDataPath"));
			jTextField5.setText(map.get("UDPEnabale"));
			jTextField6.setText(map.get("DIRLoopTime"));
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
			sb.append("SndBufSize=" + jTextField1.getText().toString().trim()
					+ "\n");
			sb.append("WaitTimesMax=" + jTextField2.getText().toString().trim()
					+ "\n");
			sb.append("WaitTimesMax=" + jTextField3.getText().toString().trim()
					+ "\n");
			sb.append("EhAndDataPath="
					+ jTextField4.getText().toString().trim() + "\n");
			sb.append("UDPEnabale=" + jTextField5.getText().toString().trim()
					+ "\n");
			sb.append("DIRLoopTime=" + jTextField6.getText().toString().trim()
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
			new SFTPTool().uploadFile(
					Configuration.config.getProperty("hostip"), 22,
					Configuration.config.getProperty("username"),
					Configuration.config.getProperty("password"),
					Configuration.config.getProperty("hostpath"), "dmail.cfg");
			JOptionPane.showMessageDialog(DMailSendSelfSetConfigPanel.this,
					"文件已保存并上传至服务器");
		}
	}

	private boolean check() {

		if ("".equals(jTextField1.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "报文长度  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField2.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "确认等待超时时间  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField3.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "最大等待时间  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField4.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "文件路径  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField5.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "UDP设置  字段不允许为空！！");
			return false;
		}
		if ("".equals(jTextField6.getText().toString().trim())) {
			JOptionPane.showMessageDialog(null, "路径扫描时间  字段不允许为空！！");
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
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();

		setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		setPreferredSize(new java.awt.Dimension(900, 650));

		jLabel1.setText("报文长度：");

		jLabel2.setText("确认等待超时时间：");

		jLabel3.setText("最大等待时间：");

		jLabel4.setText("文件路径：");

		jLabel5.setText("UDP设置：");

		jLabel6.setText("路径扫描时间：");

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
														jLabel6,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jLabel5,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
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
												.addComponent(jTextField6)
												.addComponent(jTextField5)
												.addComponent(jTextField4)
												.addComponent(jTextField3)
												.addComponent(jTextField2)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														395,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(250, Short.MAX_VALUE)));
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
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(
														jTextField3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
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
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel5)
												.addComponent(
														jTextField5,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(
														jTextField6,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(395, Short.MAX_VALUE)));
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
}
