/**
 * ClassInit_Test.java
 * Created by liurenyong at 2013-11-26
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-11-26
 */
public class ClassInit_Test extends SuperClass{

    /**
     * 
     * @author liurenyong 2013-11-26
     */
    public ClassInit_Test() {
    }
    
    /* (non-Javadoc)
     * @see lang.SuperClass#test()
     */
    @Override
    public void test() {
    	System.out.println(";;;;;;;;;;");
    }
    public static void main(String[] args) {
        System.out.println(".......");
        SuperClass test = new ClassInit_Test();
        ((SuperClass)test).test();
//        new ClassInit_Test();
    }
}

class SuperClass {
    int a = 1/10;
    static byte[] bytes = null;
//    static int b = bytes.length;
    static int b = 5;
    
    /**
	 * 
	 */
	public SuperClass() {
		test();
	}
    public void test() {
        System.out.println("-------------");
    }
}


