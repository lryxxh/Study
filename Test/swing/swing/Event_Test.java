/**
 * 
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * @author HMI-Lenovo
 * 
 */
public class Event_Test extends JFrame {

	/**
	 * 
	 */
	public Event_Test() {
		init();
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	void init() {
		JComboBox box = new JComboBox(new String[]{"AAAA", "CCC", "BBBBBB"});
		JToolBar toolBar = new JToolBar();
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.add(new JButton("8888888888"));
		System.out.println("[-------------" + panel.getPreferredSize());
		System.out.println("ppppppppppppp " + getPreferredSize());

		JButton add = new JButton("add");
		JButton delete = new JButton("delete");
		toolBar.add(add);
		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setBackground(add.getBackground());
		toolBar.add(delete);
		toolBar.add(box);
		JSlider slider = new JSlider();
//		slider.setValue(20);
		slider.putClientProperty("Slider.isFilled", Boolean.FALSE);
		slider.setPaintTrack(false);
		toolBar.add(slider);
		toolBar.add(separator);
		add.setAlignmentX(0.5f);
		delete.setAlignmentX(0.5f);
		box.setAlignmentX(0.5f);
		add.setAlignmentY(0.5f);
		delete.setAlignmentY(0.5f);
		box.setAlignmentY(0.5f);
		box.setMaximumSize(box.getPreferredSize());
		add(toolBar, BorderLayout.NORTH);
//		setLayout(null);
		final JTextField field = new JTextField();
//		field.setBounds(200, 200, 200, 200);
		System.out.println(field.getPreferredSize());
		System.out.println(toolBar.getPreferredSize());
//		field.setMaximumSize(field.getPreferredSize());
		field.setAlignmentX(0.5f);
		field.setAlignmentY(0.5f);
		toolBar.add(field);
//		add(field);
		field.setPreferredSize(field.getPreferredSize());
		field.repaint();
		toolBar.repaint();
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				field.requestFocusInWindow();
			}

			@Override
			public void componentResized(ComponentEvent e) {
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});

		JPanel label = new JPanel();

		// MouseListener[] mouseListeners =
		// label.getListeners(MouseListener.class);
		// ActionListener[] actionListeners =
		// label.getListeners(ActionListener.class);
		// MouseWheelListener[] mouseWheelListeners =
		// label.getListeners(MouseWheelListener.class);
		// KeyListener[] keyListeners = label.getListeners(KeyListener.class);
		// MouseMotionListener[] mouseMotionListeners =
		// label.getListeners(MouseMotionListener.class);
		// for(MouseListener listener : mouseListeners) {
		// label.removeMouseListener(listener);
		// }
		// for(MouseMotionListener listener : mouseMotionListeners) {
		// label.removeMouseMotionListener(listener);
		// }
		// for(ActionListener listener : actionListeners) {
		// label.removeActionListener(listener);
		// }
		// for(MouseWheelListener listener : mouseWheelListeners) {
		// label.removeMouseWheelListener(listener);
		// }
		// for(KeyListener listener : keyListeners) {
		// label.removeKeyListener(listener);
		// }
		// for(KeyListener listener : keyListeners) {
		// label.removeKeyListener(listener);
		// }
		// for(KeyListener listener : keyListeners) {
		// label.removeKeyListener(listener);
		// }
		label.setOpaque(true);
		label.setBounds(10, 10, 100, 100);
		label.setBackground(Color.RED);
		// label.addMouseListener(new MouseListener() {
		//
		// @Override
		// public void mouseReleased(MouseEvent e) {
		// }
		//
		// @Override
		// public void mousePressed(MouseEvent e) {
		// }
		//
		// @Override
		// public void mouseExited(MouseEvent e) {
		// }
		//
		// @Override
		// public void mouseEntered(MouseEvent e) {
		// }
		//
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// System.out.println("label mouse clicked");
		// }
		// });
		JButton button = new JButton("sdsd");
		button.setBounds(10, 10, 100, 100);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(".............");
			}
		});
		add(label);
		add(button);
	}

	public static void main(String[] args) {
		new Event_Test();
	}
}
