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

	/** ����id */
	private static final long serialVersionUID = -4657007595867339748L;
	/** ������� */
	private JPanel titlePanel;
	/** ״̬��� */
	private JPanel statusPanel;
	/** ��������� */
	private JScrollPane scrollPane;
	/** ���������� */
	private JTaskPane taskPane;
	/** ������Label */
	private JLabel iconLabel;
	/** �м���� */
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
	 * �������.
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
	 * ״̬���
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
	 * �õ�����������.
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
	 * ��ʼ����������.
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
	 * �����������.
	 */
	private void createTaskPaneGroups() {
		createMemberGroup();
		createSignatureGroup();
		createExportGroup();
	}

	/**
	 * ������Ա������.
	 */
	private void createMemberGroup() {
		JTaskPaneGroup inputGroup = createTaskPaneGroup("��Ա����", true);
		inputGroup.setSpecial(true);
		inputGroup.setIcon(new ImageIcon("icons/group.png"));
		inputGroup.add(new AddPartnerAction());
		inputGroup.add(new EditPartnerAction());
		inputGroup.add(new DeletePartnerAction());
		inputGroup.add(new ImportDataAction());
		taskPane.add(inputGroup);
	}

	/**
	 * ��������ǩ����.
	 */
	private void createSignatureGroup() {
		JTaskPaneGroup signatureGroup = createTaskPaneGroup("����ǩ��", true);
		signatureGroup.add(new SignatureAction());
		taskPane.add(signatureGroup);
	}

	/**
	 * ������Ϣ�����.
	 */
	private void createExportGroup() {
		JTaskPaneGroup exportGroup = createTaskPaneGroup("��Ϣ���", true);
		exportGroup.add(new ExportDataAction());
		taskPane.add(exportGroup);
	}

	/**
	 * �������ִ���һ��������.
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
	 * ������.
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
