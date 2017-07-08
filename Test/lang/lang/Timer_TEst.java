/**
 * Timer_TEst.java
 * Created by liurenyong at 2013-11-15
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * 
 * @author liurenyong 2013-11-15
 */
public class Timer_TEst {
    
    static Timer timer = null;
    public static void main(String[] args) {
        timer = new Timer (1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
//                while(true) {
                System.out.println(Thread.currentThread());
                    for (int i = 0; i< 10;i++) {
                        System.out.println(i);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
//                }
            }
        });
        timer.setRepeats(false);
        timer.start();
        JFrame frame = new JFrame();
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        System.err.println(".............stop" );
                        timer.stop();                        
                    }
                });
            }
        });
        frame.getContentPane().add(button);
        frame.setSize(400,200);
        frame.setVisible(true);
//        new Thread(){
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                timer.start();
//            };
//        }.start();
    }

}
