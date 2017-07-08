/**
 * Callable_Thread_Test.java
 * Created by liurenyong at 2013-9-11
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2013-9-11
 */
public class Callable_Thread_Test {

    private static ExecutorService service = Executors.newFixedThreadPool(20);
    private static List<Future> list = new ArrayList();
    static Callable callable = null;
    static Future future = null;
    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            CallableThread thread = new CallableThread(i);
//            Future f = service.submit(thread);
////            list.add(f);
//        }
////        for(int i = 0; i < list.size(); i ++) {
////            try {
////                System.out.println("index = " + i +"   return = " + list.get(i).get());
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            } catch (ExecutionException e) {
////                e.printStackTrace();
////            }
////        }
        
        JFrame frame = new JFrame();
        JButton startbutton = new JButton("start");
        final JButton endButton = new JButton("end");
        
        startbutton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread_interreput_Test.flag = false;
                callable = new CallableThread();
                future = service.submit(callable);
            }
        });
        
        endButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                future.cancel(true);
            }
        });
        frame.getContentPane().add(startbutton, BorderLayout.NORTH);
        frame.getContentPane().add(endButton, BorderLayout.SOUTH);
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

}

class CallableThread implements Callable {
    CallableThread() {
    }
    @Override
    public Object call() throws Exception {

        long a = 0;
        for(;;) {
            a++;
            if(a % 100000 == 0) 
            {
                System.out.println(Thread.currentThread().getName() + "  " + a);
                Thread.sleep(200);
            }
        }
    }
}