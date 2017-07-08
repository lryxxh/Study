/**
 * Timer_Test.java
 * Created by liurenyong at 2013-10-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import javax.swing.JFrame;


/**
 * 
 * @author liurenyong 2013-10-24
 */
public class Timer_Test {
    static int i = 0;
    
    public static void main(String[] args) {
//        Timer timer = new Timer(500, new ActionListener() {
//            
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(i);
//                test();
//            }
//        });
//        timer.start();
        new Thread() {
            /* (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                while (true) {
                    System.out.println(i);
                    test();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        JFrame frame = new JFrame();
        frame.setSize(600,400);
        frame.setVisible(true);
    }

    public static void test(){ 
        new Thread() {
            public void run() {
                test1();
            }
        }.start();
    }
    
    public static void test1() {
       
        if (i != 0 && i % 10 == 0) {
            try{
                int a = i / 0;
                System.out.println(a);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.err.println("....................");
        }
        i++;
    }
}
