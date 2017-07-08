/**
 * 
 */
package meeting.com.lry.meeting.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.plaf.LookAndFeelAddons;
import com.l2fprod.common.swing.plaf.windows.WindowsLookAndFeelAddons;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import meeting.com.lry.meeting.action.AddPartnerAction;
import meeting.com.lry.meeting.action.DeletePartnerAction;
import meeting.com.lry.meeting.action.EditPartnerAction;
import meeting.com.lry.meeting.action.ExportDataAction;
import meeting.com.lry.meeting.action.ImportDataAction;
import meeting.com.lry.meeting.action.SignatureAction;

/**
 * @author liurenyong
 * 
 */
public class MeetingFrame extends JFrame {

	/** 序列id */
	private static final long serialVersionUID = -4657007595867339748L;
	/** 标题面板 */
	private JPanel titlePanel;
	/** 状态面板 */
	private JPanel statusPanel;
	/** 左侧滚动面板 */
	private JScrollPane scrollPane;
	/** 左侧任务组件 */
	private JTaskPane taskPane;
	/** 标题栏Label */
	private JLabel iconLabel;
	/** 中间面板 */
	private JPanel centerPanel;

	private Border defaultBorder = new EtchedBorder(EtchedBorder.LOWERED, null,
			null);
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(new NimbusLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
					MeetingFrame frame = new MeetingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MeetingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getTitlePanel(), BorderLayout.NORTH);
		getContentPane().add(getStatusPanel(), BorderLayout.SOUTH);
		getContentPane().add(getCenterPanel(), BorderLayout.CENTER);
		getContentPane().add(getTaskScrollPane(), BorderLayout.WEST);
	}

	/**
	 * @return
	 */
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
		}
		return centerPanel;
	}

	/**
	 * 标题面板.
	 * 
	 * @return
	 */
	private JPanel getTitlePanel() {
		if (titlePanel == null) {

			titlePanel = new JPanel();
			titlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
					null));
			titlePanel.setBackground(new Color(112, 179, 192));
			titlePanel.add(Box.createVerticalStrut(40));
			titlePanel.setLayout(new BorderLayout());
			titlePanel.add(getIconLabel());
		}
		return titlePanel;
	}

	private JLabel getIconLabel() {
		if (iconLabel == null) {
			iconLabel = new JLabel();
		}
		return iconLabel;
	}

	/**
	 * 状态面板
	 * 
	 * @return
	 */
	private JPanel getStatusPanel() {
		if (statusPanel == null) {
			FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
			layout.setHgap(5);
			layout.setVgap(1);
			statusPanel = new JPanel();
			statusPanel.setBorder(defaultBorder);
			statusPanel.setLayout(layout);
			statusPanel.add(getProgressBar());
		}
		return statusPanel;
	}

	private JScrollPane getTaskScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(defaultBorder);
			scrollPane.setViewportView(getTaskPane());
		}
		return scrollPane;
	}

	/**
	 * 得到任务操作面板.
	 * 
	 * @return
	 */
	private JTaskPane getTaskPane() {
		if (taskPane == null) {
			taskPane = new JTaskPane();
			taskPane.setBackground(new Color(123, 162, 231));
			initTask();
		}
		return taskPane;
	}

	/**
	 * 初始化操作任务.
	 */
	private void initTask() {
		UIManager.put("win.xpstyle.name", "luna");
		try {
			LookAndFeelAddons.setAddon(WindowsLookAndFeelAddons.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		createTaskPaneGroups();
	}

	/**
	 * 创建任务分组.
	 */
	private void createTaskPaneGroups() {
		createMemberGroup();
		createSignatureGroup();
		createExportGroup();
	}

	/**
	 * 创建人员管理组.
	 */
	private void createMemberGroup() {
		JTaskPaneGroup inputGroup = createTaskPaneGroup("人员管理", true);
		inputGroup.setSpecial(true);
		inputGroup.setIcon(new ImageIcon("icons/group.png"));
		inputGroup.add(new AddPartnerAction());
		inputGroup.add(new EditPartnerAction());
		inputGroup.add(new DeletePartnerAction());
		inputGroup.add(new ImportDataAction());
		taskPane.add(inputGroup);
	}

	/**
	 * 创建个人签到组.
	 */
	private void createSignatureGroup() {
		JTaskPaneGroup signatureGroup = createTaskPaneGroup("个人签到", true);
		signatureGroup.add(new SignatureAction());
		taskPane.add(signatureGroup);
	}

	/**
	 * 创建信息输出组.
	 */
	private void createExportGroup() {
		JTaskPaneGroup exportGroup = createTaskPaneGroup("信息输出", true);
		exportGroup.add(new ExportDataAction());
		taskPane.add(exportGroup);
	}

	/**
	 * 根据名字创建一个任务组.
	 * 
	 * @return
	 */
	private JTaskPaneGroup createTaskPaneGroup(String groupName,
			boolean scrollOnExpand) {
		JTaskPaneGroup group = new JTaskPaneGroup();
		group.setTitle(groupName);
		group.setScrollOnExpand(scrollOnExpand);
		return group;
	}

	/**
	 * 进度条.
	 * 
	 * @return
	 */
	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
		}
		return progressBar;
	}
}
