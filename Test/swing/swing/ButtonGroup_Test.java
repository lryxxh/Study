/**
 * ButtonGroup_Test.java
 * Created by liurenyong at 2013-11-21
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * 
 * @author liurenyong 2013-11-21
 */
public class ButtonGroup_Test {

    /** 
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500,300);
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0, 7);
        layout.setHgap(0);
        layout.setVgap(0);
        panel.setLayout(layout);
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < 42; i++) {
            final JToggleButton button = new JToggleButton((i + 1) + "");
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBackground(Color.WHITE);
            button.setBorderPainted(false);
            group.add(button);
            panel.add(button);
        }
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } 

}
