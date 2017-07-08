/**
 * JFileChooser_Test.java
 * Created by HHD at 2013-6-21
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * 
 * @author HHD 2013-6-21
 */
public class JFileChooser_Test {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Icon icon = null;
		try {
			icon =  FileSystemView.getFileSystemView().getSystemIcon(new File("E:/Program Files/EditPlus 3/editplus.exe"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		icon = new JFileChooser().getFileSystemView().getSystemIcon(new File("E:/Program Files/EditPlus 3/editplus.exe"));
		
		
		
		final JFrame frame = new JFrame();
		
		frame.setSize(800, 600);
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(";;;;;;;;;;;;;;");
			}
		});
		fileChooser.setAccessory(new JButton(icon));
		fileChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		final JButton button = new JButton("dsdsds");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setIcon(icon);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int code = fileChooser.showSaveDialog(frame);
				System.out.println("====" + fileChooser.getUI());
				if (code == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.err.println(fileChooser.getFileSystemView());
					button.setIcon(fileChooser.getFileSystemView()
							.getSystemIcon(file));
					System.out.println(file);
				}
			}
		});
		JPanel panel = new JPanel();
		UIManager.getDefaults().put("Label.font",
				new Font("Dialog", Font.BOLD, 40));
		panel.add(new JLabel("测试"));
		panel.add(new JLabel("电费"));

		System.out.println(UIManager.getDefaults().entrySet());
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		try {
			System.out.println(UIManager.getUI(fileChooser));
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/Users/LRY/Desktop/1.obj"));
			oos.writeObject(frame);
			oos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
