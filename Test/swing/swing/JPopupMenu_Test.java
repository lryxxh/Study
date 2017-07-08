/**
 * JPopupMenu_Test.java
 * Created by liurenyong at 2013-9-23
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * 
 * @author liurenyong 2013-9-23
 */
public class JPopupMenu_Test extends JFrame{
    
    JPopupMenu_Test() {
        setSize(800,600);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(){
        	@Override
        	public Point getPopupLocation(MouseEvent event) {
        		System.out.println("sdsds");
        		return new Point(10,10);
        	}
        };
        panel.setBackground(Color.green);
        add(panel);
        panel.addMouseListener(new MouseAdapter() {
            /* (non-Javadoc)
             * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu menu = new JPopupMenu();
                menu.add(new JCheckBox("a"));
                menu.add(new JCheckBox("b"));
                menu.add(new JCheckBox("c"));
                menu.add(new JCheckBox("d"));
//                menu.show(JPopupMenu_Test.this, e.getX(), e.getY());
                menu.setVisible(true);
            }
        });
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new JPopupMenu_Test();
    }
}
