/**
 * Thread_interreput_Test.java
 * Created by liurenyong at 2013-10-10
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2013-10-10
 */
public class Thread_interreput_Test {
    static Thread thread = null;
    static  ExecutorService service = Executors.newFixedThreadPool(2);
    static boolean flag = false;
    /**
     * @param flag the flag to set
     */
    public synchronized static void setFlag(boolean flag) {
        Thread_interreput_Test.flag = flag;
    }
    
    public synchronized static boolean getFlag() {
        return Thread_interreput_Test.flag;
    }
    public static void main(String[] args) {
//        try {
//            System.setOut(new PrintStream("C:/Users/liurenyong/Desktop/1.txt"));
//        } catch (FileNotFoundException e2) {
//            e2.printStackTrace();
//        }
        JFrame frame = new JFrame();
        JButton startbutton = new JButton("start");
        JButton endButton = new JButton("end");
        
        startbutton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread_interreput_Test.flag = false;
                thread = new Thread() {
                    @Override
                    public void run() {
                        long a = 0;
                        for(;;) {
                            a++;
                            if(Thread_interreput_Test.flag) {
//                                Thread.interrupted();
                                Thread.currentThread().stop();
                                return;
                            }
                            System.out.println(Thread.currentThread().getName() + "  " + a);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                service.execute(thread);
            }
        });
        
        endButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println(thread);
                try {
                    setFlag(!getFlag());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(startbutton, BorderLayout.NORTH);
        frame.getContentPane().add(endButton, BorderLayout.SOUTH);
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                service.shutdown();
            }
        });
    }

}
