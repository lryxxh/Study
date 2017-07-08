/**
 * JTabbedPane_Test.java
 * Created by liurenyong at 2013-8-23
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * 
 * @author liurenyong 2013-8-23
 */
public class JTabbedPane_Test extends JFrame{

    /**
     * 
     * @author liurenyong 2013-8-23
     */
    public JTabbedPane_Test() {
        setSize(800,600);
        JTabbedPane tabbedPane = new JTabbedPane();
        for(int i = 0; i < 20; i++) {
            JLabel label = new JLabel("sssss");
            tabbedPane.addTab("tab" + i, label);
        }
        getContentPane().add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new JTabbedPane_Test();
    }
}
