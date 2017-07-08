/**
 * Exception_Test.java
 * Created by HHD at 2013-6-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.lang.Thread.UncaughtExceptionHandler;

import log.Log;

/**
 * 
 * @author ¡ı» ”¬ 2013-6-27
 */
@SuppressWarnings("unused")
public class Exception_Test {

    /**
     * 
     * @param args
     * @author HHD 2013-6-27
     */
    public static void main(String[] args) {
//    	Thread.currentThread().set
        CustomThread thread = new CustomThread();
        thread.setUncaughtExceptionHandler(
        		new UncaughtExceptionHandler() {
        			
        			@Override
        			public void uncaughtException(Thread t, Throwable e) {
//						e.printStackTrace();
        				StackTraceElement[] stackTraceElements = e.getStackTrace();
        				Log.error("Caused by: " + e.toString());
        				System.out.println(e.toString());
        				for(StackTraceElement element : stackTraceElements) {
        					Log.error(element.toString());
        					System.out.println("\tat" +element.toString());
        				}
        				
        				Throwable cause = e.getCause();
        				while(null != cause) {
        					StackTraceElement[] causeStackTraceElements = cause.getStackTrace();
        					for(StackTraceElement element : causeStackTraceElements) {
        						Log.error(element.toString());
        						System.err.println(element.toString());
        					}
        					cause = cause.getCause();
        				}
        				
//						Log.error(cause.toString());
//						Log.error("Caused by: " + e.toString());
        			}
//        			
        		});
        thread.start();
    }
    
    @SuppressWarnings("null")
    private static void test() {
        int a = 100;
        int b[] = null;
        try {
           System.out.println(b[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(".........");
        int c = 20;
        System.out.println(a/c);
    }

}

class CustomThread extends Thread {
    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @SuppressWarnings("null")
    @Override
    public void run() {
        int[] a = null;
        System.out.println(a[0]);
    }
}
