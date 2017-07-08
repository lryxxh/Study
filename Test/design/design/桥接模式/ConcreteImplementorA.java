/**
 * ConcreteImplementor.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.桥接模式;

/**
 * 
 * @author liurenyong 2013-12-24
 */
public class ConcreteImplementorA implements Implementor{

    /* (non-Javadoc)
     * @see design.桥接模式.Implementor#operationImpl()
     */
    @Override
    public void operationImpl() {
        System.out.println("具体实现。。");
    }

}
