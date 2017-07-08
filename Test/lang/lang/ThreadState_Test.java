/**
 * ThreadState_Test.java
 * Created by liurenyong at 2013-9-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2013-9-24
 */
public class ThreadState_Test {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JButton button = new JButton();
        
        final Thread thread = new Thread() {
            public void run() {
                for(;;) {
                    try {
                        synchronized (Thread.currentThread()) {
                            wait(10000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("........................");
                }
            };
        };
        thread.start();
        
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    synchronized(thread) {
                        thread.notify();
                        System.out.println("00000000000000000");
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }

}
