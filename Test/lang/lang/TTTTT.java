/**
 * TTTTT.java
 * Created by liurenyong at 2013-12-31
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-12-31
 */
public class TTTTT {
    
    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE-10000));
//        byte[] array = ByteBuffer.allocate(4).putInt(1000000000).array();
//        for(byte bb : array) {
//            System.out.println(Integer.toBinaryString(bb));
//        }
        
    }

}
class A {
    protected A a = this;
    public void test() {
        System.out.println("test");
    }
}
class B extends A {
    public void test() {
        System.out.println("test22");
    }
}

class C extends B {
    public void test() {
    	super.test();
    }
    
    public static void main(String[] args) {
        new C().test();
    }
}