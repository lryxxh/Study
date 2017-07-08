/**
 * GroupSplitPanel_Test.java
 * Created by liurenyong at 2013-8-23
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author liurenyong 2013-8-23
 */
public class GroupSplitPanel_Test extends JFrame{
    
    GroupSplitPanel_Test() {
        setSize(400, 500);
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * 
     * @author liurenyong 2013-8-23
     */
    private void init() {
        JPanel panel = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Label11"));
        panel1.add(new JLabel("Label12"));
        
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Label21"));
        panel2.add(new JLabel("Label22"));
        
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Label31"));
        panel3.add(new JLabel("Label32"));
        
        JPanel panel4 = new JPanel();
        panel4.add(new JLabel("Label41"));
        panel4.add(new JLabel("Label42"));
        
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        new GroupSplitPanel_Test();
    }

}
