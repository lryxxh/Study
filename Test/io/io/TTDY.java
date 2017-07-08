/**
 * TTDY.java
 * Created by liurenyong at 2013-9-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * 
 * @author liurenyong 2013-9-18
 */
public class TTDY {
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//        PrintWriter writer = new PrintWriter(System.out);
//        writer.println("hello world!");
//        writer.close();
        
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        
//        for (int i = 0; i < 10; i++) {
//            try {
//                String a = reader.readLine();
//                String b = reader.readLine();
//                int aNo = Integer.parseInt(a);
//                int bNo = Integer.parseInt(b);
//                add(aNo, bNo);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(0xA);
        
  
//        File file = new File("D:/Work/eclipse/D5000_WorkSpace2/Test2/src/io/1.txt");
        File file = new File("C:/Users/liurenyong/Desktop/MANIFEST.MF");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8") );
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if(line.startsWith("Bundle-Name: ")) {
                    line = new String(line.getBytes(), "utf-8");
                    System.out.println(line + "   " +new String(line.getBytes(), "utf-8"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

    /** 
     * @param aNo
     * @param bNo
     */
    private static int add(int aNo, int bNo) {
        return aNo + bNo;
    }

}
