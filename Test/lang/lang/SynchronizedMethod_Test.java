/**
 * SynchronizedMethod_Test.java
 * Created by liurenyong at 2013-10-14
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author liurenyong 2013-10-14
 */
public class SynchronizedMethod_Test {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String line = reader.readLine();
                    while (line != null) {
                        if ("stop".equals(line)) {
                            SynchronizedMethod.setFlag(!SynchronizedMethod.isFlag());
                            synchronized (SynchronizedMethod.class) {
                                SynchronizedMethod.class.notifyAll();
                            }
                        }
                        line = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread("string thread") {
            public void run() {
                String stringValue = SynchronizedMethod.getString();
                System.out.println(stringValue);
            }
        }.start();
       
        new Thread("value thread") {
            public void run() {
                String returnValue = SynchronizedMethod.getValue();
                System.out.println(returnValue);
            }
        }.start();
       
    }
}

class SynchronizedMethod {
    static boolean flag = true;
    
    /**
     * @param flag the flag to set
     */
    public static void setFlag(boolean flag) {
        SynchronizedMethod.flag = flag;
    }
    
    /** 
     * @return
     */
    public static boolean isFlag() {
        return  SynchronizedMethod.flag;
    }

    public static synchronized String getValue() {
        String string = "";
        Thread thread = Thread.currentThread();
        if(SynchronizedMethod.flag) {
            try {
                synchronized (SynchronizedMethod.class) {
                    SynchronizedMethod.class.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        string = "Hello World!";
        return string;
    }
    
    public static synchronized String getString() {
        String string = "";
        Thread thread = Thread.currentThread();
        if(SynchronizedMethod.flag) {
            try {
                synchronized (SynchronizedMethod.class) {
                    SynchronizedMethod.class.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        string = "RETURN String Value";
        return string;
    }
    
}
