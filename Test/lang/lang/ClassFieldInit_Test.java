/**
 * ClassFieldInit_Test.java
 * Created by liurenyong at 2013-11-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-11-29
 */

class Singleton {
    public static int count1 = 0;
    public static int count2 = 2 ;
    public int a = 2;
    private static Singleton instance ;//= new Singleton();
    
    static{
        System.out.println("static count1 "+count1);
        System.out.println("static count2 "+count2);
    }
    
    {
        System.out.println("{} "+a);
    }
    private Singleton() {
        System.out.println("a = "+a);
        System.out.println("count1 = "+count1);
        System.out.println("count2 = "+count2);
        count1++;
        count2++;
    }
    
    public static Singleton getInstance() {
        return instance = new Singleton();
    }
}
public class ClassFieldInit_Test {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
//        System.out.println(singleton.count1);
//        System.out.println(singleton.count2);
//        System.out.println(singleton.a);
    }

}
