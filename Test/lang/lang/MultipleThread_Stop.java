/**
 * MultipleThread_Stop.java
 * Created by liurenyong at 2013-9-30
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author liurenyong 2013-9-30
 */
public class MultipleThread_Stop {
    
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Runnable runnable = new Runnable() {
            
            @Override
            public void run() {
                for (;;) {
                    double a = Math.random();
                    try {
                        System.out.println(a);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        
        service.execute(runnable);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = reader.readLine();
            while(!line.equals("stop")) {
                System.out.println(line);
                line = reader.readLine();
            }
            System.err.println("stop service");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
