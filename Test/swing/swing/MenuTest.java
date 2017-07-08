package swing;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MenuTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("wenjian");
		JMenuItem open = new JMenuItem("´ò¿ª");
		open.setIcon(new ImageIcon("sys_manage.png"));
		JMenuItem close = new JMenuItem("¹Ø±Õ");
		file.add(open);
		open.setBackground(Color.RED);
		open.updateUI();
		file.add(close);
		menuBar.add(file);
		frame.setJMenuBar(menuBar);
		System.out.println(UIManager.getLookAndFeel());
		
		frame.setVisible(true);
		frame.setSize(400,300);
		open.setMargin(new Insets(0, 0, 0, 0));
		System.out.println(file.getLayout());
		LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		System.out.println(infos.length);
	}
}
