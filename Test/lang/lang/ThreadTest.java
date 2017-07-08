/**
 * s.java
 * Created by liurenyong at 2013-8-21
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

/**
 * Thread sleep��wait����
 * @author DreamSea 
 * 2012-1-15
 */
public class ThreadTest extends Thread {
    int number = 10;
    
    Thread tempThread = null;
    
    /**
     * @param tempThread the tempThread to set
     */
    public void setTempThread(Thread tempThread) {
        this.tempThread = tempThread;
    }
    
    /**
     * @return the tempThread
     */
    public Thread getTempThread() {
        return tempThread;
    }
    
    /**
     * 
     * @author liurenyong 2013-8-21
     */
    public ThreadTest() {
    }
//    long time = System.currentTimeMillis();
//    public void firstMethod() throws Exception {
//        synchronized (this) {
//            number += 100;
//            tempThread.start();
//            tempThread.join();
//            System.out.println(number);
//        }
//    }
//
//    public void secondMethod() throws Exception {
//        synchronized (this) {
//            /**
//             * (��Ϣ2S,�����߳�)
//             * ����֤��ǰ�̶߳���Ļ�����ռ��ʱ,
//             * �Ƿ񱻿��Է�������ͬ�������
//             */
////            Thread.sleep(2000);
//            this.join();
//            number *= 200;
//        }
//    }

    @Override
    public void run() {
//        try {
//            firstMethod();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        try {
            tempThread.start();
            tempThread.join();
            System.out.println(".....................");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadTest threadTest = new ThreadTest();
        newThread tt = new newThread();
        threadTest.setTempThread(tt);
        threadTest.start();
//        threadTest.secondMethod();
    }
    
    static class newThread extends Thread{
        newThread() {
        }
        public void run() {
            final Thread tempThread = Thread.currentThread();
            final Thread ddThread = this;
            System.out.println(tempThread.hashCode() + "-----" + ddThread.hashCode());
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (tempThread) {
                        System.out.println("notify");
                        tempThread.notify();
                        tempThread.notify();
                    }
                };
            }.start();
            try {
                synchronized (tempThread) {
                    tempThread.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("....notifyall");
        };
    }
}