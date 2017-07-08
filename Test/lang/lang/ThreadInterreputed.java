/**
 * ThreadInterreputed.java
 * Created by liurenyong at 2013-12-26
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-12-26
 */
public class ThreadInterreputed {
    
    public static void main(String[] args) {
        Thread thread = new Thread(){
            /* (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                try {
                    synchronized (Thread.currentThread()) {
                        wait(10000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("11111111111---------"+Thread.currentThread().isInterrupted());
                    interrupt();
                    System.out.println("333333333---------"+isInterrupted());
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("22222222---------"+thread.interrupted());
    }

}
