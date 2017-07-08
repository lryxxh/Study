package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.media.j3d.Bounds;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform;

public class AffineTransform_3Test extends JFrame {

	/**  */
	private static final long serialVersionUID = 5043065920751625426L;

	AffineTransform affineTransform = new AffineTransform();

	public AffineTransform_3Test() {
		setBackground(Color.black);
		// TODO Auto-generated method stub
		// this.setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		// this.getRootPane().setBackground(Color.red);
		JButton button = new JButton("旋转Angle");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String angleStr = JOptionPane.showInputDialog("");
				angle = Double.valueOf(angleStr);
				repaint();
			}
		});
		final JPanel panel = new JPanel() {

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;

				// g2.drawString("200,200", 200, 200);
				// g2.setColor(Color.red);
				// g2.drawOval(410, 250, 1, 1);
				// g2.setColor(Color.black);
				AffineTransform transform = new AffineTransform();
				// // transform.translate(200, 200);
				// // transform.translate(150, 150);
				transform.scale(scaleX, scaleY);
				// transform.rotate(Math.toRadians(angle));
				// // transform.translate(-150, -150);
				g2.setTransform(transform);
				super.paintComponent(g2);
				for (int i = 0; i < 10; i++) {
					g2.drawLine(0, 100 * (i + 1), 1000, 100 * (i + 1));
					g2.drawString("" + 100 * (i + 1), 0, 100 * (i + 1));
					g2.drawLine(100 * (i + 1), 0, 100 * (i + 1), 1000);
					g2.drawString("" + 100 * (i + 1), 100 * (i + 1), 10);
				}
				// drawSubFig(g2);
				// // g2.translate(-150, -150);
				// System.out.println(transform.transform(new Point2D.Float(220,
				// 20), new Point2D.Float(0, 0)) + " " + g2.getTransform() +
				// "  "
				// + transform.transform(new Point2D.Float(220, 20), new
				// Point2D.Float(0, 0)));
			}

			public void drawSubFig(Graphics2D g2) {
				// g2.drawLine(200, 40, 200, 60);
				// g2.drawLine(210, 30, 210, 70);
				// g2.drawLine(220, 20, 220, 80);
				g2.drawOval(100, 100, 100, 100);
				g2.drawLine(150, 150, 200, 150);

				g2.setColor(Color.red);
				g2.drawLine(150, 100, 150, 150);
			}

		};
		final JScrollPane pane = new JScrollPane();
		pane.setOpaque(true);
		pane.setDoubleBuffered(false);
		pane.setBorder(BorderFactory.createLineBorder(Color.red));
		final MyTable table = new MyTable(new Object[][] { { "A", "B", "C", "D" }, { "A", "B", "C", "D" }, { "A", "B", "C", "D" }, { "A", "B", "C", "D" } }, new Object[] { "A", "B", "C", "D" });
		pane.setViewportView(table);
		panel.add(pane);
		panel.setLayout(null);
		pane.setBounds(300, 300, 300, 300);
		getContentPane().add(panel, BorderLayout.CENTER);
		// setJMenuBar(menuBar);
		JPanel buttonPanel = new JPanel();
		JButton scaleXButton = new JButton("缩放ScaleX");
		scaleXButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String scaleStr = JOptionPane.showInputDialog("");
				scaleX = Double.valueOf(scaleStr);
				scaleY = scaleX;
				repaint();
				affineTransform.setToScale(scaleX, scaleY);
				Rectangle bounds = pane.getBounds();
				int x = bounds.x;
				int y = bounds.y;
				int width = bounds.width;
				int height = bounds.height;
				Point point = new Point(x, y);
				Point newPoint = new Point(width, height);
				Point tPoint = new Point(x + width, height + y);
				affineTransform.transform(point, point);
				affineTransform.transform(newPoint, newPoint);
				affineTransform.transform(tPoint, tPoint);

				System.out.println(affineTransform + "  " + point + "  " + newPoint);

				pane.setBounds(point.x, point.y, newPoint.x, newPoint.y);
//				table.repaintCompon  ent(affineTransform);
				table.repaint();
			}
		});
		JButton scaleYButton = new JButton("缩放ScaleY");
		scaleYButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String scaleStr = JOptionPane.showInputDialog("");
				scaleY = Double.valueOf(scaleStr);
				repaint();
			}
		});

		JButton setLocationButton = new JButton("设置位置");
		setLocationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Rectangle rectangle = pane.getBounds();
				int x = rectangle.x + 100;
				int y = rectangle.y + 50;
				int width = rectangle.width + 50;
				int height = rectangle.height + 50;
				pane.setBounds(x, y, width, height);
				panel.updateUI();
			}
		});
		buttonPanel.add(button);
		buttonPanel.add(scaleXButton);
		buttonPanel.add(setLocationButton);
		// buttonPanel.add(scaleYButton);

		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		setSize(1000, 700);
	}

	double angle = 0;
	double scaleX = 1;
	double scaleY = 1;

	public static void main(String[] args) {
		// System.out.println(0x040600000000018dl);
		AffineTransform_3Test test = new AffineTransform_3Test();
		test.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	// @Override
	// public void paint(Graphics g) {
	// Graphics2D g2 = (Graphics2D) g;
	// for(int i = 0; i < 10;i++) {
	// g2.drawLine(0, 100*(i+1), 1000, 100*(i+1));
	// g2.drawString("" + 100 * (i+1), 0, 100*(i+1));
	// }
	// g2.drawString("200,200",200, 200);
	// g2.drawString("400,400",400, 400);
	// AffineTransform transform = new AffineTransform();
	// transform.translate(200, 200);
	// transform.translate(210, 50);
	// transform.scale(1.0, 1.0);
	// transform.rotate(Math.toRadians(angle));
	// transform.translate(-210, -50);
	// g2.setTransform(transform);
	// drawSubFig(g2);
	// System.out.println(transform.transform(new Point2D.Float(100, 100), new
	// Point2D.Float(0,0)) + "   " + transform.transform(new Point2D.Float(200,
	// 200), new Point2D.Float(0,0)) +" "+g2.getTransform());
	// }

	class MyTable extends JTable {

		Graphics g = null;

		public MyTable(Object[][] objects, Object[] objects2) {
			super(objects, objects2);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Graphics2D graphics2d = (Graphics2D) g.create();
			// AffineTransform transform = graphics2d.getTransform();
			// transform.setToScale(1, 1);
			// graphics2d.setTransform(transform);
			// super.paintComponent(graphics2d);
			// graphics2d.scale(1, 1);
			// super.paintComponent(graphics2d);
			// AffineTransform affineTransform = graphics2d.getTransform();
			// affineTransform.setToIdentity();
			// AffineTransform transform = new AffineTransform();
			// // transform.translate(200, 200);
			// // transform.translate(150, 150);
			// transform.translate(getX() + getWidth()/2, getY() + getHeight()
			// /2);
			// transform.scale(scaleX, scaleY);
			// // transform.rotate(Math.toRadians(angle));
			// graphics2d.setTransform(transform);
			// super.paintComponent(graphics2d);
			// System.out.println(((Graphics2D)g).getTransform());
		};

		public void repaintComponent(AffineTransform affineTransform) {
			super.paintComponent(g);
			Graphics2D g = (Graphics2D) getGraphics().create();
			g.setTransform(affineTransform);
			g.setClip(getBounds());
			System.out.println("==============" + getBounds());
			super.paintComponent(g);
		}
		
		@Override
		public boolean contains(int x, int y) {
			return super.contains(x, y);
		}
	}

}
