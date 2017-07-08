package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class ShortKeyTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(">>>>>>>>>>>>>>>>");
			}
		});
//		button.getInputMap().put(KeyStroke.getKeyStroke(new Character('K'),InputEvent.CTRL_MASK), "Button");
//		button.getActionMap().put("Button", button.getAction());
		button.setMnemonic(KeyEvent.VK_B);
		frame.getContentPane().add(button);
		frame.setVisible(true);
		
		
	}

}
