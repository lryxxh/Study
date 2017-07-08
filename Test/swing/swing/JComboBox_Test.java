/**
 * JComboBox_Test.java
 * Created by liurenyong at 2013-10-21
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2013-10-21
 */
public class JComboBox_Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComboBox box = new JComboBox();
        for ( int i = 0 ;i < 50; i ++) {
            box.insertItemAt(i +"1", i);
        }
        frame.getContentPane().add(box);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

}
