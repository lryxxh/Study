package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/**
 * It contains a check box that lets you set whether the glass pane is "visible"
 * ¡ª whether it can get events and paint itself onscreen. When the glass pane is
 * visible, it blocks all input events from reaching the components in the
 * content pane. It also paints a red dot in the place where it last detected a
 * mouse-pressed event.
 * 
 * @author HAN
 * 
 */
public class JRootPane_GlassPane_Test {

	/**
	 * Create and show GUI. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("GlassPane Demo 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Start creating and adding components.
		JCheckBox changeButton = new JCheckBox("Glass pane \"visible\"");
		changeButton.setSelected(false);

		// Set up the content pane, where the "main" GUI lives.
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(changeButton);
		contentPane.add(new JButton("Button 1"));
		contentPane.add(new JButton("Button 2"));

		// Set up the menu bar, which appears above the content pane.
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.add(new JMenuItem("Do nothing"));
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		// Set up the glass pane, which appears both above the menu bar and the
		// content pane, and is an item listener on the changeButton.
		MyGlassPane myGlassPane = new MyGlassPane(contentPane, changeButton);
		changeButton.addItemListener(myGlassPane);
		frame.setGlassPane(myGlassPane);

		// Show the window.
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Schedule a job for the event-dispatching thread:
		// creating and showing its application's GUI.
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				createAndShowGUI();

			}

		});
	}
}

/**
 * We have to provide our own glass pane so that it can paint itself.
 * 
 * @author HAN
 * 
 */
@SuppressWarnings("serial")
class MyGlassPane extends JComponent implements ItemListener {
	private Point point;

	// React to changeButton click.
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		setVisible(e.getStateChange() == ItemEvent.SELECTED);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (point != null) {
			g.setColor(Color.RED);
			g.fillOval(point.x - 10, point.y - 10, 20, 20);
		}
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	MyGlassPane(Container contentPane, AbstractButton changeButton) {
		// Should forward the input events to check box.
		CBListener listener = new CBListener(contentPane, changeButton, this);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
}

/**
 * Listen for all events that our check box is likely to be interested in.
 * Redispatch them to the check box.
 * 
 * @author HAN
 * 
 */
class CBListener implements MouseInputListener {
	private Container contentPane;
	private Component changeButton;
	private MyGlassPane glassPane;

	CBListener(Container contentPane, Component changeButton, MyGlassPane glassPane) {
		this.contentPane = contentPane;
		this.changeButton = changeButton;
		this.glassPane = glassPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		redispatchMouseEvent(e, false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		redispatchMouseEvent(e, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		redispatchMouseEvent(e, false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		redispatchMouseEvent(e, false);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		redispatchMouseEvent(e, false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		redispatchMouseEvent(e, false);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		redispatchMouseEvent(e, false);
	}

	// A basic implementation of redispatching events.
	private void redispatchMouseEvent(MouseEvent e, Boolean repaint) {
		Point glassPanePoint = e.getPoint();
		Point contentPanePoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, contentPane);
		Component component = SwingUtilities.getDeepestComponentAt(contentPane, contentPanePoint.x, contentPanePoint.y);
		if (component != null && component.equals(changeButton)) {
			Point componentPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, component);
			changeButton.dispatchEvent(new MouseEvent(component, e.getID(), e.getWhen(), e.getModifiers(), componentPoint.x, componentPoint.y, e.getClickCount(), e.isPopupTrigger()));
		} else {
			if (repaint) {
				glassPane.setPoint(glassPanePoint);
				glassPane.repaint();
			}
		}
	}
}