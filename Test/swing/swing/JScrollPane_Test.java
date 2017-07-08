/**
 * 
 */
package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author HMI-Lenovo
 *
 */
public class JScrollPane_Test extends JFrame{
	
	/**
	 * 
	 */
	public JScrollPane_Test() {
		Init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void Init() {
		setContentPane(new CustomContentPane());
		JScrollPane sp1 = new JScrollPane();
		JScrollPane sp2 = new JScrollPane();
		sp2.getViewport().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("sdsd "+e);

			}
		});
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("working.gif")));
		JLabel label2 = new JLabel(new ImageIcon(getClass().getResource("123.gif")));
		sp1.setViewportView(label);
		sp2.setViewportView(label2);
		sp1.setPreferredSize(new Dimension(250,250));
		sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp2.setPreferredSize(new Dimension(250,250));
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(sp1);
		getContentPane().add(sp2);
	}
	
	class CustomContentPane extends JPanel {
		CustomContentPane() {
			setLayout(new FlowLayout());
		}
		/* (non-Javadoc)
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics g) {
			setBackground(Color.LIGHT_GRAY);
			super.paintComponent(g);
		}
	}
	
	public static void main(String[] args) {
		new JScrollPane_Test();
	}

}
