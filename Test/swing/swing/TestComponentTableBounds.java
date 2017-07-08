package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;

public class TestComponentTableBounds extends JComponent {
	long time = System.currentTimeMillis();
	String test = "";
	int i = 0;

	public TestComponentTableBounds(String test2) {
		this.setBorder(BorderFactory.createLineBorder(Color.red));
		this.test = test2;
	}

	@Override
	public String toString() {
		return this.test + this.getBounds();
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
//		g2.drawRect(10, 10, 40, 40);
		g2.drawString(test, 10, 10);
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

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		final boolean test = false;
		final JTextField textField = new JTextField("1.0");
		JTable table = new JTable(new Object[][] { { 1, 2, 4 }, { 2, 3, 4 },
				{ 3, 4, 5 }, { 4, 5, 6 } }, new Object[] { "col1", "col2",
				"col3" }) {
		};
		final JScrollPane tableScrollPane = new JScrollPane(table) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				System.out.println("tabletran"
						+ ((Graphics2D) g).getTransform());
			}
		};
		final JPanel panel = new JPanel() {

			@Override
			public String toString() {
				return "DrawPanel";
			}

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D graphics2d = (Graphics2D) g;
				// if (transform == null) {
//				transform = graphics2d.getTransform();
				// }
				System.out.println("panelTransform:" + transform);

				// transform.setToTranslation(-10, -10);
				// if(scale == 1) {
				// transform.translate(- 400, - 300);
				// }
				graphics2d.setTransform(transform);
				super.paintComponent(graphics2d);

			}

		};
		final JScrollPane scrollPane = new JScrollPane(panel);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Point point = new Point();
				try {
					transform.inverseTransform(e.getPoint(), point);
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
				System.out.println(e.getPoint() + "  " + point);
				Component component = panel.getComponentAt(point);
				System.out.println("click:" + component);
			}
		});

		panel.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				double oldScale = scale;
				Point2D tmpPoint = new Point2D.Double();
				try {
					for (Component component : panel.getComponents()) {
						transform.inverseTransform(component.getLocation(),
								tmpPoint);
						component.setBounds((int) (tmpPoint.getX()),
								(int) (tmpPoint.getY()), (int) (component.getWidth()
										/ oldScale),
								(int) (component.getHeight() / oldScale));
						System.err.println(component + " :"
								+ component.getLocation());
					}
				} catch (NoninvertibleTransformException e1) {
					e1.printStackTrace();
				}
				int count = e.getWheelRotation();
				Graphics2D g = (Graphics2D) panel.getGraphics();
				if (count > 0) {
					scale = scale * Math.pow(1.1, count);
				} else {
					scale = scale / Math.pow(1.1, Math.abs(count));
				}
				int initX = 0;
				int initY = 0;
				
				transform.setToIdentity();

				if (scale > 1) {
					transform.translate(-e.getPoint().getX() * (scale - 1), -e
							.getPoint().getY() * (scale - 1));
				} else {
					transform.translate(e.getPoint().getX() * (1 - scale), e
							.getPoint().getY() * (1 - scale));
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
					// component.setLocation((int)(point.getX()),
					// (int)(point.getY()));
					component.setBounds((int) (point.getX()),
							(int) (point.getY()), (int) (component.getWidth()
									* scale),
							(int) (component.getHeight() * scale));
					System.out.println(component + " ,,,,,, "
							+ component.getLocation());
				}
				g.setTransform(transform);
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

		final TestComponentTableBounds component3 = new TestComponentTableBounds(
				"s么s西sssssssss？");
		component3.setBorder(BorderFactory.createLineBorder(Color.blue));
		component3.setBounds(50, 50, 100, 100);
		TestComponentTableBounds component6 = null;
		int count = 0;
		for (int i = 0; i < 300; i = i + 100) {
			for (int j = 0; j < 200; j = j + 100) {
				component6 = new TestComponentTableBounds("" + (count));
				component6.setBounds(i, j, 80, 80);
				panel.add(component6);
				count++;
			}
		}
		JTree tree = new JTree();

		panel2.setBackground(Color.yellow);
		tableScrollPane.setBounds(0, 200, 200, 200);
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
		panel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {

			}

			@Override
			public void mouseDragged(MouseEvent e) {
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
		panel.add(textField);
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
	}
}
