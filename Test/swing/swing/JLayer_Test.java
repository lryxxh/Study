package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.lang.reflect.Field;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class JLayer_Test {
	static JPanel panel = null;
	static double scale = 1;
	static AffineTransform transform = new AffineTransform();
	static JScrollPane tableJScrollPane = null;
	static JTable table = null;

	private static void dealMouseEvent(JPanel panel2, Component component, MouseEvent e) {
		Point point = e.getPoint();
		try {
			transform.transform(point, point);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println("after convert :" + point);
		// point = SwingUtilities.convertPoint(panel2, point, component);

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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame() {
			public Graphics getGraphics() {
				Graphics g = super.getGraphics();
				((Graphics2D) g).setTransform(transform);
				System.out.println(((Graphics2D) g).getTransform());
				return g;
			}
		};

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		final DrawPanel panel2 = new DrawPanel();
		panel2.setBackground(Color.green);

		DebugGraphics.setLogStream(System.out);
		tableJScrollPane = new JScrollPane() {
			MouseWheelListener[] listeners = null;

			@Override
			public void repaint() {
				panel2.repaint();
			}

			public void repaint(int x, int y, int w, int h) {
				panel2.repaint();
			}

			public JScrollBar createVerticalScrollBar() {
				JScrollBar scrollPane = new JScrollBar(JScrollBar.VERTICAL) {
					protected void processMouseMotionEvent(MouseEvent e) {
						Point point = e.getPoint();
						point = SwingUtilities.convertPoint(this, point, panel2);
						// dealMouseEvent(panel2, table, e);
						try {
							transform.inverseTransform(point, point);
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						point = SwingUtilities.convertPoint(panel2, point, this);
						// point = SwingUtilities.convertPoint(panel2, point,
						// component);

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
						super.processMouseMotionEvent(e);
						panel2.repaint();

					}

					protected void processMouseEvent(MouseEvent e) {

						Point point = e.getPoint();
						point = SwingUtilities.convertPoint(this, point, panel2);
						// dealMouseEvent(panel2, table, e);
						try {
							transform.inverseTransform(point, point);
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						point = SwingUtilities.convertPoint(panel2, point, this);
						// point = SwingUtilities.convertPoint(panel2, point,
						// component);

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
						super.processMouseEvent(e);
						panel2.repaint();
					}

					public boolean contains(int x, int y) {
						// return false;
						// return panel2.contains(this, x, y);
						// System.out.println(this + "   " + x + "," + y);
						//
						// return getParent().contains(x, y);
						return true;

					}

					public String toString() {
						return "JScrollBar ";
					}

					public void repaint() {
						// panel2.repaint();
					}

					public void repaint(int x, int y, int w, int h) {
						panel2.repaint();
					}
				};

				return scrollPane;
			}

			// @Override
			// public void repaint(int x, int y, int width, int height) {
			// // super.repaint(x, y, width, height);
			// panel2.repaint();
			// }

			public boolean contains(int x, int y) {
				if (scale < 2) {
					return false;

				}
				// scale *=1.1;
				return panel2.contains(this, x, y);
				// return true;
				// if(getParent()!= null) {
				// getParent().contains(x,y);
				// }
				// return true;
				// return super.contains(x, y);
				// return panel2.contains(this, x, y);
			}

			protected JViewport createViewport() {
				return new JViewport() {

					public String toString() {
						return "JViewport ";
					}

					public boolean contains(int x, int y) {
						// if (scale < 1.5) {
						// return false;
						// }
						// return panel2.contains(this, x, y);
						// return super.contains(x,y);
						// return false;
						// System.out.println(this + "   " + x + "," + y);

						// return getParent().contains(x, y);
						return false;

					}

					public void setViewPosition(Point p) {
						super.setViewPosition(p);
						panel2.repaint();
					}

				};
			}

			public String toString() {
				return "tableJScrollPane ";
			}

			public void processMouseWheelEvent(MouseWheelEvent e) {
				Point point = e.getPoint();
				point = SwingUtilities.convertPoint(this, point, panel2);
				// dealMouseEvent(panel2, table, e);
				try {
					transform.inverseTransform(point, point);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				point = SwingUtilities.convertPoint(panel2, point, this);
				// point = SwingUtilities.convertPoint(panel2, point,
				// component);

				try {
					Field field = MouseWheelEvent.class.getDeclaredField("x");
					Field yField = MouseWheelEvent.class.getDeclaredField("y");
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
				super.processMouseWheelEvent(e);
				panel2.repaint();

			}

		};
		table = new JTable(new Object[][] { { 1, 2, 4 }, { 2, 3, 4 }, { 3, 4, 5 }, { 4, 5, 6 }, { 1, 2, 4 }, { 2, 3, 4 }, { 3, 4, 5 }, { 4, 5, 6 }, { 1, 2, 4 }, { 2, 3, 4 }, { 3, 4, 5 }, { 4, 5, 6 },
				{ 1, 2, 4 }, { 2, 3, 4 }, { 3, 4, 5 }, { 4, 5, 6 }, { 1, 2, 4 }, { 2, 3, 4 }, { 3, 4, 5 }, { 4, 5, 6 }, { 1, 2, 4 }, { 2, 3, 4 }, { 3, 4, 5 }, { 4, 5, 6 } }, new Object[] { "col1",
				"col2", "col3" }) {

			public void paintComponent(Graphics g) {
				System.out.println(" ddd " + ((Graphics2D) g).getTransform());
				// super.paintComponent(g);
				// AffineTransform affineTransform =
				// ((Graphics2D)g).getTransform();
				// affineTransform.concatenate(transform);
				// ((Graphics2D)g).setTransform(affineTransform);
				super.paintComponent(g);
				// System.out.println("paint table");
				// Thread.currentThread().dumpStack();

			}

			public String toString() {
				return "table ";
			}

			@Override
			protected void processMouseEvent(MouseEvent e) {
				if (e.getID() == MouseEvent.MOUSE_PRESSED)
					// System.out.println(e);
					System.out.println(e.getX() + " " + e.getY());
				Point point = e.getPoint();
				point = SwingUtilities.convertPoint(table, point, panel2);
				// dealMouseEvent(panel2, table, e);
				try {
					transform.inverseTransform(point, point);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				System.out.println("after convert :" + point);
				point = SwingUtilities.convertPoint(panel2, point, table);
				// point = SwingUtilities.convertPoint(panel2, point,
				// component);

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
				super.processMouseEvent(e);
				panel2.repaint();
			}

			@Override
			protected void processMouseMotionEvent(MouseEvent e) {

				super.processMouseMotionEvent(e);
			}

			@Override
			protected void processMouseWheelEvent(MouseWheelEvent e) {

				super.processMouseWheelEvent(e);
			}

			@Override
			public boolean contains(int x, int y) {
				// return panel2.contains(this, x, y);
				System.out.println(this + "   " + x + "," + y);

				// return getParent().contains(x, y);
				// return false;
				return false;
			}

		};
		tableJScrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		tableJScrollPane.setBounds(200, 100, 200, 200);
		panel2.setLayout(null);
		panel2.add(tableJScrollPane);
		// panel2.setBounds(0, 0, 800, 600);

		final JTextField textField = new JTextField();
		// textField.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// scale = Double.parseDouble(textField.getText());
		// transform.scale(scale, scale);
		// panel.repaint();
		// System.out.println("-------------" + scale);
		// }
		// });
		textField.setBounds(200, 10, 100, 30);
		// panel2.add(textField);

		final JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		panel3.setBounds(0, 0, 800, 600);
		panel2.requestFocusInWindow();
		panel2.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println();
				scalePic(e);
				panel2.repaint();
			}
		});
		// panel3.addMouseWheelListener(new MouseWheelListener() {
		//
		// @Override
		// public void mouseWheelMoved(MouseWheelEvent e) {
		// dealMouseEvent(panel2, e);
		// }
		// });
		// panel2.addMouseMotionListener(new MouseMotionListener() {
		//
		// @Override
		// public void mouseMoved(MouseEvent e) {
		// dealMouseEvent(panel2, e);
		// panel.repaint();
		//
		// }
		//
		// @Override
		// public void mouseDragged(MouseEvent e) {
		// dealMouseEvent(panel2, e);
		// panel.repaint();
		// }
		// });
		// panel2.addMouseListener(new MouseAdapter() {
		//
		// @Override
		// public void mouseReleased(MouseEvent e) {
		// dealMouseEvent(panel2, e);
		// panel.repaint();
		//
		// }
		//
		// @Override
		// public void mousePressed(MouseEvent e) {
		// dealMouseEvent(panel2, e);
		// panel.repaint();
		//
		// }
		//
		// // @Override
		// // public void mouseExited(MouseEvent e) {
		// // dealMouseEvent(panel2, e);
		// // panel.repaint();
		// //
		// // }
		//
		// @Override
		// public void mouseEntered(MouseEvent e) {
		// dealMouseEvent(panel2, e);
		// panel.repaint();
		//
		// }
		//
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// dealMouseEvent(panel2, e);
		// panel.repaint();
		//
		// }
		// });

		// panel.add(panel3);
		panel.add(panel2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setVisible(true);

	}

	public static void scalePic(MouseWheelEvent e) {
		int count = e.getWheelRotation();
		transform.setToIdentity();
		if (count < 0) {
			scale = scale * Math.pow(1.1, Math.abs(count));
		} else {
			scale = scale / Math.pow(1.1, count);
		}
		// if (scale > 1) {
		// if (e != null) {
		// transform.translate(-e.getPoint().getX() * (scale - 1),
		// -e.getPoint().getY() * (scale - 1));
		// } else {
		// transform.translate(0, 0);
		// }
		// } else {
		// transform.translate((canvasWidth - fig.getW() * scale) / 2,
		// (canvasHeight - fig.getH() * scale) / 2);
		// }
		transform.scale(scale, scale);
		System.out.println("-------------" + scale);
		// repaint();
	}

	private static void dealMouseEvent(final JPanel panel2, MouseEvent e) {
		Point point = e.getPoint();
		try {
			transform.inverseTransform(point, point);
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		final Component component = SwingUtilities.getDeepestComponentAt(panel2, point.x, point.y);
		if (component == null || component.equals(panel2)) {
			System.err.println("component is null ");
			return;
		}
		// System.out.println(point + "  " + component);

		e.setSource(panel2);
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
		// MouseEvent eeEvent = SwingUtilities.convertMouseEvent(panel3, e,
		// panel2);
		// System.out.println(eeEvent);
		final MouseEvent eeEvent = SwingUtilities.convertMouseEvent(panel2, e, component);
		// System.err.println(eeEvent);

		component.dispatchEvent(eeEvent);
		// table.repaint();
		// component.repaint();
		// tableJScrollPane.repaint();
		// panel2.repaint();
		panel.repaint();
		// System.out.println();
		// component.repaint();
	}

	static class DrawPanel extends JPanel {
		public String toString() {
			return "DrawPanel ";
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			AffineTransform affineTransform = ((Graphics2D) g).getTransform();
			affineTransform.concatenate(transform);
			((Graphics2D) g).setTransform(affineTransform);
			super.paintComponent(g);
			Point point = new Point(200, 200);
			Point endPoint = new Point(400, 400);
			try {
				transform.inverseTransform(point, point);
				transform.inverseTransform(endPoint, endPoint);

			} catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}

			((Graphics2D) g).setColor(Color.red);
			((Graphics2D) g).drawRect(point.x, point.y, endPoint.x - point.x, endPoint.y - point.y);

			for (int i = 0; i < 10; i++) {
				Point point1 = new Point(0, 100 * (i + 1));
				Point point2 = new Point(1000, 100 * (i + 1));
				Point point3 = new Point(100 * (i + 1), 0);
				Point point4 = new Point(100 * (i + 1), 1000);
				try {
					transform.inverseTransform(point1, point1);
					transform.inverseTransform(point2, point2);
					transform.inverseTransform(point3, point3);
					transform.inverseTransform(point4, point4);
				} catch (NoninvertibleTransformException e) {
					e.printStackTrace();
				}
				((Graphics2D) g).drawLine(point1.x, point1.y, point2.x, point2.y);
				((Graphics2D) g).drawString("" + 100 * (i + 1), 0, point2.y);
				((Graphics2D) g).drawLine(point3.x, point3.y, point4.x, point4.y);
				((Graphics2D) g).drawString("" + 100 * (i + 1), point3.x, 10);
			}

		}

		public boolean contains(int x, int y) {
			boolean flag = super.contains(x, y);
			Point point = new Point(x, y);
			try {
				transform.inverseTransform(point, point);
			} catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}
			return super.contains(point.x, point.y);
		}

		public boolean contains(Component component, int x, int y) {
			// super.contains();
			try {
				Point point = (Point) transform.inverseTransform(new Point(x, y), new Point());
				System.out.println(component + " (" + x + "," + y + ")  point " + point);
			} catch (NoninvertibleTransformException e) {
				e.printStackTrace();
			}
			return true;
		}

	}

}
