package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class TestComponent extends JLabel {
	long time = System.currentTimeMillis();
	String test = "";
	int i = 0;

	static JPanel panel = null;

	public TestComponent(String test2) {
		super();
		setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.red));
		this.test = test2;
		// addMouseListener(new MouseListener() {
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
		// System.out.println("clicked: " + e.getPoint());
		// }
		// });
	}

	@Override
	public String toString() {
		return " TestComponent " + this.test + this.getBounds();
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getTest() {
		return test;
	}

	// @Override
	// public void paint(Graphics g) {
	// super.paint(g);
	// g.drawString(test, 10, 10);
	// }

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		// System.out
		// .println(" == " + g2.getTransform().getScaleX());
		// super.paintComponent(g2);
		FontMetrics fontMetrics = g2.getFontMetrics();
		int width = fontMetrics.stringWidth("ss");
		Rectangle rectangle = g2.getClipBounds();
		Rectangle boundsRectangle = getBounds();
		g2.drawRect(10, 10, 40, 40);
		g2.drawString("sss", 10, 10);
		g2.drawString(getLocation().x + "," + getLocation().y, 5, 30);
		g2.dispose();
	}

	@Override
	public JPopupMenu getComponentPopupMenu() {
		JPopupMenu popupMenu = super.getComponentPopupMenu();
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(new JMenuItem("123"));
			popupMenu.add(new JMenuItem("333"));
		}
		return popupMenu;
	}

	@Override
	public Point getPopupLocation(MouseEvent event) {
		return event.getPoint();
	}

	static JPanel panel2 = new JPanel();
	static double scale = 1;
	static AffineTransform transform = new AffineTransform();
	private static int initx = 0;
	private static int inity = 200;
	private static Point initpoint = new Point(initx, inity);
	private static int width = 200;
	private static int height = 200;
	static double oldScale = 1.0;

	public static void main(String[] args) {
		panel = new JPanel(false) {

			@Override
			public String toString() {
				return "DrawPanel";
			}

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D graphics2d = (Graphics2D) g;
				// System.out.println("panelTransform:" + transform);

				// transform.setToTranslation(-10, -10);
				// if(scale == 1) {
				// transform.translate(- 400, - 300);
				// }
				graphics2d.setTransform(transform);
				super.paintComponent(graphics2d);

				for (int i = 0; i < 10; i++) {
					graphics2d.drawLine(0, 100 * (i + 1), 1000, 100 * (i + 1));
					graphics2d.drawString("" + 100 * (i + 1), 0, 100 * (i + 1));
					graphics2d.drawLine(100 * (i + 1), 0, 100 * (i + 1), 1000);
					graphics2d.drawString("" + 100 * (i + 1), 100 * (i + 1), 10);
				}

			}

			// @Override
			// public Component getComponentAt(int x, int y) {
			// Point point = new Point(x, y);
			// try {
			// transform.inverseTransform(point, point);
			// } catch (NoninvertibleTransformException e1) {
			// e1.printStackTrace();
			// }
			// Component component = super.getComponentAt(point.x, point.y);
			// // if (component instanceof JScrollPane) {
			// // point = SwingUtilities.convertPoint(this, point, component);
			// // component = component.getComponentAt(point);
			// // }
			// SwingUtilities.convertMouseEvent(this, , destination);
			// System.out.println(point + "   " + ((JComponent) component));
			// return component;
			// }

		};
		long time = System.currentTimeMillis();
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		final JTextField textField = new JTextField("1.0");
		// final JTable table = new JTable(new Object[][] { { 1, 2, 4 }, { 2, 3,
		// 4 }, { 3, 4, 5 }, { 4, 5, 6 } }, new Object[] { "col1", "col2",
		// "col3" }) {
		// MouseListener[] mouseListeners = null;
		// MouseMotionListener[] mouseMotionListeners = null;
		// MouseWheelListener[] mouseWheelListeners = null;
		//
		// @Override
		// protected void paintComponent(Graphics g) {
		// super.paintComponent(g);
		// Graphics2D graphics2d = (Graphics2D) g.create();
		// // System.out.println("tableScrollPane" + graphics2d.getTransform());
		// // panel.repaint();
		// }
		//
		// @Override
		// public String toString() {
		// return "table";
		// }
		//
		// @Override
		// public boolean contains(int x, int y) {
		// // return super.contains(x, y);
		// return true;
		// }
		//
		// public void addListener() {
		// for (final MouseListener mouseListener : mouseListeners) {
		// System.out.println("MouseListener:" + mouseListener);
		// MouseListener proxy = (MouseListener)
		// Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new
		// Class[] { MouseListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// // removeMouseListener(mouseListener);
		// addMouseListener(proxy);
		// }
		//
		// for (final MouseMotionListener mouseListener : mouseMotionListeners)
		// {
		// System.out.println("MouseMotionListener:" + mouseListener);
		// MouseMotionListener proxy = (MouseMotionListener)
		// Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(),
		// new Class[] { MouseMotionListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// // removeMouseMotionListener(mouseListener);
		// addMouseMotionListener(proxy);
		// }
		//
		// for (final MouseWheelListener mouseListener : mouseWheelListeners) {
		// System.out.println("MouseWheelListener :" + mouseListener);
		// MouseWheelListener proxy = (MouseWheelListener)
		// Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new
		// Class[] { MouseWheelListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// // removeMouseWheelListener(mouseListener);
		// addMouseWheelListener(proxy);
		//
		// }
		// }
		//
		// public void removeListener() {
		// this.mouseListeners = getListeners(MouseListener.class);
		// this.mouseMotionListeners = getListeners(MouseMotionListener.class);
		// this.mouseWheelListeners = getListeners(MouseWheelListener.class);
		//
		// for (final MouseListener mouseListener : mouseListeners) {
		// System.out.println("MouseListener:" + mouseListener);
		// MouseListener proxy = (MouseListener)
		// Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new
		// Class[] { MouseListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// removeMouseListener(mouseListener);
		// // table.addMouseListener(proxy);
		// }
		//
		// for (final MouseMotionListener mouseListener : mouseMotionListeners)
		// {
		// System.out.println("MouseMotionListener:" + mouseListener);
		// MouseMotionListener proxy = (MouseMotionListener)
		// Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(),
		// new Class[] { MouseMotionListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// removeMouseMotionListener(mouseListener);
		// // table.addMouseMotionListener(proxy);
		// }
		//
		// for (final MouseWheelListener mouseListener : mouseWheelListeners) {
		// System.out.println("MouseWheelListener :" + mouseListener);
		// MouseWheelListener proxy = (MouseWheelListener)
		// Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new
		// Class[] { MouseWheelListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// removeMouseWheelListener(mouseListener);
		// // table.addMouseWheelListener(proxy);
		//
		// }
		// }
		//
		//
		//
		// };
		final MyTable table = new MyTable();
		table.getTableHeader().setBackground(Color.green);
		table.setAutoCreateRowSorter(true);
		// MouseListener[] mouseListeners =
		// table.getListeners(MouseListener.class);
		// MouseMotionListener[] mouseMotionListeners =
		// table.getListeners(MouseMotionListener.class);
		// MouseWheelListener[] mouseWheelListeners =
		// table.getListeners(MouseWheelListener.class);
		//
		// for (final MouseListener mouseListener : mouseListeners) {
		// System.out.println("MouseListener:" + mouseListener);
		// MouseListener proxy = (MouseListener)
		// Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new
		// Class[] { MouseListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// table.removeMouseListener(mouseListener);
		// // table.addMouseListener(proxy);
		// }
		//
		// for (final MouseMotionListener mouseListener : mouseMotionListeners)
		// {
		// System.out.println("MouseMotionListener:" + mouseListener);
		// MouseMotionListener proxy = (MouseMotionListener)
		// Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(),
		// new Class[] { MouseMotionListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// table.removeMouseMotionListener(mouseListener);
		// // table.addMouseMotionListener(proxy);
		// }
		//
		// for (final MouseWheelListener mouseListener : mouseWheelListeners) {
		// System.out.println("MouseWheelListener :" + mouseListener);
		// MouseWheelListener proxy = (MouseWheelListener)
		// Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new
		// Class[] { MouseWheelListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// table.removeMouseWheelListener(mouseListener);
		// // table.addMouseWheelListener(proxy);
		//
		// }
		//
		MouseListener[] mouseListeners2 = table.getTableHeader().getListeners(MouseListener.class);
		MouseMotionListener[] mouseMotionListeners2 = table.getTableHeader().getListeners(MouseMotionListener.class);
		MouseWheelListener[] mouseWheelListeners2 = table.getTableHeader().getListeners(MouseWheelListener.class);

		for (final MouseListener mouseListener : mouseListeners2) {
			System.out.println("MouseListener:" + mouseListener);
			MouseListener proxy = (MouseListener) Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new Class[] { MouseListener.class }, new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Object obj = method.invoke(mouseListener, args);
					panel.repaint();
					return obj;
				}
			});
			table.getTableHeader().removeMouseListener(mouseListener);
			// table.getTableHeader().addMouseListener(proxy);
		}

		for (final MouseMotionListener mouseListener : mouseMotionListeners2) {
			System.out.println("MouseMotionListener:" + mouseListener);
			MouseMotionListener proxy = (MouseMotionListener) Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(), new Class[] { MouseMotionListener.class }, new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Object obj = method.invoke(mouseListener, args);
					panel.repaint();
					return obj;
				}
			});
			table.getTableHeader().removeMouseMotionListener(mouseListener);
			// table.getTableHeader().addMouseMotionListener(proxy);
		}

		for (final MouseWheelListener mouseListener : mouseWheelListeners2) {
			System.out.println("MouseWheelListener :" + mouseListener);
			MouseWheelListener proxy = (MouseWheelListener) Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new Class[] { MouseWheelListener.class }, new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Object obj = method.invoke(mouseListener, args);
					panel.repaint();
					return obj;
				}
			});
			// table.getTableHeader().removeMouseWheelListener(mouseListener);
			table.getTableHeader().addMouseWheelListener(proxy);

		}

		final JScrollPane tableScrollPane = new JScrollPane(table) {
			@Override
			public String toString() {
				return "TABLE_SCROLLPANE";
			}

			@Override
			public Component getComponentAt(Point point) {
				// try {
				// transform.inverseTransform(point, point);
				// } catch (NoninvertibleTransformException e1) {
				// e1.printStackTrace();
				// }
				Component component = super.getComponentAt(point.x, point.y);
				// System.out.println(point + "  scrollPane " + ((JComponent)
				// component));
				return component;
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D graphics2d = (Graphics2D) g.create();
				// System.out.println("tableScrollPane" +
				// graphics2d.getTransform());
				graphics2d.setTransform(transform);
				super.paintComponent(graphics2d);
			}

			@Override
			public void setVerticalScrollBar(JScrollBar verticalScrollBar) {
				verticalScrollBar = new MyScrollBar();
				super.setVerticalScrollBar(verticalScrollBar);
			}
		};

		final JScrollBar scrollBar = tableScrollPane.getVerticalScrollBar();
		MouseListener[] mouseListeners3 = scrollBar.getMouseListeners();
		MouseMotionListener[] mouseMotionListeners3 = scrollBar.getMouseMotionListeners();
		MouseWheelListener[] mouseWheelListeners3 = scrollBar.getMouseWheelListeners();

		for (final MouseListener mouseListener : mouseListeners3) {
			System.out.println("MouseListener:" + mouseListener);
			MouseListener proxy = (MouseListener) Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new Class[] { MouseListener.class }, new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Object obj = method.invoke(mouseListener, args);
					panel.repaint();
					return obj;
				}
			});
			scrollBar.removeMouseListener(mouseListener);
			// table.addMouseListener(proxy);
		}

		for (final MouseMotionListener mouseListener : mouseMotionListeners3) {
			System.out.println("MouseMotionListener:" + mouseListener);
			MouseMotionListener proxy = (MouseMotionListener) Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(), new Class[] { MouseMotionListener.class }, new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Object obj = method.invoke(mouseListener, args);
					panel.repaint();
					return obj;
				}
			});
			scrollBar.removeMouseMotionListener(mouseListener);
			// table.addMouseMotionListener(proxy);
		}

		for (final MouseWheelListener mouseListener : mouseWheelListeners3) {
			System.out.println("MouseWheelListener :" + mouseListener);
			MouseWheelListener proxy = (MouseWheelListener) Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new Class[] { MouseWheelListener.class }, new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Object obj = method.invoke(mouseListener, args);
					panel.repaint();
					return obj;
				}
			});
			scrollBar.removeMouseWheelListener(mouseListener);
			// table.addMouseWheelListener(proxy);

		}

		// MouseListener[] mouseListeners3 =
		// tableScrollPane.getListeners(MouseListener.class);
		// MouseMotionListener[] mouseMotionListeners3 =
		// tableScrollPane.getListeners(MouseMotionListener.class);
		// MouseWheelListener[] mouseWheelListeners3 =
		// tableScrollPane.getListeners(MouseWheelListener.class);
		//
		// for (final MouseListener mouseListener : mouseListeners3) {
		// System.out.println("MouseListener:" + mouseListener);
		// MouseListener proxy = (MouseListener)
		// Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new
		// Class[] { MouseListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// tableScrollPane.removeMouseListener(mouseListener);
		// // table.addMouseListener(proxy);
		// }
		//
		// for (final MouseMotionListener mouseListener : mouseMotionListeners3)
		// {
		// System.out.println("MouseMotionListener:" + mouseListener);
		// MouseMotionListener proxy = (MouseMotionListener)
		// Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(),
		// new Class[] { MouseMotionListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// tableScrollPane.removeMouseMotionListener(mouseListener);
		// // table.addMouseMotionListener(proxy);
		// }
		//
		// for (final MouseWheelListener mouseListener : mouseWheelListeners3) {
		// System.out.println("MouseWheelListener :" + mouseListener);
		// MouseWheelListener proxy = (MouseWheelListener)
		// Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new
		// Class[] { MouseWheelListener.class }, new InvocationHandler() {
		//
		// @Override
		// public Object invoke(Object proxy, Method method, Object[] args)
		// throws Throwable {
		// Object obj = method.invoke(mouseListener, args);
		// panel.repaint();
		// return obj;
		// }
		// });
		// tableScrollPane.removeMouseWheelListener(mouseListener);
		// // table.addMouseWheelListener(proxy);
		//
		// }

		// table.addMouseMotionListener(new MouseMotionListener() {
		//
		// @Override
		// public void mouseMoved(MouseEvent e) {
		// }
		//
		// @Override
		// public void mouseDragged(MouseEvent e) {
		// Rectangle rectangle = tableScrollPane.getBounds();
		// int x = rectangle.x;
		// int y = rectangle.y;
		// int width = rectangle.width;
		// int height = rectangle.height;
		//
		// tableScrollPane.setBounds(e.getX(), e.getY(), width, height + 1);
		// System.out.println("--------");
		// tableScrollPane.repaint();
		// }
		// });

		final JScrollPane scrollPane = new JScrollPane(panel) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Point2D tmpPoint = new Point2D.Double();
				try {
					transform.inverseTransform(getLocation(), tmpPoint);
				} catch (NoninvertibleTransformException e) {
					e.printStackTrace();
				}
				width = getWidth();
				height = getHeight();
				width = (int) (width / oldScale);
				height = (int) (height / oldScale);
				// component.setLocation((int)(Math.round(tmpPoint.getX())),
				// (int)((Math.round( tmpPoint.getY()))));
				// component.setSize(width, height);
			}

		};

		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				Point point = new Point();
				try {
					transform.inverseTransform(e.getPoint(), point);
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
				// System.out.println(e.getPoint() + "  " + point);
				Component component = SwingUtilities.getDeepestComponentAt(panel, point.x, point.y);
				System.out.println(component);
				// point = SwingUtilities.convertPoint(panel, point, component);
				if (component == null) {
					return;
				}
				if (component instanceof ListenerManager) {
					((ListenerManager) component).addListener();
				}
				// MouseEvent newEvent = new MouseEvent(component,
				// e.getID(),
				// e.getWhen(),
				// e.getModifiers(),
				// point.x,point.y,
				// e.getXOnScreen(),
				// e.getYOnScreen(),
				// e.getClickCount(),
				// e.isPopupTrigger(),
				// MouseEvent.NOBUTTON );
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
				MouseEvent newEvent = SwingUtilities.convertMouseEvent(panel, e, component);

				System.out.println(point + "   ||||   " + newEvent);
				component.dispatchEvent(newEvent);
				if (component instanceof ListenerManager) {
					((ListenerManager) component).removeListener();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("mousePressed:" + e.getPoint());
				super.mousePressed(e);
				Point point = new Point();
				try {
					transform.inverseTransform(e.getPoint(), point);
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
				// System.out.println(e.getPoint() + "  " + point);
				Component component = SwingUtilities.getDeepestComponentAt(panel, point.x, point.y);
				if (component == null) {
					return;
				}
				System.out.println(component);
				// point = SwingUtilities.convertPoint(panel, point, component);

				if (component instanceof ListenerManager) {
					((ListenerManager) component).addListener();
				}
				// MouseEvent newEvent = new MouseEvent(component,
				// e.getID(),
				// e.getWhen(),
				// e.getModifiers(),
				// point.x,point.y,
				// e.getXOnScreen(),
				// e.getYOnScreen(),
				// e.getClickCount(),
				// e.isPopupTrigger(),
				// MouseEvent.NOBUTTON );
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
				MouseEvent newEvent = SwingUtilities.convertMouseEvent(panel, e, component);

				System.out.println(point + "   ||||   " + newEvent);
				component.dispatchEvent(newEvent);
				if (component instanceof ListenerManager) {
					((ListenerManager) component).removeListener();
				}
				// table.repaint();
				// panel.repaint();
			}

			public void mouseEntered(MouseEvent e) {
				System.out.println("=======");
				System.out.println(e.getSource());
			};

			@Override
			public void mouseClicked(MouseEvent e) {

				System.out.println("mousePressed:" + e.getPoint());
				super.mouseClicked(e);
				Point point = new Point();
				try {
					transform.inverseTransform(e.getPoint(), point);
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
				// System.out.println(e.getPoint() + "  " + point);
				Component component = SwingUtilities.getDeepestComponentAt(panel, point.x, point.y);
				System.out.println(component);
				// point = SwingUtilities.convertPoint(panel, point, component);
				if (component == null) {
					return;
				}
				if (component instanceof ListenerManager) {
					((ListenerManager) component).addListener();
				}
				// MouseEvent newEvent = new MouseEvent(component,
				// e.getID(),
				// e.getWhen(),
				// e.getModifiers(),
				// point.x,point.y,
				// e.getXOnScreen(),
				// e.getYOnScreen(),
				// e.getClickCount(),
				// e.isPopupTrigger(),
				// MouseEvent.NOBUTTON );
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
				MouseEvent newEvent = SwingUtilities.convertMouseEvent(panel, e, component);

				System.out.println(point + "   ||||   " + newEvent);
				component.dispatchEvent(newEvent);
				if (component instanceof ListenerManager) {
					((ListenerManager) component).removeListener();
				}
				// table.repaint();
				// panel.repaint();

				// Point point = new Point();
				// try {
				// transform.inverseTransform(e.getPoint(), point);
				// } catch (NoninvertibleTransformException e1) {
				// e1.printStackTrace();
				// }
				// // System.out.println(e.getPoint() + "  " + point);
				// Component component =
				// SwingUtilities.getDeepestComponentAt(panel, point.x,
				// point.y);
				// System.out.println(component);
				// // SwingUtilities.convertMouseEvent(source, sourceEvent,
				// destination);
				// // component = panel.getComponentAt(point);
				// Point mouseEvent = SwingUtilities.convertPoint(panel, point,
				// component);
				//
				// component = component.getComponentAt(mouseEvent);
				//
				// System.err.println("===================" + mouseEvent);
				// MouseEvent e2 = new MouseEvent(table,
				// MouseEvent.MOUSE_PRESSED, e.getWhen(), e.getModifiers(),
				// mouseEvent.x, mouseEvent.y, e.getXOnScreen(),
				// e.getYOnScreen(), e.getClickCount(), e
				// .isPopupTrigger(), MouseEvent.NOBUTTON);
				//
				// System.out.println(e2 + " ==========" + e2.getSource());
				// table.addListener();
				// table.dispatchEvent(e2);
				// panel.repaint();
				// table.removeListener();
			}
		});

		panel.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				oldScale = scale;
				int initX = 0;
				int initY = 0;
				int width = 0;
				int height = 0;
				Point2D tmpPoint = new Point2D.Double();
				try {
					for (Component component : panel.getComponents()) {
						transform.inverseTransform(component.getLocation(), tmpPoint);
						width = component.getWidth();
						height = component.getHeight();
						width = (int) (width / scale);
						height = (int) (height / scale);
						// component.setLocation((int)(Math.round(tmpPoint.getX())),
						// (int)((Math.round( tmpPoint.getY()))));
						// component.setSize(width, height);
						System.err.println(component);
					}
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
				int count = e.getWheelRotation();
				if (count > 0) {
					scale = scale * Math.pow(1.1, count);
				} else {
					scale = scale / Math.pow(1.1, Math.abs(count));
				}
				System.out.println("=====" + scale);
				transform.setToIdentity();

				if (scale > 1) {
					transform.translate(-e.getPoint().getX() * (scale - 1), -e.getPoint().getY() * (scale - 1));
				} else {
					transform.translate(e.getPoint().getX() * (1 - scale), e.getPoint().getY() * (1 - scale));
				}
				transform.scale(scale, scale);

				// if(scale < 1) {
				// transform.translate(e.getX()*(1 - scale), e.getY()*(1 - scale
				// ));
				// } else {
				// transform.translate(e.getX()*(scale - 1), e.getY()*(scale -
				// 1));
				// }

				Point2D point = new Point2D.Double();
				textField.setText("" + scale + "     | ");
				for (Component component : panel.getComponents()) {
					transform.transform(component.getLocation(), point);
					width = component.getWidth();
					height = component.getHeight();
					width = (int) (width * scale);
					height = (int) (height * scale);
					// component.setLocation((int)(point.getX()),
					// (int)(point.getY()));
					// component.setSize(width, height);
					System.out.println(component);
				}
				// g.setTransform(transform);
				// System.out.println(initpoint + " transform:" + point);
				// scrollPane.repaint();
				// panel2.updateUI();
				panel.repaint();
				// panel.updateUI();
				// for(Component component : panel.getComponents()) {
				// System.err.println(" : "+component.getBounds());;
				// }

			}
		});
		panel.setMinimumSize(new Dimension(800, 600));
		panel.setLayout(null);

		// final TestComponent component = new TestComponent("Hello World!");
		//
		// // panel.add(component);
		// component.setBounds(10, 10, 100, 100);
		// component.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		//
		// final TestComponent component2 = new TestComponent("什么东西？");
		// component2.setBounds(90, 90, 100, 100);
		// panel.add(component2);
		// panel.setBackground(Color.GRAY);
		// component2.setBorder(BorderFactory.createLineBorder(Color.RED));
		//
		// final TestComponent component4 = new TestComponent("什么东西？");
		// component4.setBorder(BorderFactory.createLineBorder(Color.yellow));
		// component4.setBounds(80, 40, 100, 40);
		// panel.add(component4);

		final TestComponent component3 = new TestComponent("ww");
		component3.setBorder(BorderFactory.createLineBorder(Color.blue));
		component3.setBounds(50, 50, 100, 100);
		TestComponent component6 = null;
		int count = 0;
		for (int i = 0; i < 300; i = i + 100) {
			for (int j = 0; j < 200; j = j + 100) {
				component6 = new TestComponent("" + (count));
				component6.setBackground(Color.red);
				component6.setBounds(i, j, 80, 80);
				panel.add(component6);
				count++;
			}
		}
		JTree tree = new JTree();

		panel2.setBackground(Color.yellow);
		tableScrollPane.setBounds(0, 200, 600, 400);
		panel2.setLayout(new BorderLayout());
		panel2.add(tableScrollPane);
		panel.add(tableScrollPane);
		// tree.setBounds(220, 220, 200, 200);
		// panel.add(tree);
		// JPanel panel4 = new JPanel();
		// panel4.setLayout(new BorderLayout());
		// // panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		// // panel4.setBackground(Color.gray);
		// panel4.add(panel);
		// panel4.add(panel2);
		// panel4.setPreferredSize(new Dimension(500, 400));
		panel.setBackground(Color.yellow);
		// scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		// scrollPane.getViewport().setLayout(new BorderLayout());
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				Point point = new Point();
				try {
					transform.inverseTransform(e.getPoint(), point);
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
				// System.out.println(e.getPoint() + "  " + point);
				Component component = SwingUtilities.getDeepestComponentAt(panel, point.x, point.y);
				System.out.println(component);
				// point = SwingUtilities.convertPoint(panel, point, component);

				if (component instanceof ListenerManager) {
					((ListenerManager) component).addListener();
				}
				// MouseEvent newEvent = new MouseEvent(component,
				// e.getID(),
				// e.getWhen(),
				// e.getModifiers(),
				// point.x,point.y,
				// e.getXOnScreen(),
				// e.getYOnScreen(),
				// e.getClickCount(),
				// e.isPopupTrigger(),
				// MouseEvent.NOBUTTON );
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
				MouseEvent newEvent = SwingUtilities.convertMouseEvent(panel, e, component);

				System.out.println(point + "   ||||   " + newEvent);
				if (component != null) {
					component.dispatchEvent(newEvent);
					if (component instanceof ListenerManager) {
						((ListenerManager) component).removeListener();
					}
				}
				// Point point = scrollPane.getViewport().getViewPosition();
				// System.out.println("---------mouseDragged" + e.getSource());
				// System.out.println("size " + panel.getSize() +" preSize " +
				// panel.getPreferredSize());
				// scrollPane.getViewport().setViewPosition(new Point(point.x -
				// 5, point.y - 5));
				// transform.setToTranslation(e.getX(), e.getY());
				// scrollPane.repaint();
			}

		});
		// panel.addMouseMotionListener(new MouseMotionListener() {
		//
		// @Override
		// public void mouseMoved(MouseEvent e) {
		// }
		//
		// @Override
		// public void mouseDragged(MouseEvent e) {
		// System.out.println("================mouseDrag");
		// }
		// });
		// frame.getContentPane().add(textField, BorderLayout.EAST);
		textField.setBounds(300, 300, 200, 30);
		// panel.add(textField);
		frame.getContentPane().add(scrollPane);
		JButton button = new JButton("22222222");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// component2.setTest(Math.random() * 1000+"");
				// component2.repaint();

				// component.repaint();
				// panel.paintAll(graphics2d);
				// panel.update(graphics2d);
				scale = Double.parseDouble(textField.getText());
				if (scale < 2) {
					panel.repaint();
				} else {
					panel.getComponent(4).repaint();
				}
			}
		});

		// component.setBackground(Color.red);
		// component2.setBackground(Color.green);
		// component.setOpaque(true);
		// component2.setOpaque(true);

		// frame.getContentPane().add(button, BorderLayout.SOUTH);
		frame.setVisible(true);
		// System.out.println("-------" + (System.currentTimeMillis() - time));
		// try {
		// ObjectOutputStream oos = new ObjectOutputStream(new
		// FileOutputStream("C:/Users/LRY/Desktop/1.obj"));
		// oos.writeObject(frame);
		//
		// oos.flush();
		// oos.close();
		//
		// ObjectInputStream oisInputStream = new ObjectInputStream(new
		// FileInputStream("C:/Users/LRY/Desktop/1.obj"));
		// JFrame tempFrame = (JFrame) oisInputStream.readObject();
		// tempFrame.setVisible(true);
		// } catch (FileNotFoundException e1) {
		// e1.printStackTrace();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// } catch (ClassNotFoundException e1) {
		// e1.printStackTrace();
		// }

	}

	interface ListenerManager {
		public void addListener();

		public void removeListener();
	}

	static class MyTable extends JTable implements ListenerManager {

		MouseListener[] mouseListeners = null;
		MouseMotionListener[] mouseMotionListeners = null;
		MouseWheelListener[] mouseWheelListeners = null;
		static Object object[] = new Object[10];
		static Object[][] data = new Object[200][];
		static {
			for (int j = 0; j < 200; j++) {
				data[j] = new Object[10];
				for (int i = 0; i < 10; i++) {
					data[j][i] = (int)(Math.random() * 50);
					if (j == 0) {
						object[i] = "Column" + i;
					}

				}
			}
		}

		public MyTable() {
			super(new DefaultTableModel(data, object));
			setAutoCreateRowSorter(true);
			removeListener();
		}

		@Override
		protected JTableHeader createDefaultTableHeader() {
			return new MyTableHeader(columnModel);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graphics2d = (Graphics2D) g.create();
			// System.out.println("tableScrollPane" +
			// graphics2d.getTransform());
			// panel.repaint();
		}

		@Override
		public String toString() {
			return "table";
		}

		@Override
		public boolean contains(int x, int y) {
			return super.contains(x, y);
			// return true;
		}

		public void addListener() {
			for (final MouseListener mouseListener : mouseListeners) {
				System.out.println("MouseListener:" + mouseListener);
				MouseListener proxy = (MouseListener) Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new Class[] { MouseListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						panel.repaint();
						return obj;
					}
				});
				// removeMouseListener(mouseListener);
				addMouseListener(proxy);
			}

			for (final MouseMotionListener mouseListener : mouseMotionListeners) {
				System.out.println("MouseMotionListener:" + mouseListener);
				MouseMotionListener proxy = (MouseMotionListener) Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(), new Class[] { MouseMotionListener.class },
						new InvocationHandler() {

							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								Object obj = method.invoke(mouseListener, args);
								panel.repaint();
								return obj;
							}
						});
				// removeMouseMotionListener(mouseListener);
				addMouseMotionListener(proxy);
			}

			for (final MouseWheelListener mouseListener : mouseWheelListeners) {
				System.out.println("MouseWheelListener :" + mouseListener);
				MouseWheelListener proxy = (MouseWheelListener) Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new Class[] { MouseWheelListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						panel.repaint();
						return obj;
					}
				});
				// removeMouseWheelListener(mouseListener);
				addMouseWheelListener(proxy);

			}
		}

		public void removeListener() {
			this.mouseListeners = getListeners(MouseListener.class);
			this.mouseMotionListeners = getListeners(MouseMotionListener.class);
			this.mouseWheelListeners = getListeners(MouseWheelListener.class);

			for (final MouseListener mouseListener : mouseListeners) {
				// System.out.println("MouseListener:" + mouseListener);
				MouseListener proxy = (MouseListener) Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new Class[] { MouseListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						panel.repaint();
						return obj;
					}
				});
				removeMouseListener(mouseListener);
				// table.addMouseListener(proxy);
			}

			for (final MouseMotionListener mouseListener : mouseMotionListeners) {
				// System.out.println("MouseMotionListener:" + mouseListener);
				MouseMotionListener proxy = (MouseMotionListener) Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(), new Class[] { MouseMotionListener.class },
						new InvocationHandler() {

							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								Object obj = method.invoke(mouseListener, args);
								panel.repaint();
								return obj;
							}
						});
				removeMouseMotionListener(mouseListener);
				// table.addMouseMotionListener(proxy);
			}

			for (final MouseWheelListener mouseListener : mouseWheelListeners) {
				// System.out.println("MouseWheelListener :" + mouseListener);
				MouseWheelListener proxy = (MouseWheelListener) Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new Class[] { MouseWheelListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						panel.repaint();
						return obj;
					}
				});
				removeMouseWheelListener(mouseListener);
				// table.addMouseWheelListener(proxy);

			}
		}

	}

	static class MyTableHeader extends JTableHeader implements ListenerManager {
		MouseListener[] mouseListeners = null;
		MouseMotionListener[] mouseMotionListeners = null;
		MouseWheelListener[] mouseWheelListeners = null;

		public MyTableHeader(TableColumnModel columnModel) {
			super(columnModel);
			removeListener();
		}

		public void addListener() {
			removeListeners();

			for (final MouseListener mouseListener : mouseListeners) {
				System.err.println("MyScrollBar MouseListener:" + mouseListener);
				MouseListener proxy = (MouseListener) Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new Class[] { MouseListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						getParent().repaint();
						panel.updateUI();
						panel.repaint();
						return obj;
					}
				});
				// removeMouseListener(mouseListener);
				addMouseListener(proxy);
			}

			for (final MouseMotionListener mouseListener : mouseMotionListeners) {
				System.err.println("MyScrollBar MouseMotionListener:" + mouseListener);
				MouseMotionListener proxy = (MouseMotionListener) Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(), new Class[] { MouseMotionListener.class },
						new InvocationHandler() {

							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								Object obj = method.invoke(mouseListener, args);
								getParent().repaint();
								panel.updateUI();
								panel.repaint();
								return obj;
							}
						});
				// removeMouseMotionListener(mouseListener);
				addMouseMotionListener(proxy);
			}

			for (final MouseWheelListener mouseListener : mouseWheelListeners) {
				System.err.println("MyScrollBar MouseWheelListener :" + mouseListener);
				MouseWheelListener proxy = (MouseWheelListener) Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new Class[] { MouseWheelListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						getParent().repaint();
						panel.repaint();
						return obj;
					}
				});
				// removeMouseWheelListener(mouseListener);
				addMouseWheelListener(proxy);

			}
		}

		public void removeListeners() {
			removeListener();
			updateUI();
			removeListener();
		}

		public void removeListener() {
			// if(this.mouseListeners == null) {
			// }
			// if(this.mouseMotionListeners == null) {
			// }
			//
			// if(this.mouseWheelListeners == null) {
			// }

			this.mouseListeners = getListeners(MouseListener.class);
			this.mouseMotionListeners = getListeners(MouseMotionListener.class);
			this.mouseWheelListeners = getListeners(MouseWheelListener.class);
			for (final MouseListener mouseListener : mouseListeners) {
				System.err.println("MyScrollBar MouseListener:" + mouseListener);
				removeMouseListener(mouseListener);
				// table.addMouseListener(proxy);
			}

			for (final MouseMotionListener mouseListener : mouseMotionListeners) {
				System.err.println("MyScrollBar MouseMotionListener:" + mouseListener);
				removeMouseMotionListener(mouseListener);
				// table.addMouseMotionListener(proxy);
			}

			for (final MouseWheelListener mouseListener : mouseWheelListeners) {
				System.err.println("MyScrollBar MouseWheelListener :" + mouseListener);
				removeMouseWheelListener(mouseListener);
				// table.addMouseWheelListener(proxy);

			}
		}
	}

	static class MyScrollBar extends JScrollBar implements ListenerManager {

		MouseListener[] mouseListeners = null;
		MouseMotionListener[] mouseMotionListeners = null;
		MouseWheelListener[] mouseWheelListeners = null;

		public MyScrollBar() {
			super();
			removeListener();
			for (Component component : getComponents()) {
				System.err.println("===" + component);
			}
		}

		public void addListener() {
			for (final MouseListener mouseListener : mouseListeners) {
				System.err.println("MyScrollBar MouseListener:" + mouseListener);
				MouseListener proxy = (MouseListener) Proxy.newProxyInstance(MouseListener.class.getClassLoader(), new Class[] { MouseListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						getParent().repaint();
						panel.updateUI();
						panel.repaint();
						return obj;
					}
				});
				// removeMouseListener(mouseListener);
				addMouseListener(proxy);
			}

			for (final MouseMotionListener mouseListener : mouseMotionListeners) {
				System.err.println("MyScrollBar MouseMotionListener:" + mouseListener);
				MouseMotionListener proxy = (MouseMotionListener) Proxy.newProxyInstance(MouseMotionListener.class.getClassLoader(), new Class[] { MouseMotionListener.class },
						new InvocationHandler() {

							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								Object obj = method.invoke(mouseListener, args);
								getParent().repaint();
								panel.updateUI();
								panel.repaint();
								return obj;
							}
						});
				// removeMouseMotionListener(mouseListener);
				addMouseMotionListener(proxy);
			}

			for (final MouseWheelListener mouseListener : mouseWheelListeners) {
				System.err.println("MyScrollBar MouseWheelListener :" + mouseListener);
				MouseWheelListener proxy = (MouseWheelListener) Proxy.newProxyInstance(MouseWheelListener.class.getClassLoader(), new Class[] { MouseWheelListener.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object obj = method.invoke(mouseListener, args);
						getParent().repaint();
						panel.repaint();
						return obj;
					}
				});
				// removeMouseWheelListener(mouseListener);
				addMouseWheelListener(proxy);

			}
		}

		public void removeListener() {
			this.mouseListeners = getListeners(MouseListener.class);
			this.mouseMotionListeners = getListeners(MouseMotionListener.class);
			this.mouseWheelListeners = getListeners(MouseWheelListener.class);

			for (final MouseListener mouseListener : mouseListeners) {
				System.err.println("MyScrollBar MouseListener:" + mouseListener);
				removeMouseListener(mouseListener);
				// table.addMouseListener(proxy);
			}

			for (final MouseMotionListener mouseListener : mouseMotionListeners) {
				System.err.println("MyScrollBar MouseMotionListener:" + mouseListener);
				removeMouseMotionListener(mouseListener);
				// table.addMouseMotionListener(proxy);
			}

			for (final MouseWheelListener mouseListener : mouseWheelListeners) {
				System.err.println("MyScrollBar MouseWheelListener :" + mouseListener);
				removeMouseWheelListener(mouseListener);
				// table.addMouseWheelListener(proxy);

			}
		}

	}
}
