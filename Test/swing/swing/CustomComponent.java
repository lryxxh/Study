package swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomComponent extends JComponent{
	public CustomComponent() {
		setBorder(BorderFactory.createLineBorder(Color.red));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(getBounds());
		setBounds(150,150,100,100);
		g.drawString("HelloWorld!", 20, 50);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400,300);
		JPanel panel = new JPanel();
		CustomComponent component = new CustomComponent();
		component.setBounds(100,100,100,100);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		panel.add(component);
		frame.setVisible(true);

	}

}
