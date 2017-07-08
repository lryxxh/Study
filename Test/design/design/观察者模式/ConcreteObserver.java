/**
 * ConcreteObserver.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.�۲���ģʽ;

/**
 * 
 * @author liurenyong 2013-12-20
 */
public class ConcreteObserver implements Observer {

    /*
     * (non-Javadoc)
     * 
     * @see design.�۲���ģʽ.Observer#update(design.�۲���ģʽ.Subject)
     */
    @Override
    public void update(Subject obj) {
        System.out.println("���Ա��" + obj);
    }

    /* (non-Javadoc)
     * @see design.�۲���ģʽ.Observer#update(java.lang.String)
     */
    @Override
    public void update(String content) {
        
    }

}
