/**
 * CLient.java
 * Created by liurenyong at 2014-9-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package proxy;

import java.lang.reflect.Proxy;

import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2014-9-19
 */
public class Client {
    
    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        
//        frame.setSize(400,300);
//        frame.setVisible(true);
        Test test = new Test();
        ProxyHander hander = new ProxyHander(test);
        TestInterface testproxy = (TestInterface) Proxy.newProxyInstance(test.getClass().getClassLoader(), test.getClass().getInterfaces(), hander);
        testproxy.test1();
        testproxy.test2();
    }

}
