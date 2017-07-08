/**
 * JPopupMenuTest.java
 * Created by HHD at 2013-5-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 
 * @author HHD 2013-5-24
 */
public class JPopupMenuTest {

    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(new AeroLookAndFeel());
//        } catch (UnsupportedLookAndFeelException e1) {
//            e1.printStackTrace();
//        }
        final JFrame frame = new JFrame();
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu popupMenu = new JPopupMenu("ss");
                popupMenu.add(new JMenuItem("sss1"));
                popupMenu.add(new JMenuItem("sss2"));
                popupMenu.add(new JMenuItem("sss3"));
                popupMenu.add(new JMenuItem("sss4"));
                popupMenu.show(null, e.getX(), e.getY());
                popupMenu.setBorder(null);
//                popupMenu.setBorder(new MetalBorders.PopupMenuBorder());
//                popupMenu.setBorder(BorderFactory.createLineBorder(Color.RED));
                System.out.println(popupMenu.getBorder());
            }
        });
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
