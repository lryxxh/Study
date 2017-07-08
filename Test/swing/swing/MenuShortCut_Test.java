/**
 * MenuShortCut_Test.java
 * Created by liurenyong at 2013-11-4
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * 
 * @author liurenyong 2013-11-4
 */
public class MenuShortCut_Test {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(":.args..........");
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JButton("ddd"));
        frame.getContentPane().add(panel);
        frame.getRootPane().registerKeyboardAction(new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(":;;;;;;;;;;;;;;;;;;;;;;;");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        frame.setSize(800,600);
        frame.setVisible(true);
    }

}
