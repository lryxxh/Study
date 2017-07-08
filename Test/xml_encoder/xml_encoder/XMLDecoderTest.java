package xml_encoder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class XMLDecoderTest {
	public static void main(String[] args) {
		try {
			JMenu menu = new JMenu("File");
			JMenuItem openItem = new JMenuItem("Open");
			JMenuItem savetem = new JMenuItem("Save");
			JMenuItem closeItem = new JMenuItem("Close");
			menu.add(openItem);
			menu.add(new JSeparator());
			menu.add(savetem);
			menu.add(new JSeparator());
			menu.add(closeItem);
			menu.setIcon(new ImageIcon("file.gif"));
			openItem.setAction(new TestActionListenering("Open"));
//			openItem.setAction(new TestActionListenering());
//			openItem.setIcon(new ImageIcon("open.gif"));
			savetem.setIcon(new ImageIcon("save.gif"));
			closeItem.setIcon(new ImageIcon("close.gif"));
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new File("C:/Users/LRY/Desktop/2.xml")));
			encoder.writeObject(menu);
			encoder.close();
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(new File("C:/Users/LRY/Desktop/2.xml")));
			JMenu menu2 = (JMenu) decoder.readObject();
			JFrame frame = new JFrame();
			JMenuBar bar = new JMenuBar();
			bar.add(menu2);
			frame.setJMenuBar(bar);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300,200);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

