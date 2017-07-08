/**
 * 
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author HMI-Lenovo
 *
 */
public class LayoutTest extends JFrame{

	/**
	 * 
	 */
	public LayoutTest() {
		setSize(800, 600);
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LayoutTest();
	}

	/**
	 * 
	 */
	private void init() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JButton button  = new JButton();
		JPanel imagePanel = new JPanel();
		imagePanel.setPreferredSize(new Dimension(200, 250));
		imagePanel.setBackground(Color.orange);
		
		JPanel buttonPanel = new JPanel();
//		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.add(new LabelTextFieldPanel("id"));
		buttonPanel.add(new LabelTextFieldPanel("name"));
		buttonPanel.add(new LabelTextFieldPanel("age"));
		buttonPanel.add(new LabelTextFieldPanel("address"));
		buttonPanel.add(new LabelTextFieldPanel("job"));
		buttonPanel.add(new LabelTextFieldPanel("city"));
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		
//		buttonPanel.add(new JButton("sdsds"));
		panel1.add(buttonPanel, BorderLayout.CENTER);
		
		panel1.add(imagePanel, BorderLayout.EAST);
//		panel1.setBackground(Color.red);
		
		JPanel panel2 = new JPanel();
		button = new JButton("OK"); 
		button.setContentAreaFilled(false);
//		panel2.setBackground(Color.green);
		panel2.setLayout(new BorderLayout());
		panel2.add(button);
		
		button = new JButton("Cancel");
		button.setContentAreaFilled(false);
		JPanel panel3 = new JPanel();
//		panel3.setBackground(Color.blue);
		panel3.setLayout(new BorderLayout());
		panel3.add(button);
		
		add(panel1);
		add(panel2);
		add(panel3);
	}

}
