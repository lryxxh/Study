/**
 * SwingWorker_Test.java
 * Created by liurenyong at 2013-9-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

/**
 * 
 * @author liurenyong 2013-9-27
 */
public class SwingWorker_Test extends JFrame{

    public SwingWorker_Test() {
        new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                return null;
            }
            
        };
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("SwingWorker");
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println("................");
            }
        });
        getContentPane().add(button);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new SwingWorker_Test();
    }
}
