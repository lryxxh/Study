/**
 * Test.java
 * Created by liurenyong at 2014-9-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package proxy;

/**
 * 
 * @author liurenyong 2014-9-19
 */
public class Test implements TestInterface{
    
    public void test1() {
        System.out.println("test");
    }
    
    public void test2() {
        System.err.println("3333");
    }

}
