package lang;
/**
 * SwingWorker_Test.java
 * Created by liurenyong at 2013-9-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

/**
 * 
 * @author liurenyong 2013-9-27
 */
public class SwingWorker_Test extends JFrame{
    final static ExecutorService service = Executors.newFixedThreadPool(10);
//    Thread thread = null;
    Task task = null;
    public SwingWorker_Test() {
        final SwingWorker worker =  new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                System.out.println("lllllllllllllll");
                Thread threads = new Thread() {
                    public void run() {
                        for (int i = 0; i< 1000000000;i++) {
                            if(i % 100 == 0) {
                                System.out.println(i);
                            }
                        } 
                    };
                };
                threads.setDaemon(true);
                threads.start();
                return threads;
            }
            
        };
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("SwingWorker");
        
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
//                worker.execute();
                task = new Task();
                service.execute(task);
            }
        });
        
        JButton calcelButton = new JButton("cancel");
        calcelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
//                worker.cancel(true);
                task.stop();
            }
        });
        getContentPane().add(button);
        getContentPane().add(calcelButton, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new SwingWorker_Test();
    }
}
class Task implements Runnable {
    boolean stop = false;
    Thread thread = null;
    public void stop() {
        if (null != thread) {
//            try {
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            

        }
    }
    int i = 0;
    @Override
    public void run() {
        System.out.println("....................");
//        Thread threads = new Thread() {
//            public void run() {
                thread = Thread.currentThread();
                long a = 0;
                for (; ;) {
                    if(a % 1000000 == 0) {
                        System.out.println(a);
                    }
                    a = (long) (Math.random() * 1000000000);
                } 
//            };
//        };
//        threads.start();
      
    }
}