/**
 * ConcreateStrategyA.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.����ģʽ;

/**
 * 
 * @author liurenyong 2013-12-18
 */
public class ConcreateStrategyB extends Strategy{

    /* (non-Javadoc)
     * @see design.delegate.����ģʽ.Strategy#algorithmInterface()
     */
    @Override
    public void algorithmInterface() {
        System.out.println("����B");
    }

}
