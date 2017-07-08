package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
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
import java.lang.reflect.Field;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import mdl.warn_message_new.new_warn_cornfirm_msg;

public class TableScale_Test extends JFrame {

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

	public TableScale_Test() {
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
				ComponentListener[] listeners = dataTable.getComponentListeners();
				for (ComponentListener listener : listeners) {
					System.out.println(listener);
				}
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

	private void dealMouseEvent(MouseEvent e) {
		Point point = e.getPoint();
		try {
			transform.inverseTransform(point, point);
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		final Component component = SwingUtilities.getDeepestComponentAt(drawPanel, point.x, point.y);
		try {
//			System.out.println("point " + e.getPoint());
			if (component == null) {
				return;
			}
			// Field field =
			// toolTipManager.getClass().getDeclaredField("preferredLocation");
			// field.setAccessible(true);
			// field.set(toolTipManager, new Point(600, 500));
//			Field field = component.getClass().getDeclaredField("tooltipLocation");
			Point point2 = component.getLocation();
			// Point tooltipLocation = new Point(point2.x + e.getX(), point2.y +
			// e.getY());
			// System.out.println("tooltipLocation1111  " + tooltipLocation +
			// "  " );
			Point tooltipLocation = SwingUtilities.convertPoint(this, e.getPoint(), component);
//			System.out.println("tooltipLocation2222  " + tooltipLocation + "  ");

//			field.set(component, tooltipLocation);
		} catch (SecurityException e1) {
			e1.printStackTrace();
			// } catch (NoSuchFieldException e1) {
			// e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (component == null) {
			System.err.println("component is null ");
			return;
		}
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
//		System.err.println("------------" + component);
		// component.repaint()
		containerPanel.repaint();
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
		// setSize(1200, 800);
		// setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {
		containerPanel = new JPanel();
		containerPanel.setBackground(Color.gray);
		containerPanel.setLayout(null);

		eventFilterPanel = new JPanel();
		// eventFilterPanel.setOpaque(false);
		eventFilterPanel.setBackground(new Color(0, 255, 0, 100));
		eventFilterPanel.setBounds(getDeviceRectangle());
		containerPanel.add(eventFilterPanel);

		drawPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.transform(transform);
				
				String helloworld = "HelloWorld!";
				FontMetrics fontMetrics = g2.getFontMetrics();
				int width = fontMetrics.stringWidth(helloworld);
				System.out.println(g2.getTransform() + "      "+width);
				super.paintComponent(g2);
			};

		};
		drawPanel.setToolTipText("");

		drawPanel.setBackground(Color.BLACK);
		// drawPanel.setLayout(null);
		drawPanel.setBounds(getDeviceRectangle());
		containerPanel.add(drawPanel);

		tableScrollPane = new JScrollPane() {
			
			
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
						 fireStateChanged();
						containerPanel.repaint();
					}
				};
			}
		};
		JTable table2 = new JTable();
		

		Vector<Vector> indexdata = new Vector<Vector>();
		Vector indexcolumns = new Vector();
		for (int i = 0; i < 200; i++) {
			Vector row  = new Vector();
			for (int j = 0; j < 10; j++) {
				String value = "";
				value = ""+ (int)(50 * Math.random());
				if (j == 0) {
					value = i + "";
				}
				if (i == 0) {
					indexcolumns.add("Column" + j);
				}
				row.add(value);
			}
			indexdata.add(row);
		}
		table2.setModel(new DefaultTableModel(indexdata, indexcolumns));
		table2.getColumnModel().getColumn(0).setMinWidth(50);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.getColumnModel().getColumn(0).setPreferredWidth(50);
		table2.getColumnModel().getColumn(0).setMaxWidth(50);
		table2.setPreferredScrollableViewportSize(new Dimension(50, 0));
		tableScrollPane.setRowHeaderView(table2);
		tableScrollPane.setBounds(100, 100, 300, 300);
		// drawPanel.add(tableScrollPane);

		dataTable = new JTable(getTableData(), getTableColumns()) {
			public Point tooltipLocation = new Point();
			public Point popupMenuLocation = new Point();

			@Override
			public Point getToolTipLocation(MouseEvent event) {
				return tooltipLocation;
			}

			@Override
			public Point getPopupLocation(MouseEvent event) {
				return popupMenuLocation;
			}
		};
		dataTable.setAutoCreateRowSorter(true);
		dataTable.setToolTipText("dataTable");
		tableScrollPane.setViewportView(dataTable);

		treeScrollPane = new JScrollPane() {

//			@Override
//			protected JViewport createViewport() {
//				return new JViewport() {
//					@Override
//					public void setViewPosition(Point p) {
//						System.out.println("sdsdf");
//						
//						Component view = getView();
//						if (view == null) {
//							return;
//						}
//
//						int oldX, oldY, x = p.x, y = p.y;
//
//						/*
//						 * Collect the old x,y values for the views location and
//						 * do the song and dance to avoid allocating a Rectangle
//						 * object if we don't have to.
//						 */
//						if (view instanceof JComponent) {
//							JComponent c = (JComponent) view;
//							oldX = c.getX();
//							oldY = c.getY();
//						} else {
//							Rectangle r = view.getBounds();
//							oldX = r.x;
//							oldY = r.y;
//						}
//
//						/*
//						 * The view scrolls in the opposite direction to mouse
//						 * movement.
//						 */
//						int newX = -x;
//						int newY = -y;
//						view.setLocation(newX, newY);
//						// fireStateChanged();
//						containerPanel.repaint();
//						System.out.println("=====");
//					}
//				};
//			}

		};

		TableColumnModel columnModel = dataTable.getColumnModel();
		TableColumn tableColumn = null;
		for (int i = 0; i < dataTable.getColumnCount(); i++) {
			tableColumn = columnModel.getColumn(i);
			tableColumn.setPreferredWidth(80);
		}
		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
		TableScale_Test test = new TableScale_Test();
	}

}
