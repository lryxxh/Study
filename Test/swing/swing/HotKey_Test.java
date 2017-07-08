package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class HotKey_Test {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("ÎÄ¼þ");
		JMenu editMenu = new JMenu("±à¼­");
		ActionTest actionTest = new ActionTest();
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JMenuItem cutMenuItem = new JMenuItem("Cut");
		JMenuItem copyMenuItem = new JMenuItem("Copy");
		KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK);
		openMenuItem.setAccelerator(keyStroke);
//		closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
//		cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));
//		copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		
//		openMenuItem.setMnemonic(KeyEvent.VK_O);
//		closeMenuItem.setMnemonic(KeyEvent.VK_F4);
//		cutMenuItem.setMnemonic(KeyEvent.VK_X);
//		copyMenuItem.setMnemonic(KeyEvent.VK_C);
		
		openMenuItem.addActionListener(actionTest);
		closeMenuItem.addActionListener(actionTest);
		cutMenuItem.addActionListener(actionTest);
		copyMenuItem.addActionListener(actionTest);
		
		fileMenu.add(openMenuItem);
		fileMenu.add(closeMenuItem);
		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("OK");
		
		button.getActionMap().put(keyStroke.getKeyChar(), new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getSource());
			}
		});
		button.getInputMap().put(KeyStroke.getKeyStroke(keyStroke.getKeyCode(), 0), keyStroke.getKeyChar());

//		button.setMnemonic(KeyEvent.VK_B);
//		button.addActionListener(actionTest);
		frame.getContentPane().add(button);
		frame.setVisible(true);
		
	}
	
	public static class ActionTest implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource());
		}
		
	}

}
