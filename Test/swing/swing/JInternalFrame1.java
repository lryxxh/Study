package swing;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class JInternalFrame1 extends JFrame implements ActionListener {

	JDesktopPane desktopPane;
	int count = 1;

	public JInternalFrame1() {
		super("JInternalFrame1");
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		JButton b = new JButton("Create New Internal Frames");
		b.addActionListener(this);// ���û����°�ťʱ��������actionPerformed()�еĳ���
		contentPane.add(b, BorderLayout.SOUTH);
		/*
		 * ����һ���µ�JDesktopPane��������contentPane��
		 */
		desktopPane = new JDesktopPane();
		System.out.println(desktopPane.getMouseMotionListeners().length);
		contentPane.add(desktopPane);

		setSize(350, 350);
		show();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/*
	 * ����һ���ɹرա��ɸı��С�����б��⡢���������С����Internal Frame.
	 */
	public void actionPerformed(ActionEvent e) {
		JInternalFrame internalFrame = new JInternalFrame("Internal Frame "
				+ (count++), true, true, true, true);
		internalFrame.setResizable(false);
		PropertyChangeListener listeners[] = internalFrame.getPropertyChangeListeners();
		for (PropertyChangeListener listener : listeners) {
			internalFrame.removePropertyChangeListener(listener);
		}
		System.out.println("length == "+listeners.length);
		internalFrame.setLocation(20, 20);
		internalFrame.setSize(200, 200);
		internalFrame.setVisible(true);
		// ȡ��JInternalFrame��Content Pane,���Լ����µ������
		Container icontentPane = internalFrame.getContentPane();
		JTextArea textArea = new JTextArea();
		JButton b = new JButton("Internal Frame Button");
		/*
		 * ��JTextArea��JButton�������JInternalFrame�С��ɴ˴�֪��JInteranlFrame�������
		 * �ķ�ʽ��JFrame��һģһ����
		 */
		icontentPane.add(textArea, "Center");
		icontentPane.add(b, "South");
		// ��JInternalFrame����JDesktopPane�У����һ������ʹ�����ܶ�JInternalFrame,JDesktopPaneҲ
		// �ܽ�����֮��Ĺ�ϵ������൱���á�
		desktopPane.add(internalFrame);
		MouseMotionListener listeners22[] = internalFrame.getMouseMotionListeners();
		for (MouseMotionListener listener : listeners22) {
			internalFrame.removeMouseMotionListener(listener);
			System.err.println("...........");
		}
//		System.out.println(internalFrame.getMouseMotionListeners().length);
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException ex) {
			System.out.println("Exception while selecting");
		}
	}

	public static void main(String[] args) {
		new JInternalFrame1();
	}
}