/**
 * ConcreteObserver.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.观察者模式;

/**
 * 
 * @author liurenyong 2013-12-20
 */
public class ConcreteObserver implements Observer {

    /*
     * (non-Javadoc)
     * 
     * @see design.观察者模式.Observer#update(design.观察者模式.Subject)
     */
    @Override
    public void update(Subject obj) {
        System.out.println("属性变更" + obj);
    }

    /* (non-Javadoc)
     * @see design.观察者模式.Observer#update(java.lang.String)
     */
    @Override
    public void update(String content) {
        
    }

}
