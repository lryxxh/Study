package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class AffineTransform_Test2 extends JFrame {

	/**  */
	private static final long serialVersionUID = 5043065920751625426L;

	public AffineTransform_Test2() {
		// TODO Auto-generated method stub
		// this.setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		// this.getRootPane().setBackground(Color.red);
		JButton button = new JButton("Ðý×ªAngle");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String angleStr = JOptionPane.showInputDialog("");
				angle = Double.valueOf(angleStr);
				repaint();
			}
		});
		JPanel panel = new JPanel() {

			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				for (int i = 0; i < 10; i++) {
					g2.drawLine(0, 100 * (i + 1), 1000, 100 * (i + 1));
					g2.drawString("" + 100 * (i + 1), 0, 100 * (i + 1));
				}
				g2.drawString("200,200", 200, 200);
				g2.setColor(Color.red);
				g2.drawOval(410, 250, 1, 1);
				g2.setColor(Color.black);
				AffineTransform transform = new AffineTransform();
				transform.translate(200, 200);
				transform.translate(150, 150);
				transform.scale(scaleX, scaleY);
				transform.rotate(Math.toRadians(angle));
				transform.translate(-150, -150);
				g2.setTransform(transform);
				drawSubFig(g2);
				// g2.translate(-150, -150);
				System.out.println(transform.transform(new Point2D.Float(220, 20), new Point2D.Float(0, 0)) + " " + g2.getTransform() + "  "
						+ transform.transform(new Point2D.Float(220, 20), new Point2D.Float(0, 0)));
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
		panel.add(new JTable(new Object[][] {}, new Object[] { "A", "B", "C", "D" }));
		getContentPane().add(panel, BorderLayout.CENTER);
		// setJMenuBar(menuBar);
		JPanel buttonPanel = new JPanel();
		JButton scaleXButton = new JButton("Ëõ·ÅScaleX");
		scaleXButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String scaleStr = JOptionPane.showInputDialog("");
				scaleX = Double.valueOf(scaleStr);
				scaleY = scaleX;
				repaint();
			}
		});
		JButton scaleYButton = new JButton("Ëõ·ÅScaleY");
		scaleYButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String scaleStr = JOptionPane.showInputDialog("");
				scaleY = Double.valueOf(scaleStr);
				repaint();
			}
		});
		buttonPanel.add(button);
		buttonPanel.add(scaleXButton);
		// buttonPanel.add(scaleYButton);

		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		setSize(1000, 700);
	}

	double angle = 0;
	double scaleX = 1;
	double scaleY = 1;

	public static void main(String[] args) {
		// System.out.println(0x040600000000018dl);
		AffineTransform_Test2 test = new AffineTransform_Test2();
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

}
