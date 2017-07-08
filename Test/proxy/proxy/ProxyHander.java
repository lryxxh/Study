/**
 * ProxyHander.java
 * Created by liurenyong at 2014-9-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 
 * @author liurenyong 2014-9-19
 */
public class ProxyHander implements InvocationHandler{
    Test test;
    /**
     * 
     * @param test
     * @author liurenyong 2014-9-19
     */
    public ProxyHander(Test test) {
        this.test = test;
    }

    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin " + method.getName());
        method.invoke(test, args);
        System.out.println("end " + method.getName());
        return null;
    }

}
