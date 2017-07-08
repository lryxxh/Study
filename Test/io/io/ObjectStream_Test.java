/**
 * ObjectStream_Test.java
 * Created by liurenyong at 2013-8-6
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author liurenyong 2013-8-6
 */
public class ObjectStream_Test implements Serializable{
    
    /**  */
    private static final long serialVersionUID = -2058343986420259447L;
    private int a = 100;
    private int b = 200;
    public static int c = 1000;
    
    public ObjectStream_Test() {
        System.out.println("....... init " );
    }
    
    public void test() {
        System.out.println("=====test=====");
    }
    public static void main(String[] args) {
        ObjectStream_Test test = new ObjectStream_Test();
        File file = new File("C:/Users/HHD/Desktop/1.os");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(test);
            oos.flush();
            oos.close();
            System.err.println("----------------------------------");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ObjectStream_Test obj = (ObjectStream_Test) ois.readObject();
            System.out.println(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the a
     */
    public int getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(int b) {
        this.b = b;
    }

}
