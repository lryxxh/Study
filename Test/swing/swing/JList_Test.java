/**
 * JList_Test.java
 * Created by HHD at 2013-6-7
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 * 
 * @author HHD 2013-6-7
 */
public class JList_Test {

    public static void main(String[] args) {
    	
    	java.lang.reflect.Field fileField;
		try {
			fileField = MouseWheelEvent.class.getSuperclass().getDeclaredField("x");
			System.out.println(fileField);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
        JFrame frame = new JFrame();
//        AWTUtilities.setWindowOpacity(frame, 0.8f);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0,0,0,0));
        frame.setBackground(new Color(0,0,0,0));
        JList list = new JList(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" });
//        list.setCellRenderer(new DefaultListCellRenderer(){
//            @Override
//            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                int width = component.getHeight();
//                component.setPreferredSize(new Dimension(width, 30));
//                return component;
//            }
//        });
//        list.setCellRenderer(new ListCellRenderer() {
//			
//			@Override
//			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//				JTextField textField = new JTextField();
//				textField.setText(value.toString());
//				textField.setFocusable(true);
//				textField.setEditable(true);
//				return textField;
//			}
//		});
        list.setCellRenderer(new TextFieldCellRender());
        list.setSelectedIndices(new int[] { 0, 4, 6, 7 });
        list.setLayout(new GridLayout(3, 4));
        frame.getContentPane().add(list);
        frame.setSize(500, 400);
        frame.setVisible(true);
        System.out.println(list.getLayout());
        System.err.println(SwingUtilities.getAncestorOfClass(Container.class, list));
    }
    
    static class TextFieldCellRender extends JTextField implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			setText(value.toString());
			return this;
		}
    	
    }

}
