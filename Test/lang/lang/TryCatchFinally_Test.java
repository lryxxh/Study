/**
 * TryCatchFinally_Test.java
 * Created by liurenyong at 2013-10-9
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

/**
 * 
 * @author liurenyong 2013-10-9
 */
public class TryCatchFinally_Test {
    
    public static void main(String[] args) {
        int a = test();
        System.out.println(a);
    }
    
    public static int test() {
        int x;
        try{
            x = 1;
            int b = x/0 ;
            return x;
        } catch (NullPointerException e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

}
