package lang;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class StringSplit_RunTime_Test {

	public static void main(String[] args) {
	    try {
//            System.setSecurityManager(new SecurityManager());
//            SecurityManager manager = System.getSecurityManager();
//            System.out.println(manager);
//            new FilePermission("C:/Program Files/Internet Explorer", "execute");
//            System.getSecurityManager().checkExec("iexplore");
            Runtime.getRuntime().exec("iexplore.exe http://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
//		System.out.println(getScreenSize());
		
//		String[] fontNames = getFontNames();
//		System.err.println(fontNames.length);
//		for (String fontName : fontNames) {
//			System.out.println(fontName);
//		}
		
//		showColorChooser();
		
//		test();
//		SystemLog.printMemory("1");
//		long time = System.currentTimeMillis();
//		indexTest();
//		splitTest();
//		System.out.println("===="+(System.currentTimeMillis() - time));
//		SystemLog.printMemory("2");
//		try {
//			Thread.sleep(100000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void splitTest() {
		List<String> string = stringTest();
		List<List<String>> data = new ArrayList<List<String>>(); 
		
		for (String temp : string) {
			String[] tempArray = temp.split(",");
			List<String> ttdata=  Arrays.asList(tempArray);
			data.add(ttdata);
		}
		System.out.println(data.size());
	}
	
	public static void indexTest() {
		List<String> string = stringTest();
		List<List<String>> data = new ArrayList<List<String>>(); 
	
		for (String temp : string) {
			int index = string.indexOf(",");
			List<String> list = new ArrayList<String>(); 
			String otherString = temp;
			int offset = 0;
			while (index != -1) {
				System.out.println(offset +" " + index);
				String tempString = otherString.substring(offset + 1, index - 1);
				otherString = otherString.substring(index + 1);
				list.add(tempString);
				offset = 0;
				index = otherString.indexOf(",");
			}
			data.add(list);
		}
		System.out.println(data.size());
	}
	
	public static List<String> stringTest() {
		List<String> list = new ArrayList<String>(); 
		for(int j = 0; j < 10000; j++) {
			StringBuffer str = new StringBuffer();
			for(int i = 0; i< 100;i++) {
				str.append("1000000000000000000,");
			}
			list.add(str.toString());
		}
		return list;
	}
	
	
	
	public static void test() {
		Vector<String> temp = new Vector<String>();
		temp.add("1234");
		temp.add("12345");
		Vector<String> temp2 = new Vector<String>();
		temp2.add("1234");
		temp2.add("12345");
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		data.add(temp);
		data.add(temp2);

		Vector<?> clone = (Vector<?>) data.clone();
		data.remove(1);
		System.out.println(data.hashCode() + " length " + data.size());
		System.out.println(clone.hashCode() + " length " + clone.size());
	}
	
	public static Dimension getScreenSize() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		GraphicsConfiguration defaultGraphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(defaultGraphicsConfiguration);
		size.setSize(size.width - insets.left - insets.right, size.height - insets.bottom - insets.top);
		return size;
	}
	
	public static String[] getFontNames() {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		return graphicsEnvironment.getAvailableFontFamilyNames();
	}
	
	public static void showColorChooser() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
            @Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser chooser = new JColorChooser();
				int panel = chooser.getChooserPanels().length;
				chooser.showDialog(null, "", null);
				System.out.println(panel);
				
			}
		});
		frame.add(button);
		frame.setSize(400,200);
		frame.setVisible(true);
		
	}
}
