/**
 * Thread_Notify_Test.java
 * Created by liurenyong at 2013-11-25
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author liurenyong 2013-11-25
 */
public class Thread_Notify_Test {
    public static Object obj = new Object();
    public static Object object = new Object();
    boolean flag = false;

    public static void main(String[] args) {
        final Thread thread1 = new Thread() {
            public void run() {
                try {
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("1111111111");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };
        };
        final Thread thread2 = new Thread() {
            public void run() {
                int length = 0;
                for (;;) {
                    length++;
                    if (length % 100 == 0) {
                        System.out.println("..........." + length);
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // try {
                // synchronized (object) {
                // object.wait();
                // }
                // System.out.println("222222222222222");
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
            };
        };
        thread1.start();
        // thread2.start();
        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        JButton button = new JButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        dddd();
                    }
                });
                
                System.out.println("----------------------");
                synchronized (obj) {
                    obj.notifyAll();
                }
                
            }

            /** 
             * 
             */
            private void dddd() {
                synchronized (obj) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }

}
