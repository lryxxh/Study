package swing;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class CopyOfTableScale_Test extends JFrame {

	/** */
	private static final long serialVersionUID = 1L;
	JPanel containerPanel = null;

	JPanel drawPanel = null;

	JPanel eventFilterPanel = null;

	JScrollPane tableScrollPane = null;

	JTable dataTable = null;

	AffineTransform transform = new AffineTransform();

	double scale = 1.0;
	JScrollPane treeScrollPane = null;

	JTree tree = null;

	public CopyOfTableScale_Test() {
		initComponents();
		addListener();
		setShowProperty();
	}

	private void addListener() {
		eventFilterPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				dealMouseEvent(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				dealMouseEvent(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				dealMouseEvent(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				dealMouseEvent(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				dealMouseEvent(e);
			}
		});

		eventFilterPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				dealMouseEvent(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				dealMouseEvent(e);
			}
		});

		eventFilterPanel.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				scalePic(e);
				containerPanel.repaint();
			}
		});
	}

	Component oldcomponent = null;

	private void dealMouseEvent(MouseEvent e) {

		Point point = e.getPoint();
		try {
			transform.inverseTransform(point, point);
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		final Component component = SwingUtilities.getDeepestComponentAt(drawPanel, point.x, point.y);
		if (component== null ||component.equals(drawPanel)||component.equals(eventFilterPanel)) {
			return;
		}
		if (oldcomponent != component && e.getID() == MouseEvent.MOUSE_MOVED) {
			Field field;
			try {
				field = AWTEvent.class.getDeclaredField("id");
				field.setAccessible(true);
				try {
//					field.set(e, MouseEvent.MOUSE_EXITED);
					if(oldcomponent != null) {
						e.setSource(oldcomponent);
						oldcomponent.dispatchEvent(e);
					}
//					field.set(e, MouseEvent.MOUSE_ENTERED);
					e.setSource(component);
					component.dispatchEvent(e);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println(component + "   mouseentered");

			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			}

			oldcomponent = component;
		} else {
			e.setSource(drawPanel);
			try {
				Field field = MouseEvent.class.getDeclaredField("x");
				Field yField = MouseEvent.class.getDeclaredField("y");
				field.setAccessible(true);
				yField.setAccessible(true);
				field.set(e, point.x);
				yField.set(e, point.y);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			final MouseEvent eeEvent = SwingUtilities.convertMouseEvent(drawPanel, e, component);
			component.dispatchEvent(eeEvent);
			oldcomponent = component;
			containerPanel.repaint();

		}

	}

	public void scalePic(MouseWheelEvent e) {
		int count = e.getWheelRotation();
		transform.setToIdentity();
		if (count < 0) {
			scale = scale * Math.pow(1.1, Math.abs(count));
		} else {
			scale = scale / Math.pow(1.1, count);
		}
		transform.scale(scale, scale);
	}

	private void setShowProperty() {
		 setSize(800, 700);
		// setResizable(false);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {
		containerPanel = new JPanel();
		containerPanel.setBackground(Color.gray);
		containerPanel.setLayout(null);

		eventFilterPanel = new JPanel() {
			@Override
			public String toString() {
				return "eventFilterPanel";
			}
		};
		eventFilterPanel.setOpaque(false);
		// eventFilterPanel.setBackground(new Color(0, 255, 0, 100));
		eventFilterPanel.setBounds(getDeviceRectangle());
		containerPanel.add(eventFilterPanel);

		drawPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.transform(transform);
				super.paintComponent(g2);
			};

			@Override
			public String toString() {
				return "drawPanel";
			}

		};

		drawPanel.setBackground(Color.BLACK);
		// drawPanel.setLayout(null);
		drawPanel.setBounds(getDeviceRectangle());
		containerPanel.add(drawPanel);

		tableScrollPane = new JScrollPane() {
			
			@Override
			public String toString() {
				return "tableScrollPane";
			}
			@Override
			protected JViewport createViewport() {
				return new JViewport() {
					@Override
					public void setViewPosition(Point p) {
						Component view = getView();
						if (view == null) {
							return;
						}

						int oldX, oldY, x = p.x, y = p.y;

						/*
						 * Collect the old x,y values for the views location and
						 * do the song and dance to avoid allocating a Rectangle
						 * object if we don't have to.
						 */
						if (view instanceof JComponent) {
							JComponent c = (JComponent) view;
							oldX = c.getX();
							oldY = c.getY();
						} else {
							Rectangle r = view.getBounds();
							oldX = r.x;
							oldY = r.y;
						}

						/*
						 * The view scrolls in the opposite direction to mouse
						 * movement.
						 */
						int newX = -x;
						int newY = -y;
						view.setLocation(newX, newY);
						// fireStateChanged();
						containerPanel.repaint();
					}
				};
			}
		};
		tableScrollPane.setBounds(100, 100, 300, 300);
		// drawPanel.add(tableScrollPane);

		dataTable = new JTable(getTableData(), getTableColumns()){
			@Override
			public String toString() {
				return "dataTable";
			}
		};
		dataTable.setToolTipText("====ssssssssssss");
		dataTable.setAutoCreateRowSorter(true);
		tableScrollPane.setViewportView(dataTable);

		treeScrollPane = new JScrollPane() {
			
			@Override
			public String toString() {
				return "treeScrollPane";
			}

			@Override
			protected JViewport createViewport() {
				return new JViewport() {
					@Override
					public void setViewPosition(Point p) {
						Component view = getView();
						if (view == null) {
							return;
						}

						int oldX, oldY, x = p.x, y = p.y;

						/*
						 * Collect the old x,y values for the views location and
						 * do the song and dance to avoid allocating a Rectangle
						 * object if we don't have to.
						 */
						if (view instanceof JComponent) {
							JComponent c = (JComponent) view;
							oldX = c.getX();
							oldY = c.getY();
						} else {
							Rectangle r = view.getBounds();
							oldX = r.x;
							oldY = r.y;
						}

						/*
						 * The view scrolls in the opposite direction to mouse
						 * movement.
						 */
						int newX = -x;
						int newY = -y;
						view.setLocation(newX, newY);
						// fireStateChanged();
						containerPanel.repaint();
						System.out.println("=====");
					}
				};
			}

		};
		treeScrollPane.setBounds(450, 100, 400, 400);
		JTree tree = new JTree();
		treeScrollPane.setViewportView(tree);

		// drawPanel.add(treeScrollPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setLeftComponent(treeScrollPane);
		splitPane.setRightComponent(tableScrollPane);
		drawPanel.setLayout(null);
		splitPane.setBounds(200, 200, 400, 400);
		drawPanel.add(splitPane);

		add(containerPanel);
	}

	private Rectangle getDeviceRectangle() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Rectangle rectangle = new Rectangle();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		Insets insets = toolkit.getScreenInsets(device.getDefaultConfiguration());
		Dimension screenSize = toolkit.getScreenSize();
		rectangle.setBounds(insets.left, insets.top, screenSize.width - insets.left - insets.right, screenSize.height - insets.top - insets.bottom);
		return rectangle;
	}

	private Object[][] getTableData() {
		Object[][] data = new Object[200][];
		for (int j = 0; j < 200; j++) {
			data[j] = new Object[10];
			for (int i = 0; i < 10; i++) {
				data[j][i] = (int) (Math.random() * 50);
			}
		}
		return data;
	}

	private Object[] getTableColumns() {
		Object[] column = new Object[10];
		for (int i = 0; i < 10; i++) {
			column[i] = "Column" + i;
		}
		return column;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CopyOfTableScale_Test test = new CopyOfTableScale_Test();
	}

}
